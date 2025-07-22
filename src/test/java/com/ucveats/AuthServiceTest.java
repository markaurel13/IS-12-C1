package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import service.AuthService;
import modelos.Usuario;

public class AuthServiceTest {

    @Test
    void loginExitoso() {
        // Arrange
        String cedula = "12345678";  // Usa una cédula válida que exista en tu datos.txt
        String password = "passwordValido"; // Usa la contraseña correcta

        // Act
        Usuario usuario = AuthService.login(cedula, password);

        // Assert
        assertNotNull(usuario, "El login debería ser exitoso y retornar un Usuario.");
        assertEquals(cedula, usuario.getCedula());
    }

    @Test
    void loginCredencialesInvalidas() {
        // Arrange
        String cedula = "12345678";  // Usa una cédula válida
        String password = "passwordIncorrecto";

        // Act
        Usuario usuario = AuthService.login(cedula, password);

        // Assert
        assertNull(usuario, "El login debería fallar y retornar null si la contraseña es incorrecta.");
    }

    @Test
    void loginFaltanCredenciales() {
        // Arrange
        String cedula = null;
        String password = "";

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            AuthService.login(cedula, password);
        }, "Debe lanzar IllegalArgumentException si faltan credenciales.");
    }
}
