package com.thecurseds.gamequest;

public class game_md {
    private String descripcion;
    private String precio;
    private String nombre;

    public game_md() {
        // Constructor vacío requerido para Firebase
    }

    public game_md(String descripcion, String precio, String nombre) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}


