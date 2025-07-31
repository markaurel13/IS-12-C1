/*
 * revisar linea 132 y 80 c0n la logica del .setTex
 */

package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionListener;


public class costoVariableUI extends JPanel {
    private final JTextField campoProteinas, campoCarbohidratos, campoEnergia;
    private final JComboBox<String> campoTipoBandeja;
    private final JDateChooser campoFecha;
    private final JLabel totalLabel;
    private final BotonPanel btnGuardar;
    private MyFrame parentFrame; // Referencia a la ventana principal

    public costoVariableUI(MyFrame frame) {
        this.parentFrame = frame;

        this.setLayout(null);
        this.setBackground(Color.decode("#f4f6f8"));

        totalLabel = new JLabel("Costo Actual Bs. 0.00");
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

        btnGuardar = new BotonPanel("Guardar Costos"); 
        btnGuardar.setBounds(100, 360, 180, 40);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        this.add(btnGuardar);
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

        // --- MÉTODOS PARA EL CONTROLADOR EXTERNO ---
        public String getProteinas() { return campoProteinas.getText(); }
        public String getCarbohidratos() { return campoCarbohidratos.getText(); }
        public String getEnergia() { return campoEnergia.getText(); }
        public String getTipoBandeja() { return (String) campoTipoBandeja.getSelectedItem(); }
        public Date getFecha() { return campoFecha.getDate(); }

        public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void setTotal(double total) {
        totalLabel.setText(String.format("Costo Actual Bs. %.2f", total));
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
