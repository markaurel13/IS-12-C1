package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CargarMermaUI extends JPanel {
    private final JTextField campoMerma;
    private final JLabel mermaActualLabel;
    private final BotonPanel btnGuardar;
    private final MyFrame parentFrame;

    public CargarMermaUI(MyFrame frame) {
        this.parentFrame = frame;

        // Configuraciones del panel
        this.setLayout(null);
        this.setBackground(Color.decode("#f4f6f8"));

        // Etiqueta para mostrar el valor actual de la merma
        mermaActualLabel = new JLabel("Merma Actual: 0.0%");
        mermaActualLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        mermaActualLabel.setForeground(Color.decode("#2f3829"));
        mermaActualLabel.setBounds(60, 30, 340, 30);
        this.add(mermaActualLabel);

        // Título para la sección de registro
        JLabel tituloRegistro = new JLabel("Registrar Nuevo Porcentaje de Merma");
        tituloRegistro.setFont(new Font("Montserrat", Font.BOLD, 16));
        tituloRegistro.setBounds(60, 80, 350, 25);
        this.add(tituloRegistro);

        // Campo de entrada para la merma
        campoMerma = crearCampo("Merma (%):", 130, this);

        // Botón para guardar la merma
        btnGuardar = new BotonPanel("Guardar Merma");
        btnGuardar.setBounds(100, 200, 180, 40);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        this.add(btnGuardar);
    }

    private JTextField crearCampo(String etiqueta, int y, JPanel panel) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBounds(40, y, 120, 25);
        panel.add(label);

        JTextField campo = new JTextField();
        campo.setBounds(180, y, 150, 25);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(campo);
        return campo;
    }

    // Métodos para el controlador externo
    public String getMerma() {
        return campoMerma.getText();
    }

    public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void setMermaActual(double merma) {
        mermaActualLabel.setText(String.format("Merma Actual: %.1f%%", merma));
    }
    
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
