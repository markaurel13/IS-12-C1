package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class MenuAdminPanel extends JPanel { 

    private MyFrame parentFrame; // Necesitamos una referencia a MyFrame
    private BotonPanel botonVerCostosF;
    private BotonPanel botonVerCostosV;
    private BotonPanel botonCargarMenu;
    private BotonPanel botonCerrarSesion;
    private JLabel nombreLabel;
    private JLabel tipoLabel;


    public MenuAdminPanel(MyFrame frame) { 
        this.parentFrame = frame; // Guarda la referencia a la ventana principal

        // Configuraciones de este JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#ffffff"));

        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode("#2f3829")));
        
        setPreferredSize(new Dimension(200, MyFrame.DEFAULT_HEIGHT)); 
        setMaximumSize(new Dimension(200, MyFrame.DEFAULT_HEIGHT));
        
        // --- Contenido del Panel (Icono y datos de usuario) ---
        ImageIcon imagenUsuario;
        URL imageUrl = MenuUsuarioPanel.class.getResource("/user_icon.png"); 
        if (imageUrl != null) {
            Image img = new ImageIcon(imageUrl).getImage();
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
            imagenUsuario = new ImageIcon(scaledImg);
        } else {
            System.err.println("Advertencia: Imagen no encontrada en el classpath: " + "/user_icon.png");
            imagenUsuario = new ImageIcon(); 
        }

        JLabel imagenLabel = new JLabel(imagenUsuario);
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Datos de usuario de ejemplo
        nombreLabel = new JLabel("Nombre Apellido");
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nombreLabel.setForeground(Color.decode("#2f3829"));

        tipoLabel = new JLabel("Administrador");
        tipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tipoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        tipoLabel.setForeground(Color.decode("#2f3829"));


        // --- Botones del Menú ---
        botonVerCostosF = new BotonPanel("Costos Fijos", 180, 35);
        botonVerCostosV = new BotonPanel("Costos Variables", 180, 35);
        botonCargarMenu = new BotonPanel("Cargar Menú", 180, 35);
        botonCerrarSesion = new BotonPanel("Cerrar Sesión", 180, 35);

        this.add(Box.createVerticalStrut(20));
        this.add(imagenLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(nombreLabel);
        this.add(Box.createVerticalStrut(5));
        this.add(tipoLabel);
        this.add(Box.createVerticalStrut(50));
        this.add(botonVerCostosF);
        this.add(Box.createVerticalStrut(10));
        this.add(botonVerCostosV);
        this.add(Box.createVerticalStrut(10));
        this.add(botonCargarMenu);
        this.add(Box.createVerticalStrut(70));
        this.add(botonCerrarSesion);
    }

    public void setDatosUsuario(String nombre, String rol) {
        nombreLabel.setText(nombre);
        tipoLabel.setText(rol);
    }

    public void addCostosFijosListener(ActionListener listener) {
        botonVerCostosF.addActionListener(listener);
    }

    public void addCostosVariablesListener(ActionListener listener) {
        botonVerCostosV.addActionListener(listener);
    }

    public void addCargarMenuListener(ActionListener listener) {
        botonCargarMenu.addActionListener(listener);
    }
    public void addCerrarSesionListener(ActionListener listener) {
        botonCerrarSesion.addActionListener(listener);
    }

}