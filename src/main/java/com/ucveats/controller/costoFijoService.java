package com.ucveats.controller;

import com.ucveats.model.CostoFijo;
import javax.swing.JOptionPane;
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
            //JOptionPane.showMessageDialog(null, "Error al cargar los costos fijos. Se iniciará con valores por defecto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void guardarCostos(double manoObra, double mantenimiento, double alquiler) {
        if (manoObra < 0 || mantenimiento < 0 || alquiler < 0) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un valor numérico positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (manoObra == 0 || mantenimiento == 0 || alquiler == 0) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        costoFijo.setManoObra(manoObra);
        costoFijo.setMantenimiento(mantenimiento);
        costoFijo.setAlquiler(alquiler);
        guardarEnArchivo();
        JOptionPane.showMessageDialog(null,
                    "✅ ¡Costos registrados exitosamente! ✅" +
                            "\n Mano de Obra: (" + manoObra + ")" +
                            "\n Mantenimiento: (" + mantenimiento + ")" +
                            "\n Alquiler: (" + alquiler + ")"

                    , "Proceso Exitoso", JOptionPane.PLAIN_MESSAGE);
        //JOptionPane.showMessageDialog(null, "Costos fijos registrados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String costoFijoData = String.format(Locale.US,"%.2f,%.2f,%.2f", costoFijo.getManoObra(), costoFijo.getMantenimiento(), costoFijo.getAlquiler());
            bw.write(costoFijoData);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getCostoFijoTotal() {
        return costoFijo.getTotal();
    }

    public CostoFijo getCostoFijo() {
        return costoFijo;
    }
}