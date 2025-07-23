package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import is12c1.model.costoVariableService;

import static org.junit.jupiter.api.Assertions.*;

class CostoVariableServiceTest {

    private CostoVariableService costoVariableService;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Crear un archivo temporal para pruebas
        tempFile = Files.createTempFile("test-datos", ".txt");
        // Redirigir FILE_PATH al archivo temporal
        System.setProperty("user.dir", tempFile.getParent().toString());
        CostoFijoService.FILE_PATH = tempFile.toString();
        costoVariableService = new CostoVariableService();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Limpiar archivo temporal
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testGuardarCostos_Exitoso() {
        costoVariableService.guardarCostos(20.0, 15.0, 10.0, "14/07/25", "Almuerzo");
        assertEquals(45.0, costoVariableService.getCostoVariableTotal(), 0.01);
        assertEquals("14/07/25", costoVariableService.getCostoVariable().getFecha());
        assertEquals("Almuerzo", costoVariableService.getCostoVariable().getTipoBandeja());
    }

    @Test
    void testGuardarCostos_CamposVacios() {
        costoVariableService.guardarCostos(20.0, 0.0, 10.0, "", "Almuerzo");
        assertEquals(0.0, costoVariableService.getCostoVariableTotal(), 0.01); // No se guarda
    }

    @Test
    void testGuardarCostos_ValoresIncoherentes() {
        costoVariableService.guardarCostos(-20.0, 15.0, 10.0, "14/07/25", "Almuerzo");
        assertEquals(0.0, costoVariableService.getCostoVariableTotal(), 0.01); // No se guarda
    }

    @Test
    void testGuardarCostos_FormatoFechaInvalido() {
        costoVariableService.guardarCostos(20.0, 15.0, 10.0, "2025-07-14", "Almuerzo");
        assertEquals(0.0, costoVariableService.getCostoVariableTotal(), 0.01); // No se guarda
    }

    @Test
    void testModificarInsumo_Exitoso() {
        costoVariableService.guardarCostos(20.0, 15.0, 10.0, "14/07/25", "Almuerzo");
        costoVariableService.modificarInsumo("proteinas", 30.0);
        assertEquals(55.0, costoVariableService.getCostoVariableTotal(), 0.01);
    }

    @Test
    void testModificarInsumo_ValoresIncoherentes() {
        costoVariableService.guardarCostos(20.0, 15.0, 10.0, "14/07/25", "Almuerzo");
        costoVariableService.modificarInsumo("proteinas", -30.0);
        assertEquals(45.0, costoVariableService.getCostoVariableTotal(), 0.01); // No se modifica
    }
}