package main;

public class CostoVariable {
    private double proteinas;
    private double carbohidratos;
    private double energia;
    private String fecha;
    private String tipoBandeja;

    public CostoVariable(double proteinas, double carbohidratos, double energia, String fecha, String tipoBandeja) {
        this.proteinas = proteinas;
        this.carbohidratos = carbohidratos;
        this.energia = energia;
        this.fecha = fecha;
        this.tipoBandeja = tipoBandeja;
    }

    public double getTotal() {
        return proteinas + carbohidratos + energia;
    }

    public double getProteinas() { return proteinas; }
    public void setProteinas(double proteinas) { this.proteinas = proteinas; }
    public double getCarbohidratos() { return carbohidratos; }
    public void setCarbohidratos(double carbohidratos) { this.carbohidratos = carbohidratos; }
    public double getEnergia() { return energia; }
    public void setEnergia(double energia) { this.energia = energia; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public String getTipoBandeja() { return tipoBandeja; }
    public void setTipoBandeja(String tipoBandeja) { this.tipoBandeja = tipoBandeja; }
}
