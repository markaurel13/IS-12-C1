package com.ucveats.model;

public class CostoFijo {
    private double manoObra;
    private double mantenimiento;
    private double alquiler;

    public CostoFijo(double manoObra, double mantenimiento, double alquiler) {
        this.manoObra = manoObra;
        this.mantenimiento = mantenimiento;
        this.alquiler = alquiler;
    }

    public double getTotal() {
        return manoObra + mantenimiento + alquiler;
    }

    public double getManoObra() { return manoObra; }
    public void setManoObra(double manoObra) { this.manoObra = manoObra; }
    public double getMantenimiento() { return mantenimiento; }
    public void setMantenimiento(double mantenimiento) { this.mantenimiento = mantenimiento; }
    public double getAlquiler() { return alquiler; }
    public void setAlquiler(double alquiler) { this.alquiler = alquiler; }
}
