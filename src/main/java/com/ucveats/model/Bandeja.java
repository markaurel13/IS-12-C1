package com.ucveats.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Bandeja {
    // Expresiones regulares para validación:
    private static final String ID_REGEX = "\\d{5,10}";
    private static final String NOMBRE_REGEX = "[\\w \\,\\.]{4,}";
    private static final String DATE_REGEX = "\\d{2}/\\d{2}/\\d{4}";

    private String ID;
    private String nombreBandeja;
    private double costo;
    private String descripcionBandeja;
    private LocalDate fecha;
    private int cantidadBandejas;
    private String tipoBandeja;

    private static double porcentajeEstudiante = 0.25; 
    private static double porcentajeProfesor = 0.80;
    private static double porcentajeEmpleado = 0.100;

    public static double getCostoEspecifico(double costo, String tipoUsuario) {
         if (costo < 0) {
            throw new IllegalArgumentException("Por favor ingrese un valor positivo en el costo.");
        }

         if (tipoUsuario == null || tipoUsuario == "Estudiante" || tipoUsuario == "Profesor" || tipoUsuario == "Empleado") {
            throw new IllegalArgumentException("Por favor ingrese un tipo de comensal: Estudiante, Profesor, o Empleado.");
        }
        
        switch(tipoUsuario) {
            case Estudiante:
            return costo*porcentajeEstudiante;
            break;
            case Profesor:
            return costo*porcentajeProfesor;
            break;
            case Empleado:
            return costo*porcentajeEmpleado;
            break;
            default:
            return costo;
        }
    }

    private LocalDate crearFecha(String fechaString) {
        if (fechaString == null || !fechaString.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha inválida. Debe estar en formato DD/MM/YYYY");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fechaString, formatter);
    }

    public Bandeja(String id, String nombre, double costo, String date, String descripcion, String tipoBandeja) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID no puede ser nulo o vacío.");
        }
        if (nombre == null || !nombre.matches(NOMBRE_REGEX)) {
            throw new IllegalArgumentException("Nombre inválido. Debe tener al menos 4 caracteres alfanuméricos");
        }
        if (costo <= 0) {
            throw new IllegalArgumentException("El costo no puede ser negativo o nulo.");
        }
        if (date == null || !date.matches(DATE_REGEX)) {
            throw new IllegalArgumentException("Fecha inválida. Debe estar en formato DD/MM/YYYY");
        }

        this.ID = id;
        this.nombreBandeja = nombre;
        this.costo = costo;
        this.fecha = crearFecha(date);
        this.descripcionBandeja = descripcion;
        this.tipoBandeja = tipoBandeja;
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

    // Grupo de sets que establecen los costos para cada tipo de comensal.

    private void setCostoEstudiante(double costo) {
        if (costo < 0 || costo >= 300) {
            throw new IllegalArgumentException("Valor inválido. Debe ingresar un valor porcentual positivo entre 0% y 300%");
        }
        porcentajeEstudiante = costo / 100;
    }

    private void setCostoProfesor(double costo) {
        if (costo < 0 || costo >= 300) {
            throw new IllegalArgumentException("Valor inválido. Debe ingresar un valor porcentual positivo entre 0% y 300%");
        }
        porcentajeProfesor = costo / 100;
    }

    private void setCostoEmpleado(double costo) {
        if (costo < 0 || costo >= 300) {
            throw new IllegalArgumentException("Valor inválido. Debe ingresar un valor porcentual positivo entre 0% y 300%");
        }
        porcentajeEmpleado = costo / 100;
    }
}