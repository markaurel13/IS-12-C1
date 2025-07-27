package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener; // Asegúrate de que este import exista si BotonPanel lo necesita

// !!! CAMBIO CLAVE 1: Ahora extiende JPanel, no MyFrame !!!
public class registroInterface extends JPanel {

    // CAMBIO CLAVE 2: Necesitamos una referencia a MyFrame para poder cambiar el contenido.
    private MyFrame parentFrame;

    // CAMBIO CLAVE 3: Constructor modificado para recibir la instancia de MyFrame
    public registroInterface(MyFrame frame) {
        this.parentFrame = frame;

        // CAMBIO CLAVE 4: Configura este JPanel directamente.
        // Ya no llamas a super("titulo") ni estableces tamaño, etc.,
        // porque estas propiedades las maneja MyFrame.
        this.setLayout(null); // Tu diseño original usaba null layout
        this.setBackground(Color.decode("#ffffff"));

        // --- CAMBIO CLAVE 5: Gestión del botón "Iniciar Sesión" en el topPanel ---
        // Esta lógica NO va aquí directamente dentro del JPanel de la vista.
        // El topPanel pertenece a MyFrame, y la MyFrame o la clase principal (App.java)
        // deberían gestionar qué botones aparecen en él dinámicamente según la vista.
        // He comentado el código original aquí, y te recordaré cómo manejarlo en App.java.

        /*
        BotonPanel botonIniciarSesion = new BotonPanel("Iniciar Sesión",30, 15);
        botonIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonIniciarSesion.addActionListener(e -> {
            // parentFrame.setContentPanel(new inicioSesionInterface(parentFrame)); // Cambia a la interfaz de inicio de sesión
            // Puedes añadir lógica para remover el botón del topPanel si lo agregaste dinámicamente.
        });
        // getTopPanel().add(botonIniciarSesion, BorderLayout.EAST); // Esto intentaba añadirlo al topPanel de MyFrame
        */

        // --- Componentes de la interfaz de registro (sin cambios en la mayoría) ---
        JLabel titulo = new JLabel("Registro de Usuario", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setBounds(70, 30, 250, 25);
        this.add(titulo); // Se añade a 'this' (este JPanel de registro)

        // ciclo para añadir campos a registrar
        int y = 80;
        String[] campos = {"Nombre", "Apellido", "Cédula", "Teléfono", "Correo", "Contraseña", "Confirmar Contraseña"};
        JTextField[] entradas = new JTextField[campos.length];

        for (int i = 0; i < campos.length; i++) {
            JLabel etiqueta = new JLabel(campos[i] + ":");
            etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            etiqueta.setBounds(30, y, 140, 25);
            this.add(etiqueta); // Se añade a 'this'

            JTextField campo;
            if (campos[i].toLowerCase().contains("contraseña")) {
                campo = new JPasswordField();
            } else {
                campo = new JTextField();
            }
            campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            campo.setBounds(190, y, 150, 25);
            entradas[i] = campo;
            this.add(campo); // Se añade a 'this'

            y += 40;
        }

        BotonPanel botonRegistrar = new BotonPanel("REGISTRAR", 200, 40);
        botonRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrar.setBounds(80, y + 10, 200, 40);
        botonRegistrar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        botonRegistrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "¡Registro exitoso!");
            // Opcional: Después del registro exitoso, podrías querer volver a la pantalla de inicio de sesión
            parentFrame.setContentPanel(new inicioSesionInterface(parentFrame)); // CAMBIO CLAVE 6: Navegación de vuelta
            // Opcional: Si el botón de menú flotante debe aparecer después del registro,
            // puedes activarlo aquí o en inicioSesionInterface si esta se carga después del login.
        });
        this.add(botonRegistrar);
        
        //intento de colocar el boton de inicio de sesion en el top panel de registro
        //parentFrame.setVisibleInicioButton(); 
    }
}
