/*
 * solo falta agregar en el boton de pagar la logica para comparar las imagenes
 */
package com.ucveats.view;

import com.ucveats.controller.ImageUtilities;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class cobroServicio  extends JPanel {
    
    private File archivo = null;
    private JLabel etiquetaStatus;
    private static double costoBandejaEstudiante = 0.0;
    private static double costoBandejaProfesor = 0.0;
    private static double costoBandejaAdministrativo = 0.0;

    public cobroServicio(MyFrame frame) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#ffffff"));

        JLabel titulo = new JLabel("Cobro de Servicio");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.decode("#2f3829"));

        JLabel costoEstudiante = new JLabel("Estudiante: " + costoBandejaEstudiante + " BsS");
        costoEstudiante.setAlignmentX(Component.CENTER_ALIGNMENT);
        costoEstudiante.setFont(new Font("Segoe UI", Font.BOLD, 18));
        costoEstudiante.setForeground(Color.decode("#2f3829"));

        JLabel costoProfesor = new JLabel("Profesor: " + costoBandejaProfesor + " BsS");
        costoProfesor.setAlignmentX(Component.CENTER_ALIGNMENT);
        costoProfesor.setFont(new Font("Segoe UI", Font.BOLD, 18));
        costoProfesor.setForeground(Color.decode("#2f3829"));

        JLabel costoAdministrativo = new JLabel("Administrativo: " + costoBandejaAdministrativo + " BsS");
        costoAdministrativo.setAlignmentX(Component.CENTER_ALIGNMENT);
        costoAdministrativo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        costoAdministrativo.setForeground(Color.decode("#2f3829"));

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

        BotonPanel botonPagar = new BotonPanel("Pagar Servicio", 200, 40, e -> {
            // Aquí puedes agregar la lógica para compara las dos iamgenes
            archivo = null;
            etiquetaStatus.setText("");
            JOptionPane.showMessageDialog(this, "Pago realizado con éxito");
        });



        this.add(Box.createVerticalStrut(20));
        this.add(titulo);
        this.add(Box.createVerticalStrut(30));
        this.add(costoEstudiante);
        this.add(Box.createVerticalStrut(10));
        this.add(costoProfesor);
        this.add(Box.createVerticalStrut(10));
        this.add(costoAdministrativo);
        this.add(Box.createVerticalStrut(10));
        this.add(botonAgregarImg);
        this.add(Box.createVerticalStrut(10));
        this.add(etiquetaStatus);
        this.add(Box.createVerticalStrut(30));
        this.add(botonPagar);
    }
}
