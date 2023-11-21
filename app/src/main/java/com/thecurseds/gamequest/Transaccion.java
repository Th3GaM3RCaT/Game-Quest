package com.thecurseds.gamequest;

public class Transaccion {
    private String comprador;
    private String vendedor;
    private String juego;
    private String tipoTransaccion;
    private String fecha;
    private String descripcion;
    private String precio;

    public Transaccion() {
        this.comprador = "comprador";
        this.vendedor = "vendedor";
        this.juego = "juego";
        this.tipoTransaccion = "tipoTransaccion";
        this.fecha = "fecha";
        this.descripcion = "descripcion";
        this.precio = "precio";
    }

    public Transaccion(String comprador, String vendedor, String juego, String tipoTransaccion, String fecha, String descripcion, String precio) {
        this.comprador = comprador;
        this.vendedor = vendedor;
        this.juego = juego;
        this.tipoTransaccion = tipoTransaccion;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getJuego() {
        return juego;
    }

    public void setJuego(String juego) {
        this.juego = juego;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
}
