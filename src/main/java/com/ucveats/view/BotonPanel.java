package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BotonPanel extends JButton { 

    // --- Configuraciones Predeterminadas (pueden personalizarse) ---
    public static final int DEFAULT_WIDTH = 180;   // Ancho predeterminado de la ventana
    public static final int DEFAULT_HEIGHT = 40;  // Alto predeterminado
    private static final String DEFAULT_TITLE = "Configura el titulo de la ventana"; // Título por defecto
    private static final ActionListener DEFAULT_ACTION = null; // Acción predeterminada
    

    public BotonPanel() {
        this(DEFAULT_TITLE, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_ACTION);
    }
    
    public BotonPanel(ActionListener accion) {
        this(DEFAULT_TITLE, DEFAULT_WIDTH, DEFAULT_HEIGHT, accion);
    }

    public BotonPanel(String title) {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_ACTION);
    }

    public BotonPanel( String title, int width) {
        this(title, width, DEFAULT_HEIGHT, DEFAULT_ACTION);
    }

    public BotonPanel( String title, int width, int height) {
        this(title, width, height, DEFAULT_ACTION);
    }


    public BotonPanel(String nombre, int width, int height, ActionListener accion) {
        super(nombre); // Llama al constructor de JButton para establecer el texto
        this.setFont(new Font("Segoe UI", Font.BOLD, 12));
        this.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Ajusta el tamaño máximo del botón, si quieres que se vea bien en el panel de menú
        this.setMaximumSize(new Dimension(width, height)); // Un poco más ancho
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setBackground(Color.decode("#87cc2e"));
        this.setForeground(Color.decode("#2f3829"));
        this.setFocusPainted(false);
        this.addActionListener(accion);
    }
}
