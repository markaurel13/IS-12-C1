package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class seleccionInicio extends JPanel {
    private BotonPanel botonIniciarSesion;
    private BotonPanel botonPagarServicio;

    public seleccionInicio(MyFrame frame) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#ffffff"));
        this.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20)); // márgenes

        // Logo UCV
        JLabel logoLabel = new JLabel();
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        URL logoURL = getClass().getResource("/logo_ucv.png");
        if (logoURL != null) {
            ImageIcon icon = new ImageIcon(logoURL);
            Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            logoLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            logoLabel.setText("Logo UCV no encontrado");
            logoLabel.setForeground(Color.RED);
        }

        // Título
        JLabel titulo = new JLabel("Bienvenido a UCVeats");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.decode("#2f3829"));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Botones
        botonIniciarSesion = new BotonPanel("Iniciar Sesión", 220, 40);
        botonIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);

        botonPagarServicio = new BotonPanel("Pagar Servicio", 220, 40);
        botonPagarServicio.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Agregar componentes
        this.add(Box.createVerticalGlue());
        this.add(logoLabel);
        this.add(titulo);
        this.add(Box.createVerticalStrut(20));
        this.add(botonIniciarSesion);
        this.add(Box.createVerticalStrut(15));
        this.add(botonPagarServicio);
        this.add(Box.createVerticalGlue());
    }

    public void addLoginFlowListener(ActionListener listener) {
        botonIniciarSesion.addActionListener(listener);
    }

    public void addPaymentFlowListener(ActionListener listener) {
        botonPagarServicio.addActionListener(listener);
    }
}
