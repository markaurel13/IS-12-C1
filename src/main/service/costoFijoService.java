package main.service;

import is12c1.model.costoFijo;
import javax.swing.JOptionPane;

public class CostoVariableService {
    private CostoVariable costoVariable;
    private CostoFijoService costoFijoService;

    public CostoVariableService() {
        this.costoFijoService = new CostoFijoService();
        this.costoVariable = cargarCostosVariables();
        if (costoVariable == null) {
            this.costoVariable = new CostoVariable(0, 0, 0, "", "");
        }
    }

    private CostoVariable cargarCostosVariables() {
        try (BufferedReader br = new BufferedReader(new FileReader(CostoFijoService.FILE_PATH))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] partes = linea.split("\\|")[1].split(",");
                return new CostoVariable(
                    Double.parseDouble(partes[0]),
                    Double.parseDouble(partes[1]),
                    Double.parseDouble(partes[2]),
                    partes[3], // fecha
                    partes[4]  // tipoBandeja
                );
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los costos variables. Se iniciará con valores por defecto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void guardarCostos(double proteinas, double carbohidratos, double energia, String fecha, String tipoBandeja) {
        if (proteinas < 0 || carbohidratos < 0 || energia < 0) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese un valor numérico positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (proteinas + carbohidratos + energia == 0 || fecha.isEmpty() || tipoBandeja.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{2}")) {
            JOptionPane.showMessageDialog(null, "La fecha debe estar en formato DD/MM/YY.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        costoVariable.setProteinas(proteinas);
        costoVariable.setCarbohidratos(carbohidratos);
        costoVariable.setEnergia(energia);
        costoVariable.setFecha(fecha);
        costoVariable.setTipoBandeja(tipoBandeja);
        costoFijoService.guardarEnArchivo();
        JOptionPane.showMessageDialog(null, "Costos variables registrados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void modificarInsumo(String tipo, double nuevoValor) {
        if (nuevoValor < 0) {
            JOptionPane.showMessageDialog(null, "El valor debe ser positivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        switch (tipo.toLowerCase()) {
            case "proteinas": costoVariable.setProteinas(nuevoValor); break;
            case "carbohidratos": costoVariable.setCarbohidratos(nuevoValor); break;
            case "energia": costoVariable.setEnergia(nuevoValor); break;
            default:
                JOptionPane.showMessageDialog(null, "Tipo de insumo no válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }
        costoFijoService.guardarEnArchivo();
        JOptionPane.showMessageDialog(null, "Insumo modificado correctamente. Costo variable total actualizado: " + String.format("%.2f", getCostoVariableTotal()), "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public double getCostoVariableTotal() {
        return costoVariable.getTotal();
    }

    public CostoVariable getCostoVariable() {
        return costoVariable;
    }
}
