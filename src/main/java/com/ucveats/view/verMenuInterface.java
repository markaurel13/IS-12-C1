package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.net.URL;

// Importar Boton si está en un archivo separado
// import com.ucveats.view.Boton;

public class verMenuInterface extends MyFrame { // <-- Esta clase debe extender MyFrame

    // Método para mostrar la ventana del menú. Es el punto de entrada principal para esta interfaz.
    public static void mostrarVentanaVerMenu() {
        SwingUtilities.invokeLater(() -> new verMenuInterface().mostrarVentana());
    }

    // Clase interna para representar un elemento del menú
    public static class elementoMenu extends JPanel {
        private ImageIcon iconoPlato;
        private JLabel labelIcono;
        private JLabel nombrePlato;
        private JLabel descripcionPlato;

        public elementoMenu(String rutaIcono, String nombre, String descripcion) {
            URL imageUrl = getClass().getResource(rutaIcono);
            if (imageUrl != null) {
                // Opcional: Redimensionar iconos de elementos del menú si son muy grandes
                Image img = new ImageIcon(imageUrl).getImage();
                // Por ejemplo, escalar a 80x80. Ajusta el tamaño según tus iconos y diseño.
                Image scaledImg = img.getScaledInstance(80, 80, Image.SCALE_SMOOTH); 
                this.iconoPlato = new ImageIcon(scaledImg);
            } else {
                System.err.println("Advertencia: Imagen no encontrada en el classpath: " + rutaIcono);
                this.iconoPlato = new ImageIcon();
            }
            
            this.labelIcono = new JLabel(iconoPlato);
            this.nombrePlato = new JLabel(nombre);
            this.descripcionPlato = new JLabel(descripcion);

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(Color.decode("#ffffff"));

            this.labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.nombrePlato.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.nombrePlato.setFont(new Font("Segoe UI", Font.BOLD, 16));
            this.nombrePlato.setForeground(Color.decode("#2f3829"));

            this.descripcionPlato.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.descripcionPlato.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            this.descripcionPlato.setForeground(Color.decode("#2f3829"));

            this.add(Box.createVerticalStrut(10));
            this.add(labelIcono);
            this.add(Box.createVerticalStrut(10));
            this.add(nombrePlato);
            this.add(Box.createVerticalStrut(10));
            this.add(descripcionPlato);
            this.add(Box.createVerticalStrut(10));
        }
    }
    
    // --- Constructor de la Interfaz verMenuInterface ---
    public verMenuInterface() {
        // 1. Llama al constructor de MyFrame para configurar la ventana base
        super("Menú Disponible", MyFrame.DEFAULT_WIDTH, MyFrame.DEFAULT_HEIGHT); // Usa los anchos y altos predeterminados

        // 2. Panel para el contenido central del menú (antes era el 'centerPanel' en tu main)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#ffffff"));

        // Tu lógica de labels y elementos del menú va aquí
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

        JLabel tituloDesayuno = new JLabel("Desayuno");
        tituloDesayuno.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloDesayuno.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloDesayuno.setForeground(Color.decode("#2f3829"));

        elementoMenu desayuno = new elementoMenu("/desayuno.png", "Huevos con Tocino", "Deliciosos Huevos con tocino crujiente y pan");

        JLabel tituloAlmuerzo = new JLabel("Almuerzo");
        tituloAlmuerzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAlmuerzo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloAlmuerzo.setForeground(Color.decode("#2f3829"));

        elementoMenu almuerzo = new elementoMenu("/almuerzo.png", "Pato a la Naranja", "Delicioso pato con salsa de naranja");

        // Añadir los elementos al contentPanel
        contentPanel.add(labelTitulo);
        contentPanel.add(labelFecha);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(tituloDesayuno);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(desayuno); 
        contentPanel.add(Box.createVerticalStrut(40));
        contentPanel.add(tituloAlmuerzo);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(almuerzo);
        contentPanel.add(Box.createVerticalStrut(20));

        // 3. Crear un JScrollPane para el contentPanel y añadirlo al MyPanel de MyFrame
        JScrollPane scrollPane = new JScrollPane(contentPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Eliminar el borde predeterminado del scrollPane
        getMyPanel().add(scrollPane, BorderLayout.CENTER); // Añadir el scrollPane al centro de MyPanel


        // --- Activar el botón de menú y el panel flotante ---
        // 4. Crea una instancia de tu MenuUsuarioPanel
        MenuUsuarioPanel menuUsuarioPanel = new MenuUsuarioPanel();
        // 5. Pásale esta instancia a MyFrame para que MyFrame la gestione como panel flotante
        setFloatingMenuPanel(menuUsuarioPanel); 
        
        // 6. Activa el botón de menú en el topPanel de MyFrame
        // La acción de este botón será simplemente alternar la visibilidad del 'menuUsuarioPanel'.
        addMenuButton("/icono_lineas.png", e -> {
            // No necesitas añadir lógica aquí si toggleFloatingMenu() ya hace el trabajo.
            // Puedes añadir un System.out.println() si es solo para depurar.
            // System.out.println("Botón de menú en 'Ver Menú' clicado.");
        });

        // La llamada a mostrarVentana() se hace externamente desde mostrarVentanaVerMenu()
        // o desde el main si es la ventana principal de la aplicación.
    }

    // El método main para ejecutar directamente esta interfaz para pruebas
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // Mejor apariencia nativa
        } catch (Exception ignored) {}

        mostrarVentanaVerMenu(); // Llama al método estático para mostrar la ventana
    }
}