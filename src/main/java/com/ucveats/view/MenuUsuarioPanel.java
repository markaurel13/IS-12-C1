package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MenuUsuarioPanel extends JPanel {

    private MyFrame parentFrame; // Necesitamos una referencia a MyFrame

    public MenuUsuarioPanel(MyFrame frame) { 
        this.parentFrame = frame; // Guarda la referencia a la ventana principal

        // Configuraciones de este JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#ffffff"));

        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode("#2f3829"))); // Borde izquierdo
        
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
        String tipoUsuario = "Tipo Prueba", nombreUsuario = "Nombre", apelldoUsuario = "Apellido";
        JLabel nombreLabel = new JLabel(nombreUsuario + " " + apelldoUsuario);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nombreLabel.setForeground(Color.decode("#2f3829"));

        JLabel tipoLabel = new JLabel(tipoUsuario);
        tipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tipoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        tipoLabel.setForeground(Color.decode("#2f3829"));

        // --- Botones del Menú ---
        BotonPanel botonVerMenu = new BotonPanel("Ver Menu", 180, 35, e -> {
            this.setVisible(false); // Oculta este panel flotante

            parentFrame.setContentPanel(new verMenuInterface(parentFrame)); // Cambia el contenido de MyPanel
            System.out.println("Navegando a Ver Menú...");
        });

        BotonPanel botonVerMonedero = new BotonPanel("Ver Monedero", 180, 35, e -> {
            this.setVisible(false); // Oculta este panel flotante

            parentFrame.setContentPanel(new MonederoUI(parentFrame)); // Cambia el contenido de MyPanel
            System.out.println("Navegando a Ver Monedero...");
        });

        BotonPanel botonCerrarSesion = new BotonPanel("Cerrar Sesión", 180, 35, e -> {
            this.setVisible(false); // Oculta este panel flotante

            parentFrame.setContentPanel(new inicioSesionInterface(parentFrame)); // Vuelve a la pantalla de inicio de sesión
            parentFrame.removeMenuButton(); // Quita el botón de menú del topPanel
            System.out.println("Cerrando Sesión...");
        });

        this.add(Box.createVerticalStrut(20));
        this.add(imagenLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(nombreLabel);
        this.add(Box.createVerticalStrut(5));
        this.add(tipoLabel);
        this.add(Box.createVerticalStrut(50));
        this.add(botonVerMenu);
        this.add(Box.createVerticalStrut(10));
        this.add(botonVerMonedero);
        this.add(Box.createVerticalStrut(80));
        this.add(botonCerrarSesion);
    }
    
}