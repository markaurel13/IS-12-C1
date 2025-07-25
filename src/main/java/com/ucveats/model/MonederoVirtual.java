package com.ucveats.model;

import java.text.DecimalFormat;

public class MonederoVirtual {
private double saldo;

public MonederoVirtual() {
    saldo = 0;
    MonederoGlobal.getInstancia();
}

public String getSaldo() {
    String moneda = "BsS. "; 
    String saldoRounded = new DecimalFormat("#.0#").format(saldo);
    String textoSaldo = moneda + saldoRounded;
    return textoSaldo;
}

public void recargar(double recarga) {
    if(recarga <= 0) {
        throw new IllegalArgumentException("Recargue un monto válido positivo.");
    }
    else {
        saldo += recarga;
        MonederoGlobal.getInstancia().recargarSaldo(recarga);
        System.out.println("Recarga exitosa.");
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


