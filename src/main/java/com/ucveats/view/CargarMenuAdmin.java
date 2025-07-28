/*
 * aqui hay que manejar que al cargar la iamgen y los datos esta infromacion sirva para alimentar
 * la interface de ver menu
 */
package com.ucveats.view;

import com.ucveats.controller.ImageUtilities;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CargarMenuAdmin extends JPanel {

    private MyFrame parentFrame;
    private File archivo = null;
    private JLabel etiquetaStatus;

    public CargarMenuAdmin(MyFrame frame) {
        this.parentFrame = frame;

        // Configuración del panel
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.decode("#ffffff"));

        JLabel Titulo = new JLabel("Aañdir plato al menu");
        Titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        Titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        Titulo.setForeground(Color.decode("#2f3829"));
        Titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 10, 0));

        JPanel panelCampos = new JPanel();
        panelCampos.setBackground(Color.decode("#ffffff"));
        panelCampos.setLayout(new GridLayout(3, 2, 0, 10));
        panelCampos.setMaximumSize(new Dimension(300, 115));
        

        JLabel etiquetaTitulo = new JLabel("Titulo:");
        etiquetaTitulo.setFont(new Font("Segoe ui", Font.PLAIN, 14));
        panelCampos.add(etiquetaTitulo);

        JTextField campoTitulo = new JTextField();
        campoTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoTitulo.setPreferredSize(new Dimension(150, 25));
        panelCampos.add(campoTitulo);

        JLabel etiquetaDescripcion = new JLabel("Descripcion:");
        etiquetaDescripcion.setFont(new Font("Segoe ui", Font.PLAIN, 14));
        panelCampos.add(etiquetaDescripcion);

        JTextField campoDescripcion = new JTextField();
        campoDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoDescripcion.setPreferredSize(new Dimension(150, 25));
        panelCampos.add(campoDescripcion);

        JLabel etiquetaSelecionar = new JLabel("Seleccione un archivo:");
        etiquetaSelecionar.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelCampos.add(etiquetaSelecionar);


        etiquetaStatus = new JLabel(" ");
        etiquetaStatus.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        etiquetaStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaStatus.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JButton botonCargarImagen = new JButton("Cargar Imagen");
        botonCargarImagen.addActionListener(e -> {
            archivo =ImageUtilities.selectorImagen(this);

             if (archivo != null) {
                etiquetaStatus.setText("<html><p style='width: 250px;'>Archivo seleccionado: " + archivo.getAbsolutePath() + "</p></html>");
            } else {
                etiquetaStatus.setText("Selección de archivo cancelada.");
            }
        });
        panelCampos.add(botonCargarImagen);


        BotonPanel botonCargarMenu = new BotonPanel("Cargar Menu", 200, 40, e -> {
            archivo = null;
            etiquetaStatus.setText("");
            JOptionPane.showMessageDialog(this, "✅ ¡Plato añadido al menú!");
        });
        botonCargarMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        

        this.add(Titulo);
        this.add(Box.createVerticalStrut(20));
        this.add(panelCampos);
        this.add(Box.createVerticalStrut(10));
        this.add(etiquetaStatus);
        this.add(Box.createVerticalStrut(30));
        this.add(botonCargarMenu);




        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });
    }

}
