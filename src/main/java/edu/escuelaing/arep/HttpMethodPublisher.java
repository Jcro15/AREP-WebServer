package edu.escuelaing.arep;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import com.google.gson.Gson;


public class HttpMethodPublisher {


    private static Map<String,BiFunction<Request,Response,String>> endpoints=new HashMap<>();


    public static void get(String path, BiFunction<Request,Response,String> function){
        endpoints.put("GET"+path,function);
    }
    public static void post(String path, BiFunction<Request,Response,String> function){
        endpoints.put("POST"+path,function);
    }

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
