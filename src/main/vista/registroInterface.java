import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class registroInterface extends JFrame {

    public registroInterface() {
        setTitle("Registro UCV Eats");
        setSize(380, 580);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#f4f6f8"));

        // Panel Superior
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#353535"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("UCVeats");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logo.setForeground(Color.decode("#ffffff"));

        JLabel seccion = new JLabel("Registro", SwingConstants.RIGHT);
        seccion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        seccion.setForeground(Color.decode("#ffffff"));
        Border borde = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#ffffff"), 2, true),
                BorderFactory.createEmptyBorder(2, 6, 2, 6));
        seccion.setBorder(borde);

        topPanel.add(logo, BorderLayout.WEST);
        topPanel.add(seccion, BorderLayout.EAST);

        // Panel Central
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(null);
        centralPanel.setBackground(Color.decode("#ffffff"));

        JLabel titulo = new JLabel("REGISTRO DE USUARIO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setBounds(70, 30, 250, 25);
        centralPanel.add(titulo);

        int y = 80;
        String[] campos = {"Nombre", "Apellido", "Cédula", "Teléfono", "Correo", "Contraseña", "Confirmar Contraseña"};
        JTextField[] entradas = new JTextField[campos.length];

        for (int i = 0; i < campos.length; i++) {
            JLabel etiqueta = new JLabel(campos[i] + ":");
            etiqueta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            etiqueta.setBounds(40, y, 130, 25);
            centralPanel.add(etiqueta);

            JTextField campo;
            if (campos[i].toLowerCase().contains("contraseña")) {
                campo = new JPasswordField();
            } else {
                campo = new JTextField();
            }
            campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            campo.setBounds(180, y, 150, 25);
            entradas[i] = campo;
            centralPanel.add(campo);
            y += 40;
        }

        JButton botonRegistrar = new JButton("REGISTRAR");
        botonRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrar.setBackground(Color.decode("#87cc2e"));
        botonRegistrar.setForeground(Color.decode("#2f3829"));
        botonRegistrar.setFocusPainted(false);
        botonRegistrar.setBounds(80, y + 10, 200, 40);
        botonRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonRegistrar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        botonRegistrar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "¡Registro exitoso!");
        });
        centralPanel.add(botonRegistrar);

        add(topPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        new registroInterface();
    }
}
