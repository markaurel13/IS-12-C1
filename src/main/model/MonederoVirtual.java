package main.model;

import java.text.DecimalFormat;

public class MonederoVirtual {
    private double saldo;
    
    // Constructor del monedero (usado al crearse un comensal)
    public MonederoVirtual() {
        saldo = 0;
        MonederoGlobal.getInstancia();
    }

    // Devuelve el saldo disponible en una cadena de formato "BsS. 0.00"
    public double getSaldo() {
        string moneda = "BsS. "; 
        string saldoRounded = new DecimalFormat("#.0#").format(saldo);
        string textoSaldo = moneda + saldoRounded;
        return textoSaldo;
    }

}
