import javax.swing.*;
import java.awt.*;

public class VerMenuInterface {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        MyFrame frame = new MyFrame("Menú Disponible");
        frame.setSize(380, 580);
        frame.getMyPanel().setLayout(new BorderLayout());
        frame.getMyPanel().setBackground(Color.decode("#ffffff"));

        // Panel Superior: logo izquierda
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#353535"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logoUCV = new JLabel("UCVeats");
        logoUCV.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoUCV.setForeground(Color.decode("#ffffff"));

        topPanel.add(logoUCV, BorderLayout.WEST);
        frame.getMyPanel().add(topPanel, BorderLayout.NORTH);

        // Panel Central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.decode("#ffffff"));

        JLabel labelTitulo = new JLabel("Menú del Día");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        ImageIcon iconoPlato = new ImageIcon("src/assets/plato.png");
        Image imagen = iconoPlato.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        JLabel labelIcono = new JLabel(new ImageIcon(imagen));
        labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelNombrePato = new JLabel("Pato a la naranja");
        labelNombrePato.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNombrePato.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelNombrePato.setForeground(Color.decode("#2f3829"));

        JLabel labelPrecioPato = new JLabel("Precio: $10.00");
        labelPrecioPato.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelPrecioPato.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        labelPrecioPato.setForeground(Color.decode("#2f3829"));

        JLabel labelDescripcionPato = new JLabel("Delicioso pato con salsa de naranja");
        labelDescripcionPato.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDescripcionPato.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelDescripcionPato.setForeground(Color.decode("#2f3829"));

        JCheckBox checkPlato = new JCheckBox("Seleccionar plato");
        checkPlato.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkPlato.setBackground(Color.decode("#ffffff"));
        checkPlato.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        checkPlato.setForeground(Color.decode("#2f3829"));

        JButton botonSeleccionar = new JButton("Seleccionar");
        botonSeleccionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonSeleccionar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonSeleccionar.setBackground(Color.decode("#87cc2e"));
        botonSeleccionar.setForeground(Color.decode("#2f3829"));
        botonSeleccionar.setFocusPainted(false);
        botonSeleccionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonSeleccionar.setMaximumSize(new Dimension(220, 40));
        botonSeleccionar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        botonSeleccionar.addActionListener(e -> {
            if (checkPlato.isSelected()) {
                JOptionPane.showMessageDialog(frame, "✅ Plato seleccionado", "Proceso exitoso", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Debes seleccionar el plato primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        centerPanel.add(labelTitulo);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(labelIcono);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(labelNombrePato);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelPrecioPato);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelDescripcionPato);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(checkPlato);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(botonSeleccionar);

        frame.getMyPanel().add(centerPanel, BorderLayout.CENTER);
        frame.mostrarVentana();
    }
}
