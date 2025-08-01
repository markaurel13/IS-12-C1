package com.ucveats.model;

public class Merma {

    private double merma;

    public Merma(double merma) {
        if(merma < 0 || merma > 100) {
            throw new IllegalArgumentException("Valor inválido. Debe ser un porcentaje en el rango del 0% al 100%");
        }

        this.merma = merma;
    }

    getMerma() { return merma; }
    
}
