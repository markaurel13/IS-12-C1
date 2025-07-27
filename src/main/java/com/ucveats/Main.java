package com.ucveats; // O el paquete donde quieras que esté tu clase principal

import com.ucveats.view.MyFrame;
import com.ucveats.view.inicioSesionInterface;
import com.ucveats.view.registroInterface;
import com.ucveats.view.verMenuInterface; // Importa verMenuInterface
import com.ucveats.view.MonederoUI;       // Importa MonederoUI
import com.ucveats.view.MenuUsuarioPanel; // Importa MenuUsuarioPanel
import com.ucveats.view.MenuAdminPanel;   // Importa MenuAdminPanel
import com.ucveats.view.BotonPanel;       // Importa BotonPanel (siempre necesaria)
import com.ucveats.view.CargarMenuAdmin;

import javax.swing.*;

import java.awt.Menu;
import java.awt.event.ActionListener; // Necesario para los ActionListeners

public class Main {

    private static MyFrame mainFrame; // La única instancia de tu ventana principal
    public static MenuUsuarioPanel menuUsuarioFlotante; // La única instancia de tu menú flotante
    public static MenuAdminPanel menuAdminFlotante; // La instancia del menú de administrador

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Puedes usar UIManager.getSystemLookAndFeelClassName() para una apariencia nativa
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 1. Instancia tu MyFrame principal (la única ventana de la aplicación)
            mainFrame = new MyFrame("UCVeats");
            
            // 2. Instancia tu MenuUsuarioPanel, pasándole la referencia a mainFrame
            // Esto permite que los botones del menú cambien el contenido de mainFrame
            menuUsuarioFlotante = new MenuUsuarioPanel(mainFrame);
            menuAdminFlotante = new MenuAdminPanel(mainFrame);
            
            // 3. Asocia el menú flotante con MyFrame
            //mainFrame.setFloatingMenuPanel(menuUsuarioFlotante);
            //mainFrame.setFloatingMenuPanel(menuAdminFlotante);

            // 4. Carga la primera interfaz (por ejemplo, Inicio de Sesión)
            mainFrame.setContentPanel(new inicioSesionInterface(mainFrame));
            
            // 5. Muestra la ventana principal
            mainFrame.mostrarVentana();

            // --- Lógica adicional para el botón "Iniciar Sesión" en el topPanel de Registro ---
            // Como comentamos, si el botón "Iniciar Sesión" solo debe aparecer en el topPanel
            // cuando la interfaz de registro está visible, necesitarías una forma de controlar
            // eso desde aquí (o dentro de MyFrame, pero App es el orquestador).
            // Una forma sería:
            // mainFrame.addTemporaryTopPanelButton("Iniciar Sesion", e -> {
            //      mainFrame.setContentPanel(new inicioSesionInterface(mainFrame));
            //      mainFrame.removeTemporaryTopPanelButton(); // Quitarlo al cambiar de vista
            // });
            // Pero esto requiere un método en MyFrame para añadir/quitar botones temporales del topPanel.
            // Por simplicidad, por ahora, las vistas (inicioSesionInterface, verMenuInterface, MonederoUI)
            // activan el botón de menú de MyFrame cuando se muestran, y el botón de "Registrarse"
            // en inicioSesionInterface te lleva a registroInterface.
            // Si necesitas el botón "Iniciar Sesión" en el topPanel cuando estás en Registro,
            // MyFrame podría tener un método `setTopRightButton(String text, ActionListener action)`
            // y lo llamarías así desde `registroInterface` al ser creada.

        });
    }
}