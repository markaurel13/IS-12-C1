package com.ucveats.model;

import java.text.DecimalFormat;

public class MonederoVirtual {
private double saldo;

public MonederoVirtual() {
    saldo = 0;
    MonederoGlobal.getInstancia();
}

public double getSaldo() {
    string moneda = "BsS. "; 
    string saldoRounded = new DecimalFormat("#.0#").format(saldo);
    string textoSaldo = moneda + saldoRounded;
    return textoSaldo;
}

public void recargar(double recarga) {
    if(recarga <= 0) {
        throw new IllegalArgumentException("Recargue un monto válido positivo.");
    }
    else {
        saldo += recarga;
        system.out.println("Recarga exitosa.");
    }
}

public boolean pagarBandeja(double costo) {
    if (saldo >= costo) {
        saldo -= costo;
        return true;
    }
    return false;
}

}


