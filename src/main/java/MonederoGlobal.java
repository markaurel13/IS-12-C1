package main;

import java.text.DecimalFormat;

// creada con patron Singleton (un solo monedero global)
public class MonederoGlobal {
    private double saldoTotal;
    private double saldoPagado;
    private static MonederoGlobal instancia = null;

    private MonederoGlobal() { // Constructor privado
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.saldoTotal = 0;
        this.saldoPagado = 0;
    }

    public static getInstancia() {
        if (MonederoGlobal.instancia == null){
            MonederoGlobal.instancia = new MonederoGlobal();
        }
        return MonederoGlobal.instancia;
    }

}
