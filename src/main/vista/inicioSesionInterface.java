package main.vista;
import main.vista.MyFrame;
import javax.swing.*;
import java.awt.*;

//este import se coloca unicamente para cablear el codigo y que los botones cambein de ventana
import main.vista.registroInterface;

public class inicioSesionInterface {

    //clase para llamar al main desde registro de usuario, se coloca unucamente para cablear el codigo y que los botones cambein de ventana
    public static void mostrarVentanaInicioSesion() {
        main(new String[0]);
    }
    public static void main(String[] args) {
        // no se que hace esta verga
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        MyFrame frame = new MyFrame("Inicio de Sesión UCVeats");
    
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

        JButton botonIniciarSesion = new JButton("Iniciar Sesión");
        botonIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonIniciarSesion.setBackground(Color.decode("#87cc2e"));
        botonIniciarSesion.setForeground(Color.decode("#2f3829"));
        botonIniciarSesion.setFocusPainted(false);
        botonIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIniciarSesion.setMaximumSize(new Dimension(220, 40));
        botonIniciarSesion.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        botonIniciarSesion.addActionListener(e -> JOptionPane.showMessageDialog(null, "✅ ¡Sesión iniciada!"));

        JLabel sinCuenta = new JLabel("¿No tienes cuenta? Regístrate aquí");
        sinCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        sinCuenta.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        sinCuenta.setForeground(Color.decode("#2f3829"));
        sinCuenta.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JButton botonRegistrarse = new JButton("Registrarse");
        botonRegistrarse.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonRegistrarse.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrarse.setBackground(Color.decode("#ffffff"));
        botonRegistrarse.setForeground(Color.decode("#2f3829"));
        botonRegistrarse.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonRegistrarse.setFocusPainted(false);
        botonRegistrarse.setMaximumSize(new Dimension(200, 35));
        botonRegistrarse.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 1, true));
        botonRegistrarse.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(botonRegistrarse)).dispose(); // Cierra la ventana actual
            main.vista.registroInterface.mostrarVentanaRegistro(); // Muestra la ventana de registro
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

        frame.getMyPanel().add(centerPanel, BorderLayout.CENTER);
        frame.mostrarVentana();
    }
}
