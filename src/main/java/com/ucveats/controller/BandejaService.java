package com.ucveats.controller;

import com.ucveats.model.Bandeja;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Servicio para manejar la lógica de negocio y persistencia de las bandejas del menú.
 */
public class BandejaService {
    private List<Bandeja> bandejas;
    private static final String FILE_PATH = "data/bandejas.txt";

    public BandejaService() {
        this.bandejas = cargarBandejas();
    }

    /**
     * Carga todas las bandejas desde el archivo de texto.
     * @return Una lista de objetos Bandeja.
     */
    private List<Bandeja> cargarBandejas() {
        List<Bandeja> lista = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return lista; // Devuelve lista vacía si el archivo no existe
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",", 5); // Limita a 5 partes para que la descripción no se rompa si contiene comas
                if (partes.length == 5) {
                    Bandeja bandeja = new Bandeja(partes[0], partes[1], Double.parseDouble(partes[2]), partes[3], partes[4]);
                    lista.add(bandeja);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al cargar las bandejas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Guarda la lista completa de bandejas en el archivo, sobreescribiendo el contenido anterior.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    private void guardarTodasLasBandejas() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, false))) { // false para sobreescribir
            for (Bandeja bandeja : bandejas) {
                String linea = String.format(Locale.US, "%s,%s,%.2f,%s,%s",
                        bandeja.getID(),
                        bandeja.getNombreBandeja(),
                        bandeja.getCosto(),
                        bandeja.getFecha(),
                        bandeja.getDescripcionBandeja());
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    /**
     * Devuelve una copia de la lista de bandejas.
     * @return Lista de todas las bandejas.
     */
    public List<Bandeja> getBandejas() {
        return new ArrayList<>(this.bandejas);
    }

    /**
     * Crea una nueva bandeja, le asigna un ID único, la añade a la lista y guarda los cambios.
     * @param nombre Nombre del plato.
     * @param costo Costo del plato.
     * @param fecha Fecha en formato "dd/MM/yyyy".
     * @param descripcion Descripción del plato.
     * @throws IOException Si ocurre un error al guardar.
     */
    public void crearBandeja(String nombre, double costo, String fecha, String descripcion) throws IOException {
        int maxId = 0;
        for (Bandeja b : bandejas) {
            maxId = Math.max(maxId, Integer.parseInt(b.getID()));
        }
        String nuevoId = String.format("%05d", maxId + 1);
        Bandeja nuevaBandeja = new Bandeja(nuevoId, nombre, costo, fecha, descripcion);
        this.bandejas.add(nuevaBandeja);
        guardarTodasLasBandejas();
    }
}

