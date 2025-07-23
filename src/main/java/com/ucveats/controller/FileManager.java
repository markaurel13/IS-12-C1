// FileManager.java
package com.ucveats.controller;

import com.ucveats.model.Usuario.RolUsuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la persistencia de usuarios en un archivo de texto.
 * Nuevo formato de cada línea: cedula,correo,telefono,passwordHash,rol
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
     * @throws IOException Si hay error al escribir en el archivo.
     * @throws IllegalArgumentException Si algún parámetro es inválido.
     */
    public static void guardarUsuario(String cedula, String correo, String telefono, 
                                    String passwordHash, RolUsuario rol) 
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
            writer.write(cedula + "," + correo + "," + telefono + "," + passwordHash + "," + rol.name());
            writer.newLine();
        }
    }

    /**
     * Lee todos los usuarios del archivo.
     * @return Lista de arrays donde cada array es [cedula, correo, telefono, passwordHash, rol].
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
                if (partes.length == 5) {
                    usuarios.add(partes);
                }
            }
        }
        return usuarios;
    }
}