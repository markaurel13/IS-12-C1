package service;

import modelos.Usuario.RolUsuario;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Maneja la persistencia de usuarios en un archivo de texto.
 * Formato de cada línea: email,passwordHash,rol
 */
public class FileManager {
    private static final String DEFAULT_FILE_PATH = "data/usuarios.txt";
    private static String filePath = DEFAULT_FILE_PATH;

    /**
     * Cambia la ruta del archivo de usuarios (útil para pruebas).
     */
    public static void setFilePath(String path) {
        filePath = path;
    }

    /**
     * Guarda un usuario en el archivo.
     * @param email Email válido (no nulo).
     * @param passwordHash Contraseña hasheada (no nula).
     * @param rol Rol del usuario (no nulo).
     * @throws IOException Si hay error al escribir en el archivo.
     * @throws IllegalArgumentException Si algún parámetro es inválido.
     */
    public static void guardarUsuario(String email, String passwordHash, RolUsuario rol) 
            throws IOException, IllegalArgumentException {
        if (email == null || passwordHash == null || rol == null) {
            throw new IllegalArgumentException("Email, passwordHash y rol no pueden ser nulos");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(email + "," + passwordHash + "," + rol.name()); // Usa enum.name()
            writer.newLine();
        }
    }

    /**
     * Lee todos los usuarios del archivo.
     * @return Lista de arrays donde cada array es [email, passwordHash, rol].
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
                if (partes.length == 3) {
                    usuarios.add(partes);
                }
            }
        }
        return usuarios;
    }
}