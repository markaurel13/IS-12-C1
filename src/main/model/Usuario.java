package model;

/**
 * Clase abstracta que representa un usuario del sistema de comedor.
 * Las clases hijas deben definir comportamientos específicos.
 */
public abstract class Usuario {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final String email;       // Inmutable
    private final String passwordHash; // Inmutable
    private final RolUsuario rol;     // Inmutable

    /**
     * Constructor protegido para garantizar que solo las clases hijas puedan instanciarse.
     * @param email Email del usuario (formato válido requerido).
     * @param passwordHash Contraseña hasheada del usuario.
     * @param rol Rol del usuario (COMENSAL o ADMIN, no nulo).
     * @throws IllegalArgumentException Si el email o rol son inválidos.
     */
    public Usuario(String email, String passwordHash, RolUsuario rol) {
        if (email == null || email.trim().isEmpty() || !email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        if (rol == null) {
            throw new IllegalArgumentException("Rol no puede ser nulo");
        }
        this.email = email;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }

    // Getters
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public RolUsuario getRol() { return rol; }

    /**
     * Representación en String del usuario (para logging/depuración).
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", rol=" + rol +
                '}';
    }

    /**
     * Enumeración de roles posibles para un usuario.
     */
    public enum RolUsuario {
        COMENSAL, ADMIN;

        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase(); // "COMENSAL" -> "Comensal"
        }
    }
}