package com.ucveats.view;

import javax.swing.*;
import java.awt.*;


public class HomePage extends JPanel{

    private MyFrame parentFrame;
    private JLabel labelTitulo;

    public HomePage(MyFrame frame) {
        this.parentFrame = frame;

        this.setBackground(Color.decode("#ffffff"));

        labelTitulo = new JLabel("Bienvenido Usuario");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));
        this.add(labelTitulo);
    }

    public void setNombreUsuario(String nombre) {
        labelTitulo.setText("Bienvenido " + nombre);
    }

}
