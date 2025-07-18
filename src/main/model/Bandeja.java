package main.model;

public class Bandeja {
    // Expresiones regulares para valsidación:
    private static final String ID_REGEX = "\\d{5,10}";
    private static final String NOMBRE_REGEX = "[\\w \\,\\.]{4,}";
    
    // Contador de ID para platillos creados
    private static int identifierNumber = 0;

    // Campo no-final (puede cambiarse):
    private String ID;
    private String nombreBandeja;
    private double costo;
    private String descripcionBandeja;

    private string crearID(int ID) {
        string numberID = Integer.toString(identifierNumber);
        string fillerID = "0";
        int lengthID = numberID.length();
        
        string finalID = numberID;

        while(finalID.length() < 5) {
            finalID = fillerId + finalID;
        }

        if(!finalID.matches(ID_REGEX)) {
            throw new IllegalArgumentException("ID inválida.");
        }

        return finalID;
    }
    
    // Constructores
    public void Platillo(int identifierNumber, string nombre, int costo, string descripcion) {
        if (nombre == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("Nombre inválido. Debe tener caracteres alfanuméricos y una longitud mínima de 4 caracteres");
        }

        if (costo < 0) {
            throw new IllegalArgumentException("Costo inválido. Debe tener un valor igual o mayor a cero.");
        }
        
        this.ID = crearID(identifierNumber);
        this.nombreBandeja = nombre;
        this.costo = costo;
        this.descripcionBandeja = descripcion;
        identifierNumber++;
    }

    public void Platillo(int identifierNumber, string nombre, int costo) {
        if (nombre == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("Nombre inválido. Debe tener caracteres alfanuméricos y una longitud mínima de 4 caracteres");
        }

        if (costo < 0) {
            throw new IllegalArgumentException("Costo inválido. No puede ser un valor negativo.");
        }
        
        this.ID = crearID(identifierNumber);
        this.nombreBandeja = nombre;
        this.costo = costo;
        this.descripcionBandeja = "No hay una descripción disponible para este platillo.";
        identifierNumber++;
    }

    // --- Getters (accesos a los campos) ---
    public String getID() { return ID; }
    public String getNombreBandeja() { return nombreBandeja; }
    public double getCosto() { return costo; }
    public String getDescripcionBandeja() { return descripcionBandeja; }

    // --- Setter (para todos los campos excepto la ID) ---
    public void setNombreBandeja(String nombreBandeja) { 
        if (nombreBandeja == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("El nombre de bandeja no puede ser nulo");
        }
        this.nombreBandeja = nombreBandeja;
    }

    public void getCosto(double costo) { 
        if (costo < 0) {
            throw new IllegalArgumentException("El costo de bandeja no puede ser negativo");
        }
        this.costo = costo; 
    }

    public void getDescripcionBandeja(string descripcionBandeja) { 
        if (descripcionBandeja == null) {
            descripcionBandeja = "No hay una descripción disponible para este platillo.";
        } else {
        this.nombreBandeja = nombreBandeja;
        }
    }
}
