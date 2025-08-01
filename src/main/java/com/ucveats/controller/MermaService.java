package com.ucveats.controller;

import com.ucveats.model.Merma;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class mermaService {
    private Merma merma;
    private static final String FILE_PATH = "src/main/resources/merma.txt";

    public mermaService() {
        this.merma = cargarMerma();
        if (this.merma == null) {
            this.merma = new Merma(0); // Valor por defecto
        }
    }

    private Merma cargarMerma() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea = br.readLine();
            if (linea != null) {
                return new Merma(Double.parseDouble(linea));
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar la merma: " + e.getMessage());
        }
        return null;
    }

    public void guardarMerma(double nuevaMerma) throws IllegalArgumentException, IOException {
        if (nuevaMerma < 0 || nuevaMerma > 100) {
            throw new IllegalArgumentException("Valor inválido. Debe ser un porcentaje en el rango del 0% al 100%");
        }
        merma.setMerma(nuevaMerma);
        guardarEnArchivo();
    }

    private void guardarEnArchivo() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(String.format(Locale.US, "%.2f", merma.getMerma()));
        } catch (IOException e) {
            throw new IOException("Error al guardar el valor de la merma.", e);
        }
    }

    public double getMerma() {
        return merma.getMerma();
    }
}