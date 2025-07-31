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
  
        contentInternalPanel.add(labelTitulo);
        contentInternalPanel.add(labelFecha);

        //  Crear un JScrollPane para el contentInternalPanel y añadirlo a 'this' (el JPanel verMenuInterface)
        JScrollPane scrollPanel = new JScrollPane(contentInternalPanel, 
                                                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPanel.setBorder(BorderFactory.createEmptyBorder()); // Eliminar el borde predeterminado del scrollPanel
        this.add(scrollPanel, BorderLayout.CENTER); // Añadir el scrollPanel al centro de este JPanel


        // --- Activar el botón de menú en el topPanel de MyFrame ---
        // Este código activa el botón de menú en la barra superior cuando esta vista está activa.
        // La instancia de MenuUsuarioPanel y su asociación con MyFrame ya se hacen una vez en App.java.
        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });
    }

    public void mostrarMenu(List<Bandeja> bandejas) {
        // Limpiar cualquier menú anterior
        // Se mantienen el título y la fecha
        Component[] components = contentInternalPanel.getComponents();
        for (int i = 2; i < components.length; i++) {
            contentInternalPanel.remove(components[i]);
        }

        if (bandejas == null || bandejas.isEmpty()) {
            JLabel noHayMenu = new JLabel("No hay menú disponible para hoy.");
            noHayMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
            contentInternalPanel.add(noHayMenu);
        } else {
            for (Bandeja bandeja : bandejas) {
                // Asumimos que tienes una imagen por defecto o lógica para encontrar la imagen
                elementoMenu item = new elementoMenu("/almuerzo.png", bandeja.getNombreBandeja(), bandeja.getDescripcionBandeja());
                contentInternalPanel.add(item);
                contentInternalPanel.add(Box.createVerticalStrut(20));
            }
        }
        contentInternalPanel.revalidate();
        contentInternalPanel.repaint();
    }
}