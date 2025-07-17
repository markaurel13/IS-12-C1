// AuthService.java
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
     * Autentica a un usuario mediante cédula y contraseña.
     * @param cedula Cédula del usuario.
     * @param password Contraseña en texto plano (se hashea internamente).
     * @return Instancia de Usuario (Comensal o Administrador) si las credenciales son válidas, o null.
     * @throws IllegalArgumentException Si la cédula o password son nulos/vacíos.
     */
    public static Usuario login(String cedula, String password) 
        throws IllegalArgumentException {
        if (cedula == null || cedula.trim().isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Cédula y contraseña no pueden estar vacíos");
        }

        List<String[]> usuarios = FileManager.leerUsuarios();
        
        for (String[] usuarioData : usuarios) {
            if (usuarioData[0].equals(cedula)) { // Compara con cédula
                if (SecurityUtils.checkPassword(password, usuarioData[3])) { // Password ahora está en posición 3
                    RolUsuario rol = RolUsuario.valueOf(usuarioData[4].toUpperCase()); // Rol ahora está en posición 4
                    return rol == RolUsuario.COMENSAL 
                        ? new Comensal(usuarioData[0], usuarioData[1], usuarioData[2], usuarioData[3])
                        : new Administrador(usuarioData[0], usuarioData[1], usuarioData[2], usuarioData[3]);
                }
            }
        }
        return null;
    }

    /**
     * Registra un nuevo comensal en el sistema.
     * @param cedula Cédula del usuario.
     * @param correo Correo electrónico.
     * @param telefono Teléfono del usuario.
     * @param password Contraseña en texto plano.
     * @return true si el registro fue exitoso, false si el usuario ya existe.
     * @throws IllegalArgumentException Si algún dato es inválido.
     */
    public static boolean registrar(String cedula, String correo, String telefono, String password) {
        if (cedula == null || correo == null || telefono == null || password == null) {
            throw new IllegalArgumentException("Datos inválidos");
        }

        List<String[]> usuarios = FileManager.leerUsuarios();
        for (String[] u : usuarios) {
            if (u[0].equals(cedula)) { // Verifica por cédula
                return false;
            }
        }

        String hashedPassword = SecurityUtils.hashPassword(password);
        FileManager.guardarUsuario(cedula, correo, telefono, hashedPassword, RolUsuario.COMENSAL); // Todos se registran como COMENSAL
        return true;
    }
}