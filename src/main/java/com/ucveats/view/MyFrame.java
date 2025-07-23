package main.vista;
import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    // --- Configuraciones Predeterminadas (pueden personalizarse) ---
    private static final int DEFAULT_WIDTH = 380;  // Ancho predeterminado de la ventana
    private static final int DEFAULT_HEIGHT = 580; // Alto predeterminado
    private static final String DEFAULT_TITLE = "Configura el titulo de la ventana"; // Título por defecto
    
    private JPanel MyPanel, topPanel;
    private JLabel logoUCVeast;


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
        MyPanel.setLayout(new BorderLayout());
        MyPanel.setBackground(Color.decode("#ffffff")); 


        topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#353535"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        logoUCVeast = new JLabel("UCVeats");
        logoUCVeast.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoUCVeast.setForeground(Color.decode("#ffffff"));
        topPanel.add(logoUCVeast, BorderLayout.WEST);


        this.add(MyPanel);
        this.MyPanel.add(topPanel, BorderLayout.NORTH);
        
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
}
