package com.example.asistentevial.ui.senales;

public class Imagen {
    private int resourceId;
    private String descripcion;

    public Imagen(int resourceId, String descripcion) {
        this.resourceId = resourceId;
        this.descripcion = descripcion;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
