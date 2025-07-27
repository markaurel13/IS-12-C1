package com.ucveats.view;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.File;

public class CargarMenuAdmin extends JPanel {

    private MyFrame parentFrame;
    JLabel status;

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

        JLabel etiquetaStatus = new JLabel("Seleccione un archivo:");
        etiquetaStatus.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panelCampos.add(etiquetaStatus);

        JButton botonCargarImagen = new JButton("Cargar Imagen");
        botonCargarImagen.addActionListener(e -> selectorImagen());
        panelCampos.add(botonCargarImagen);

        status = new JLabel(" ");
        status.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        status.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));


        BotonPanel botonCargarMenu = new BotonPanel("Cargar Menu", 200, 40, e -> {
            JOptionPane.showMessageDialog(this, "✅ ¡Plato añadido al menú!");
        });
        botonCargarMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        

        this.add(Titulo);
        this.add(Box.createVerticalStrut(20));
        this.add(panelCampos);
        this.add(Box.createVerticalStrut(10));
        this.add(status);
        this.add(Box.createVerticalStrut(30));
        this.add(botonCargarMenu);




        parentFrame.removeMenuButton(); // Opcional: Asegúrate de que no haya otro botón configurado
        parentFrame.addMenuButton("/icono_lineas.png", e -> {
            // No se necesita lógica adicional aquí, ya que MyFrame.toggleFloatingMenu() gestiona la visibilidad.
        });
    }

    public void selectorImagen() {
        // 1. Crear una instancia de JFileChooser
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle("Seleccionar Imagen");

        //Filtrar tipos de archivos (ej. solo archivos de texto)
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
        selectorArchivo.addChoosableFileFilter(filtro);
        selectorArchivo.setFileFilter(filtro); // Establecer el filtro por defecto

        // 2. Mostrar el cuadro de diálogo de selección de archivo
        // showOpenDialog() es para seleccionar un archivo para abrir/cargar
        // showSaveDialog() es para seleccionar un lugar para guardar un archivo
        int resultado = selectorArchivo.showOpenDialog(this); // 'this' se refiere a la ventana JFrame

        // 3. Procesar la selección del usuario
        if (resultado == JFileChooser.APPROVE_OPTION) {
            // El usuario seleccionó un archivo y presionó "Abrir"
            File archivoSelecionado = selectorArchivo.getSelectedFile();
            //String rutaArchivo = archivoSelecionado.getAbsolutePath();
            status.setText("<html><p style='width: 250px;'>Archivo seleccionado: " + archivoSelecionado.getAbsolutePath()+ "</p></html>");
            System.out.println("Archivo seleccionado: " + archivoSelecionado.getAbsolutePath());

            // Aquí es donde típicamente leerías el archivo
            // y operar el archivo seleccionado


        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            // El usuario cerró el diálogo sin seleccionar un archivo
            status.setText("Selección de archivo cancelada.");
            System.out.println("Selección de archivo cancelada.");
        }
    }
}
