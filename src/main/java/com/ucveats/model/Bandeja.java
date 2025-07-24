package com.ucveats.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ucveats.view.inicioSesionInterface;

public class Bandeja {
    // Expresiones regulares para valsidación:
    private static final String ID_REGEX = "\\d{5,10}";
    private static final String NOMBRE_REGEX = "[\\w \\,\\.]{4,}";
    private static final String FECHA_REGEX = "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    
    // Contador de ID para platillos creados
    private static int identifierNumber = 0;

    // Campo no-final (puede cambiarse):
    private String ID;
    private String nombreBandeja;
    private double costo;
    private String descripcionBandeja;
    private LocalDate fecha;

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

    private LocalDate crearFecha(string fechaString) {
        if (fechaString == null || !fechaString.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha invalida. Debe estar en formato MM-DD-YYYY");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fecha = LocalDate.parse(fechaString, formatter);

        return fecha;
    }
    
    // Constructores
    public void Platillo(int identifierNumber, string nombre, int costo, string date, string descripcion) {
        if (nombre == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("Nombre inválido. Debe tener caracteres alfanuméricos y una longitud mínima de 4 caracteres");
        }

        if (costo < 0) {
            throw new IllegalArgumentException("Costo inválido. Debe tener un valor igual o mayor a cero.");
        }

        if (date == null || !date.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha invalida. Debe estar en formato MM-DD-YYYY");
        }
        
        this.ID = crearID(identifierNumber);
        this.nombreBandeja = nombre;
        this.costo = costo;
        this.fecha = LocalDate.parse(date);
        this.descripcionBandeja = descripcion;
        identifierNumber++;
    }

    public void Platillo(int identifierNumber, string nombre, int costo, string date) {
        if (nombre == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("Nombre inválido. Debe tener caracteres alfanuméricos y una longitud mínima de 4 caracteres");
        }

        if (costo < 0) {
            throw new IllegalArgumentException("Costo inválido. No puede ser un valor negativo.");
        }
        
        if (date == null || !date.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha invalida. Debe estar en formato MM-DD-YYYY");
        }

        this.ID = crearID(identifierNumber);
        this.nombreBandeja = nombre;
        this.costo = costo;
        this.fecha = crearFecha(date);
        this.descripcionBandeja = "No hay una descripción disponible para este platillo.";
        identifierNumber++;
    }

    // --- Getters (accesos a los campos) ---
    public String getID() { return ID; }
    public String getNombreBandeja() { return nombreBandeja; }
    public double getCosto() { return costo; }
    public String getDescripcionBandeja() { return descripcionBandeja; }
    public String getFecha() { return this.fecha.toString(); }

    // --- Setter (para todos los campos excepto la ID) ---
    public void setNombreBandeja(String nombreBandeja) { 
        if (nombreBandeja == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("El nombre de bandeja no puede ser nulo");
        }
        this.nombreBandeja = nombreBandeja;
    }

    public void setCosto(double costo) { 
        if (costo < 0) {
            throw new IllegalArgumentException("El costo de bandeja no puede ser negativo");
        }
        this.costo = costo; 
    }

    public void setDescripcionBandeja(string descripcionBandeja) { 
        if (descripcionBandeja == null) {
            descripcionBandeja = "No hay una descripción disponible para este platillo.";
        } else {
        this.descripcionBandejaBandeja = descripcionBandeja;
        }
    }

    public void setFecha(string fecha) {
        if (date == null || !date.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha invalida. Debe estar en formato MM-DD-YYYY");
        }
        this.fecha = crearFecha(date);
    }
}
