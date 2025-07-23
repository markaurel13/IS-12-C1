// Comensal.java
package com.ucveats.model;

/**
 * Representa a un comensal del sistema de comedor.
 * Extiende de {@link Usuario} con rol predefinido COMENSAL.
 */
public class Comensal extends Usuario {
    MonederoVirtual Monedero;
    /**
     * Crea una instancia de Comensal.
     * @param cedula Cédula del comensal.
     * @param correo Email válido del comensal.
     * @param telefono Teléfono del comensal.
     * @param passwordHash Contraseña hasheada.
     * @throws IllegalArgumentException Si algún dato es inválido.
     */
    public Comensal(String cedula, String correo, String telefono, String passwordHash) {
        super(cedula, correo, telefono, passwordHash, Usuario.RolUsuario.COMENSAL);
        // Creacion del monedero virtual asignado a este usuario
        Monedero = MonederoVirtual.MonederoVirtual(); 
    }
}