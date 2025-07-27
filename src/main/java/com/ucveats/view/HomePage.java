package com.ucveats.view;

import javax.swing.*;
import java.awt.*;


public class HomePage extends JPanel{

    private MyFrame parentFrame;
    String nombreUsiario = "Usuario";

    public HomePage(MyFrame frame) {
        this.parentFrame = frame;

        JLabel labelTitulo = new JLabel("Bienvenido " + nombreUsiario);
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        
        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });

        this.add(labelTitulo);
    }

}
