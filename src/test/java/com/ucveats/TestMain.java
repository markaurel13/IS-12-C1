package com.ucveats.controller;

public class TestMain {
    public static void main(String[] args) {
        try {
            // Intentamos registrar un nuevo usuario comensal
            boolean registrado = AuthService.registrar(
                "12345678",                 // cédula
                "correo@example.com",       // correo válido
                "04121234567",              // teléfono válido (formato requerido)
                "claveSegura123"            // contraseña en texto plano
            );

            if (registrado) {
                System.out.println("✅ Usuario registrado correctamente.");
            } else {
                System.out.println("⚠️ El usuario ya existe en usuarios.txt");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al registrar: " + e.getMessage());
        }
    }
}
