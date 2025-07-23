/*
 * hay que colocar los respectivos botones para cerrar sesion y aceder al menu ademas de ver el saldo que tengo disponible y el usuario que 
 * esta registrado
 */

package com.ucveats.view;
//import com.ucveats.view.MyFrame;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.net.URL;


public class verMenuInterface {

    // Clase para llamar al main desde el menú de usuario, se coloca unicamente para cablear el código y que los botones cambien de ventana
    public static void mostrarVentanaVerMenu() {
        main(new String[0]);
    }

    // Clase interna para representar un elemento del menú
    // Cada elemento del menú tiene un icono, nombre y descripción
    public static class elementoMenu extends JPanel {

        private ImageIcon iconoPlato;
        private JLabel labelIcono;
        private JLabel nombrePlato;
        private JLabel descripcionPlato;

        // Constructor de la clase elementoMenu
        // Recibe la ruta del icono, el nombre del plato y una breve descripción
        public elementoMenu (String rutaIcono, String nombre, String descripcion) {

            URL imageUrl = getClass().getResource(rutaIcono); // Intenta cargar el recurso
            if (imageUrl != null) {
                this.iconoPlato = new ImageIcon(imageUrl);
            } else {
                // Manejar el caso en que la imagen no se encuentre
                System.err.println("Advertencia: Imagen no encontrada en el classpath: " + rutaIcono);
                this.iconoPlato = new ImageIcon(); // Icono vacío o un icono predeterminado de error
            }
            
            //this.iconoPlato = new ImageIcon(rutaIcono);
            this.labelIcono = new JLabel(iconoPlato);
            this.nombrePlato = new JLabel(nombre);
            this.descripcionPlato = new JLabel(descripcion);

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(Color.decode("#ffffff"));

            this.labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

            this.nombrePlato.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.nombrePlato.setFont(new Font("Segoe UI", Font.BOLD, 16));
            this.nombrePlato.setForeground(Color.decode("#2f3829"));

            this.descripcionPlato.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.descripcionPlato.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            this.descripcionPlato.setForeground(Color.decode("#2f3829"));

            this.add(Box.createVerticalStrut(10));
            this.add(labelIcono);
            this.add(Box.createVerticalStrut(10));
            this.add(nombrePlato);
            this.add(Box.createVerticalStrut(10));
            this.add(descripcionPlato);
            this.add(Box.createVerticalStrut(10));
        }


    }
    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}

        MyFrame frame = new MyFrame("Menú Disponible");


        // Panel Central
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.decode("#ffffff"));

        JLabel labelTitulo = new JLabel("Menú del Día");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        labelTitulo.setForeground(Color.decode("#2f3829"));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // Obtener la fecha actual
        LocalDate fechaHoy = LocalDate.now();
        int dia = fechaHoy.getDayOfMonth();
        int mes = fechaHoy.getMonthValue();
        int año = fechaHoy.getYear();
        // Formatear la fecha como "dd/mm/yyyy"
        JLabel labelFecha = new JLabel("Fecha: " + dia + "/" + mes + "/" + año);
        labelFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelFecha.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelFecha.setForeground(Color.decode("#2f3829"));

        // titulo de desayuno y elemento del desayuno
        JLabel tituloDesayuno = new JLabel("Desayuno");
        tituloDesayuno.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloDesayuno.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloDesayuno.setForeground(Color.decode("#2f3829"));

        elementoMenu desayuno = new elementoMenu("/desayuno.png", "Huevos con Tocino", "Deliciosos Huevos con tocino crujiente y pan");

        // titulo de almuerzo y elemento del almuerzo
        JLabel tituloAlmuerzo = new JLabel("Almuerzo");
        tituloAlmuerzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAlmuerzo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloAlmuerzo.setForeground(Color.decode("#2f3829"));

        elementoMenu almuerzo = new elementoMenu("/almuerzo.png", "Pato a la Naranja", "Delicioso pato con salsa de naranja");

        // Agregar los elementos colocados en central panel a un panel que tiene scroll
        JScrollPane scrollPane = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        centerPanel.add(labelTitulo);
        centerPanel.add(labelFecha);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(tituloDesayuno);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(desayuno); 
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(tituloAlmuerzo);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(almuerzo);
        centerPanel.add(Box.createVerticalStrut(20));

        frame.getMyPanel().add(scrollPane);
        frame.mostrarVentana();
    }
}
