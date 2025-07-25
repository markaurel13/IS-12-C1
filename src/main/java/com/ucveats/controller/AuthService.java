package com.ucveats.controller;

import com.ucveats.model.Usuario;
import com.ucveats.model.Comensal;
import com.ucveats.model.Administrador;
import com.ucveats.model.Usuario.RolUsuario;
import java.io.IOException;
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
     * @throws IOException Si ocurre un error al leer el archivo de usuarios.
     */
    public static Usuario login(String cedula, String password) throws IllegalArgumentException, IOException {
        if (cedula == null || cedula.trim().isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Cédula y contraseña no pueden estar vacíos");
        }

        List<String[]> usuarios = FileManager.leerUsuarios();

        for (String[] usuarioData : usuarios) {
            if (usuarioData[0].equals(cedula)) { // Compara con cédula
                if (SecurityUtils.checkPassword(password, usuarioData[3])) { // Password en posición 3
                    RolUsuario rol = RolUsuario.valueOf(usuarioData[4].toUpperCase()); // Rol en posición 4
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
     * @throws IOException Si ocurre un error al leer o guardar el archivo de usuarios.
     */
    public static boolean registrar(String cedula, String correo, String telefono, String password)
            throws IllegalArgumentException, IOException {
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
        FileManager.guardarUsuario(cedula, correo, telefono, hashedPassword, RolUsuario.COMENSAL);
        return true;
    }
}
