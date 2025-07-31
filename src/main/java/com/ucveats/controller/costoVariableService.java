package com.ucveats.controller;

import com.ucveats.model.CostoVariable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class costoVariableService {
    private CostoVariable costoVariable;
    private static final String FILE_PATH = "src/main/resources/costosVariables.txt";

    public costoVariableService() {
        this.costoVariable = cargarCostosVariables();
        if (costoVariable == null) {
            this.costoVariable = new CostoVariable(0, 0, 0, LocalDate.now(), "");
        }
    }

    private CostoVariable cargarCostosVariables() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 5) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    return new CostoVariable(
                        Double.parseDouble(partes[0]),    // proteinas
                        Double.parseDouble(partes[1]),    // carbohidratos
                        Double.parseDouble(partes[2]),    // energia
                        LocalDate.parse(partes[3], formatter), // fecha
                        partes[4]                         // tipoBandeja
                    );
                }
            }
        } catch (Exception e) { // Captura más genérica para parseo de fecha también
            System.err.println("Error al cargar los costos variables: " + e.getMessage());
        }
        return null;
    }

    public void guardarCostos(double proteinas, double carbohidratos, double energia, LocalDate fecha,
            String tipoBandeja) throws IllegalArgumentException, IOException {
        if (proteinas < 0 || carbohidratos < 0 || energia < 0) {
            throw new IllegalArgumentException("Por favor ingrese un valor numérico positivo.");
        }

        if (fecha == null || tipoBandeja == null || tipoBandeja.trim().isEmpty()) {
            throw new IllegalArgumentException("Complete todos los campos obligatorios.");
        }

        // Validación de límites de fecha usando java.time
        LocalDate fechaMinima = LocalDate.of(2000, 1, 1);
        LocalDate fechaMaxima = LocalDate.of(2035, 1, 1);
        
        if (fecha.isBefore(fechaMinima) || fecha.isAfter(fechaMaxima)) {
            throw new IllegalArgumentException("La fecha debe estar entre 01/01/2000 y 01/01/2035.");
        }

        // Si pasó todas las validaciones
        costoVariable.setProteinas(proteinas);
        costoVariable.setCarbohidratos(carbohidratos);
        costoVariable.setEnergia(energia);
        costoVariable.setFecha(fecha);
        costoVariable.setTipoBandeja(tipoBandeja);
        this.guardarEnArchivo();
    }

    /**
     * Guarda los datos del modelo CostoVariable en el archivo como una nueva línea.
     * @throws IOException si ocurre un error durante la escritura del archivo.
     */
    public void guardarEnArchivo() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) { // true para modo append
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String fechaFormateada = costoVariable.getFecha().format(formatter);
            String costoVariableData = String.format(Locale.US, "%.2f,%.2f,%.2f,%s,%s",
                costoVariable.getProteinas(),
                costoVariable.getCarbohidratos(),
                costoVariable.getEnergia(),
                fechaFormateada,
                costoVariable.getTipoBandeja());
            bw.write(costoVariableData); // Escribe la nueva línea
            bw.newLine(); // Añade un salto de línea
        } catch (IOException e) {
            throw new IOException("Error al guardar los datos de costos variables.", e);
        }
    }

    public double getCostoVariableTotal() {
        return costoVariable.getTotal();
    }

    public CostoVariable getCostoVariable() {
        return costoVariable;
    }
}