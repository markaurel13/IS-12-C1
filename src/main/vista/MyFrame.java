package main.vista;
import javax.swing.*;

public class MyFrame extends JFrame {

    // --- Configuraciones Predeterminadas (pueden personalizarse) ---
    private static final int DEFAULT_WIDTH = 600;  // Ancho predeterminado de la ventana
    private static final int DEFAULT_HEIGHT = 600; // Alto predeterminado
    private static final String DEFAULT_TITLE = "Configura el titulo de la ventana"; // Título por defecto
    
    private JPanel MyPanel;


     public MyFrame() {
        this(DEFAULT_TITLE, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MyFrame(String title) {
        this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public MyFrame(String title, int width, int heigth) {
        super(title);
        this.setSize(width, heigth);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.MyPanel = new JPanel();
        this.add(MyPanel);
        
    }

    public JPanel getMyPanel() {
    return MyPanel;
    }

    public void mostrarVentana() {
        setVisible(true);
    }

    public void ocultarVentana() {
        setVisible(false);
    }
}
