package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;



// --- Clase MenuUsuario convertida a JPanel ---
public class MenuAdminPanel extends JPanel { // Ahora extiende JPanel

private MyFrame parentFrame; // Necesitamos una referencia a MyFrame

    // Constructor de MenuUsuarioPanel, modificado para recibir la instancia de MyFrame
    public MenuAdminPanel(MyFrame frame) { 
        this.parentFrame = frame; // Guarda la referencia a la ventana principal

        // Configuraciones de este JPanel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.decode("#ffffff"));

        // Opcional: Añade un borde para que se vea como un panel flotante
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.decode("#2f3829"))); // Borde izquierdo
        
        // Define un tamaño preferido para que el LayoutManager lo posicione correctamente
        // Es importante que este tamaño sea consistente con cómo MyFrame lo posiciona
        setPreferredSize(new Dimension(200, MyFrame.DEFAULT_HEIGHT)); 
        setMaximumSize(new Dimension(200, MyFrame.DEFAULT_HEIGHT));
        
        // --- Contenido del Panel (Icono y datos de usuario) ---
        ImageIcon imagenUsuario;
        URL imageUrl = MenuUsuarioPanel.class.getResource("/user_icon.png"); 
        if (imageUrl != null) {
            Image img = new ImageIcon(imageUrl).getImage();
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); 
            imagenUsuario = new ImageIcon(scaledImg);
        } else {
            System.err.println("Advertencia: Imagen no encontrada en el classpath: " + "/user_icon.png");
            imagenUsuario = new ImageIcon(); 
        }

        JLabel imagenLabel = new JLabel(imagenUsuario);
        imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Datos de usuario de ejemplo
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
        BotonPanel botonVerCostosF = new BotonPanel("Costos Fijos", 180, 35, e -> {
            // Al hacer clic, ocultamos este mismo panel
            this.setVisible(false); 
            // La ventana actual que contiene este menú deberá mostrar la siguiente interfaz
            // Ejemplo: com.ucveats.view.verMenuInterface.mostrarVentanaVerMenu();
            // Esto lo coordinará la clase que llama a este panel.
            parentFrame.setContentPanel(new CostosFijosUI(parentFrame)); // Cambia el contenido de MyPanel
            System.out.println("Navegando a Costos Fijos...");
        });

        BotonPanel botonVerCostosV = new BotonPanel("Costos Variables", 180, 35, e -> {
            this.setVisible(false);
            //com.ucveats.view.verMonederoInterface.mostrarVentanaVerMonedero();
            //com.ucveats.view.costoVariableUI.main(null);
            //parentFrame.setContentPanel(new costoVariableUI(parentFrame)); // Cambia el contenido de MyPanel a costo varaible, descomentar para que funcione
            System.out.println("Navegando a Costos Variables...");
        });

        BotonPanel botonCargarMenu = new BotonPanel("Cargar Menú", 180, 35, e -> {
            this.setVisible(false);
            // Aquí podrías mostrar una interfaz de cuenta de usuario
            // Ejemplo: com.ucveats.view.verCuentaInterface.mostrarVentanaVerCuenta();
            parentFrame.setContentPanel(new CargarMenuAdmin(parentFrame)); // Cambia el contenido de MyPanel
            System.out.println("Navegando a Cargar Menú...");
        });

        BotonPanel botonCerrarSesion = new BotonPanel("Cerrar Sesión", 180, 35, e -> {
            this.setVisible(false);
            // La ventana actual que contiene este menú se encarga de mostrar la siguiente interfaz
            // Por ejemplo, MyFrame.this.ocultarVentana(); (si se llama desde la ventana contenedora)
            // o directamente com.ucveats.view.inicioSesionInterface.mostrarVentanaInicioSesion();
            parentFrame.setContentPanel(new inicioSesionInterface(parentFrame)); // Vuelve a la pantalla de inicio de sesión
            parentFrame.removeMenuButton(); // Quita el botón de menú del topPanel
            System.out.println("Cerrando Sesión...");
            //com.ucveats.view.inicioSesionInterface.main(null);; 
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
        this.add(Box.createVerticalStrut(10));
        this.add(botonCargarMenu);
        this.add(Box.createVerticalStrut(70));
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