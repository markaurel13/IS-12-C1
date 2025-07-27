package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

// IMPORTANTE: Esta clase ahora extiende JPanel, no MyFrame
public class verMenuInterface extends JPanel {

    private MyFrame parentFrame; // Necesitamos una referencia a MyFrame

    // Constructor modificado para recibir la instancia de MyFrame
    public verMenuInterface(MyFrame frame) {
        this.parentFrame = frame;

        // Este JPanel usará un BorderLayout para organizar su contenido principal
        // y permitir el JScrollPane en el centro.
        this.setLayout(new BorderLayout()); 
        this.setBackground(Color.decode("#ffffff"));

        // Panel interno para el contenido del menú, que irá dentro del JScrollPane
        JPanel contentInternalPanel = new JPanel(); 
        contentInternalPanel.setLayout(new BoxLayout(contentInternalPanel, BoxLayout.Y_AXIS));
        contentInternalPanel.setBackground(Color.decode("#ffffff"));

        // --- Componentes de la interfaz del menú ---
        JLabel labelTitulo = new JLabel("Menú del Día");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        LocalDate fechaHoy = LocalDate.now();
        JLabel labelFecha = new JLabel("Fecha: " + fechaHoy.getDayOfMonth() + "/" + fechaHoy.getMonthValue() + "/" + fechaHoy.getYear());
        labelFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelFecha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFecha.setForeground(Color.decode("#2f3829"));

        /*
         * deberia agregar aca un precio de la bandeja
         */

        JLabel tituloDesayuno = new JLabel("Desayuno");
        tituloDesayuno.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloDesayuno.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloDesayuno.setForeground(Color.decode("#2f3829"));

        // Instancia de la clase interna elementoMenu
        elementoMenu desayuno = new elementoMenu("/desayuno.png", "Huevos con Tocino", "Deliciosos Huevos con tocino crujiente y pan");

        JLabel tituloAlmuerzo = new JLabel("Almuerzo");
        tituloAlmuerzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAlmuerzo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloAlmuerzo.setForeground(Color.decode("#2f3829"));

        // Instancia de la clase interna elementoMenu
        elementoMenu almuerzo = new elementoMenu("/almuerzo.png", "Pato a la Naranja", "Delicioso pato con salsa de naranja");

        // Añadir los elementos al panel interno con desplazamiento
        contentInternalPanel.add(labelTitulo);
        contentInternalPanel.add(labelFecha);
        contentInternalPanel.add(Box.createVerticalStrut(15));
        contentInternalPanel.add(tituloDesayuno);
        contentInternalPanel.add(Box.createVerticalStrut(10));
        contentInternalPanel.add(desayuno); 
        contentInternalPanel.add(Box.createVerticalStrut(40));
        contentInternalPanel.add(tituloAlmuerzo);
        contentInternalPanel.add(Box.createVerticalStrut(10));
        contentInternalPanel.add(almuerzo);
        contentInternalPanel.add(Box.createVerticalStrut(20));

        // 3. Crear un JScrollPane para el contentInternalPanel y añadirlo a 'this' (el JPanel verMenuInterface)
        JScrollPane scrollPane = new JScrollPane(contentInternalPanel, 
                                                 JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
                                                 JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Eliminar el borde predeterminado del scrollPane
        this.add(scrollPane, BorderLayout.CENTER); // Añadir el scrollPane al centro de este JPanel


        // --- Activar el botón de menú en el topPanel de MyFrame ---
        // Este código activa el botón de menú en la barra superior cuando esta vista está activa.
        // La instancia de MenuUsuarioPanel y su asociación con MyFrame ya se hacen una vez en App.java.
        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });
    }

  
    
    
    // NOTA IMPORTANTE: Los métodos main() y mostrarVentanaVerMenu() originales han sido eliminados.
    // Esta clase es ahora un JPanel, no una ventana independiente,
    // y su visibilidad y carga son gestionadas por MyFrame a través de la clase App.java.
}