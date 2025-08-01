/*
 * habria que biscar la manera de que cuadno se carga un meni se pueda instancias la clase aqui para poder 
 * ver lo elementos en la pagina
 */
package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.ucveats.model.Bandeja;


public class verMenuInterface extends JPanel {

    double precioBandjea = 0.0;
    private JLabel labelPrecio;

    private MyFrame parentFrame; // Necesitamos una referencia a MyFrame
    private JPanel contentInternalPanel;

    public verMenuInterface(MyFrame frame) {
        this.parentFrame = frame;

        this.setLayout(new BorderLayout()); 
        this.setBackground(Color.decode("#ffffff"));

        // Panel interno para el contenido del menú, que irá dentro del JScrollPane
        contentInternalPanel = new JPanel(); 
        contentInternalPanel.setLayout(new BoxLayout(contentInternalPanel, BoxLayout.Y_AXIS));
        contentInternalPanel.setBackground(Color.decode("#ffffff"));

        // --- Componentes de la interfaz del menú ---
        JLabel labelTitulo = new JLabel("Menú del Día");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        LocalDate fechaHoy = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        JLabel labelFecha = new JLabel("Fecha: " + fechaHoy.format(formatter));
        labelFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelFecha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFecha.setForeground(Color.decode("#2f3829"));

        labelPrecio = new JLabel("Precio: " + precioBandjea);
        labelPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPrecio.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelPrecio.setForeground(Color.decode("#2f3829"));
        
        
        contentInternalPanel.add(labelTitulo);
        contentInternalPanel.add(labelFecha);
        contentInternalPanel.add(labelPrecio);

        //  Crear un JScrollPane para el contentInternalPanel y añadirlo a 'this' (el JPanel verMenuInterface)
        JScrollPane scrollPanel = new JScrollPane(contentInternalPanel, 
                                                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder()); // Eliminar el borde predeterminado del scrollPanel
        this.add(scrollPanel, BorderLayout.CENTER); // Añadir el scrollPanel al centro de este JPanel
    }

    public void setPrecio(double precio) {
        this.precioBandjea = precio;
        labelPrecio.setText("Precio: " + String.format("%.2f", precioBandjea) + " BsS"); // <-- Actualiza el label
        labelPrecio.revalidate();
        labelPrecio.repaint();
    }

    public void mostrarMenu(List<Bandeja> bandejas) {
        // Limpiar cualquier menú anterior
        // Se mantienen el título y la fecha
        Component[] components = contentInternalPanel.getComponents();
        for (int i = 3; i < components.length; i++) {
            contentInternalPanel.remove(components[i]);
        }

        if (bandejas == null || bandejas.isEmpty()) {
            JLabel noHayMenu = new JLabel("No hay menú disponible para hoy.");
            noHayMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16)); 
            noHayMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentInternalPanel.add(noHayMenu);
        } else {
            for (Bandeja bandeja : bandejas) {
                // Asumimos que tienes una imagen por defecto o lógica para encontrar la imagen
                elementoMenu item = new elementoMenu("/"+bandeja.getTipoBandeja()+".png", bandeja.getNombreBandeja(), bandeja.getDescripcionBandeja(), bandeja.getTipoBandeja());
                contentInternalPanel.add(item);
                contentInternalPanel.add(Box.createVerticalStrut(20));
            }
        }
        contentInternalPanel.revalidate();
        contentInternalPanel.repaint();
    }
}