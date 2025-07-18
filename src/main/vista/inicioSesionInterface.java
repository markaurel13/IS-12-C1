package main.vista;
import main.vista.MyFrame;
import javax.swing.*;
import java.awt.Font;

public class inicioSesionInterface {
    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Inicio de Sesión UCV Eats");

        frame.getMyPanel().setLayout(new BoxLayout(frame.getMyPanel(), BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Bienbenido a UCV Eats");
        labelTitulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel labelUsuario = new JLabel("Ingrese su usuario: ");
        labelUsuario.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        labelUsuario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        campoUsuario.setMaximumSize(new java.awt.Dimension(400, 30)); 
        
        JLabel labelContrasena = new JLabel("Ingrese su contraseña: ");
        labelContrasena.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelContrasena.setFont(new Font("Arial", Font.BOLD, 16));
        labelContrasena.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        campoContrasena.setMaximumSize(new java.awt.Dimension(400, 30));
        
        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setAlignmentX(JButton.CENTER_ALIGNMENT);
        botonIniciarSesion.addActionListener(e -> {
            // Aquí puedes agregar la lógica para redirigir a la ventana de registro
            // Por ejemplo, puedes usar un JFrame o un JOptionPane para mostrar un mensaje
            JOptionPane.showMessageDialog(null, "¡sesión Iniciada!");
        });

        JLabel sinCuenta = new JLabel("¿No tienes cuenta? Regístrate aquí");
        sinCuenta.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        sinCuenta.setFont(new Font("Arial", Font.ITALIC, 14));
        sinCuenta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setAlignmentX(JButton.CENTER_ALIGNMENT);
        botonRegistrarse.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(botonRegistrarse)).dispose(); // Cierra la ventana actual
            main.vista.registroInterface.mostrarVentanaRegistro(); // Abre la ventana de registro
            JOptionPane.showMessageDialog(null, "¡Bienvenido a la ventana de registro!");
        });
        


        
        

        frame.getMyPanel().add(labelTitulo);
        frame.getMyPanel().add(labelUsuario);
        frame.getMyPanel().add(campoUsuario);
        frame.getMyPanel().add(labelContrasena);
        frame.getMyPanel().add(campoContrasena);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(botonIniciarSesion);
        frame.getMyPanel().add(sinCuenta);
        frame.getMyPanel().add(botonRegistrarse);
        frame.mostrarVentana();
       
    }
}