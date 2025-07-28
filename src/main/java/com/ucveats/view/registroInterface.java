/*
 * en la esquin superir del panel gris, topPanel, habia un boton de iniciar sesion 
 * este se elimino al refactorizar para extender de jpanel y la logica ya no se maneja en este panel
 * si se considera agregar esta coemntado en cambio clave 5 la consideracion y el boton antiguo
 * si no se agregara este boton o no se agregara en ese lugar eliminar comentarios
 */
package com.ucveats.view;

import javax.swing.*;
import java.awt.*;

public class registroInterface extends JPanel {

    //Necesitamos una referencia a MyFrame para poder cambiar el contenido.
    private MyFrame parentFrame;

    //Constructor modificado para recibir la instancia de MyFrame
    public registroInterface(MyFrame frame) {
        this.parentFrame = frame;


        this.setLayout(null); 
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

        // --- Componentes de la interfaz de registro  ---
        JLabel titulo = new JLabel("Registro de Usuario", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setBounds(70, 30, 250, 25);
        this.add(titulo); 

        // ciclo para añadir campos a registrar
        int y = 80;
        String[] campos = {"Nombre", "Apellido", "Cédula", "Teléfono", "Correo", "Contraseña", "Confirmar Contraseña"};
        JTextField[] entradas = new JTextField[campos.length];

        for (int i = 0; i < campos.length; i++) {
            JLabel etiqueta = new JLabel(campos[i] + ":");
            etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            etiqueta.setBounds(30, y, 140, 25);
            this.add(etiqueta); 

            JTextField campo;
            if (campos[i].toLowerCase().contains("contraseña")) {
                campo = new JPasswordField();
            } else {
                campo = new JTextField();
            }
            campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            campo.setBounds(190, y, 150, 25);
            entradas[i] = campo;
            this.add(campo);

            y += 40;
        }

        BotonPanel botonRegistrar = new BotonPanel("REGISTRAR", 200, 40);
        botonRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrar.setBounds(80, y + 10, 200, 40);
        botonRegistrar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        botonRegistrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "¡Registro exitoso!");
            parentFrame.setContentPanel(new inicioSesionInterface(parentFrame));
        });
        this.add(botonRegistrar);
        
    }
}
