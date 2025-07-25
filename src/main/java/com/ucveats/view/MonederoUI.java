package com.ucveats.view;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.ucveats.model.MonederoVirtual;

public class MonederoUI extends MyFrame {
    private final MonederoVirtual monedero;
    private final JLabel saldoLabel;
    private final JTextField campoMonto;

    public MonederoUI() {
        monedero = new MonederoVirtual();

        setTitle("💳 Monedero Virtual UCVeats");
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

        JLabel logoUCV = new JLabel("UCVeats");
        logoUCV.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoUCV.setForeground(Color.decode("#ffffff"));

        saldoLabel = new JLabel("SALDO: Bs. 0.00", SwingConstants.RIGHT);
        saldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        saldoLabel.setForeground(Color.decode("#ffffff"));
        Border borde = BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#ffffff"), 2, true),
                BorderFactory.createEmptyBorder(2, 6, 2, 6));
        saldoLabel.setBorder(borde);

        topPanel.add(logoUCV, BorderLayout.WEST);
        topPanel.add(saldoLabel, BorderLayout.EAST);

        // Panel Central
        JPanel centralPanel = new JPanel(null);
        centralPanel.setBackground(Color.decode("#ffffff"));

        JLabel titulo = new JLabel("RECARGA DE MONEDERO");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setBounds(50, 70, 280, 25);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        centralPanel.add(titulo);

        JLabel etiquetaMonto = new JLabel("Monto a recargar:");
        etiquetaMonto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        etiquetaMonto.setBounds(40, 120, 150, 25);
        centralPanel.add(etiquetaMonto);

        campoMonto = new JTextField();
        campoMonto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        campoMonto.setBounds(180, 120, 150, 25);
        centralPanel.add(campoMonto);

        JButton btnRecargar = new JButton("RECARGAR");
        btnRecargar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRecargar.setBackground(Color.decode("#87cc2e"));
        btnRecargar.setForeground(Color.decode("#2f3829"));
        btnRecargar.setFocusPainted(false);
        btnRecargar.setBounds(73, 170, 220, 40);
        btnRecargar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRecargar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnRecargar.addActionListener(this::recargarSaldo);
        centralPanel.add(btnRecargar);

        JLabel textoPago = new JLabel("PAGO DE BANDEJA");
        textoPago.setFont(new Font("Segoe UI", Font.BOLD, 16));
        textoPago.setBounds(100, 240, 170, 25);
        textoPago.setHorizontalAlignment(SwingConstants.CENTER);
        centralPanel.add(textoPago);

        JButton btnFacial = new JButton("PAGAR BANDEJA");
        btnFacial.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnFacial.setBackground(Color.decode("#87cc2e"));
        btnFacial.setForeground(Color.decode("#2f3829"));
        btnFacial.setFocusPainted(false);
        btnFacial.setBounds(70, 280, 220, 40);
        btnFacial.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnFacial.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFacial.addActionListener(this::simularPagoFacial);
        centralPanel.add(btnFacial);

        add(topPanel, BorderLayout.NORTH);
        add(centralPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void recargarSaldo(ActionEvent e) {
        try {
            double monto = Double.parseDouble(campoMonto.getText());
            if (monto <= 0) {
                throw new NumberFormatException();
            }
            monedero.recargar(monto);
            campoMonto.setText("");
            actualizarSaldo();
            JOptionPane.showMessageDialog(this, "¡Recarga exitosa! Agregaste Bs. " + String.format("%.2f", monto));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Campo vacío o monto inválido.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void simularPagoFacial(ActionEvent e) {
        JProgressBar barra = new JProgressBar();
        barra.setIndeterminate(true);
        barra.setPreferredSize(new Dimension(300, 20));
        JLabel label = new JLabel("Escaneando rostro...");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(barra, BorderLayout.CENTER);

        JDialog dialog = new JDialog(this, "Escaneo Facial", true);
        dialog.getContentPane().add(panel);
        dialog.setSize(300, 30);
        dialog.setLocationRelativeTo(this);
        dialog.setUndecorated(true);

        Timer timer = new Timer(2000, event -> {
            dialog.dispose();
            procesarPagoFacial();
        });

        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void procesarPagoFacial() {
        double costoBandeja = 1000;
        if (monedero.pagarBandeja(costoBandeja)) {
            actualizarSaldo();
            JOptionPane.showMessageDialog(this, "✅ ¡Escaneo exitoso! Pago por Bs. " + costoBandeja + " ✅", "Proceso Completado", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "ERROR: Saldo insuficiente. Costo de bandeja: Bs." + costoBandeja, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void actualizarSaldo() {
        saldoLabel.setText("Saldo: BsS. " + String.format("%.2f", monedero.getSaldo()));
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        new MonederoUI();
    }
}
