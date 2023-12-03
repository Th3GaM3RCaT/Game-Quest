package com.thecurseds.gamequest;

import android.graphics.Bitmap;
import android.net.Uri;

public class UsuarioChat {
    private Uri foto;
    private String Nombre;
    private String mensaje;

    public UsuarioChat(Bitmap bitmap, String nombre, String mensaje) {
        this.foto = null;
        this.Nombre = "";
        this.mensaje = "";
    }

    public UsuarioChat(Uri foto, String nombre, String mensaje) {
        this.foto = foto;
        Nombre = nombre;
        this.mensaje = mensaje;
    }

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
