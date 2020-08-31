package edu.escuelaing.arep.httpServer;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Clase encargada de publicar los métodos HTTP sobre el servidor, permite publicar petodos get y post
 */
public class HttpMethodPublisher {


    private static Map<String,BiFunction<Request,Response,String>> endpoints=new HashMap<>();

    /**
     * Publica una función GET sobre el servidor
     * @param path la ruta en la que se va a publicar el endpoint
     * @param function  la función que se ejecutará cuando se realice una petición GET a la ruta especificada
     */
    public static void get(String path, BiFunction<Request,Response,String> function){
        endpoints.put("GET"+path,function);
    }

    /**
     * Publica una función POST sobre el servidor
     * @param path la ruta en la que se va a publicar el endpoint
     * @param function la función que se ejecutará cuando se realice una petición POST en la ruta especificada
     */
    public static void post(String path, BiFunction<Request,Response,String> function){
        endpoints.put("POST"+path,function);
    }

    /**
     * Busca un endpoint con el metodo y ruta especificado en el objeto request, si lo encuentra ejecuta la función del
     * endpoint
     * @param request el objeto en el que se almacenan los detalles de la peticion del cliente
     * @return el resultado del endpoint especificado, null si el endpoint no esta publicado en el servidor
     */
    public static  Response execute(Request request){
        String key=request.getMethod()+request.getPath();
        if(endpoints.containsKey(key)){
            Response response=new Response();
            response.setBody(endpoints.get(key).apply(request,response));
            return response;
        }
        else
            return null;
    }



}
