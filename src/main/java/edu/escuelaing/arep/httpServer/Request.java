package edu.escuelaing.arep.httpServer;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String method;
    private String path;
    private Map<String ,String> headers;
    private String body;


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }



    public Request() {
        this.headers = new HashMap<>();
        this.method="";
        this.body="";
        this.path="";
    }



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public void addHeader(String headerName, String header) {
        this.headers.put(headerName,header);
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", headers=" + headers +
                '}';
    }
}
