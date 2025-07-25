/*detalle para corregir aca:
el focus de los botones que se ve en el de inicio y no en el de registro*/

package com.ucveats.view;
import javax.swing.*;
import java.awt.*;

//este import se coloca unicamente para cablear el codigo y que los botones cambein de ventana
//import main.vista.inicioSesionInterface;      REVISAR ESTE IMPORT SI NO FUNCIONA


public class registroInterface extends JFrame {

    //clase para llamar al main desde inicio de sesion, se coloca para cablear el codigo y que los botones cambein de ventana
    public static void mostrarVentanaRegistro() {
        main(new String[0]);
    }


    public static void main(String[] args) {

        // no se que hace esta verga
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        
        MyFrame frame = new MyFrame("Registro UCV Eats");

        // boton superior de iniciar sesion en el top panel de MyFrame
        BotonPanel botonIniciarSesion = new BotonPanel("Iniciar Sesión",30, 15);
        botonIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonIniciarSesion.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(botonIniciarSesion)).dispose(); // Cierra la ventana actual
            com.ucveats.view.inicioSesionInterface.mostrarVentanaInicioSesion(); // Abre la ventana de inicio de sesión
        });

        frame.getTopPanel().add(botonIniciarSesion, BorderLayout.EAST);


        // Panel Central
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(null);
        centralPanel.setBackground(Color.decode("#ffffff"));

        JLabel titulo = new JLabel("Registro de Usuario", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setBounds(70, 30, 250, 25);

        centralPanel.add(titulo);

        // ciclo para anadir campos a registar
        int y = 80;
        String[] campos = {"Nombre", "Apellido", "Cédula", "Teléfono", "Correo", "Contraseña", "Confirmar Contraseña"};
        JTextField[] entradas = new JTextField[campos.length];

        for (int i = 0; i < campos.length; i++) {
            JLabel etiqueta = new JLabel(campos[i] + ":");
            etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            etiqueta.setBounds(30, y, 140, 25);

            centralPanel.add(etiqueta);

            JTextField campo;
            if (campos[i].toLowerCase().contains("contraseña")) {
                campo = new JPasswordField();
            } else {
                campo = new JTextField();
            }
            campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            campo.setBounds(190, y, 150, 25);
            entradas[i] = campo;

            centralPanel.add(campo);

            y += 40;
        }

        BotonPanel botonRegistrar = new BotonPanel("REGISTRAR", 200, 40);
        botonRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrar.setBounds(80, y + 10, 200, 40);
        botonRegistrar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        botonRegistrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
        });
        
        centralPanel.add(botonRegistrar);

       
       
        frame.getMyPanel().add(centralPanel, BorderLayout.CENTER);
        frame.mostrarVentana();


    }
}