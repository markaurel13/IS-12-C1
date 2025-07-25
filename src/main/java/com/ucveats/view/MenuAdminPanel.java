package com.ucveats.view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import java.awt.*;
import java.net.URL;



// --- Clase MenuUsuario convertida a JPanel ---
public class MenuAdminPanel extends JPanel { // Ahora extiende JPanel

    // Constructor de MenuAdminPanel
    public MenuAdminPanel() {
        // Configuraciones de este JPanel (que antes estaban en MyFrame o el main)
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#ffffff"));

        // Opcional: Añade un borde para que se vea como un panel flotante
        Border bordeCompuesto = BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 1, 0, 0, Color.GRAY),
            BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.GRAY, Color.WHITE)
        );
        setBorder(bordeCompuesto); 
        // Define un tamaño preferido para que el LayoutManager lo posicione correctamente
        setPreferredSize(new Dimension(200, 580)); // Ancho y alto para tu menú
        setMaximumSize(new Dimension(200, 580));
        
        // --- Contenido del Panel Central (lo que estaba en tu 'main' original) ---
        // Ya no necesitas 'centralPanel' si este 'MenuAdminPanel' es el centralPanel en sí
        // JPanel centralPanel = new JPanel(); // <-- REMOVE THIS LINE

        // Icono de usuario
        ImageIcon imagenUsuario;
        URL imageUrl = MenuAdminPanel.class.getResource("/user_icon.png"); 
        if (imageUrl != null) {
            // Opcional: Redimensionar imagen si es grande
            Image img = new ImageIcon(imageUrl).getImage();
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
            imagenUsuario = new ImageIcon(scaledImg);
        } else {
            System.err.println("Advertencia: Imagen no encontrada en el classpath: " + "/user_icon.png");
            imagenUsuario = new ImageIcon(); 
        }

        JLabel imagenLabel = new JLabel(imagenUsuario);
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String tipoUsuario = "Tipo Prueba", nombreUsuario = "Nombre", apelldoUsuario = "Apellido";
        JLabel nombreLabel = new JLabel(nombreUsuario + " " + apelldoUsuario);
        nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nombreLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nombreLabel.setForeground(Color.decode("#2f3829"));

        JLabel tipoLabel = new JLabel(tipoUsuario);
        tipoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        tipoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        tipoLabel.setForeground(Color.decode("#2f3829"));

        // --- Botones del Menú ---
        // Las acciones de los botones ahora deben interactuar con la ventana que contiene este panel,
        // o con un controlador externo. Ya no pueden llamar a 'frame.ocultarVentana()'.
        // Aquí, simplemente ocultamos ESTE PANEL. La lógica de 'mostrar otra ventana'
        // deberá ser manejada por la clase que contenga este panel o por un controlador.

        BotonPanel botonVerCostosF = new BotonPanel("Costos Fijos", 180, 35, e -> {
            // Al hacer clic, ocultamos este mismo panel
            this.setVisible(false); 
            // La ventana actual que contiene este menú deberá mostrar la siguiente interfaz
            // Ejemplo: com.ucveats.view.verMenuInterface.mostrarVentanaVerMenu();
            // Esto lo coordinará la clase que llama a este panel.
            com.ucveats.view.CostosFijosUI.main(null);
            System.out.println("Navegando a Costos Fijos...");
        });

        BotonPanel botonVerCostosV = new BotonPanel("Costos Variables", 180, 35, e -> {
            this.setVisible(false);
            //com.ucveats.view.verMonederoInterface.mostrarVentanaVerMonedero();
            //com.ucveats.view.costoVariableUI.main(null);
            System.out.println("Navegando a Costos Variables...");
        });

        BotonPanel botonCerrarSesion = new BotonPanel("Cerrar Sesión", 180, 35, e -> {
            this.setVisible(false);
            // La ventana actual que contiene este menú se encarga de mostrar la siguiente interfaz
            // Por ejemplo, MyFrame.this.ocultarVentana(); (si se llama desde la ventana contenedora)
            // o directamente com.ucveats.view.inicioSesionInterface.mostrarVentanaInicioSesion();
            System.out.println("Cerrando Sesión...");
            com.ucveats.view.inicioSesionInterface.mostrarVentanaInicioSesion(); 
        });

        // --- Añadir los componentes a este JPanel (antes era 'centralPanel') ---
        this.add(Box.createVerticalStrut(20));
        this.add(imagenLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(nombreLabel);
        this.add(Box.createVerticalStrut(5));
        this.add(tipoLabel);
        this.add(Box.createVerticalStrut(50));
        this.add(botonVerCostosF);
        this.add(Box.createVerticalStrut(10));
        this.add(botonVerCostosV);
        this.add(Box.createVerticalStrut(80));
        this.add(botonCerrarSesion);
        
        // El resto del código de tu 'main' original (que agregaba 'centralPanel' al 'frame')
        // ya no va aquí, ya que este es el panel en sí y se agregará a otro JFrame/JPanel.
        // frame.getMyPanel().add(centralPanel, BorderLayout.CENTER); // <-- REMOVE THIS LINE
        // frame.mostrarVentana(); // <-- REMOVE THIS LINE
    }

    // Ya no es un JFrame, por lo tanto, no hay método main aquí
    // public static void main(String[] args) { ... }
    
    // Si necesitas un método para obtener una instancia, podrías considerar un Singleton
    // public static MenuAdminPanel getInstance() { ... }
}