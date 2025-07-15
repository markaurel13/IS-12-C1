package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import is12c1.model.costoFijoService;

import static org.junit.jupiter.api.Assertions.*;

class CostoFijoServiceTest {

    private CostoFijoService costoFijoService;
    private Path tempFile;

    @BeforeEach
    void setUp() throws IOException {
        // Crear un archivo temporal para pruebas
        tempFile = Files.createTempFile("test-datos", ".txt");
        // Redirigir FILE_PATH al archivo temporal
        System.setProperty("user.dir", tempFile.getParent().toString());
        CostoFijoService.FILE_PATH = tempFile.toString();
        costoFijoService = new CostoFijoService();
    }

    @AfterEach
    void tearDown() throws IOException {
        // Limpiar archivo temporal
        Files.deleteIfExists(tempFile);
    }

    @Test
    void testGuardarCostos_Exitoso() {
        costoFijoService.guardarCostos(100.0, 50.0, 30.0);
        assertEquals(180.0, costoFijoService.getCostoFijoTotal(), 0.01);
    }

    @Test
    void testGuardarCostos_CamposVacios() {
        costoFijoService.guardarCostos(0.0, 50.0, 30.0);
        assertEquals(0.0, costoFijoService.getCostoFijoTotal(), 0.01); // No se guarda
    }

    @Test
    void testGuardarCostos_ValoresIncoherentes() {
        costoFijoService.guardarCostos(-100.0, 50.0, 30.0);
        assertEquals(0.0, costoFijoService.getCostoFijoTotal(), 0.01); // No se guarda
    }

    @Test
    void testActualizarCostosFijos_Exitoso() {
        costoFijoService.guardarCostos(100.0, 50.0, 30.0); // Registro inicial
        costoFijoService.actualizarCostosFijos(200.0, 100.0, 60.0);
        assertEquals(360.0, costoFijoService.getCostoFijoTotal(), 0.01);
    }

    @Test
    void testActualizarCostosFijos_CamposVacios() {
        costoFijoService.guardarCostos(100.0, 50.0, 30.0); // Registro inicial
        costoFijoService.actualizarCostosFijos(0.0, 100.0, 60.0);
        assertEquals(180.0, costoFijoService.getCostoFijoTotal(), 0.01); // No se actualiza
    }

    @Test
    void testActualizarCostosFijos_ValoresIncoherentes() {
        costoFijoService.guardarCostos(100.0, 50.0, 30.0); // Registro inicial
        costoFijoService.actualizarCostosFijos(-200.0, 100.0, 60.0);
        assertEquals(180.0, costoFijoService.getCostoFijoTotal(), 0.01); // No se actualiza
    }
}
