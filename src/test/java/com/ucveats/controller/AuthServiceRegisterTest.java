package com.ucveats.controller;
import com.ucveats.controller.AuthService;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;

public class AuthServiceRegisterTest {

    @Test
    public void testRegistrarUsuarioValido() throws IOException {
        String cedula = "12345678";
        String correo = "correo@example.com";
        String telefono = "04121234567";
        String password = "claveSegura123";

        boolean registrado = AuthService.registrar(cedula, correo, telefono, password);

        assertTrue(registrado, "El usuario debería haberse registrado correctamente.");
    }

    @Test
    public void testRegistrarUsuarioExistente() throws IOException {
        String cedula = "12345678";  // Supón que ya existe este usuario
        String correo = "correo@example.com";
        String telefono = "04121234567";
        String clave = "claveSegura123";

        // Registra primero el usuario (para simular que ya existe)
        AuthService.registrar(cedula, correo, telefono, clave);

        // Intenta registrar otra vez, debe devolver false
        boolean registradoDeNuevo = AuthService.registrar(cedula, correo, telefono, clave);

        assertFalse(registradoDeNuevo, "El usuario no debería registrarse dos veces.");
    }
}
