package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.ucveats.controller.costoFijoService;

public class CostosFijosUI extends JFrame {
    private final JTextField campoManoObra, campoMantenimiento, campoAlquiler;
    private final JLabel totalLabel;
    private final costoFijoService servicioCostos;

    public CostosFijosUI() {
        
        setTitle("Registro de Costos Fijos");
        setSize(380, 580);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.decode("#f4f6f8"));

        // Panel Superior: logo izquierda + saldo derecha
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.decode("#353535"));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logoUCV = new JLabel("UCVeats");
        logoUCV.setFont(new Font("Segoe UI", Font.BOLD, 20));
        logoUCV.setForeground(Color.decode("#ffffff"));

        totalLabel = new JLabel("Total: Bs. 0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        totalLabel.setForeground(Color.WHITE);
        totalLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.white, 2, true),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        topPanel.add(logoUCV, BorderLayout.WEST);
        topPanel.add(totalLabel, BorderLayout.EAST);

        // Panel central
        JPanel central = new JPanel(null);
        central.setBackground(Color.decode("#f4f6f8"));

        JLabel titulo = new JLabel("Registrar Costos Fijos");
        titulo.setFont(new Font("Montserrat", Font.BOLD, 18));
        titulo.setBounds(60, 30, 300, 25);
        central.add(titulo);

        campoManoObra = crearCampo("Mano de Obra:", 80, central);
        campoMantenimiento = crearCampo("Mantenimiento:", 130, central);
        campoAlquiler = crearCampo("Alquiler:", 180, central);

        JButton btnGuardar = new JButton("Guardar Costos");
        btnGuardar.setBounds(100, 250, 180, 40);
        btnGuardar.setBackground(Color.decode("#87cc2e"));
        btnGuardar.setForeground(Color.decode("#2f3829"));
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnGuardar.addActionListener(this::guardarCostos);
        central.add(btnGuardar);

        add(topPanel, BorderLayout.NORTH);
        add(central, BorderLayout.CENTER);

this.servicioCostos = new costoFijoService();
double total = servicioCostos.getCostoFijoTotal();
    totalLabel.setText(String.format("Total: Bs. %.2f", total));

        setVisible(true);
        
    }

    private JTextField crearCampo(String etiqueta, int y, JPanel panel) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        label.setBounds(40, y, 120, 25);
        panel.add(label);

        JTextField campo = new JTextField();
        campo.setBounds(180, y, 150, 25);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(campo);
        return campo;
    }

    private void guardarCostos(ActionEvent e) {
        try {
            double manoObra = Double.parseDouble(campoManoObra.getText());
            double mantenimiento = Double.parseDouble(campoMantenimiento.getText());
            double alquiler = Double.parseDouble(campoAlquiler.getText());
            
            servicioCostos.guardarCostos(manoObra, mantenimiento, alquiler);
            double total = servicioCostos.getCostoFijoTotal();
            totalLabel.setText(String.format("Total: Bs. %.2f", total));

            /*JOptionPane.showMessageDialog(this,
                    "✅ ¡Costos registrados exitosamente! ✅" +
                            "\n Mano de Obra: (" + manoObra + ")" +
                            "\n Mantenimiento: (" + mantenimiento + ")" +
                            "\n Alquiler: (" + alquiler + ")"

                    , "Proceso Exitoso", JOptionPane.PLAIN_MESSAGE); */

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ERROR: Valor inválido. Intente de nuevo.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        new CostosFijosUI();
    }
}
