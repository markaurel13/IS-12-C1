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
                // Formato nuevo: 0:cedula, 1:nombre, 2:apellido, 3:correo, 4:telefono, 5:rolUcv, 6:password, 7:rol, 8:saldo
                if (usuarioData.length < 9) continue; // Ignora líneas con formato antiguo/incorrecto para evitar errores

                    if (SecurityUtils.checkPassword(password, usuarioData[6])) { // Password en posición 6
                    RolUsuario rol = RolUsuario.valueOf(usuarioData[7].toUpperCase()); // Rol en posición 7

                    if (rol == RolUsuario.COMENSAL) {
                        // Llama al constructor con 6 argumentos
                        Comensal comensal = new Comensal(usuarioData[0], usuarioData[1], usuarioData[2], usuarioData[3], usuarioData[4], usuarioData[5], usuarioData[6]);
                        // Cargar saldo si existe (columna 9, índice 8)
                        if (usuarioData.length > 8 && usuarioData[8] != null) {
                            try {
                                double saldo = Double.parseDouble(usuarioData[8]);
                                comensal.getMonedero().setSaldoInicial(saldo);
                            } catch (NumberFormatException e) { /* Saldo inválido, se mantiene en 0 */ }
                        }
                        return comensal;
                    } else {
                        // Llama al constructor con 6 argumentos
                        return new Administrador(usuarioData[0], usuarioData[1], usuarioData[2], usuarioData[3], usuarioData[4], usuarioData[5], usuarioData[6]);
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
        
        // Nueva validación: Comprobar la longitud de la contraseña en texto plano.
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }

        // 1. Validar si la cédula pertenece a la comunidad UCV
        String[] ucvData = FileManager.buscarEnUcvDb(cedula);
        if (ucvData == null) {
            throw new IllegalArgumentException("La cédula no pertenece a la comunidad UCV.");
        }
        String nombre = ucvData[0];
        String apellido = ucvData[1];
        String rolUcv = ucvData[2];

        // 2. Se revisa si la cédula o el correo ya están registrados en la app.
        List<String[]> usuarios = FileManager.leerUsuarios();
        for (String[] u : usuarios) {
            if (u[0].equals(cedula)) { // Verifica por cédula
                return false;
            }
            // Asume que el correo está en la cuarta columna (índice 3) en el nuevo formato
            if (u.length > 3 && u[3].equalsIgnoreCase(correo)) { // El correo sigue en la misma posición
                throw new IllegalArgumentException("El correo electrónico ya está registrado.");
            }
        }

        // 3. Se hashea la contraseña.
        String hashedPassword = SecurityUtils.hashPassword(password);

        // 4. Se crea un objeto Comensal para forzar la validación del modelo.
        //    El constructor de Usuario/Comensal lanzará una IllegalArgumentException si los datos son inválidos.
        new Comensal(cedula, nombre, apellido, correo, telefono, rolUcv, hashedPassword);

        // 5. Si la validación es exitosa, se guarda el usuario.
        FileManager.guardarUsuario(cedula, nombre, apellido, correo, telefono, rolUcv, hashedPassword, RolUsuario.COMENSAL, 0.0);
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
                // Formato: cedula,nombre,apellido,correo,telefono,rolUcv,password,rol,saldo
                nuevosDatos = new String[]{
                        comensal.getCedula(),
                        comensal.getNombre(),
                        comensal.getApellido(),
                        comensal.getCorreo(),
                        comensal.getTelefono(),
                        comensal.getRolUcv(),
                        comensal.getPasswordHash(), // La contraseña ya está hasheada
                        comensal.getRol().name().toUpperCase(),
                        String.valueOf(comensal.getMonedero().getSaldo())
                };
            } else if (usuarioActualizado instanceof Administrador) {
                Administrador admin = (Administrador) usuarioActualizado;
                // Para un admin, el rolUcv siempre será N/A
                nuevosDatos = new String[]{
                        admin.getCedula(),
                        admin.getNombre(),
                        admin.getApellido(),
                        admin.getCorreo(),
                        admin.getTelefono(),
                        "N/A", // Placeholder para el rol UCV del admin
                        admin.getPasswordHash(),
                        admin.getRol().name().toUpperCase()
                        // Los admins no tienen saldo, así que no se añade la última columna.
                };
            }
            else {
                nuevosDatos = todosLosUsuarios.get(indexToUpdate);
            }
            todosLosUsuarios.set(indexToUpdate, nuevosDatos);
        }

        FileManager.reescribirUsuarios(todosLosUsuarios);
    }
}
