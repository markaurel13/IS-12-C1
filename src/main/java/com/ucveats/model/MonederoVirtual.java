package com.ucveats.model;

import java.text.DecimalFormat;

public class MonederoVirtual {
private double saldo;

public MonederoVirtual() {
    saldo = 0;
    MonederoGlobal.getInstancia();
}

public double getSaldo() {
    return saldo;
}

public void recargar(double recarga) {
    if(recarga <= 0) {
        throw new IllegalArgumentException("Recargue un monto válido positivo.");
    }
    else {
        saldo += recarga;
        MonederoGlobal.getInstancia().recargarSaldo(recarga);
    }
}

public boolean pagarBandeja(double costo) {
    if (saldo >= costo) {
        saldo -= costo;
        MonederoGlobal.getInstancia().pagarComida(costo);
        return true;
    }
    return false;
}

}
