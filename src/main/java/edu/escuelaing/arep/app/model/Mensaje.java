package edu.escuelaing.arep.app.model;

import java.util.Date;

public class Mensaje {

    private String mensaje;
    private Date fecha;

    public Mensaje(String mensaje) {
        this.mensaje=mensaje;
        this.fecha=new Date();
    }
    public Mensaje(String mensaje,Date fecha){
        this.mensaje=mensaje;
        this.fecha=fecha;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
