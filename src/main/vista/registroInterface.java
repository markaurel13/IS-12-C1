package main.vista;
import main.vista.MyFrame;
import javax.swing.*;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Dimension;

public class registroInterface {

    private static JPanel boxNombre() {

        JPanel panelNombre = new JPanel();
        panelNombre.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelNombre.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelNombre.setPreferredSize(new Dimension(200, 30));

        JLabel labelNombre = new JLabel("Ingrese su nombre: ");
        labelNombre.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField campoNombre = new JTextField();
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        campoNombre.setPreferredSize(new Dimension(200, 30));
        
        

        panelNombre.add(labelNombre);
        panelNombre.add(campoNombre);

        return panelNombre;
    }



    private static JPanel boxApellido() {

        JPanel panelApellido = new JPanel();
        panelApellido.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelApellido.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelApellido.setPreferredSize(new Dimension(200, 30));

        JLabel labelApellido = new JLabel("Ingrese su apellido: ");
        labelApellido.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField campoApellido = new JTextField();
        campoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
        campoApellido.setPreferredSize(new Dimension(200, 30));
        

        panelApellido.add(labelApellido);
        panelApellido.add(campoApellido);

        return panelApellido;
    }



    private static JPanel boxCedula() {
        JPanel panelCedula = new JPanel();
        panelCedula.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelCedula.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelCedula.setPreferredSize(new Dimension(200, 30));

        JLabel labelCedula = new JLabel("Ingrese su cédula: ");
        labelCedula.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField campoCedula = new JTextField();
        campoCedula.setFont(new Font("Arial", Font.PLAIN, 16));
        campoCedula.setPreferredSize(new Dimension(200, 30));

        panelCedula.add(labelCedula);
        panelCedula.add(campoCedula);

        return panelCedula;
    }

    private static JPanel boxCorreo() {
        JPanel panelCorreo = new JPanel();
        panelCorreo.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelCorreo.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelCorreo.setPreferredSize(new Dimension(200, 30));

        JLabel labelCorreo = new JLabel("Ingrese su correo: ");
        labelCorreo.setFont(new Font("Arial", Font.BOLD, 14));

        JTextField campoCorreo = new JTextField();
        campoCorreo.setFont(new Font("Arial", Font.PLAIN, 16));
        campoCorreo.setPreferredSize(new Dimension(200, 30));

        panelCorreo.add(labelCorreo);
        panelCorreo.add(campoCorreo);

        return panelCorreo;
    }

    private static JPanel boxContrasena() {
        JPanel panelContrasena = new JPanel();
        panelContrasena.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelContrasena.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelContrasena.setPreferredSize(new Dimension(200, 30));

        JLabel labelContrasena = new JLabel("Ingrese su contraseña: ");
        labelContrasena.setFont(new Font("Arial", Font.BOLD, 14));

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        campoContrasena.setPreferredSize(new Dimension(200, 30));

        panelContrasena.add(labelContrasena);
        panelContrasena.add(campoContrasena);

        return panelContrasena;
    }

    private static JPanel boxConfirmarContrasena() {
        JPanel panelConfirmar = new JPanel();
        panelConfirmar.setLayout((new FlowLayout(FlowLayout.LEFT)));
        panelConfirmar.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        panelConfirmar.setPreferredSize(new Dimension(200, 30));

        JLabel labelConfirmar = new JLabel("Confirme su contraseña: ");
        labelConfirmar.setFont(new Font("Arial", Font.BOLD, 14));

        JPasswordField campoConfirmar = new JPasswordField();
        campoConfirmar.setFont(new Font("Arial", Font.PLAIN, 16));
        campoConfirmar.setPreferredSize(new Dimension(200, 30));

        panelConfirmar.add(labelConfirmar);
        panelConfirmar.add(campoConfirmar);

        return panelConfirmar;
    }

    

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Registro UCV Eats", 600, 900);

        frame.getMyPanel().setLayout(new BoxLayout(frame.getMyPanel(), BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Registro de Usuarios");
        labelTitulo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setAlignmentX(JButton.CENTER_ALIGNMENT);
        botonRegistrar.addActionListener(e -> {
            // Aquí puedes agregar la lógica para redirigir a la ventana de registro
            // Por ejemplo, puedes usar un JFrame o un JOptionPane para mostrar un mensaje
            JOptionPane.showMessageDialog(null, "¡Registro exitoso!");
        });



        frame.getMyPanel().add(labelTitulo);
        frame.getMyPanel().add(boxNombre());
        frame.getMyPanel().add(boxApellido());
        frame.getMyPanel().add(boxCedula());
        frame.getMyPanel().add(boxCorreo());
        frame.getMyPanel().add(boxContrasena());
        frame.getMyPanel().add(boxConfirmarContrasena());
        frame.mostrarVentana();
       
    }

}