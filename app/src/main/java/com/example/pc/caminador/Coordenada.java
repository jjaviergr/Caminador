package com.example.pc.caminador;

/**
 * Created by pc on 29/02/2016.
 */
public class Coordenada {
    private String latitud;
    private String longitud;
    private String fecha;

    public Coordenada(String latitud, String longitud, String fecha) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.fecha = fecha;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}