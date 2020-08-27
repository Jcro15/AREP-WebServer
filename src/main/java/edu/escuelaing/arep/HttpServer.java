package edu.escuelaing.arep;


import java.net.*;
import java.io.*;

public class HttpServer extends Thread {


    private boolean running = false;
    private static String rootPath = "src/main/resources";
    private OutputStream outputStream;


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }

    public static void staticFilesLocation(String path) {
        rootPath += path;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(getPort());
            } catch (IOException e) {
                System.err.println("Could not listen on port:." + getPort());
                System.exit(1);
            }

            boolean running = true;
            while (running) {
                try {
                    Socket clientSocket = null;
                    try {
                        System.out.println("Listo para recibir ...");
                        clientSocket = serverSocket.accept();
                    } catch (IOException e) {
                        System.err.println("Accept failed.");
                        System.exit(1);
                    }
                    processRequest(clientSocket);
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private void processRequest(Socket clientSocket) throws IOException {
        outputStream=clientSocket.getOutputStream();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        Request request = new Request();
        boolean requestLineReady = false;
        while ((inputLine=in.readLine())!=null) {
            System.out.println("Recibí: " + inputLine);

            if (!requestLineReady) {
                String[] entry = inputLine.split(" ");
                request.setMethod(entry[0]);
                request.setPath(entry[1]);
                requestLineReady = true;
            } else if(inputLine.length()>0){
                String[] entry = inputLine.split(":");
                request.addHeader(entry[0], entry[1]);
            }
            if (!in.ready()||inputLine.length()==0) {
                break;
            }
        }
        if(request.getMethod().equals("POST")){
            StringBuilder payload = new StringBuilder();
            while(in.ready()){
                payload.append((char) in.read());
            }
            request.setBody(payload.toString());
        }
        if(!request.getMethod().equals("")){
            handleRequest(request, clientSocket);
        }
        in.close();
        outputStream.close();
    }

    private void getResource(String path, String header) throws IOException {
        PrintStream out=new PrintStream(outputStream);
        File file = new File(rootPath+path);
        if(file.exists()){
            InputStream inputStream= new FileInputStream(rootPath+path);
            out.print(header);
            byte[] arrbyte= new byte[4096];
            int n;
            while ((n=inputStream.read(arrbyte))>0){
                out.write(arrbyte,0,n);
            }
        }
        else errorResponse();
        out.close();
    }





    private void handleRequest(Request request, Socket clientSocket) throws IOException {
        Response endpointResponse=HttpMethodPublisher.execute(request);
        if (request.getPath().equals("/")) {
            request.setPath("/index.html");
        }
        if(endpointResponse!=null){
            String header=generateHeader(false,endpointResponse.getType());
            PrintWriter out = new PrintWriter(outputStream, true);
            out.print(header);
            out.print(endpointResponse.getBody());
            out.close();
        }
        else if (request.getMethod().equals("GET") && request.getPath().contains(".")){
            String extension = request.getPath().split("\\.")[1];
            String header;
            if (extension.equals("png")||extension.equals("jpg")) {
                header = generateHeader(true, extension);
            } else {
                if(extension.equals("js")){
                    extension="javascript";
                }
                header = generateHeader(false, "text/"+extension);
            }
            getResource(request.getPath(), header);
        }
        else errorResponse();
    }

    private void errorResponse(){
        PrintWriter out = new PrintWriter(outputStream, true);
        out.print("HTTP/1.1 404 Not Found \r\n" + "Content-type: text/html"+"\r\n\r\n");
        out.print("<h1> 404 File not found </h1>");
        out.close();
    }

    private String generateHeader(boolean isImage, String extension) {
        String header = null;
        if (isImage) {
            header = "HTTP/1.1 200 OK \r\nContent-Type: image/" + extension + "\r\n\r\n";
        } else {
            header = "HTTP/1.1 200 OK \r\nContent-Type: "+ extension + "\r\n\r\n";
        }
        return header;
    }

}