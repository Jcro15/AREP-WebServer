package edu.escuelaing.arep;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        HttpServer server=new HttpServer();
        server.staticFilesLocation("/static");
        server.start();
        HttpMethodPublisher.get("/a",((request, response) -> "HOLA"));
        HttpMethodPublisher.post("/calcular",((request, response) -> {
            response.setType("application/json");
            return new Gson().toJson("{\"media\": \""+"44"+"\", \"std\": \""+"99"+"\"}") ;

        }));
    }
}
