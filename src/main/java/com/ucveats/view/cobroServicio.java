package com.ucveats.view;

import com.ucveats.controller.ImageUtilities;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.ActionListener;

public class cobroServicio extends JPanel {

    private File archivo = null;
    private JLabel etiquetaStatus;
    private JLabel EstudianteLabel;
    private JLabel PrecioEstudiante;
    private JLabel ProfesorLabel;
    private JLabel PrecioProfesor;
    private JLabel AdministrativoLabel;
    private JLabel PrecioAdministrativo;
    private BotonPanel botonPagar;

    public cobroServicio(MyFrame frame) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#ffffff"));

        JLabel titulo = new JLabel("Cobro de Servicio");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.decode("#2f3829"));

        JPanel panelCampos = new JPanel();
        panelCampos.setBackground(Color.decode("#ffffff"));
        panelCampos.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCampos.setLayout(new GridLayout(3, 2, 0, 10));
        panelCampos.setPreferredSize(new Dimension(300, 150));
        panelCampos.setMaximumSize(new Dimension(300, 150));

        EstudianteLabel = new JLabel("Estudiante");
        EstudianteLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        EstudianteLabel.setForeground(Color.decode("#2f3829"));

        PrecioEstudiante = new JLabel(" ");
        PrecioEstudiante.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        PrecioEstudiante.setForeground(Color.decode("#2f3829"));

        panelCampos.add(EstudianteLabel);
        panelCampos.add(PrecioEstudiante);

        ProfesorLabel = new JLabel("Profesor");
        ProfesorLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        ProfesorLabel.setForeground(Color.decode("#2f3829"));

        PrecioProfesor = new JLabel(" ");
        PrecioProfesor.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        PrecioProfesor.setForeground(Color.decode("#2f3829"));

        panelCampos.add(ProfesorLabel);
        panelCampos.add(PrecioProfesor);

        AdministrativoLabel = new JLabel("Administrativo");
        AdministrativoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        AdministrativoLabel.setForeground(Color.decode("#2f3829"));

        PrecioAdministrativo = new JLabel(" ");
        PrecioAdministrativo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        PrecioAdministrativo.setForeground(Color.decode("#2f3829"));

        panelCampos.add(AdministrativoLabel);
        panelCampos.add(PrecioAdministrativo);

        etiquetaStatus = new JLabel(" ");
        etiquetaStatus.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        etiquetaStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaStatus.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JButton botonAgregarImg = new JButton("Cargar Imagen");
        botonAgregarImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAgregarImg.addActionListener(e -> {
            archivo = ImageUtilities.selectorImagen(this);

            if (archivo != null) {
                etiquetaStatus.setText("<html><p style='width: 250px;'>Archivo seleccionado: " + archivo.getAbsolutePath() + "</p></html>");
            } else {
                etiquetaStatus.setText("Selección de archivo cancelada.");
            }
        });

        botonPagar = new BotonPanel("Pagar Servicio", 200, 40);

        this.add(Box.createVerticalStrut(20));
        this.add(titulo);
        this.add(Box.createVerticalStrut(30));
        this.add(panelCampos);
        this.add(Box.createVerticalStrut(10));
        this.add(botonAgregarImg);
        this.add(Box.createVerticalStrut(10));
        this.add(etiquetaStatus);
        this.add(Box.createVerticalStrut(30));
        this.add(botonPagar);
    }

    // --- MÉTODOS PARA EL CONTROLADOR EXTERNO ---

    public void setCostos(double estudiante, double profesor, double admin) {
        PrecioEstudiante.setText(String.format("%.2f BsS", estudiante));
        PrecioProfesor.setText(String.format("%.2f BsS", profesor));
        PrecioAdministrativo.setText(String.format("%.2f BsS", admin));
    }

    public void addPagarListener(ActionListener listener) {
        botonPagar.addActionListener(listener);
    }

    private void simularEscaneoFacialYValidar() {
        JDialog dialogo = new JDialog(SwingUtilities.getWindowAncestor(this), "Verificación Facial", Dialog.ModalityType.APPLICATION_MODAL);
        dialogo.setSize(300, 100);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());

        JLabel etiqueta = new JLabel("Escaneando rostro...", SwingConstants.CENTER);
        JProgressBar barra = new JProgressBar();
        barra.setIndeterminate(true);

        dialogo.add(etiqueta, BorderLayout.NORTH);
        dialogo.add(barra, BorderLayout.CENTER);

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                Thread.sleep(2000);
                return null;
            }

            @Override
            protected void done() {
                dialogo.dispose();
                try {
                    String nombreArchivoCargado = archivo.getName();
                    String nombreArchivoReferencia = "estudiante.png";

                    if (nombreArchivoCargado.equalsIgnoreCase(nombreArchivoReferencia)) {
                        mostrarExito("Verificación facial exitosa. Servicio pagado.");
                    } else {
                        throw new IllegalArgumentException("Datos biométricos no registrados.");
                    }

                } catch (IllegalArgumentException ex) {
                    mostrarError(ex.getMessage());
                } catch (Exception ex) {
                    mostrarError("Ocurrió un error inesperado: " + ex.getMessage());
                }
            }
        };

        worker.execute();
        dialogo.setVisible(true);
    }

    public void ejecutarVerificacionFacial() {
        if (archivo == null) {
            mostrarError("Debe cargar una imagen antes de pagar el servicio.");
            return;
        }
        simularEscaneoFacialYValidar();
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
