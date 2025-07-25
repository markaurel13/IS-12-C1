/*
 * intenter poner un manejador de layout 
 */

package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.ucveats.controller.costoFijoService;
import com.ucveats.controller.costoFijoService;

public class CostosFijosUI extends MyFrame {
    private final JTextField campoManoObra, campoMantenimiento, campoAlquiler;
    private final JLabel totalLabel;
    double totalCostosFijos = 0.0; // Inicializar el total de costos
    private final costoFijoService servicioCostos;

    public CostosFijosUI() {
        super("Registro de Costos Fijos");
        

        // Panel contentPanel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBackground(Color.decode("#f4f6f8"));

        
        totalLabel = new JLabel("Costo Actual Bs. " + totalCostosFijos);
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        totalLabel.setForeground(Color.decode("#2f3829"));
        totalLabel.setBounds(60, 30, 340, 30);
        contentPanel.add(totalLabel);

        JLabel tituloRegistro = new JLabel("Registrar Nuevos Costos Fijos");
        tituloRegistro.setFont(new Font("Montserrat", Font.BOLD, 16));
        tituloRegistro.setBounds(60, 80, 300, 25);
        contentPanel.add(tituloRegistro);

        campoManoObra = crearCampo("Mano de Obra:", 130, contentPanel);
        campoMantenimiento = crearCampo("Mantenimiento:", 180, contentPanel);
        campoAlquiler = crearCampo("Alquiler:", 230, contentPanel);

        JButton btnGuardar = new JButton("Guardar Costos");
        btnGuardar.setBounds(100, 300, 180, 40);
        btnGuardar.setBackground(Color.decode("#87cc2e"));
        btnGuardar.setForeground(Color.decode("#2f3829"));
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnGuardar.addActionListener(this::guardarCostos);
        contentPanel.add(btnGuardar);


        this.servicioCostos = new costoFijoService();
        totalCostosFijos = servicioCostos.getCostoFijoTotal();
        totalLabel.setText("Costo Actual Bs. " + totalCostosFijos);




        // --- Activar el botón de menú y el panel flotante ---
        //Crea una instancia de tu MenuUsuarioPanel
        MenuAdminPanel menuAdminPanel = new MenuAdminPanel();
        // Pásale esta instancia a MyFrame para que MyFrame la gestione como panel flotante
        setFloatingMenuPanel(menuAdminPanel); 
        
        // Activa el botón de menú en el topPanel de MyFrame
        // La acción de este botón será simplemente alternar la visibilidad del 'menuUsuarioPanel'.
        addMenuButton("/icono_lineas.png", e -> {
            // No necesitas añadir lógica aquí si toggleFloatingMenu() ya hace el trabajo.
            // Puedes añadir un System.out.println() si es solo para depurar.
            // System.out.println("Botón de menú en 'Ver Menú' clicado.");
        });

        
        getMyPanel().add(contentPanel, BorderLayout.CENTER); // Añadir el contentPanel al MyPanel de MyFrame

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

    private void guardarCostos(ActionEvent e) {
        try {
            double manoObra = Double.parseDouble(campoManoObra.getText());
            double mantenimiento = Double.parseDouble(campoMantenimiento.getText());
            double alquiler = Double.parseDouble(campoAlquiler.getText());

            servicioCostos.guardarCostos(manoObra, mantenimiento, alquiler);
            totalCostosFijos = servicioCostos.getCostoFijoTotal();
            totalLabel.setText("Costo Actual Bs. " + totalCostosFijos);

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
        } catch (Exception ignored) {}
        new CostosFijosUI().mostrarVentana();
    }
}
