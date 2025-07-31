// Administrador.java
package com.ucveats.model;

/**
 * Representa a un administrador del sistema de comedor.
 * Extiende de {@link Usuario} con rol predefinido ADMIN.
 */
public class Administrador extends Usuario {
    /**
     * Crea una instancia de Administrador.
     * @param nombre Nombre del administrador.
     * @param apellido Apellido del administrador.
     * @param cedula Cédula del administrador.
     * @param correo Email válido del administrador.
     * @param telefono Teléfono del administrador.
     * @param passwordHash Contraseña hasheada.
     * @throws IllegalArgumentException Si algún dato es inválido.
     */
    public Administrador(String cedula, String nombre, String apellido, String correo, String telefono, String rolUcv, String passwordHash) {
        super(cedula, nombre, apellido, correo, telefono, rolUcv, passwordHash, Usuario.RolUsuario.ADMIN);
    }
}