package com.ucveats.view;
import javax.swing.*;
import java.awt.*;

//este import se coloca unicamente para cablear el codigo y que los botones cambein de ventana
//import main.java.com.ucveats.view.registroInterface;

public class inicioSesionInterface extends MyFrame {


    public inicioSesionInterface() {
        
        super("Inicio de Sesión UCVeats");
    
        // Panel Central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.decode("#ffffff"));

        JLabel labelTitulo = new JLabel("Bienvenido a UCVeats");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JLabel labelUsuario = new JLabel("Ingrese su usuario:");
        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelUsuario.setForeground(Color.decode("#2f3829"));

        JTextField campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoUsuario.setMaximumSize(new Dimension(300, 30));

        JLabel labelContrasena = new JLabel("Ingrese su contraseña:");
        labelContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelContrasena.setForeground(Color.decode("#2f3829"));

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoContrasena.setMaximumSize(new Dimension(300, 30));

        BotonPanel botonIniciarSesion = new BotonPanel("Iniciar Sesión", 220, 40);
        botonIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonIniciarSesion.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        botonIniciarSesion.addActionListener(e -> JOptionPane.showMessageDialog(null, "✅ ¡Sesión iniciada!"));

        JLabel sinCuenta = new JLabel("¿No tienes cuenta? Regístrate aquí");
        sinCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        sinCuenta.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        sinCuenta.setForeground(Color.decode("#2f3829"));
        sinCuenta.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        BotonPanel botonRegistrarse = new BotonPanel("Registrarse", 200, 35);
        botonRegistrarse.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrarse.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 1, true));
        botonRegistrarse.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(botonRegistrarse)).dispose(); // Cierra la ventana actual
            com.ucveats.view.registroInterface.main(null);; // Muestra la ventana de registro
            JOptionPane.showMessageDialog(null, "¡Bienvenido a la ventana de registro!");
        });

        centerPanel.add(labelTitulo);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelUsuario);
        centerPanel.add(campoUsuario);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelContrasena);
        centerPanel.add(campoContrasena);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(botonIniciarSesion);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(sinCuenta);
        centerPanel.add(botonRegistrarse);

        getMyPanel().add(centerPanel, BorderLayout.CENTER);

    }

    //clase para llamar al main desde registro de usuario, se coloca unucamente para cablear el codigo y que los botones cambein de ventana
    public static void main(String[] args) {
        // no se que hace esta verga
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        new inicioSesionInterface().mostrarVentana();

    }
}
