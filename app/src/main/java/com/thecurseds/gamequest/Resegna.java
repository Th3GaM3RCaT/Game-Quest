package com.thecurseds.gamequest;

public class Resegna {
    private int resegnador;
    private String resegnado;
    private String resegna;
    private int valoracion;

    public void resegna() {
        this.resegnador = 0;
        this.resegnado = "";
        this.resegna = "";
        this.valoracion = 0;
    }

    public void resegna(int resegnador, String resegnado, String resegna, int valoracion) {
        this.resegnador = resegnador;
        this.resegnado = resegnado;
        this.resegna = resegna;
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

    public String getResegna() {
        return resegna;
    }

    public void setResegna(String resegna) {
        this.resegna = resegna;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }
}
