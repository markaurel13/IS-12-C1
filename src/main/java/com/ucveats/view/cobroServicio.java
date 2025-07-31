/*
 * solo falta agregar en el boton de pagar la logica para comparar las imagenes
 */
package com.ucveats.view;

import com.ucveats.controller.ImageUtilities;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.ActionListener;


public class cobroServicio  extends JPanel {
    
    private File archivo = null;
    private JLabel etiquetaStatus;
    private JLabel costoEstudianteLabel;
    private JLabel costoProfesorLabel;
    private JLabel costoAdministrativoLabel;
    private BotonPanel botonPagar;


    public cobroServicio(MyFrame frame) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#ffffff"));

        JLabel titulo = new JLabel("Cobro de Servicio");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.decode("#2f3829"));

        costoEstudianteLabel = new JLabel("Estudiante: 0.00 BsS");
        costoEstudianteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        costoEstudianteLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        costoEstudianteLabel.setForeground(Color.decode("#2f3829"));

        costoProfesorLabel = new JLabel("Profesor: 0.00 BsS");
        costoProfesorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        costoProfesorLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        costoProfesorLabel.setForeground(Color.decode("#2f3829"));

        costoAdministrativoLabel = new JLabel("Administrativo: 0.00 BsS");
        costoAdministrativoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        costoAdministrativoLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        costoAdministrativoLabel.setForeground(Color.decode("#2f3829"));

        etiquetaStatus = new JLabel(" ");
        etiquetaStatus.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        etiquetaStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaStatus.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JButton botonAgregarImg = new JButton("Cargar Imagen");
        botonAgregarImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonAgregarImg.addActionListener(e -> {
            archivo =ImageUtilities.selectorImagen(this);

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
        this.add(costoEstudianteLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(costoProfesorLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(costoAdministrativoLabel);
        this.add(Box.createVerticalStrut(10));
        this.add(botonAgregarImg);
        this.add(Box.createVerticalStrut(10));
        this.add(etiquetaStatus);
        this.add(Box.createVerticalStrut(30));
        this.add(botonPagar);
    }

    // --- MÉTODOS PARA EL CONTROLADOR EXTERNO ---

    public void setCostos(double estudiante, double profesor, double admin) {
        costoEstudianteLabel.setText(String.format("Estudiante: %.2f BsS", estudiante));
        costoProfesorLabel.setText(String.format("Profesor: %.2f BsS", profesor));
        costoAdministrativoLabel.setText(String.format("Administrativo: %.2f BsS", admin));
    }

    public void addPagarListener(ActionListener listener) {
        botonPagar.addActionListener(listener);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
