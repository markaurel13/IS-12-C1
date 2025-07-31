// Comensal.java
package com.ucveats.model;

/**
 * Representa a un comensal del sistema de comedor.
 * Extiende de {@link Usuario} con rol predefinido COMENSAL.
 */
public class Comensal extends Usuario {
    private final MonederoVirtual monedero;
    /**
     * Crea una instancia de Comensal.
     * @param nombre Nombre del comensal.
     * @param apellido Apellido del comensal.
     * @param cedula Cédula del comensal.
     * @param correo Email válido del comensal.
     * @param telefono Teléfono del comensal.
     * @param passwordHash Contraseña hasheada.
     * @throws IllegalArgumentException Si algún dato es inválido.
     */
    public Comensal(String cedula, String nombre, String apellido, String correo, String telefono, String rolUcv, String passwordHash) {
        super(cedula, nombre, apellido, correo, telefono, rolUcv, passwordHash, Usuario.RolUsuario.COMENSAL);
        // Creacion del monedero virtual asignado a este usuario
        this.monedero = new MonederoVirtual();
    }

    public MonederoVirtual getMonedero() {
        return monedero;
    }
}