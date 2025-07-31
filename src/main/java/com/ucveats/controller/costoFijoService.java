package com.ucveats.controller;

import com.ucveats.model.CostoFijo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;


public class costoFijoService {
    private CostoFijo costoFijo;
    private static final String FILE_PATH = "src/main/resources/costosFijos.txt";

    public costoFijoService() {
        this.costoFijo = cargarCostosFijos();
        if (costoFijo == null) {
            this.costoFijo = new CostoFijo(0, 0, 0); // Valores por defecto
        }
    }

    private CostoFijo cargarCostosFijos() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] partes = linea.split(",");
                return new CostoFijo(
                    Double.parseDouble(partes[0]), // manoObra
                    Double.parseDouble(partes[1]), // mantenimiento
                    Double.parseDouble(partes[2])  // alquiler
                );
            }
        } catch (IOException | NumberFormatException e) {
            // En una aplicación real, aquí se podría registrar el error en un log.
            System.err.println("Error al cargar los costos fijos: " + e.getMessage());
        }
        return null;
    }

    public void guardarCostos(double manoObra, double mantenimiento, double alquiler) throws IllegalArgumentException, IOException {
        if (manoObra < 0 || mantenimiento < 0 || alquiler < 0) {
            throw new IllegalArgumentException("Por favor ingrese un valor numérico positivo.");
        }
        if (manoObra == 0 || mantenimiento == 0 || alquiler == 0) {
            throw new IllegalArgumentException("Complete todos los campos obligatorios.");
        }
        costoFijo.setManoObra(manoObra);
        costoFijo.setMantenimiento(mantenimiento);
        costoFijo.setAlquiler(alquiler);
        guardarEnArchivo();
    }
    /**
     * Guarda los datos del modelo CostoFijo en el archivo.
     * @throws IOException si ocurre un error durante la escritura del archivo.
     */

    public void guardarEnArchivo() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String costoFijoData = String.format(Locale.US,"%.2f,%.2f,%.2f", costoFijo.getManoObra(), costoFijo.getMantenimiento(), costoFijo.getAlquiler());
            bw.write(costoFijoData);
            bw.newLine();
        } catch (IOException e) {
            // Relanzamos la excepción para que la capa superior (la vista/main) decida cómo manejarla.
            throw new IOException("Error al guardar los datos de costos fijos.", e);
        }
    }

    public double getCostoFijoTotal() {
        return costoFijo.getTotal();
    }

    public CostoFijo getCostoFijo() {
        return costoFijo;
    }
}