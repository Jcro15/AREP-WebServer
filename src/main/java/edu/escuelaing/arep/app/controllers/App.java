package edu.escuelaing.arep.app.controllers;

import com.google.gson.Gson;
import edu.escuelaing.arep.app.services.MessageService;
import edu.escuelaing.arep.app.services.impl.MessageServiceImpl;
import edu.escuelaing.arep.httpServer.HttpMethodPublisher;
import edu.escuelaing.arep.httpServer.HttpServer;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MessageService messageService= new MessageServiceImpl();
        HttpServer server=new HttpServer();
        server.staticFilesLocation("/static");
        server.start();
        HttpMethodPublisher.get("/mensajes",((request, response) ->
                new Gson().toJson(messageService.getAllMessages())));
        HttpMethodPublisher.post("/mensajes", (request, response) -> {
            messageService.addMessage(request.getBody());
            return "" ;

        });
    }
}
