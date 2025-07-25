package com.ucveats.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.ucveats.view.inicioSesionInterface;

public class Bandeja {
    // Expresiones regulares para validación:
    private static final String ID_REGEX = "\\d{5,10}";
    private static final String NOMBRE_REGEX = "[\\w \\,\\.]{4,}";
    private static final String DATE_REGEX = "\\d{2}/\\d{2}/\\d{4}";

    private static int identifierNumber = 0;

    private String ID;
    private String nombreBandeja;
    private double costo;
    private String descripcionBandeja;
    private LocalDate fecha;

    private String crearID(int id) {
        String numberID = Integer.toString(id);
        while (numberID.length() < 5) {
            numberID = "0" + numberID;
        }
        if (!numberID.matches(ID_REGEX)) {
            throw new IllegalArgumentException("ID inválida.");
        }
        return numberID;
    }

    private LocalDate crearFecha(String fechaString) {
        if (fechaString == null || !fechaString.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha inválida. Debe estar en formato DD/MM/YYYY");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaString, formatter);
    }

    public Bandeja(int identifierNumber, String nombre, double costo, String date, String descripcion) {
        if (nombre == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("Nombre inválido. Debe tener al menos 4 caracteres alfanuméricos");
        }
        if (costo < 0) {
            throw new IllegalArgumentException("Costo inválido. No puede ser negativo.");
        }
        if (date == null || !date.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha inválida. Debe estar en formato DD/MM/YYYY");
        }

        this.ID = crearID(identifierNumber);
        this.nombreBandeja = nombre;
        this.costo = costo;
        this.fecha = crearFecha(date);
        this.descripcionBandeja = descripcion;
        Bandeja.identifierNumber++;
    }

    public Bandeja(int identifierNumber, String nombre, double costo, String date) {
        this(identifierNumber, nombre, costo, date, "No hay una descripción disponible para este platillo.");
    }

    // Getters
    public String getID() { return ID; }
    public String getNombreBandeja() { return nombreBandeja; }
    public double getCosto() { return costo; }
    public String getDescripcionBandeja() { return descripcionBandeja; }
    public String getFecha() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    // Setters
    public void setNombreBandeja(String nombreBandeja) {
        if (nombreBandeja == null || !nombreBandeja.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("Nombre inválido.");
        }
        this.nombreBandeja = nombreBandeja;
    }

    public void setCosto(double costo) {
        if (costo < 0) {
            throw new IllegalArgumentException("Costo no puede ser negativo.");
        }
        this.costo = costo;
    }

    public void setDescripcionBandeja(String descripcionBandeja) {
        if (descripcionBandeja == null) {
            this.descripcionBandeja = "No hay una descripción disponible para este platillo.";
        } else {
            this.descripcionBandeja = descripcionBandeja;
        }
    }

    public void setFecha(String fecha) {
        if (fecha == null || !fecha.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha inválida. Debe estar en formato DD/MM/YYYY");
        }
        this.fecha = crearFecha(fecha);
    }
}