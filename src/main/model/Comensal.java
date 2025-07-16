package model;

/**
 * Representa a un comensal del sistema de comedor.
 * Extiende de {@link Usuario} con rol predefinido COMENSAL.
 */
public class Comensal extends Usuario {
    /**
     * Crea una instancia de Comensal.
     * @param email Email válido del comensal.
     * @param passwordHash Contraseña hasheada.
     * @throws IllegalArgumentException Si el email es inválido.
     */
    public Comensal(String email, String passwordHash) {
        super(email, passwordHash, Usuario.RolUsuario.COMENSAL);
    }
}