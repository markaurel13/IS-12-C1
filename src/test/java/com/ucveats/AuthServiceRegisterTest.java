package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import service.AuthService;

public class AuthServiceRegisterTest {

    @Test
    void registrarExitoso() {
        // Arrange
        String cedula = "99999999"; // Usa una cédula que no exista aún en datos.txt
        String correo = "nuevo@ejemplo.com";
        String telefono = "123456789";
        String password = "passwordNuevo";

        // Act
        boolean resultado = AuthService.registrar(cedula, correo, telefono, password);

        // Assert
        assertTrue(resultado, "El registro debería ser exitoso para un nuevo usuario.");
    }

    @Test
    void registrarUsuarioExistente() {
        // Arrange
        String cedula = "12345678"; // Usa una cédula que ya exista en datos.txt
        String correo = "existente@ejemplo.com";
        String telefono = "987654321";
        String password = "passwordExistente";

        // Act
        boolean resultado = AuthService.registrar(cedula, correo, telefono, password);

        // Assert
        assertFalse(resultado, "El registro debería fallar si el usuario ya existe.");
    }

    @Test
    void registrarDatosNulos() {
        // Arrange
        String cedula = null;
        String correo = null;
        String telefono = null;
        String password = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            AuthService.registrar(cedula, correo, telefono, password);
        }, "Debe lanzar IllegalArgumentException si algún dato es nulo.");
    }
}
