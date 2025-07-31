/*
 * en la esquin superir del panel gris, topPanel, habia un boton de iniciar sesion 
 * este se elimino al refactorizar para extender de jpanel y la logica ya no se maneja en este panel
 * si se considera agregar esta coemntado en cambio clave 5 la consideracion y el boton antiguo
 * si no se agregara este boton o no se agregara en ese lugar eliminar comentarios
 */
package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class registroInterface extends JPanel {

    //Necesitamos una referencia a MyFrame para poder cambiar el contenido.
    private MyFrame parentFrame;
    private JTextField campoCedula, campoTelefono, campoCorreo;
    private JPasswordField campoPassword, campoConfirmarPassword;
    private BotonPanel botonRegistrar;

    //Constructor modificado para recibir la instancia de MyFrame
    public registroInterface(MyFrame frame) {
        this.parentFrame = frame;


        this.setLayout(null); 
        this.setBackground(Color.decode("#ffffff"));

        // --- Componentes de la interfaz de registro  ---
        JLabel titulo = new JLabel("Registro de Usuario", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setBounds(70, 30, 250, 25);
        this.add(titulo); 

        campoCedula = crearCampo("Cédula:", 80, false);
        campoTelefono = crearCampo("Teléfono:", 120, false);
        campoCorreo = crearCampo("Correo:", 160, false);
        campoPassword = (JPasswordField) crearCampo("Contraseña:", 200, true);
        campoConfirmarPassword = (JPasswordField) crearCampo("Confirmar Contraseña:", 240, true);

        botonRegistrar = new BotonPanel("REGISTRAR", 200, 40);
        botonRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonRegistrar.setBounds(80, 300, 200, 40);
        botonRegistrar.setBorder(BorderFactory.createLineBorder(Color.decode("#2f3829"), 2, true));
        this.add(botonRegistrar);
    }

        private JTextField crearCampo(String etiqueta, int y, boolean isPassword) {
            JLabel label = new JLabel(etiqueta);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            label.setBounds(30, y, 140, 25);
            this.add(label);

        JTextField campo;
        if (isPassword) {
            campo = new JPasswordField();
        } else {
            campo = new JTextField();
        }
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBounds(190, y, 150, 25);
        this.add(campo);
        return campo;
    }

    // --- MÉTODOS PARA EL CONTROLADOR EXTERNO ---

    public String getCedula() { return campoCedula.getText(); }
    public String getCorreo() { return campoCorreo.getText(); }
    public String getTelefono() { return campoTelefono.getText(); }
    public String getPassword() { return new String(campoPassword.getPassword()); }
    public String getConfirmarPassword() { return new String(campoConfirmarPassword.getPassword()); }

    public void addRegisterButtonListener(ActionListener listener) {
        botonRegistrar.addActionListener(listener);
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de Registro", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarExito() {
        JOptionPane.showMessageDialog(this, "¡Registro exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        // Limpiar los campos después de un registro exitoso.
        // La navegación ahora es manejada por el controlador principal (Main.java).
        campoCedula.setText("");
        campoCorreo.setText("");
        campoTelefono.setText("");
        campoPassword.setText("");
        campoConfirmarPassword.setText("");
    }
}
