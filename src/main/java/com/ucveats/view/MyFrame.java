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
    
    private JPanel MyPanel, topPanel;
    private JLabel logoUCVeast;
    private JLabel menuButonLabel;

    // --- Nuevo: Referencia al panel de menú flotante ---
    private JPanel floatingMenuPanel; // Usaremos 'JPanel' ya que MenuUsuarioPanel extiende JPanel
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
        
        this.MyPanel = new JPanel();
        MyPanel.setLayout(new BorderLayout());
        MyPanel.setBackground(Color.decode("#ffffff")); 

        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#353535"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        logoUCVeast = new JLabel("UCVeats");
        logoUCVeast.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoUCVeast.setForeground(Color.decode("#ffffff"));
        topPanel.add(logoUCVeast, BorderLayout.WEST);
        
        menuButonLabel = new JLabel();
        menuButonLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuButonLabel.setVisible(false); // Oculto por defecto
        topPanel.add(menuButonLabel, BorderLayout.EAST);

        this.add(MyPanel);
        this.MyPanel.add(topPanel, BorderLayout.NORTH);
        
        // --- Importante: No inicializamos 'floatingMenuPanel' aquí ---
        // Se asignará externamente a través de setFloatingMenuPanel()
    }

    public JPanel getMyPanel() {
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
                    // La 'accion' original del botón se puede ejecutar aquí si quieres una acción adicional,
                    // además de mostrar/ocultar el menú. Si solo quieres alternar el menú, puedes eliminar esta línea.
                    // accion.actionPerformed(new ActionEvent(menuButonLabel, ActionEvent.ACTION_PERFORMED, "menuButton"));
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
     * Al hacer visible, lo posiciona cerca del botón de menú.
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
            // (Obtenido de la posición en pantalla del botón)
            Point buttonLocationInFrame = SwingUtilities.convertPoint(menuButonLabel.getParent(), menuButonLabel.getLocation(), MyFrame.this.getLayeredPane());
            
            // Calcula la posición para que el menú aparezca a la derecha del botón
            // y justo debajo del topPanel.
            // Puedes ajustar 'x' y 'y' para moverlo a tu gusto.
            int x = buttonLocationInFrame.x - floatingMenuPanel.getPreferredSize().width + menuButonLabel.getWidth(); // Alinea a la derecha del botón
            int y = topPanel.getHeight(); 

            floatingMenuPanel.setBounds(x, y, 
                                        floatingMenuPanel.getPreferredSize().width, 
                                        floatingMenuPanel.getPreferredSize().height);
            floatingMenuPanel.revalidate();
            floatingMenuPanel.repaint();
            
            // --- Añadir MouseListener al JLayeredPane para ocultar al hacer clic fuera ---
            // Es mejor añadir el listener al JLayeredPane o al contentPane del frame,
            // ya que son los que cubren toda la ventana y capturan clics "fuera" del menú.
            // Para evitar conflictos con otros listeners, se puede usar un listener específico.
            getLayeredPane().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Convertir el punto de clic al sistema de coordenadas del panel flotante
                    Point clickPointInPanel = SwingUtilities.convertPoint(MyFrame.this.getLayeredPane(), e.getPoint(), floatingMenuPanel);
                    
                    // Si el menú está visible y el clic no fue dentro del panel flotante
                    if (isFloatingMenuVisible && !floatingMenuPanel.contains(clickPointInPanel)) {
                        // También verificar si el clic no fue en el botón de menú mismo
                        Point clickPointInButton = SwingUtilities.convertPoint(MyFrame.this.getLayeredPane(), e.getPoint(), menuButonLabel);
                        if (!menuButonLabel.contains(clickPointInButton)) {
                             toggleFloatingMenu(); // Oculta el menú
                             // Remover este MouseListener del JLayeredPane después de ocultar
                             MyFrame.this.getLayeredPane().removeMouseListener(this); 
                        }
                    }
                }
            });

        } else {
            // Si el menú se está ocultando, removemos el MouseListener de clic fuera que se agregó
            // Solo removemos la instancia específica del MouseAdapter temporal
            for (MouseListener ml : getLayeredPane().getMouseListeners()) {
                if (ml instanceof MouseAdapter && ml.getClass().getName().contains("$") && ml.getClass().getName().contains("MyFrame")) { 
                    getLayeredPane().removeMouseListener(ml);
                }
            }
        }
    }
}