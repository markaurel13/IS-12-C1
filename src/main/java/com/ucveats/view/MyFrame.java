package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MyFrame extends JFrame {

    // --- Configuraciones Predeterminadas (pueden personalizarse) ---
    public static final int DEFAULT_WIDTH = 380;   // Ancho predeterminado de la ventana
    public static final int DEFAULT_HEIGHT = 580;  // Alto predeterminado
    private static final String DEFAULT_TITLE = "Configura el titulo de la ventana"; // Título por defecto

    // Manteniendo el nombre 'MyPanel'
    private JPanel MyPanel; // Este es el panel que contendrá dinámicamente tus otras interfaces.
    private JPanel topPanel;
    private JLabel logoUCVeast;
    private JLabel menuButonLabel;

    // --- Referencia al panel de menú flotante ---
    private JPanel floatingMenuPanel; 
    private boolean isFloatingMenuVisible = false;

    public MyFrame() {
        this(DEFAULT_TITLE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MyFrame(String title) {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MyFrame( String title, int width) {
        this(title, width, DEFAULT_HEIGHT);
    }

    public MyFrame(String title, int width, int heigth) {
        super(title);
        this.setSize(width, heigth);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        // El topPanel se añade directamente al JFrame, en la parte superior.
        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#353535"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        logoUCVeast = new JLabel("UCVeats");
        logoUCVeast.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoUCVeast.setForeground(Color.decode("#ffffff"));
        topPanel.add(logoUCVeast, BorderLayout.WEST);
        
        menuButonLabel = new JLabel();
        menuButonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //menuButonLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menuButonLabel.setVisible(false); // Oculto por defecto
        topPanel.add(menuButonLabel, BorderLayout.EAST);

        //intento de colocar el boton de inicio de sesion en el top panel de registro
        /*BotonPanel botonIniciarSesion = new BotonPanel("Iniciar Sesión",30, 15);
        botonIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        botonIniciarSesion.setVisible(false); // Oculto por defecto
        botonIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonIniciarSesion.addActionListener(e -> {
            this.setContentPanel(new inicioSesionInterface(this)); // Cambia a la interfaz de inicio de sesión
            // Puedes añadir lógica para remover el botón del topPanel si lo agregaste dinámicamente.
        });*/
        //topPanel.add(botonIniciarSesion, BorderLayout.EAST);

        // Añade el topPanel directamente al JFrame en la posición NORTH
        this.add(topPanel, BorderLayout.NORTH);

        // Inicializa el panel que contendrá el contenido dinámico, manteniendo el nombre MyPanel
        this.MyPanel = new JPanel();
        this.MyPanel.setLayout(new BorderLayout()); // Usa BorderLayout para el contenido
        this.MyPanel.setBackground(Color.decode("#ffffff")); 
        
        // Añade el MyPanel al centro del JFrame.
        this.add(MyPanel, BorderLayout.CENTER);

        // El 'floatingMenuPanel' se asignará externamente a través de setFloatingMenuPanel()
    }

    /**
     * Reemplaza el contenido actual de MyPanel con un nuevo JPanel.
     * Este es el método clave para cambiar de interfaces sin abrir nuevas ventanas.
     * @param newPanel El JPanel que representa la nueva interfaz/vista a mostrar.
     */
    public void setContentPanel(JPanel newPanel) {
        MyPanel.removeAll(); // Elimina todos los componentes del panel actual
        MyPanel.add(newPanel, BorderLayout.CENTER); // Añade el nuevo panel
        MyPanel.revalidate(); // Recalcula el layout
        MyPanel.repaint(); // Redibuja el panel
    }

    // Métodos getter
    public JPanel getMyPanel() { // Getter para MyPanel
        return MyPanel;
    }

    public JPanel getTopPanel() {
        return topPanel;
    }

    public JLabel getLogoUCVeast() {
        return logoUCVeast;
    }

    public void mostrarVentana() {
        setVisible(true);
    }

    public void ocultarVentana() {
        setVisible(false);
    }

    
    //intento de colocar el boton de inicio de sesion en el top panel de registro
    /*public void setVisibleInicioButton (){
        // Este método se puede usar para mostrar u ocultar el botón de inicio de sesión
        for (Component comp : topPanel.getComponents()) {
            if (comp instanceof BotonPanel) {
                comp.setVisible(true); // Muestra el botón de inicio de sesión
            
                this.getTopPanel().add(comp, BorderLayout.EAST);
                this.getTopPanel().revalidate(); // Recalcula el layout del topPanel
                this.getTopPanel().repaint(); // Redibuja el topPanel
            }
        }
    }*/

    /**
     * Establece el panel de menú flotante externo que MyFrame gestionará.
     * Debe llamarse antes de usar el botón de menú para que el panel se active.
     * @param panel El JPanel (tu MenuUsuarioPanel) que actuará como menú flotante.
     */
    public void setFloatingMenuPanel(JPanel panel) {
        this.floatingMenuPanel = panel;
        // Añadir el panel al JLayeredPane del frame para que esté "encima de todo"
        getLayeredPane().add(floatingMenuPanel, JLayeredPane.PALETTE_LAYER);
        floatingMenuPanel.setVisible(false); // Asegura que esté oculto al inicio
    }

    public void addMenuButton ( String rutaIcono, ActionListener accion) {
        URL imageUrl = getClass().getResource(rutaIcono);
        if (imageUrl != null) {
            ImageIcon icono = new ImageIcon(imageUrl);
            menuButonLabel.setIcon(icono);
            
            // Remueve listeners anteriores para evitar duplicados
            for (MouseListener ml : menuButonLabel.getMouseListeners()) {
                if (ml instanceof MouseAdapter) {
                    menuButonLabel.removeMouseListener(ml);
                }
            }

            menuButonLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Si el panel flotante ha sido establecido, alternamos su visibilidad
                    if (floatingMenuPanel != null) {
                        toggleFloatingMenu();
                    } else {
                        System.err.println("Advertencia: El panel de menú flotante no ha sido establecido. Llama a setFloatingMenuPanel() primero.");
                    }
                }
            });
            menuButonLabel.setVisible(true);
            topPanel.revalidate();
            topPanel.repaint();
        } else {
            System.err.println("Error: Icono de menú no encontrado en el classpath: " + rutaIcono);
        }
    }

    /**
     * Desactiva y oculta el botón de menú.
     */
    public void removeMenuButton() {
        menuButonLabel.setIcon(null);
        menuButonLabel.setVisible(false);
        
        // Remueve todos los MouseListeners para limpiar
        for (MouseListener ml : menuButonLabel.getMouseListeners()) {
             if (ml instanceof MouseAdapter) {
                 menuButonLabel.removeMouseListener(ml);
             }
        }
        topPanel.revalidate(); 
        topPanel.repaint();
    }

    /**
     * Alterna la visibilidad del panel de menú flotante.
     * Al hacerlo visible, lo posiciona cerca del botón de menú.
     */
    private void toggleFloatingMenu() {
        // Asegurarse de que el panel ha sido establecido antes de intentar manipularlo
        if (floatingMenuPanel == null) {
            System.err.println("Error: No se puede alternar el menú flotante, no ha sido establecido.");
            return;
        }

        isFloatingMenuVisible = !isFloatingMenuVisible;
        floatingMenuPanel.setVisible(isFloatingMenuVisible);

        if (isFloatingMenuVisible) {
            // Posicionar el panel flotante en relación al botón de menú
            Point buttonLocationInFrame = SwingUtilities.convertPoint(menuButonLabel.getParent(), menuButonLabel.getLocation(), MyFrame.this.getLayeredPane());
            
            // Calcula la posición para que el menú aparezca a la derecha del botón
            // y justo debajo del topPanel.
            int x = buttonLocationInFrame.x - floatingMenuPanel.getPreferredSize().width + menuButonLabel.getWidth(); 
            int y = topPanel.getHeight(); 

            floatingMenuPanel.setBounds(x, y, 
                                        floatingMenuPanel.getPreferredSize().width, 
                                        floatingMenuPanel.getPreferredSize().height);
            floatingMenuPanel.revalidate();
            floatingMenuPanel.repaint();
            
            // Añadir MouseListener al JLayeredPane para ocultar al hacer clic fuera del menú o del botón
            getLayeredPane().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Point clickPointInPanel = SwingUtilities.convertPoint(MyFrame.this.getLayeredPane(), e.getPoint(), floatingMenuPanel);
                    
                    if (isFloatingMenuVisible && !floatingMenuPanel.contains(clickPointInPanel)) {
                        Point clickPointInButton = SwingUtilities.convertPoint(MyFrame.this.getLayeredPane(), e.getPoint(), menuButonLabel);
                        if (!menuButonLabel.contains(clickPointInButton)) {
                            toggleFloatingMenu(); // Oculta el menú
                            MyFrame.this.getLayeredPane().removeMouseListener(this); // Remueve este MouseListener
                        }
                    }
                }
            });

        } else {
            // Si el menú se está ocultando, removemos el MouseListener de clic fuera
            for (MouseListener ml : getLayeredPane().getMouseListeners()) {
                if (ml instanceof MouseAdapter && ml.getClass().getName().contains("$") && ml.getClass().getName().contains("MyFrame")) { 
                    getLayeredPane().removeMouseListener(ml);
                }
            }
        }
    }
}