// FileManager.java
package com.ucveats.controller;

import com.ucveats.model.Usuario.RolUsuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la persistencia de usuarios en un archivo de texto.
 * Formato de cada línea: cedula,correo,telefono,passwordHash,rol,saldo
 */
public class FileManager {
    private static final String DEFAULT_FILE_PATH = "data/usuarios.txt";
    private static String filePath = DEFAULT_FILE_PATH;

    public static void setFilePath(String path) {
        filePath = path;
    }

    /**
     * Guarda un usuario en el archivo.
     * @param cedula Cédula del usuario.
     * @param correo Correo electrónico.
     * @param telefono Teléfono del usuario.
     * @param passwordHash Contraseña hasheada.
     * @param rol Rol del usuario (siempre COMENSAL al registrarse).
     * @param saldo Saldo inicial del monedero.
     * @throws IOException Si hay error al escribir en el archivo.
     * @throws IllegalArgumentException Si algún parámetro es inválido.
     */
    public static void guardarUsuario(String cedula, String correo, String telefono, 
                                    String passwordHash, RolUsuario rol, double saldo) 
            throws IOException, IllegalArgumentException {
        if (cedula == null || correo == null || telefono == null || passwordHash == null || rol == null) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Formato: cedula,correo,telefono,password,rol,saldo
            writer.write(String.join(",", cedula, correo, telefono, passwordHash, rol.name(), String.valueOf(saldo)));
            writer.newLine();
        }
    }

    /**
     * Lee todos los usuarios del archivo.
     * @return Lista de arrays donde cada array es [cedula, correo, telefono, passwordHash, rol, saldo].
     * @throws IOException Si hay error al leer el archivo.
     */
    public static List<String[]> leerUsuarios() throws IOException {
        List<String[]> usuarios = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) return usuarios;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                // Acepta líneas con 5 (formato antiguo) o 6 campos (nuevo formato con saldo)
                if (partes.length >= 5) {
                    usuarios.add(partes);
                }
            }
        }
        return usuarios;
    }

    /**
     * Reescribe completamente el archivo de usuarios con una lista de datos de usuario.
     * Esto es útil para actualizar o eliminar registros.
     * @param usuarios La lista completa de usuarios a escribir en el archivo.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public static void reescribirUsuarios(List<String[]> usuarios) throws IOException {
        // El 'false' en FileWriter indica que se debe sobreescribir el archivo.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (String[] usuarioData : usuarios) {
                writer.write(String.join(",", usuarioData));
                writer.newLine();
            }
        }
    }
}