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

                    if (rol == RolUsuario.COMENSAL) {
                        Comensal comensal = new Comensal(usuarioData[0], usuarioData[1], usuarioData[2], usuarioData[3]);
                        // Cargar saldo si existe (columna 6, índice 5)
                        if (usuarioData.length > 5 && usuarioData[5] != null) {
                            try {
                                double saldo = Double.parseDouble(usuarioData[5]);
                                comensal.getMonedero().setSaldoInicial(saldo);
                            } catch (NumberFormatException e) { /* Saldo inválido, se mantiene en 0 */ }
                        }
                        return comensal;
                    } else {
                        return new Administrador(usuarioData[0], usuarioData[1], usuarioData[2], usuarioData[3]);
                    }
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
        // 1. Se revisa primero si el usuario ya existe para fallar rápido.
        List<String[]> usuarios = FileManager.leerUsuarios();
        for (String[] u : usuarios) {
            if (u[0].equals(cedula)) { // Verifica por cédula
                return false;
            }
        }

        // 2. Se hashea la contraseña.
        String hashedPassword = SecurityUtils.hashPassword(password);

        // 3. Se crea un objeto Comensal para forzar la validación del modelo.
        //    El constructor de Usuario/Comensal lanzará una IllegalArgumentException si los datos son inválidos.
        new Comensal(cedula, correo, telefono, hashedPassword);

        // 4. Si la validación es exitosa, se guarda el usuario.
        FileManager.guardarUsuario(cedula, correo, telefono, hashedPassword, RolUsuario.COMENSAL, 0.0);
        return true;
    }

    /**
     * Actualiza la información de un usuario en el archivo de persistencia.
     * @param usuarioActualizado El objeto Usuario con la información actualizada.
     * @throws IOException Si ocurre un error de lectura o escritura en el archivo.
     */
    public static void actualizarUsuario(Usuario usuarioActualizado) throws IOException {
        if (usuarioActualizado == null) {
            return;
        }

        List<String[]> todosLosUsuarios = FileManager.leerUsuarios();
        int indexToUpdate = -1;
        for (int i = 0; i < todosLosUsuarios.size(); i++) {
            if (todosLosUsuarios.get(i)[0].equals(usuarioActualizado.getCedula())) {
                indexToUpdate = i;
                break;
            }
        }

        if (indexToUpdate != -1) {
            String[] nuevosDatos;
            if (usuarioActualizado instanceof Comensal) {
                Comensal comensal = (Comensal) usuarioActualizado;
                // Formato: cedula,correo,telefono,password,rol,saldo
                nuevosDatos = new String[]{
                        comensal.getCedula(),
                        comensal.getCorreo(),
                        comensal.getTelefono(),
                        comensal.getPasswordHash(), // La contraseña ya está hasheada
                        comensal.getRol().name(),
                        String.valueOf(comensal.getMonedero().getSaldo())
                };
            } else {
                nuevosDatos = todosLosUsuarios.get(indexToUpdate); // No hay cambios para el admin por ahora
            }
            todosLosUsuarios.set(indexToUpdate, nuevosDatos);
        }

        FileManager.reescribirUsuarios(todosLosUsuarios);
    }
}
