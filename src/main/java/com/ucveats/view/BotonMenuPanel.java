package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BotonMenuPanel extends JButton { // Ya no es 'static class' si está fuera de menuAdminPanel
    // 'JButton nombreBoton;' es redundante ya que la clase Boton ya es un JButton.
    // public JButton nombreBoton; // <-- REMOVE THIS LINE

    public BotonMenuPanel(String nombre, ActionListener accion) {
        super(nombre); // Llama al constructor de JButton para establecer el texto
        this.setFont(new Font("Segoe UI", Font.BOLD, 12));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Ajusta el tamaño máximo del botón, si quieres que se vea bien en el panel de menú
        this.setMaximumSize(new Dimension(180, 35)); // Un poco más ancho
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.decode("#87cc2e"));
        this.setForeground(Color.decode("#2f3829"));
        this.setFocusPainted(false);
        this.addActionListener(accion);
    }
}
