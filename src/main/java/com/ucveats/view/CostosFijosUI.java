/*
 * intentar poner un manejador de layout 
 */

package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CostosFijosUI extends JPanel {
    private final JTextField campoManoObra, campoMantenimiento, campoAlquiler;
    private final JLabel totalLabel;
    private final BotonPanel btnGuardar;
    private MyFrame parentFrame; // Referencia a la ventana principal

    public CostosFijosUI(MyFrame frame) {
        this.parentFrame = frame; // Guarda la referencia a la ventana principal
    
        // Panel this
        this.setLayout(null);
        this.setBackground(Color.decode("#f4f6f8"));

        
        totalLabel = new JLabel("Costo Actual Bs. 0.00");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        totalLabel.setForeground(Color.decode("#2f3829"));
        totalLabel.setBounds(60, 30, 340, 30);
        this.add(totalLabel);

        JLabel tituloRegistro = new JLabel("Registrar Nuevos Costos Fijos");
        tituloRegistro.setFont(new Font("Montserrat", Font.BOLD, 16));
        tituloRegistro.setBounds(60, 80, 300, 25);
        this.add(tituloRegistro);

        campoManoObra = crearCampo("Mano de Obra:", 130, this);
        campoMantenimiento = crearCampo("Mantenimiento:", 180, this);
        campoAlquiler = crearCampo("Alquiler:", 230, this);

        btnGuardar = new BotonPanel("Guardar Costos");
        btnGuardar.setBounds(100, 300, 180, 40);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        this.add(btnGuardar);
    }

    private JTextField crearCampo(String etiqueta, int y, JPanel panel) {
        JLabel label = new JLabel(etiqueta);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setBounds(40, y, 120, 25);
        panel.add(label);

        JTextField campo = new JTextField();
        campo.setBounds(180, y, 150, 25);
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(campo);
        return campo;
    }

    // --- MÉTODOS PARA EL CONTROLADOR EXTERNO ---

    public String getManoObra() { return campoManoObra.getText(); }
    public String getMantenimiento() { return campoMantenimiento.getText(); }
    public String getAlquiler() { return campoAlquiler.getText(); }       

        public void addGuardarListener(ActionListener listener) {
        btnGuardar.addActionListener(listener);
    }

    public void setTotal(double total) {
        totalLabel.setText(String.format("Costo Actual Bs. %.2f", total));
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
}
