package edu.escuelaing.arep.httpServer;

/**
 * Clase que representa la respuestas del servidor
 */
public class Response {


    private String type="text/html";

    private String body;

    public Response() {
    }

    /**
     * retorna el tipo de contenido de la respuesta, por defecto es text/html
     * @return el tipo de contenido de la respuesta
     */
    public String getType() {
        return type;
    }

    /**
     * establece el tipo de contenido de la respuesta
     * @param type el nuevo tipo
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * retorna el contenido de la respueta
     * @return el contenido de la respuesta
     */
    public String getBody() {
        return body;
    }

    /**
     * establece el contenido de la respuesta
     * @param body el contenido de la respuesta
     */
    public void setBody(String body) {
        this.body = body;
    }
}
