package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class inicioSesionInterface extends JPanel { 

    private MyFrame parentFrame; 
    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private BotonPanel botonIniciarSesion;
    private BotonPanel botonRegistrarse;


    public inicioSesionInterface(MyFrame frame) { 
        this.parentFrame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        this.setBackground(Color.decode("#ffffff"));

        // --- Componentes de la interfaz de inicio de sesión ---
        JLabel labelTitulo = new JLabel("Bienvenido a UCVeats");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JLabel labelUsuario = new JLabel("Ingrese su usuario:");
        labelUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelUsuario.setForeground(Color.decode("#2f3829"));

        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoUsuario.setMaximumSize(new Dimension(300, 30));

        JLabel labelContrasena = new JLabel("Ingrese su contraseña:");
        labelContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelContrasena.setForeground(Color.decode("#2f3829"));

        campoContrasena = new JPasswordField();
        campoContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoContrasena.setMaximumSize(new Dimension(300, 30));

        botonIniciarSesion = new BotonPanel("Iniciar Sesión", 220, 40);
        botonIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonIniciarSesion.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));

        JLabel sinCuenta = new JLabel("¿No tienes cuenta? Regístrate aquí");
        sinCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        sinCuenta.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        sinCuenta.setForeground(Color.decode("#2f3829"));
        sinCuenta.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        botonRegistrarse = new BotonPanel("Registrarse", 200, 35);
        botonRegistrarse.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrarse.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 1, true));

        this.add(labelTitulo);
        this.add(Box.createVerticalStrut(10));
        this.add(labelUsuario);
        this.add(campoUsuario);
        this.add(Box.createVerticalStrut(10));
        this.add(labelContrasena);
        this.add(campoContrasena);
        this.add(Box.createVerticalStrut(20));
        this.add(botonIniciarSesion);
        this.add(Box.createVerticalStrut(10));
        this.add(sinCuenta);
        this.add(botonRegistrarse);

    }

    // --- MÉTODOS PARA EL CONTROLADOR EXTERNO ---

    public String getCedula() {
        return campoUsuario.getText();
    }

    public String getPassword() {
        return new String(campoContrasena.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        botonIniciarSesion.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        botonRegistrarse.addActionListener(listener);
    }

    public void limpiarCampos() {
        campoUsuario.setText("");
        campoContrasena.setText("");
    }
}