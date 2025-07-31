package com.ucveats.model;

/**
 * Clase abstracta que representa un usuario del sistema de comedor.
 * Las clases hijas deben definir comportamientos específicos.
 */
public abstract class Usuario {
    // Expresiones regulares para validación:
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String CEDULA_REGEX = "[1-9]\\d{5,9}"; // 6-10 dígitos
    private static final String TELEFONO_REGEX = "04[12][246]\\d{7}"; // 11 dígitos

    // Campos finales (inmutables después de la creación):
    private final String cedula;
    private final String nombre;
    private final String apellido;
    private final String correo;
    private final String telefono;
    private final String rolUcv; // Nuevo campo para el rol específico (estudiante, profesor)
    private final String passwordHash; // Contraseña hasheada (nunca en texto plano)
    
    // Campo no-final (puede cambiarse):
    private RolUsuario rol;           // COMENSAL o ADMIN

    /**
     * Constructor protegido (solo para clases hijas).
     * @param cedula - 8 a 10 dígitos (validado con regex)
     * @param correo - Formato email válido
     * @param telefono - 10 dígitos exactos
     * @param passwordHash - Contraseña ya hasheada (mínimo 5 caracteres)
     * @param rol - COMENSAL (por defecto) o ADMIN
     * @throws IllegalArgumentException Si algún dato es inválido
     */
    protected Usuario(String cedula, String nombre, String apellido, String correo, String telefono, String rolUcv,
                     String passwordHash, RolUsuario rol) {
        
        // Validación de cédula (8-10 dígitos):
        if (cedula == null || !cedula.matches(CEDULA_REGEX)) {
            throw new IllegalArgumentException("Cédula inválida. Debe tener 6 a 10 dígitos");
        }
        
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        
        // Validación de email:
        if (correo == null || !correo.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Correo electrónico inválido");
        }
        
        // Validación de teléfono (10 dígitos):
        if (telefono == null || !telefono.matches(TELEFONO_REGEX)) {
            throw new IllegalArgumentException("Teléfono inválido. Debe tener 11 dígitos y estar en formato 04XX0000000");
        }
        
        // Validación de contraseña (mínimo 5 caracteres):
        if (passwordHash == null || passwordHash.length() < 5) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 5 caracteres");
        }
        
        // Validación de rol:
        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }

        // Si todo es válido, asignamos los valores:
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.rolUcv = rolUcv;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }

    // --- Getters (accesos a los campos) ---
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
    public String getRolUcv() { return rolUcv; }
    public String getPasswordHash() { return passwordHash; }
    public RolUsuario getRol() { return rol; }

    // --- Setter solo para el rol (los demás campos son inmutables) ---
    public void setRol(RolUsuario rol) {
        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
        this.rol = rol;
    }

    // Representación textual para depuración:
    @Override
    public String toString() {
        return String.format(
            "Usuario[cedula=%s, nombre=%s %s, rol=%s]",
            cedula, nombre, apellido, rol
        );
    }

    // Enumeración de roles posibles:
    public enum RolUsuario {
        COMENSAL, ADMIN;

        // Versión "bonita" del nombre del rol:
        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }
}