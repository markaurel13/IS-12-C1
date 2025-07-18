package main.vista;
import main.vista.MyFrame;
import javax.swing.*;
import java.awt.Font;
import java.net.URL;      
import java.awt.Image;
import java.io.File;


public class verMenuInterface {

    public static void main(String[] args) {

        MyFrame frame = new MyFrame("Menu disponible");
        frame.getMyPanel().setLayout(new BoxLayout(frame.getMyPanel(), BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Menu del dia");
        labelTitulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        // Intenta cargar la imagen
        ImageIcon iconoPlato = new ImageIcon("D:\\archivos\\Documents\\01 UCV\\01-2025\\ing software\\IS-12-C1\\src\\main\\vista\\plato.png");
        JLabel labelIcono = new JLabel(iconoPlato);
        labelIcono.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel labelNombrePato = new JLabel("Pato a la naranja");
        labelNombrePato.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelNombrePato.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel labelPrecioPato = new JLabel("Precio: $10.00");
        labelPrecioPato.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelPrecioPato.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel labelDescripcionPato = new JLabel("Delicioso pato con salsa de naranja");
        labelDescripcionPato.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelDescripcionPato.setFont(new Font("Arial", Font.ITALIC, 14));

        JCheckBox checkPlato = new JCheckBox("Seleccionar plato");
        checkPlato.setAlignmentX(JCheckBox.CENTER_ALIGNMENT);

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        botonSeleccionar.addActionListener(e -> {
            if (checkPlato.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Plato seleccionado");
            } else {
                JOptionPane.showMessageDialog(frame, "Debes seleccionar el plato primero");
            }
        });

        

        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(labelTitulo);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(labelIcono);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(labelNombrePato);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(labelPrecioPato);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(labelDescripcionPato);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(checkPlato);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(botonSeleccionar);
        frame.mostrarVentana();
    }
}