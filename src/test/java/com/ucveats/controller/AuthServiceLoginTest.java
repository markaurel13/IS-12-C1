package com.ucveats.controller;

import com.ucveats.model.Comensal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.ucveats.model.Usuario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AuthServiceLoginTest {
    private static final String TEST_FILE_PATH = "data/test_usuarios_login.txt";
    private final String testCedula = "20999888";
    private final String testPassword = "claveSegura123";
    private final double testSaldo = 123.45;


    @BeforeEach
    void setUp() throws IOException {
        // 1. Establece la ruta al archivo de prueba.
        FileManager.setFilePath(TEST_FILE_PATH);

        // 2. Borra el archivo anterior si existe.
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }

        // 3. Crea un usuario de prueba directamente en el archivo.
        //    Esto nos da control total sobre los datos, incluyendo el saldo.
        String hashedPassword = SecurityUtils.hashPassword(testPassword);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE_PATH))) {
            // Formato: cedula,correo,telefono,password,rol,saldo
            String testUserLine = String.join(",", testCedula, "login@test.com", "04121112233", hashedPassword, "COMENSAL", String.valueOf(testSaldo));
            writer.write(testUserLine);
            writer.newLine();
        }
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

    @Test
    public void testLoginCargaSaldoCorrectamente() throws Exception {
        Usuario usuario = AuthService.login(testCedula, testPassword);
        assertNotNull(usuario, "El usuario debería poder iniciar sesión.");
        assertTrue(usuario instanceof Comensal, "El usuario debería ser de tipo Comensal.");

        Comensal comensal = (Comensal) usuario;
        assertEquals(testSaldo, comensal.getMonedero().getSaldo(), 0.001,
                "El saldo del monedero no se cargó correctamente desde el archivo.");
    }
}
