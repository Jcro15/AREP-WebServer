package edu.escuelaing.arep.app.persistence.impl;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.escuelaing.arep.app.model.Mensaje;
import edu.escuelaing.arep.app.persistence.MessagePersistence;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

public class DBConnection implements MessagePersistence {
    public MongoClientURI uri;
    public MongoClient mongoClient;

    public DBConnection() {
        uri = new MongoClientURI(
                "mongodb+srv://admin:Admin123@cluster0.n7msp.mongodb.net/Arep?retryWrites=true&w=majority");
        mongoClient = new MongoClient(uri);
    }
    
    @Override
    public void writeMessage(Mensaje mensaje) {
        MongoDatabase database = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection =database.getCollection("Mensajes");
        Document document=new Document();
        document.put("mensaje",mensaje.getMensaje());
        document.put("fecha",mensaje.getFecha());
        collection.insertOne(document);
    }

    @Override
    public ArrayList<Mensaje> loadMessages() {
        ArrayList<Mensaje> mensajes=new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection =database.getCollection("Mensajes");
        FindIterable fit = collection.find();
        ArrayList<Document> docs = new ArrayList<Document>();
        StringBuilder results = new StringBuilder();
        fit.into(docs);

        for (Document document:docs) {
            String mensaje= (String) document.get("mensaje");
            Date date=(Date)document.get("fecha");
            mensajes.add(new Mensaje(mensaje,date));
        }


        return mensajes;
    }
}
