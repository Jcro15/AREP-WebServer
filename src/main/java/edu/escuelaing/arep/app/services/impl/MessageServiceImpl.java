package edu.escuelaing.arep.app.services.impl;

import edu.escuelaing.arep.app.model.Mensaje;
import edu.escuelaing.arep.app.persistence.MessagePersistence;
import edu.escuelaing.arep.app.persistence.impl.DBConnection;
import edu.escuelaing.arep.app.services.MessageService;

import java.util.ArrayList;

public class MessageServiceImpl implements MessageService {

    MessagePersistence messagePersistence= new DBConnection();

    @Override
    public void addMessage(String mensaje) {
        messagePersistence.writeMessage(new Mensaje(mensaje));
    }

    @Override
    public ArrayList<Mensaje> getAllMessages() {
        return messagePersistence.loadMessages();
    }
}
