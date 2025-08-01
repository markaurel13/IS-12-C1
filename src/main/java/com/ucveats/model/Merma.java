package com.ucveats.model;

public class Merma {

    static private double merma;

    public static void setMerma(double merma) {
        if (merma < 0 || merma > 100) {
            throw new IllegalArgumentException("Valor inválido. Debe ser un porcentaje en el rango del 0% al 100%");
        }
        Merma.merma = merma;
    }

    public static double getMerma() { return merma; }
    
}
