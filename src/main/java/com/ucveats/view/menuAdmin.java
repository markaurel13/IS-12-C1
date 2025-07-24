/*  
 * en esta interface hay que buscar un lugar donde poner el nombre del usuario que tiene la sesion iniciada
 * no fue necesario importar los archivos de las otras interfaces, asumo que es por que estan en el mismo paquete
 * los botones estan cableados menos el de ver monedero.
 * Tambien hay que cablear los botones
 */


package com.ucveats.view;
import com.ucveats.view.MyFrame;
import javax.swing.*;
import java.awt.*;


public class menuAdmin {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Menu de Administrador", 380, 400);


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

        JButton botonCostosFijos = new JButton("Costos Fijos");
        botonCostosFijos.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonCostosFijos.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCostosFijos.setMaximumSize(new Dimension(280, 35));
        botonCostosFijos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonCostosFijos.setBackground(Color.decode("#87cc2e"));
        botonCostosFijos.setForeground(Color.decode("#2f3829"));
        botonCostosFijos.setFocusPainted(false);
        botonCostosFijos.addActionListener(e -> {
            /* 
            ((JFrame) SwingUtilities.getWindowAncestor(botonCostosFijos)).dispose();// Cierra la ventana actual
            main.vista.verMenuInterface.mostrarVentanaVerMenu(); // Muestra la ventana del menu
            */
        });

        JButton botonCostosVariables = new JButton("Costos Variables");
        botonCostosVariables.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonCostosVariables.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonCostosVariables.setMaximumSize(new Dimension(280, 35));
        botonCostosVariables.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonCostosVariables.setBackground(Color.decode("#87cc2e"));
        botonCostosVariables.setForeground(Color.decode("#2f3829"));
        botonCostosVariables.setFocusPainted(false);
        botonCostosVariables.addActionListener(e -> {
            /*
            ((JFrame) SwingUtilities.getWindowAncestor(botonCostosVariables)).dispose(); // Cierra la ventana actual
            //main.vista.MonederoUI.mostrarVentanaVerMonedero(); // Muestra la ventana del monedero
            */
        });

        centralPanel.add(Box.createVerticalStrut(20));
        centralPanel.add(botonCostosFijos);
        centralPanel.add(Box.createVerticalStrut(10));
        centralPanel.add(botonCostosVariables);

        frame.getMyPanel().add(centralPanel, BorderLayout.CENTER);
        frame.mostrarVentana();
    }
    
}
