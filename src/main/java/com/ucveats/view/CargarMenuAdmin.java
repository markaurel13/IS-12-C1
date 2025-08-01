/*
 * aqui hay que manejar que al cargar la iamgen y los datos esta infromacion sirva para alimentar
 * la interface de ver menu
 */
package com.ucveats.view;

import com.ucveats.controller.ImageUtilities;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import java.util.Date;

public class CargarMenuAdmin extends JPanel {

    private MyFrame parentFrame;
    private File archivo = null;
    private JLabel etiquetaStatus;
    private JTextField campoTitulo;
    private JTextField campoDescripcion;
    //private JTextField campoCosto;
    private JDateChooser campoFecha;
    private BotonPanel botonCargarMenu;
    private JComboBox<String> campoTipoBandeja;
    private JTextField campoCantBandejas;
    private int cantidadBandejas = 0;

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
        panelCampos.setLayout(new GridLayout(7, 2, 0, 10));
        panelCampos.setPreferredSize(new Dimension(300, 300));
        panelCampos.setMaximumSize(new Dimension(300, 300));
        

        JLabel etiquetaTitulo = new JLabel("Titulo:");
        etiquetaTitulo.setFont(new Font("Segoe ui", Font.PLAIN, 14));
        panelCampos.add(etiquetaTitulo);

        campoTitulo = new JTextField();
        campoTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoTitulo.setPreferredSize(new Dimension(150, 25));
        panelCampos.add(campoTitulo);

        /*JLabel etiquetaCosto = new JLabel("Costo (BsS.):");
        etiquetaCosto.setFont(new Font("Segoe ui", Font.PLAIN, 14));
        panelCampos.add(etiquetaCosto);

        campoCosto = new JTextField();
        campoCosto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoCosto.setPreferredSize(new Dimension(150, 25));
        panelCampos.add(campoCosto);*/

        JLabel etiquetaDescripcion = new JLabel("Descripcion:");
        etiquetaDescripcion.setFont(new Font("Segoe ui", Font.PLAIN, 14));
        panelCampos.add(etiquetaDescripcion);

        campoDescripcion = new JTextField();
        campoDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoDescripcion.setPreferredSize(new Dimension(150, 25));
        panelCampos.add(campoDescripcion);

        JLabel labelTipoBandeja = new JLabel("Tipo de Bandeja:");
        labelTipoBandeja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        labelTipoBandeja.setBounds(40, 310, 140, 25);
        panelCampos.add(labelTipoBandeja);

        String[] opcionesBandeja = { "Desayuno", "Almuerzo"};
        campoTipoBandeja = new JComboBox<>(opcionesBandeja);
        campoTipoBandeja.setBounds(190, 310, 150, 25);
        campoTipoBandeja.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panelCampos.add(campoTipoBandeja);

        JLabel etiquetaCantBandejas = new JLabel("Cant. de bandejas:");
        etiquetaCantBandejas.setFont(new Font("Segoe ui", Font.PLAIN, 14));
        panelCampos.add(etiquetaCantBandejas);

        campoCantBandejas = new JTextField();
        campoCantBandejas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoCantBandejas.setPreferredSize(new Dimension(150, 25));
        panelCampos.add(campoCantBandejas);
        
       /* try {
            String cantBandejas = campoCantBandejas.getText();
            cantidadBandejas = Integer.parseInt(cantBandejas);
            
            
        } catch (NumberFormatException e) {
            // Si la conversión falla (porque el string está vacío o no es un número),
            // el programa saltará a este bloque en lugar de detenerse.
            System.err.println("Error: Debes ingresar un número válido.");
        } */


        JLabel etiquetaFecha = new JLabel("Fecha:");
        etiquetaFecha.setFont(new Font("Segoe ui", Font.PLAIN, 14));
        panelCampos.add(etiquetaFecha);

        campoFecha = new JDateChooser();
        campoFecha.setDateFormatString("dd/MM/yyyy");
        panelCampos.add(campoFecha);

        JLabel etiquetaSelecionar = new JLabel("Seleccione una imagen:");
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


        botonCargarMenu = new BotonPanel("Cargar Menu", 200, 40);
        botonCargarMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        this.add(Titulo);
        this.add(Box.createVerticalStrut(20));
        this.add(panelCampos);
        this.add(Box.createVerticalStrut(10));
        this.add(etiquetaStatus);
        this.add(Box.createVerticalStrut(30));
        this.add(botonCargarMenu);
    
    }

    // --- MÉTODOS PARA EL CONTROLADOR EXTERNO ---

    public String getTitulo() { return campoTitulo.getText(); }
    public String getDescripcion() { return campoDescripcion.getText(); }
    //public String getCosto() { return campoCosto.getText(); }
    public Date getFecha() { return campoFecha.getDate(); }
    public File getArchivoImagen() { return archivo; }
    public int getNumeroBandejas() { return cantidadBandejas = Integer.parseInt(campoCantBandejas.getText()); }
    public String getTipoBandeja() { return (String) campoTipoBandeja.getSelectedItem(); }

    public void addCargarMenuListener(ActionListener listener) {
        botonCargarMenu.addActionListener(listener);
    }

    public void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        // Limpiar campos
        campoTitulo.setText("");
        campoDescripcion.setText("");
        //campoCosto.setText("");
        campoFecha.setDate(null);
        etiquetaStatus.setText(" ");
        archivo = null;
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
