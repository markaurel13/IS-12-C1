/*
 * hay que revisar la conexion de esta interface con el monedero
 * ademas de podria extraer la funcion de validar el campo como una utilities para reusarla en los distintos formularios
 */
package com.ucveats.view;

import javax.swing.*;
import java.awt.*;


public class MonederoUI extends JPanel { 
    // private final MonederoVirtual monedero; // Mantenido comentado
    private final JLabel saldoLabel;
    private final JTextField campoMonto;

    double saldoDisponible = 0.0; // Inicializar el saldo disponible

    private MyFrame parentFrame; // Necesitamos una referencia a MyFrame

    public MonederoUI(MyFrame frame) {
        this.parentFrame = frame;

 
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        this.setBackground(Color.decode("#ffffff"));

        // --- Componentes de la interfaz del Monedero ---
        JLabel tituloPanel = new JLabel("Disponible (BsS.)");
        tituloPanel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        tituloPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        saldoLabel = new JLabel(String.format("%.2f", saldoDisponible));
        saldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 80));
        saldoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelRecarga = new JPanel();
        panelRecarga.setBackground(Color.decode("#ffffff"));
        panelRecarga.setLayout(new FlowLayout());
        panelRecarga.setMaximumSize(new Dimension(540, 40));
        JLabel etiquetaMonto = new JLabel("Monto a recargar:");
        etiquetaMonto.setFont(new Font("Segoe ui", Font.ITALIC, 14));
        panelRecarga.add(etiquetaMonto);

        campoMonto = new JTextField();
        campoMonto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        campoMonto.setPreferredSize(new Dimension(150, 25));
        panelRecarga.add(campoMonto);

        BotonPanel btnRecargar = new BotonPanel("Recargar Saldo", 200, 40, e -> validarCampo());
        btnRecargar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRecargar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnRecargar.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(10));
        this.add(tituloPanel);
        this.add(Box.createVerticalStrut(5));
        this.add(saldoLabel);
        this.add(Box.createVerticalStrut(15));
        this.add(panelRecarga);
        this.add(Box.createVerticalStrut(10));
        this.add(btnRecargar);
        this.add(Box.createVerticalStrut(50));

        // --- Activar el botón de menú en el topPanel de MyFrame ---
        // Este código activa el botón de menú en la barra superior cuando esta vista está activa.
        // La instancia de MenuUsuarioPanel y su asociación con MyFrame ya se hacen una vez en App.java.
        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });
    }

    private void simularPagoMovil() {
        JProgressBar barra = new JProgressBar();
        barra.setIndeterminate(true);
        barra.setPreferredSize(new Dimension(300, 20));
        JLabel label = new JLabel("Procesando Pago Movil");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(barra, BorderLayout.CENTER);

        // Es importante pasar el parentFrame como 'owner' del JDialog
        JDialog dialog = new JDialog(parentFrame, "Procesador de Pago", true); 
        dialog.getContentPane().add(panel);
        dialog.setSize(300, 63);
        dialog.setLocationRelativeTo(parentFrame); // Centrar en el MyFrame principal
        dialog.setUndecorated(true);

        Timer timer = new Timer(2000, event -> {
            dialog.dispose();
        });

        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void validarCampo() {
        String valorIngresado = campoMonto.getText().trim();
        if (valorIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ERROR: Campo vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            valorIngresado = valorIngresado.replace(",", "."); // Reemplazar coma por punto para el formato decimal

            if (!valorIngresado.matches("^-?\\d+(\\.\\d+)?$")) {
                JOptionPane.showMessageDialog(this, "ERROR: Campo inválido. Solo se permiten números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else if (Double.parseDouble(valorIngresado) <= 0) {
                JOptionPane.showMessageDialog(this, "ERROR: Monto inválido. Debe ser mayor a 0.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                simularPagoMovil();
                JOptionPane.showMessageDialog(this, "¡Pago Movil exitoso! Pago por Bs. " + valorIngresado, "Proceso Completado", JOptionPane.PLAIN_MESSAGE);
                saldoDisponible += Double.parseDouble(valorIngresado);
                saldoLabel.setText(String.format("%.2f", saldoDisponible));
            }
        }
        campoMonto.setText(""); // Limpiar el campo después de procesar el pago
    }

}