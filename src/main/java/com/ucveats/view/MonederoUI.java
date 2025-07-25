/*
* Averiguar porque el boton no se le configura el alto y porque queda tan abajo 
* se comentaron los elementos que se comunican con monedeoVirtual porque daban errores en el archivo Monedero Virtual
* se quito el pago de bandeja porque eso no se paga aqui, eso se paga al momento de ingresar al comedor 
*/
package com.ucveats.view;
import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
//import com.ucveats.model.MonederoVirtual;
//import java.text.DecimalFormat;

public class MonederoUI extends MyFrame {
    //private final MonederoVirtual monedero;
    private final JLabel saldoLabel;
    private final JTextField campoMonto;

    double saldoDisponible = 0.0; // Inicializar el saldo disponible

    public MonederoUI() {
        super("Monedero Virtual UCVeats 💳");

        //monedero = new MonederoVirtual();


        // Panel content
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.decode("#ffffff"));

        JLabel tituloPanel = new JLabel("Disponible (BsS.)");
        tituloPanel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        tituloPanel.setAlignmentX(Component.CENTER_ALIGNMENT);


        saldoLabel = new JLabel(String.format("%.2f", saldoDisponible));
        saldoLabel.setFont(new Font("Segoe UI", Font.BOLD, 80));
        saldoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel panelRecarga = new JPanel();
        panelRecarga.setBackground(Color.decode("#ffffff"));
        panelRecarga.setLayout(new FlowLayout());
        panelRecarga.setPreferredSize(new Dimension(540, 40));
        JLabel etiquetaMonto = new JLabel("Monto a recargar:");
        etiquetaMonto.setFont(new Font("Segoe ui", Font.ITALIC, 14));
        panelRecarga.add(etiquetaMonto);

        campoMonto = new JTextField();
        campoMonto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        campoMonto.setPreferredSize(new Dimension(150, 25));
        panelRecarga.add(campoMonto);



        BotonPanel btnRecargar = new BotonPanel("Recargar Saldo", 200, 40 , e -> validarCampo());
        btnRecargar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnRecargar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        btnRecargar.setAlignmentX(Component.CENTER_ALIGNMENT);



        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(tituloPanel);
        contentPanel.add(Box.createVerticalStrut(5));
        contentPanel.add(saldoLabel);
        contentPanel.add(Box.createVerticalStrut(15));
        contentPanel.add(panelRecarga);
        contentPanel.add(Box.createVerticalStrut(10));
        contentPanel.add(btnRecargar);
        contentPanel.add(Box.createVerticalStrut(50));

        // --- Activar el botón de menú y el panel flotante ---
        //Crea una instancia de tu MenuUsuarioPanel
        MenuUsuarioPanel menuUsuarioPanel = new MenuUsuarioPanel();
        // Pásale esta instancia a MyFrame para que MyFrame la gestione como panel flotante
        setFloatingMenuPanel(menuUsuarioPanel); 
        
        // Activa el botón de menú en el topPanel de MyFrame
        // La acción de este botón será simplemente alternar la visibilidad del 'menuUsuarioPanel'.
        addMenuButton("/icono_lineas.png", e -> {
            // No necesitas añadir lógica aquí si toggleFloatingMenu() ya hace el trabajo.
            // Puedes añadir un System.out.println() si es solo para depurar.
            // System.out.println("Botón de menú en 'Ver Menú' clicado.");
        });

        
        getMyPanel().add(contentPanel, BorderLayout.CENTER); // Añadir el contentPanel al MyPanel de MyFrame

    }

    private void simularPagoMovil() {
        JProgressBar barra = new JProgressBar();
        barra.setIndeterminate(true);
        barra.setPreferredSize(new Dimension(300, 20));
        JLabel label = new JLabel("Procesando Pago Movil");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(label, BorderLayout.NORTH);
        panel.add(barra, BorderLayout.CENTER);

        JDialog dialog = new JDialog(this, "Procesador de Pago", true);
        dialog.getContentPane().add(panel);
        dialog.setSize(300, 63);
        dialog.setLocationRelativeTo(this);
        dialog.setUndecorated(true);

        Timer timer = new Timer(2000, event -> {
            dialog.dispose();
            //procesarPago();
        });

        timer.setRepeats(false);
        timer.start();
        dialog.setVisible(true);
    }

    private void validarCampo() {
        String valorIngresado = campoMonto.getText().trim();
        if (valorIngresado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ERROR: Campo vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        else {

            valorIngresado = valorIngresado.replace(",", "."); // Reemplazar coma por punto para el formato decimal

            if (!valorIngresado.matches("^-?\\d+(\\.\\d+)?$")) {
                JOptionPane.showMessageDialog(this, "ERROR: Campo inválido. Solo se permiten números.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                
            } 
            else if (Double.parseDouble(valorIngresado) <= 0) {
                JOptionPane.showMessageDialog(this, "ERROR: Monto inválido. Debe ser mayor a 0.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } 
            else {
                simularPagoMovil();
                JOptionPane.showMessageDialog(this, "¡Pago Movil exitoso! Pago por Bs. " + valorIngresado, "Proceso Completado", JOptionPane.PLAIN_MESSAGE);
                saldoDisponible += Double.parseDouble(valorIngresado);
                saldoLabel.setText(String.format("%.2f", saldoDisponible));
            }
        }
        campoMonto.setText(""); // Limpiar el campo después de procesar el pago
    }


    /*private void recargarSaldo(ActionEvent e) {
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
    }*/

    /*private void actualizarSaldo() {
        saldoLabel.setText("Saldo: BsS. " + String.format("%.2f", monedero.getSaldo()));
    }*/

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
        new MonederoUI().mostrarVentana();
    }
}
