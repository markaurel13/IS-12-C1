package com.ucveats.model;

import java.text.DecimalFormat;

// creada con patron Singleton (un solo monedero global)
public class MonederoGlobal {
    private double saldoUsuarios;
    private double saldoPagado;
    private static MonederoGlobal instancia = null;

    private MonederoGlobal() { // Constructor privado
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.saldoUsuarios = 0;
        this.saldoPagado = 0;
    }

    // luisangel anadio el tipo MonederoGlobal a la clase get instancia porque habia un error
    public static MonederoGlobal getInstancia() {
        if (instancia == null){
            instancia = new MonederoGlobal();
        }
        return instancia;
    }

    public void recargarSaldo(double pago) {
        this.saldoUsuarios += pago;
    }

    public void pagarComida(double pago) {
        this.saldoPagado += pago;
    }

}
