package com.ucveats.model;

import java.text.DecimalFormat;

// creada con patron Singleton (un solo monedero global)
public class MonederoGlobal {
    private double saldoUsuarios;
    private double saldoPagado;
    private static MonederoGlobal instancia = null;

    private MonederoGlobal() { // Constructor privado
        this.saldoUsuarios = 0;
        this.saldoPagado = 0;
    }

    // Se añade 'synchronized' para hacerlo seguro en entornos con múltiples hilos (thread-safe).
    public static synchronized MonederoGlobal getInstancia() {
        if (instancia == null){
            instancia = new MonederoGlobal();
        }
        return instancia;
    }

    public synchronized void recargarSaldo(double pago) {
        this.saldoUsuarios += pago;
    }

    public synchronized void pagarComida(double pago) {
        this.saldoPagado += pago;
    }

}
