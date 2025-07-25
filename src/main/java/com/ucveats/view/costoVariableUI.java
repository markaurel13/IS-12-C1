package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.ucveats.controller.costoVariableService;
import java.util.Calendar;
import java.util.Date;

public class costoVariableUI extends JFrame {
    private final JTextField campoProteinas, campoCarbohidratos, campoEnergia;
    private final JComboBox<String> campoTipoBandeja;
    private final JLabel totalLabel;
    private final JDateChooser campoFecha;
    private final costoVariableService servicioCostos;

    public costoVariableUI() {
        setTitle("Registro de Costos Variables");
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#f4f6f8"));

        // Panel superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#353535"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logoUCV = new JLabel("UCVeats");
        logoUCV.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoUCV.setForeground(Color.decode("#ffffff"));

        totalLabel = new JLabel("Total: Bs. 0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        topPanel.add(logoUCV, BorderLayout.WEST);
        topPanel.add(totalLabel, BorderLayout.EAST);

        // Panel central
        JPanel central = new JPanel(null);
        central.setBackground(Color.decode("#f4f6f8"));

        JLabel titulo = new JLabel("Registrar Costos Variables");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setBounds(80, 30, 300, 25);
        central.add(titulo);

        campoProteinas = crearCampo("Proteínas:", 80, central);
        campoCarbohidratos = crearCampo("Carbohidratos:", 130, central);
        campoEnergia = crearCampo("Energía:", 180, central);

        // Creacion de JComboBox
        JLabel labelTipo = new JLabel("Tipo de Bandeja:");
        labelTipo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelTipo.setBounds(40, 280, 140, 25);
        central.add(labelTipo);

        String[] opcionesBandeja = { "Desayuno", "Almuerzo"};
        campoTipoBandeja = new JComboBox<>(opcionesBandeja);
        campoTipoBandeja.setBounds(190, 280, 150, 25);
        campoTipoBandeja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        central.add(campoTipoBandeja);

        JLabel labelFecha = new JLabel("Fecha:");
        labelFecha.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelFecha.setBounds(40, 230, 140, 25);
        central.add(labelFecha);

        campoFecha = new JDateChooser();
        campoFecha.setBounds(190, 230, 150, 25);
        campoFecha.setDateFormatString("dd/MM/yyyy"); // Formato de fecha
        Calendar maxCal = Calendar.getInstance();
        maxCal.set(2035, Calendar.JANUARY, 1); // 01/01/2035
        campoFecha.setMaxSelectableDate(maxCal.getTime());
        Calendar minCal = Calendar.getInstance();
        minCal.set(2000, Calendar.JANUARY, 1); // 01/01/2000
        campoFecha.setMinSelectableDate(minCal.getTime());
        central.add(campoFecha);

        JButton btnGuardar = new JButton("GUARDAR COSTO");
        btnGuardar.setBounds(110, 350, 180, 40);
        btnGuardar.setBackground(Color.decode("#87cc2e"));
        btnGuardar.setForeground(Color.decode("#2f3829"));
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnGuardar.addActionListener(this::guardarCostos);
        central.add(btnGuardar);
this.servicioCostos = new costoVariableService();

        add(topPanel, BorderLayout.NORTH);
        add(central, BorderLayout.CENTER);

double total = servicioCostos.getCostoVariableTotal();
totalLabel.setText(String.format("Total: Bs. %.2f", total));

        setVisible(true);
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

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        new costoVariableUI();
    }
}
