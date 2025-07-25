// Comensal.java
package com.ucveats.model;

/**
 * Representa a un comensal del sistema de comedor.
 * Extiende de {@link Usuario} con rol predefinido COMENSAL.
 */
public class Comensal extends Usuario {
    private MonederoVirtual Monedero;
    private RolComensal rol;
    /**
     * Crea una instancia de Comensal.
     * @param cedula Cédula del comensal.
     * @param correo Email válido del comensal.
     * @param telefono Teléfono del comensal.
     * @param passwordHash Contraseña hasheada.
     * @throws IllegalArgumentException Si algún dato es inválido.
     */
    public Comensal(String cedula, String correo, String telefono, String passwordHash, RolComensal rol) {
        super(cedula, correo, telefono, passwordHash, Usuario.RolUsuario.COMENSAL);
        // Creacion del monedero virtual asignado a este usuario
        this.Monedero = MonederoVirtual.MonederoVirtual();

        // Validación de rol:
        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }

        this.rol = rol;
    }

    // --- Setter para el rol ---
    public void setRol(RolComensal rol) {

        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }

        this.rol = rol;
    }

    // Enumeración de roles posibles:
    public enum RolComensal {
        ESTUDIANTE, PROFESOR, EMPLEADO;

        // Versión "bonita" del nombre del rol:
        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }

}