/*
 * intenter poner un manejador de layout 
 */

package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import com.ucveats.controller.costoFijoService;


public class CostosFijosUI extends JPanel {
    private final JTextField campoManoObra, campoMantenimiento, campoAlquiler;
    private final JLabel totalLabel;
    double totalCostosFijos = 0.0; // Inicializar el total de costos
    private final costoFijoService servicioCostos;
    private MyFrame parentFrame; // Referencia a la ventana principal

    public CostosFijosUI(MyFrame frame) {
        this.parentFrame = frame; // Guarda la referencia a la ventana principal
        
        

        // Panel this
        /*JPanel this = new JPanel();*/
        this.setLayout(null);
        this.setBackground(Color.decode("#f4f6f8"));

        
        totalLabel = new JLabel("Costo Actual Bs. " + totalCostosFijos);
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

        BotonPanel btnGuardar = new BotonPanel("Guardar Costos");
        btnGuardar.setBounds(100, 300, 180, 40);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnGuardar.addActionListener(this::guardarCostos);
        this.add(btnGuardar);


        this.servicioCostos = new costoFijoService();
        totalCostosFijos = servicioCostos.getCostoFijoTotal();
        totalLabel.setText("Costo Actual Bs. " + totalCostosFijos);




        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });

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

    /*public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        new CostosFijosUI().mostrarVentana();
    }*/
}
