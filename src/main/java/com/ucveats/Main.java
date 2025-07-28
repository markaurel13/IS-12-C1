package com.ucveats; // O el paquete donde quieras que esté tu clase principal

import com.ucveats.view.MyFrame;
import com.ucveats.view.cobroServicio;
import com.ucveats.view.inicioSesionInterface;
import com.ucveats.view.MenuUsuarioPanel; // Importa MenuUsuarioPanel
import com.ucveats.view.MenuAdminPanel;   // Importa MenuAdminPanel
import com.ucveats.view.seleccionInicio; // Importa seleccionInicio

import javax.swing.*;


public class Main {

    private static MyFrame mainFrame; // La única instancia de la ventana principal depues de iniciar el programa
    private static MyFrame frameSeleccion; //instancia de la ventala de selecion al inicio
    public static MenuUsuarioPanel menuUsuarioFlotante; // La única instancia del menú flotante
    public static MenuAdminPanel menuAdminFlotante; // La instancia del menú de administrador
    public static int seleccionador = 0; //sirve para controlar a que flujo vamos al selecionar en el primer frame

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // para una apariencia cross platform descomentar la lina de abajo
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                // para una apariencia navita descomentar a linea de abajo
                //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }



            if (seleccionador == 0) {
                frameSeleccion = new MyFrame("Selecion de Funcion", 600,300);
                frameSeleccion.setContentPanel(new seleccionInicio(frameSeleccion));
                frameSeleccion.mostrarVentana();
               
            } else if (seleccionador == 1) {

                frameSeleccion.dispose(); // Cierra la ventana de selección
                // Si seleccionas Iniciar Sesión, muestra la interfaz de inicio de sesión
                mainFrame = new MyFrame("UCVeats");

                menuUsuarioFlotante = new MenuUsuarioPanel(mainFrame);
                menuAdminFlotante = new MenuAdminPanel(mainFrame);

                // este set se esta haciendo en el boton de inicio de sesion porque depende de que tipo de usuario inicie sesion
                //mainFrame.setFloatingMenuPanel(menuUsuarioFlotante);
                //mainFrame.setFloatingMenuPanel(menuAdminFlotante);

                mainFrame.setContentPanel(new inicioSesionInterface(mainFrame));
                mainFrame.mostrarVentana();

            } else if (seleccionador == 2) {
                frameSeleccion.dispose(); // Cierra la ventana de selección
                // Si seleccionas Pagar Servicio, muestra la interfaz de registro
                mainFrame = new MyFrame("UCVeats Cobro de Servicio");
                mainFrame.setContentPanel(new cobroServicio(mainFrame));
                mainFrame.mostrarVentana();
            }


        });
    }
}