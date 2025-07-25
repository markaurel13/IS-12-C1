package com.ucveats.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.ucveats.model.Usuario;

public class AuthServiceLoginTest {

    @Test
    public void testLoginValido() throws Exception {
        String cedula = "12345678";
        String password = "claveSegura123";

        Usuario usuario = AuthService.login(cedula, password);
        assertNotNull(usuario, "El usuario debería poder hacer login");
        assertEquals("12345678", usuario.getCedula());
    }

    @Test
    public void testLoginInvalido() throws Exception {
        String cedula = "12345678";
        String password = "passwordIncorrecto";

        Usuario usuario = AuthService.login(cedula, password);
        assertNull(usuario, "No debería permitir login con contraseña incorrecta");
    }
}
