// Administrador.java
package model;

/**
 * Representa a un administrador del sistema de comedor.
 * Extiende de {@link Usuario} con rol predefinido ADMIN.
 */
public class Administrador extends Usuario {
    /**
     * Crea una instancia de Administrador.
     * @param cedula Cédula del administrador.
     * @param correo Email válido del administrador.
     * @param telefono Teléfono del administrador.
     * @param passwordHash Contraseña hasheada.
     * @throws IllegalArgumentException Si algún dato es inválido.
     */
    public Administrador(String cedula, String correo, String telefono, String passwordHash) {
        super(cedula, correo, telefono, passwordHash, Usuario.RolUsuario.ADMIN);
    }
}