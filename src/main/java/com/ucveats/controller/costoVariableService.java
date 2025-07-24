

package com.ucveats.controller;

import com.ucveats.model.CostoVariable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import java.util.Locale;

public class costoVariableService {
    private CostoVariable costoVariable;
    private static final String FILE_PATH = "src/main/resources/costosVariables.txt";

    public costoVariableService() {
        this.costoVariable = cargarCostosVariables();
        if (costoVariable == null) {
            this.costoVariable = new CostoVariable(0, 0, 0, null, "");
        }
    }

private CostoVariable cargarCostosVariables() {
    try (BufferedReader br = new BufferedReader(new FileReader(costoVariableService.FILE_PATH))) {
        String linea = br.readLine();
        
        if (linea != null) {
            String[] partes = linea.split(",");
                
                if (partes.length >= 5) {
                    return new CostoVariable(
                            Double.parseDouble(partes[0]),
                            Double.parseDouble(partes[1]),
                            Double.parseDouble(partes[2]),
                            new SimpleDateFormat("dd/MM/yyyy").parse(partes[3]), // fecha
                            partes[4]  // tipoBandeja
                    );
                }

        }
    } catch (IOException | NumberFormatException | ParseException e)
 {
        /*JOptionPane.showMessageDialog(null, 
            "❌ Error al cargar los costos variables.\nSe iniciará con valores por defecto.", 
            "Error", JOptionPane.ERROR_MESSAGE); */
    }
    return null;
}

public void guardarCostos(double proteinas, double carbohidratos, double energia, Date fecha,
        String tipoBandeja) {
    if (proteinas < 0 || carbohidratos < 0 || energia < 0) {
        JOptionPane.showMessageDialog(null, "Por favor ingrese un valor numérico positivo.", "Error",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (fecha == null || tipoBandeja.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios.", "Error",
                JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validación de formato y límites de fecha
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    sdf.setLenient(false); // para que no acepte cosas como 32/01/2024


    Date fechaMinima;
    Date fechaMaxima;
    try {

        fechaMinima = sdf.parse("01/01/2000");
        fechaMaxima = sdf.parse("01/01/2035");

        if (fecha.before(fechaMinima) || fecha.after(fechaMaxima)) {
            JOptionPane.showMessageDialog(null, "La fecha debe estar entre 01/01/2000 y 01/01/2035.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    } catch (ParseException e) {
        JOptionPane.showMessageDialog(null, "La fecha ingresada no tiene un formato válido (dd/MM/yyyy).", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Si pasó todas las validaciones
    costoVariable.setProteinas(proteinas);
    costoVariable.setCarbohidratos(carbohidratos);
    costoVariable.setEnergia(energia);
    costoVariable.setFecha(fecha);
    costoVariable.setTipoBandeja(tipoBandeja);
    this.guardarEnArchivo();
    JOptionPane.showMessageDialog(null, "Costos variables registrados correctamente.", "Éxito",
            JOptionPane.INFORMATION_MESSAGE);
}

    public void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy").format(costoVariable.getFecha());

            String costoFijoData = String.format(Locale.US, "%.2f,%.2f,%.2f,%s,%s", costoVariable.getProteinas(),
                    costoVariable.getCarbohidratos(), costoVariable.getEnergia(), fechaFormateada
                    , costoVariable.getTipoBandeja());
            bw.write(costoFijoData);
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double getCostoVariableTotal() {
        return costoVariable.getTotal();
    }

    public CostoVariable getCostoVariable() {
        return costoVariable;
    }
}