package model;

/**
 * Representa a un administrador del sistema de comedor.
 * Extiende de {@link Usuario} con rol predefinido ADMIN.
 */
public class Administrador extends Usuario {
    /**
     * Crea una instancia de Administrador.
     * @param email Email válido del administrador (formato: usuario@dominio.ext).
     * @param passwordHash Contraseña hasheada.
     * @throws IllegalArgumentException Si el email es inválido (validado por la clase padre).
     */
    public Administrador(String email, String passwordHash) {
        super(email, passwordHash, Usuario.RolUsuario.ADMIN); // Usa el Enum
    }
}