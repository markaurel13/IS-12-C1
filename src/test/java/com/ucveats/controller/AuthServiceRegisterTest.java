package com.ucveats.controller;

import java.io.BufferedWriter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceRegisterTest {

    private static final String TEST_FILE_PATH = "data/test_usuarios_register.txt";
    private static final String TEST_UCV_DB_PATH = "data/test_ucv_database.txt";

    @BeforeEach
    void setUp() throws IOException {
        // 1. Antes de CADA prueba, establece la ruta al archivo de prueba.
        FileManager.setFilePath(TEST_FILE_PATH);
        FileManager.setUcvDbPath(TEST_UCV_DB_PATH);

        // 2. Asegúrate de que el archivo de prueba no exista para empezar desde cero.
        File file = new File(TEST_FILE_PATH);
        file.delete();
        // 3. Crea una base de datos de UCV falsa para las pruebas.
        File ucvDb = new File(TEST_UCV_DB_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ucvDb))) {
            // Formato: nombre,apellido,rol,cedula
            writer.write("Maria,Test,estudiante,20123456\n");
            writer.write("Pedro,Duplicado,profesor,20111222\n");
        }
    }

    @AfterEach
    void tearDown() {
        // 4. Después de CADA prueba, borra los archivos para no afectar a la siguiente.
        File file = new File(TEST_FILE_PATH);
        file.delete();
        File ucvDb = new File(TEST_UCV_DB_PATH);
        ucvDb.delete();
    }

    @Test
    public void testRegistrarUsuarioValido() throws IOException {
        // Esta cédula SÍ está en nuestra base de datos de prueba.
        boolean registrado = AuthService.registrar("20123456", "test@ucv.com", "04129876543", "password1234");
        assertTrue(registrado, "El usuario debería haberse registrado correctamente.");
    }

    @Test
    public void testRegistrarUsuarioExistente() throws IOException {
        String cedula = "20111222";
        String correo = "duplicado@ucv.com";
        String telefono = "04161112233";
        String clave = "password1234";

        // Registra primero el usuario (para simular que ya existe)
        AuthService.registrar(cedula, correo, telefono, clave);

        // Intenta registrar otra vez, debe devolver false
        boolean registradoDeNuevo = AuthService.registrar(cedula, correo, telefono, clave);
        assertFalse(registradoDeNuevo, "El usuario no debería registrarse dos veces.");
    }

    @Test
    public void testRegistrarUsuarioNoPertenecienteAUcv() {
        // Esta cédula NO está en nuestra base de datos de prueba.
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            AuthService.registrar("99999999", "externo@mail.com", "04121234567", "passwordValido");
        });
        assertEquals("La cédula no pertenece a la comunidad UCV.", exception.getMessage());
    }
}
