/*  
 * en esta interface hay que buscar un lugar donde poner el nombre del usuario que tiene la sesion iniciada
 * no fue necesario importar los archivos de las otras interfaces, asumo que es por que estan en el mismo paquete
 * los botones estan cableados menos el de ver monedero 
 */

package com.ucveats.view;
import com.ucveats.view.MyFrame;
import javax.swing.*;
import java.awt.*;

public class menuUsuario {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Menu de Usuario", 380, 400);


        // boton en top panel para cesar sesion
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        botonCerrarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonCerrarSesion.setBackground(Color.decode("#87cc2e"));
        botonCerrarSesion.setForeground(Color.decode("#2f3829"));
        botonCerrarSesion.setFocusPainted(false);
        botonCerrarSesion.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(botonCerrarSesion)).dispose();
            com.ucveats.view.inicioSesionInterface.mostrarVentanaInicioSesion();
        });

        frame.getTopPanel().add(botonCerrarSesion, BorderLayout.EAST);

        // Panel Central
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));
        centralPanel.setBackground(Color.decode("#ffffff"));

        JButton botonVerMenu = new JButton("Ver Menu");
        botonVerMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonVerMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVerMenu.setMaximumSize(new Dimension(280, 35));
        botonVerMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVerMenu.setBackground(Color.decode("#87cc2e"));
        botonVerMenu.setForeground(Color.decode("#2f3829"));
        botonVerMenu.setFocusPainted(false);
        botonVerMenu.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(botonVerMenu)).dispose();// Cierra la ventana actual
            com.ucveats.view.verMenuInterface.mostrarVentanaVerMenu(); // Muestra la ventana del menu
        });

        JButton botonVerMonedero = new JButton("Ver Monedero");
        botonVerMonedero.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonVerMonedero.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonVerMonedero.setMaximumSize(new Dimension(280, 35));
        botonVerMonedero.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonVerMonedero.setBackground(Color.decode("#87cc2e"));
        botonVerMonedero.setForeground(Color.decode("#2f3829"));
        botonVerMonedero.setFocusPainted(false);
        botonVerMonedero.addActionListener(e -> {
            ((JFrame) SwingUtilities.getWindowAncestor(botonVerMonedero)).dispose(); // Cierra la ventana actual
            //main.vista.MonederoUI.mostrarVentanaVerMonedero(); // Muestra la ventana del monedero
        });

        centralPanel.add(Box.createVerticalStrut(20));
        centralPanel.add(botonVerMenu);
        centralPanel.add(Box.createVerticalStrut(10));
        centralPanel.add(botonVerMonedero);

        frame.getMyPanel().add(centralPanel, BorderLayout.CENTER);
        frame.mostrarVentana();
    }
    
}
