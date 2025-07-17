package main.vista;
import main.vista.MyFrame;
import javax.swing.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

public class registroInterface {

    public static void mostrarVentanaRegistro() {
        main(new String[0]);
    }

    private static class moduloRegistro extends JPanel {

        private static String DEFAULT_TITLE = "Título por defecto";

        JLabel labelTitulo;
        JTextField campoTexto;

        public moduloRegistro(String title) {
            moduloRegistro.DEFAULT_TITLE= title;
            this.setLayout(new GridLayout(1, 2, 50, 0));
            this.setBorder(BorderFactory.createEmptyBorder(10, 45, 10, 45));

            labelTitulo = new JLabel(title);
            labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
            labelTitulo.setPreferredSize(new Dimension(200, 30));

            campoTexto = new JTextField();
            campoTexto.setFont(new Font("Arial", Font.PLAIN, 14));
            campoTexto.setBounds(0, 0, 200, 30);
            

            this.add(labelTitulo);
            this.add(campoTexto);
        }
        
    }
    

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Registro UCV Eats");

        frame.getMyPanel().setLayout(new BoxLayout(frame.getMyPanel(), BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Registro de Usuarios");
        labelTitulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        moduloRegistro nombre = new moduloRegistro("Ingrese su Nombre: ");

        moduloRegistro apellido = new moduloRegistro("Ingrese su Apellido: ");
        moduloRegistro cedula = new moduloRegistro("Ingrese su Cédula: ");
        moduloRegistro telefono = new moduloRegistro("Ingrese su Teléfono: ");
        moduloRegistro correo = new moduloRegistro("Ingrese su Correo: ");
        moduloRegistro contrasena = new moduloRegistro("Ingrese su Contraseña: ");
        moduloRegistro confirmarContrasena = new moduloRegistro("Confirme su Contraseña: ");


        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        botonRegistrar.addActionListener(e -> {
            // Aquí puedes agregar la lógica para redirigir a la ventana de registro
            // Por ejemplo, puedes usar un JFrame o un JOptionPane para mostrar un mensaje
            JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
        });



        frame.getMyPanel().add(labelTitulo);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(nombre);
        //frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(apellido);
        //frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(cedula);
        //frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(telefono);
       // frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(correo);
        //frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(contrasena);
        //frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(confirmarContrasena);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.getMyPanel().add(botonRegistrar);
        frame.getMyPanel().add(Box.createVerticalStrut(20));
        frame.mostrarVentana();
       
    }

}