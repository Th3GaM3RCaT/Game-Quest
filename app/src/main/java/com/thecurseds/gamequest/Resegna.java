package com.thecurseds.gamequest;

public class Resegna {
    private int resegnador;
    private String resegnado;
    private String resegnaa;
    private int valoracion;

    public Resegna(int resegnador, String resegnado, String resegnaa, int valoracion) {
        this.resegnador = resegnador;
        this.resegnado = resegnado;
        this.resegnaa = resegnaa;
        this.valoracion = valoracion;
    }

    public int getResegnador() {
        return resegnador;
    }

    public void setResegnador(int resegnador) {
        this.resegnador = resegnador;
    }

    public String getResegnado() {
        return resegnado;
    }

    public void setResegnado(String resegnado) {
        this.resegnado = resegnado;
    }

    public String getResegnaa() {
        return resegnaa;
    }

    public void setResegnaa(String resegnaa) {
        this.resegnaa = resegnaa;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
