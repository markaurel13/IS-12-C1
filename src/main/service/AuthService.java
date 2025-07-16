package service;

import modelos.Usuario;
import modelos.Comensal;
import modelos.Administrador;
import modelos.Usuario.RolUsuario;
import java.util.List;

/**
 * Servicio para manejar autenticación y registro de usuarios.
 */
public class AuthService {

    /**
     * Autentica a un usuario mediante email y contraseña.
     * @param email Email del usuario.
     * @param password Contraseña en texto plano (se hashea internamente).
     * @return Instancia de Usuario (Comensal o Administrador) si las credenciales son válidas, o null.
     * @throws IllegalArgumentException Si el email o password son nulos/vacíos.
     */
    public static Usuario login(String email, String password) 
        throws IllegalArgumentException {
        if (email == null || email.trim().isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Email y contraseña no pueden estar vacíos");
        }

        List<String[]> usuarios = FileManager.leerUsuarios();
        
        for (String[] usuarioData : usuarios) {
            if (usuarioData[0].equals(email) && 
                SecurityUtils.checkPassword(password, usuarioData[1])) {
                RolUsuario rol = RolUsuario.valueOf(usuarioData[2].toUpperCase());
                return rol == RolUsuario.COMENSAL 
                    ? new Comensal(email, usuarioData[1])
                    : new Administrador(email, usuarioData[1]);
            }
        }
        return null;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param usuario Instancia de Usuario (Comensal o Administrador) con contraseña EN TEXTO PLANO.
     * @return true si el registro fue exitoso, false si el usuario ya existe.
     * @throws IllegalArgumentException Si el usuario es nulo o tiene datos inválidos.
     */
    public static boolean registrar(String email, String password, RolUsuario rol) {
    if (email == null || password == null || rol == null) {
        throw new IllegalArgumentException("Datos inválidos");
    }

    List<String[]> usuarios = FileManager.leerUsuarios();
    for (String[] u : usuarios) {
        if (u[0].equals(email)) {
            return false;
        }
    }

    String hashedPassword = SecurityUtils.hashPassword(password); // Hashing único
    FileManager.guardarUsuario(email, hashedPassword, rol);
    return true;
}
}