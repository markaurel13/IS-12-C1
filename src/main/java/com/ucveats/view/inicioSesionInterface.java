package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener; // Asegúrate de que este import exista si BotonPanel lo necesita
import com.ucveats.Main;

// !!! CAMBIO CLAVE 1: Ahora extiende JPanel, no MyFrame !!!
public class inicioSesionInterface extends JPanel { 

    // CAMBIO CLAVE 2: Necesitamos una referencia a MyFrame para poder cambiar el contenido.
    private MyFrame parentFrame; 

    // CAMBIO CLAVE 3: Constructor modificado para recibir la instancia de MyFrame
    public inicioSesionInterface(MyFrame frame) { 
        this.parentFrame = frame;

        // CAMBIO CLAVE 4: Configura este JPanel directamente.
        // Ya no llamas a super("titulo") ni estableces tamaño, etc.,
        // porque estas propiedades las maneja MyFrame.
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        this.setBackground(Color.decode("#ffffff"));

        // --- Componentes de la interfaz de inicio de sesión (sin cambios aquí) ---
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
        botonIniciarSesion.addActionListener(e -> {
            // Aquí debes agregar la lógica para iniciar sesión.
            // verificar los datos ingresados para saber si es usuario comensal o admin.
            // y segun eso setear el menu flotante de usuario o admin
            // y el nombre del usuario que se muestra en homepage

            parentFrame.setFloatingMenuPanel(Main.menuUsuarioFlotante);
            parentFrame.setContentPanel(new HomePage(parentFrame));
            JOptionPane.showMessageDialog(this, "✅ ¡Sesión iniciada!");

            // si el usuario es admin, se debe mostrar el menu de admin

            /*parentFrame.setFloatingMenuPanel(Main.menuAdminFlotante);
            parentFrame.setContentPanel(new HomePage(parentFrame));
            JOptionPane.showMessageDialog(this, "✅ ¡Sesión iniciada!");*/
        });

        JLabel sinCuenta = new JLabel("¿No tienes cuenta? Regístrate aquí");
        sinCuenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        sinCuenta.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        sinCuenta.setForeground(Color.decode("#2f3829"));
        sinCuenta.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        BotonPanel botonRegistrarse = new BotonPanel("Registrarse", 200, 35);
        botonRegistrarse.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrarse.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 1, true));
        
        // --- CAMBIO CLAVE 5: Lógica de navegación modificada ---
        botonRegistrarse.addActionListener(e -> {
            // Usamos la referencia a parentFrame para cambiar el contenido de MyPanel en MyFrame.
            parentFrame.setContentPanel(new registroInterface(parentFrame)); 
            System.out.println("Navegando a Registro..."); 
        });

        // Añadir componentes a este JPanel (inicioSesionInterface)
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

        // CAMBIO CLAVE 6: Se elimina esta línea. Ahora 'this' (este JPanel) será añadido directamente
        // al MyPanel de MyFrame por la clase principal (App.java) usando setContentPanel().
        // getMyPanel().add(centerPanel, BorderLayout.CENTER); 

        // CAMBIO CLAVE 7: Se elimina el método main(). Esta clase ya no se lanza como ventana independiente.
    }
}