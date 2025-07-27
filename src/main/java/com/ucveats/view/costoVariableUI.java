/*
 * revisar linea 132 y 80 c0n la logica del .setTex
 */

package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.ucveats.controller.costoVariableService;
import java.util.Calendar;
import java.util.Date;

public class costoVariableUI extends JPanel {
    private final JTextField campoProteinas, campoCarbohidratos, campoEnergia;
    private final JComboBox<String> campoTipoBandeja;
    private final JDateChooser campoFecha;
    private final costoVariableService servicioCostos;
    private final JLabel totalLabel;
    double totalCostosVariables = 0.0; // Inicializar el total de costos
    private MyFrame parentFrame; // Referencia a la ventana principal

    public costoVariableUI(MyFrame frame) {
        //super("Registro de Costos Variables");
        this.parentFrame = frame;

        // Panel this
        //JPanel this = new JPanel(null);
        this.setLayout(null);
        this.setBackground(Color.decode("#f4f6f8"));

        totalLabel = new JLabel("Costo Actual Bs. " + totalCostosVariables);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        totalLabel.setForeground(Color.decode("#2f3829"));
        totalLabel.setBounds(60, 30, 340, 30);
        this.add(totalLabel);

        JLabel tituloRegistro = new JLabel("Registrar Costos Variables");
        tituloRegistro.setFont(new Font("Montserrat", Font.BOLD, 16));
        tituloRegistro.setBounds(60, 80, 300, 25);
        this.add(tituloRegistro);

        campoProteinas = crearCampo("Proteínas:", 130, this);
        campoCarbohidratos = crearCampo("Carbohidratos:", 180, this);
        campoEnergia = crearCampo("Energía:", 230, this);

        // Creacion de JComboBox
        JLabel labelTipoBandeja = new JLabel("Tipo de Bandeja:");
        labelTipoBandeja.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelTipoBandeja.setBounds(40, 310, 140, 25);
        this.add(labelTipoBandeja);

        String[] opcionesBandeja = { "Desayuno", "Almuerzo"};
        campoTipoBandeja = new JComboBox<>(opcionesBandeja);
        campoTipoBandeja.setBounds(190, 310, 150, 25);
        campoTipoBandeja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        this.add(campoTipoBandeja);

        JLabel labelFecha = new JLabel("Fecha:");
        labelFecha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelFecha.setBounds(40, 260, 140, 25);
        this.add(labelFecha);

        campoFecha = new JDateChooser();
        campoFecha.setBounds(190, 260, 150, 25);
        campoFecha.setDateFormatString("dd/MM/yyyy"); // Formato de fecha
        Calendar maxCal = Calendar.getInstance();
        maxCal.set(2035, Calendar.JANUARY, 1); // 01/01/2035
        campoFecha.setMaxSelectableDate(maxCal.getTime());
        Calendar minCal = Calendar.getInstance();
        minCal.set(2000, Calendar.JANUARY, 1); // 01/01/2000
        campoFecha.setMinSelectableDate(minCal.getTime());
        this.add(campoFecha);

        BotonPanel btnGuardar = new BotonPanel("Guardar Costos");
        btnGuardar.setBounds(100, 360, 180, 40);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnGuardar.addActionListener(this::guardarCostos);
        this.add(btnGuardar);



        this.servicioCostos = new costoVariableService();
        totalCostosVariables = servicioCostos.getCostoVariableTotal();
        totalLabel.setText("Costo Actual Bs. " + totalCostosVariables);

        
        // --- Activar el botón de menú y el panel flotante ---
        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });
    }

    private JTextField crearCampo(String etiqueta, int y, JPanel panel) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setBounds(40, y, 140, 25);
        panel.add(label);

        JTextField campo = new JTextField();
        campo.setBounds(190, y, 150, 25);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(campo);
        return campo;
    }

    private void guardarCostos(ActionEvent e) {
        try {
            double proteinas = Double.parseDouble(campoProteinas.getText());
            double carbohidratos = Double.parseDouble(campoCarbohidratos.getText());
            double energia = Double.parseDouble(campoEnergia.getText());
            String tipoBandeja = (String) campoTipoBandeja.getSelectedItem();


            // Obtiene la fecha seleccionada en formato string
            Date fechaSeleccionada = campoFecha.getDate();
            if (fechaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha válida.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date fecha = fechaSeleccionada;

            servicioCostos.guardarCostos(proteinas, carbohidratos, energia, fecha, tipoBandeja);
            totalLabel.setText("Total: Bs. " + String.format("%.2f", servicioCostos.getCostoVariableTotal()));

            /*JOptionPane.showMessageDialog(this,
                    "✅ ¡Costos variables registrados exitosamente! ✅\n\n" +
                            "- Proteínas: " + proteinas +
                            "\n- Carbohidratos: " + carbohidratos +
                            "\n- Energía: " + energia +
                            "\n- Fecha: " + fecha +
                            "\n- Tipo de Bandeja: " + tipoBandeja,
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);*/

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "ERROR: Valor inválido ingresado. Intente de nuevo.",
                    "Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        new costoVariableUI().mostrarVentana();
    }*/
}
