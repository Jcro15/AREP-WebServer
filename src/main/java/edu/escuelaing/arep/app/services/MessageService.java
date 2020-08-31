package edu.escuelaing.arep.app.services;

import edu.escuelaing.arep.app.model.Mensaje;

import java.util.ArrayList;

public interface MessageService {
    public void addMessage(String mensaje);

    public ArrayList<Mensaje> getAllMessages();
}
