/*
 * en este interface deberia crear un objeto de tipo plato para poder crear los diferentes platos necesarios 
 * hay que colocar los respectivos botones para cerrar sesion y aceder al menu ademas de ver el saldo que tengo disponible y el usuario que 
 * esta registrado
 */

package main.vista;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class verMenuInterface {

    // Clase para llamar al main desde el menú de usuario, se coloca unicamente para cablear el código y que los botones cambien de ventana
    
    public static void mostrarVentanaVerMenu() {
        main(new String[0]);
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


        JLabel tituloDesayuno = new JLabel("Desayuno");
        tituloDesayuno.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloDesayuno.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloDesayuno.setForeground(Color.decode("#2f3829"));

        ImageIcon iconoDesayuno = new ImageIcon("D:\\archivos\\Documents\\01 UCV\\01-2025\\ing software\\IS-12-C1\\src\\main\\vista\\desayuno.png");
        JLabel labelIconoDesayuno = new JLabel(iconoDesayuno);
        labelIconoDesayuno.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelNombrePalatoDesayuno = new JLabel("Huevos con Tocino");
        labelNombrePalatoDesayuno.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNombrePalatoDesayuno.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelNombrePalatoDesayuno.setForeground(Color.decode("#2f3829"));


        JLabel labelDescripcionPlatoDesayuno = new JLabel("Deliciosos Huevos con tocino crujiente y pan");
        labelDescripcionPlatoDesayuno.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDescripcionPlatoDesayuno.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelDescripcionPlatoDesayuno.setForeground(Color.decode("#2f3829"));



        JLabel tituloAlmuerzo = new JLabel("Almuerzo");
        tituloAlmuerzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        tituloAlmuerzo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloAlmuerzo.setForeground(Color.decode("#2f3829"));

        ImageIcon iconoAlmuerzo = new ImageIcon("D:\\archivos\\Documents\\01 UCV\\01-2025\\ing software\\IS-12-C1\\src\\main\\vista\\plato.png");
        JLabel labelIconoAlmuerzo = new JLabel(iconoAlmuerzo);
        labelIconoAlmuerzo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelNombrePalatoAlmuerzo = new JLabel("Pato a la naranja");
        labelNombrePalatoAlmuerzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNombrePalatoAlmuerzo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        labelNombrePalatoAlmuerzo.setForeground(Color.decode("#2f3829"));

        JLabel labelDescripcionPlatoAlmuerzo = new JLabel("Delicioso pato con salsa de naranja");
        labelDescripcionPlatoAlmuerzo.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelDescripcionPlatoAlmuerzo.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        labelDescripcionPlatoAlmuerzo.setForeground(Color.decode("#2f3829"));



        JScrollPane scrollPane = new JScrollPane(centerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        centerPanel.add(labelTitulo);
        centerPanel.add(labelFecha);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(tituloDesayuno);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelIconoDesayuno);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(labelNombrePalatoDesayuno);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelDescripcionPlatoDesayuno);
        centerPanel.add(Box.createVerticalStrut(40));
        centerPanel.add(tituloAlmuerzo);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelIconoAlmuerzo);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(labelNombrePalatoAlmuerzo);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(labelDescripcionPlatoAlmuerzo);
        centerPanel.add(Box.createVerticalStrut(20));

        frame.getMyPanel().add(scrollPane);
        frame.mostrarVentana();
    }
}
