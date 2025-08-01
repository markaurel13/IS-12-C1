package com.ucveats.view;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JPanel {

    private MyFrame parentFrame;
    private JLabel labelTitulo;
    private JLabel labelSubtitulo;

    public HomePage(MyFrame frame) {
        this.parentFrame = frame;
        this.setBackground(Color.decode("#f5f8fa")); // Fondo suave

        // Panel intermedio para centrar vertical y horizontal
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(Color.decode("#f5f8fa"));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Logo
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/logo_ucv.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelCentral.add(logoLabel);

        // Título
        labelTitulo = new JLabel("Bienvenido Usuario");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        labelTitulo.setForeground(Color.decode("#2f3e51"));
        panelCentral.add(labelTitulo);

        // Subtítulo
        labelSubtitulo = new JLabel("¡Gestiona con eficiencia y sabor!");
        labelSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        labelSubtitulo.setForeground(Color.decode("#5f6c7b"));
        labelSubtitulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panelCentral.add(labelSubtitulo);

        // Botón "Ver Menú"
        JButton btnVerMenu = new JButton("Ver Menú");
        btnVerMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnVerMenu.setBackground(Color.decode("#87cc2e"));
        btnVerMenu.setForeground(Color.decode("#2f3829"));
        btnVerMenu.setFocusPainted(false);
        btnVerMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btnVerMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVerMenu.addActionListener(e -> parentFrame.alternarMenuLateral()); // <-- llama al método del frame

        // Espaciado y agregado al panel
        panelCentral.add(Box.createVerticalStrut(20));
        panelCentral.add(btnVerMenu);


        this.setLayout(new GridBagLayout());
        this.add(panelCentral);
    }

    public void setNombreUsuario(String nombre) {
        labelTitulo.setText("Bienvenido " + nombre);
    }
}

