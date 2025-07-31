// FileManager.java
package com.ucveats.controller;

import com.ucveats.model.Usuario.RolUsuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la persistencia de usuarios en un archivo de texto.
 * Formato de cada línea: cedula,nombre,apellido,correo,telefono,rolUcv,passwordHash,rol,saldo
 */
public class FileManager {
    private static final String DEFAULT_FILE_PATH = "data/usuarios.txt";
    private static final String DEFAULT_UCV_DB_PATH = "data/ucv_database.txt";

    private static String filePath = DEFAULT_FILE_PATH;
    private static String ucvDbPath = DEFAULT_UCV_DB_PATH;

    public static void setFilePath(String path) {
        filePath = path;
    }

    public static void setUcvDbPath(String path) {
        ucvDbPath = path;
    }

    /**
     * Guarda un usuario en el archivo.
     * @param nombre Nombre del usuario.
     * @param apellido Apellido del usuario.
     * @param cedula Cédula del usuario.
     * @param correo Correo electrónico.
     * @param rolUcv Rol específico de la UCV (estudiante, profesor, etc.).
     * @param telefono Teléfono del usuario.
     * @param passwordHash Contraseña hasheada.
     * @param rol Rol del usuario.
     * @param saldo Saldo inicial del monedero.
     * @throws IOException Si hay error al escribir en el archivo.
     * @throws IllegalArgumentException Si algún parámetro es inválido.
     */
    public static void guardarUsuario(String cedula, String nombre, String apellido, String correo, String telefono, String rolUcv,
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
            // Formato: cedula,nombre,apellido,correo,telefono,rolUcv,password,rol,saldo
            writer.write(String.join(",", cedula, nombre, apellido, correo, telefono, rolUcv, passwordHash, rol.name(), String.valueOf(saldo)));
            writer.newLine();
        }
    }

    /**
     * Lee todos los usuarios del archivo.
     * @return Lista de arrays donde cada array es [cedula, nombre, apellido, correo, telefono, rolUcv, passwordHash, rol, saldo].
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
                // Acepta líneas con el formato nuevo (9 campos) o antiguo para retrocompatibilidad
                if (partes.length >= 8) {
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

    /**
     * Busca una cédula en la base de datos general de la UCV.
     * @param cedula La cédula a buscar.
     * @return Un array con [nombre, apellido, rol_ucv] si se encuentra, o null si no.
     * @throws IOException Si hay un error leyendo el archivo.
     */
    public static String[] buscarEnUcvDb(String cedula) throws IOException {
        File file = new File(ucvDbPath);
        if (!file.exists()) {
            // Si el archivo no existe, nadie puede registrarse.
            // En una app real, esto podría ser un error crítico.
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(","); // formato: nombre,apellido,rol,cedula
                if (partes.length == 4 && partes[3].equals(cedula)) {
                    return new String[]{partes[0], partes[1], partes[2]}; // Devuelve [nombre, apellido, rol_ucv]
                }
            }
        }
        return null; // No se encontró la cédula
    }
}