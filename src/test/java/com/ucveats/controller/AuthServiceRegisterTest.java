package com.ucveats.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceRegisterTest {

    private static final String TEST_FILE_PATH = "data/test_usuarios_register.txt";

    @BeforeEach
    void setUp() {
        // 1. Antes de CADA prueba, establece la ruta al archivo de prueba.
        FileManager.setFilePath(TEST_FILE_PATH);

        // 2. Asegúrate de que el archivo de prueba no exista para empezar desde cero.
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        // 3. Después de CADA prueba, borra el archivo para no afectar a la siguiente.
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testRegistrarUsuarioValido() throws IOException {
        // La prueba ahora se ejecuta en un archivo limpio gracias a setUp()
        boolean registrado = AuthService.registrar("20123456", "test@ucv.com", "04129876543", "password123");
        assertTrue(registrado, "El usuario debería haberse registrado correctamente.");
    }

    @Test
    public void testRegistrarUsuarioExistente() throws IOException {
        String cedula = "20111222";
        String correo = "duplicado@ucv.com";
        String telefono = "04161112233";
        String clave = "password123";

        // Registra primero el usuario (para simular que ya existe)
        AuthService.registrar(cedula, correo, telefono, clave);

        // Intenta registrar otra vez, debe devolver false
        boolean registradoDeNuevo = AuthService.registrar(cedula, correo, telefono, clave);
        assertFalse(registradoDeNuevo, "El usuario no debería registrarse dos veces.");
    }
}
