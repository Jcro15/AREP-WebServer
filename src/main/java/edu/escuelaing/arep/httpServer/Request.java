package edu.escuelaing.arep.httpServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa las peticiones de los clientes
 */
public class Request {
    private String method;
    private String path;
    private Map<String ,String> headers;
    private String body;


    public Request() {
        this.headers = new HashMap<>();
        this.method="";
        this.body="";
        this.path="";
    }

    /**
     * retorna los datos del cuerpo de la peticion
     * @return los datos del cuerpo de la peticion
     */
    public String getBody() {
        return body;
    }

    /**
     * establece el cual es el cuerpo de la peticion
     * @param body el nuevo cuerpo
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * retorna el método utilizado en la petición
     * @return un string con el método utilizado en la petición
     */
    public String getMethod() {
        return method;
    }

    /**
     * establece el metodo que utiliza la peticion
     * @param method el metodo utilizado
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * retorna la ruta a la que se realiza la peticion
     * @return la ruta a la que se realiza la peticion
     */
    public String getPath() {
        return path;
    }

    /**
     * establece la ruta de la peticion
     * @param path la ruta de la peticion
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * retorna un encabezado especifico de la peticion
     * @param header  el nombre del encabezado
     * @return el contenido del encabezado
     */
    public String getHeader(String header) {
        return headers.get(header);
    }

    /**
     * añade un nuevo encabezado a la peticion
     * @param headerName nombre del encabezado
     * @param header contenido del encabezado
     */
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
