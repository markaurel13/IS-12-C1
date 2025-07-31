package com.ucveats.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.ucveats.model.Usuario;

import java.io.File;
import java.io.IOException;

public class AuthServiceLoginTest {
    private static final String TEST_FILE_PATH = "data/test_usuarios_login.txt";
    private final String testCedula = "20999888";
    private final String testPassword = "claveSegura123";

    @BeforeEach
    void setUp() throws IOException {
        // 1. Establece la ruta al archivo de prueba.
        FileManager.setFilePath(TEST_FILE_PATH);

        // 2. Borra el archivo anterior si existe.
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }

        // 3. Crea un usuario conocido para poder probar el login.
        AuthService.registrar(testCedula, "login@test.com", "04121112233", testPassword);
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }


    @Test
    public void testLoginValido() throws Exception {
        Usuario usuario = AuthService.login(testCedula, testPassword);
        assertNotNull(usuario, "El usuario debería poder hacer login");
        assertEquals(testCedula, usuario.getCedula());
    }

    @Test
    public void testLoginInvalido() throws Exception {
        Usuario usuario = AuthService.login(testCedula, "passwordIncorrecto");
        assertNull(usuario, "No debería permitir login con contraseña incorrecta");
    }
}
