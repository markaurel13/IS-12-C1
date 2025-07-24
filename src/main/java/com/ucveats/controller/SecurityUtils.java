package com.ucveats.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utilidades de seguridad para hashing de contraseñas con SHA-256 + salt.
 */
public class SecurityUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Genera un hash SHA-256 de una contraseña con salt.
     * @param password Contraseña en texto plano (no nula ni vacía).
     * @return String con formato "salt:hash" (ej: "abc123:4d6f6e..."). 
     * @throws IllegalArgumentException Si la contraseña es inválida.
     */
    public static String hashPassword(String password) throws IllegalArgumentException {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }

        try {
            byte[] salt = generateSalt();
            String hash = sha256(password + Base64.getEncoder().encodeToString(salt));
            return Base64.getEncoder().encodeToString(salt) + ":" + hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash", e); // Nunca debería ocurrir
        }
    }

    /**
     * Verifica si una contraseña coincide con un hash almacenado.
     * @param password Contraseña a verificar.
     * @param storedHash Hash almacenado (formato "salt:hash").
     * @return true si la contraseña es válida.
     */
    public static boolean checkPassword(String password, String storedHash) {
        if (password == null || storedHash == null || !storedHash.contains(":")) {
            return false;
        }

        try {
            String[] parts = storedHash.split(":");
            String salt = parts[0];
            String hash = sha256(password + salt);
            return hash.equals(parts[1]);
        } catch (NoSuchAlgorithmException e) {
            return false;
        }
    }

    // --- Métodos privados ---
    private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return salt;
    }

    private static String sha256(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}