package edu.escuelaing.arep.app.persistence;

import edu.escuelaing.arep.app.model.Mensaje;

import java.util.ArrayList;

public interface MessagePersistence {

    void writeMessage(Mensaje mensaje);

    ArrayList<Mensaje> loadMessages();
}
