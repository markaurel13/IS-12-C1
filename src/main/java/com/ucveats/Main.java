package com.ucveats;

import com.ucveats.controller.AuthService;
import com.ucveats.controller.BandejaService;
import com.ucveats.controller.costoFijoService;
import com.ucveats.controller.costoVariableService;
import com.ucveats.model.Comensal;
import com.ucveats.model.Usuario;
import com.ucveats.view.*;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

/**
 * Clase principal que inicia y ensambla la aplicación UCVeats.
 * Sigue el patrón de diseño MVC, donde esta clase actúa como el "Ensamblador"
 * que crea las instancias de las Vistas y Controladores y las conecta.
 */
public class Main {

    // Almacena el usuario que ha iniciado sesión. Es el estado central de la app.
    private static Usuario currentUser;

    public static void main(String[] args) {
        // Todas las operaciones de Swing deben ejecutarse en el Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {

            // --- 1. INSTANCIACIÓN DE COMPONENTES ---

            // Controladores (Servicios)
            BandejaService bandejaService = new BandejaService();
            costoFijoService costoFijoService = new costoFijoService();
            costoVariableService costoVariableService = new costoVariableService();

            // Vista Principal (Ventana)
            MyFrame mainFrame = new MyFrame("UCVeats", 400, 620);

            // Vistas (Paneles)
            inicioSesionInterface loginView = new inicioSesionInterface(mainFrame);
            registroInterface registerView = new registroInterface(mainFrame);
            HomePage homeView = new HomePage(mainFrame);
            MonederoUI monederoView = new MonederoUI(mainFrame);
            verMenuInterface menuView = new verMenuInterface(mainFrame);
            CargarMenuAdmin cargarMenuView = new CargarMenuAdmin(mainFrame);
            CostosFijosUI costosFijosView = new CostosFijosUI(mainFrame);
            costoVariableUI costoVariableView = new costoVariableUI(mainFrame);
            cobroServicio cobroServicioView = new cobroServicio(mainFrame);

            // Menús flotantes
            MenuUsuarioPanel menuUsuarioPanel = new MenuUsuarioPanel(mainFrame);
            MenuAdminPanel menuAdminPanel = new MenuAdminPanel(mainFrame);

            // --- 2. CONEXIÓN (WIRING) DE LÓGICA Y NAVEGACIÓN ---

            // Lógica de Inicio de Sesión
            loginView.addLoginListener(e -> {
                String cedula = loginView.getCedula();
                String password = loginView.getPassword();
                try {
                    Usuario user = AuthService.login(cedula, password);
                    if (user != null) {
                        currentUser = user;
                        homeView.setNombreUsuario(user.getCedula()); // Personaliza la bienvenida

                        // Configura el menú y la vista según el rol del usuario
                        if (user.getRol() == Usuario.RolUsuario.ADMIN) {
                            mainFrame.setFloatingMenuPanel(menuAdminPanel);
                        } else {
                            mainFrame.setFloatingMenuPanel(menuUsuarioPanel);
                        }
                        mainFrame.setContentPanel(homeView);

                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Cédula o contraseña incorrecta.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Error al leer datos de usuario: " + ex.getMessage(), "Error de Archivo", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(mainFrame, ex.getMessage(), "Datos inválidos", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Navegación de Login a Registro
            loginView.addRegisterListener(e -> mainFrame.setContentPanel(registerView));

            // Lógica de Registro
            registerView.addRegisterButtonListener(e -> {
                String cedula = registerView.getCedula();
                String correo = registerView.getCorreo();
                String telefono = registerView.getTelefono();
                String password = registerView.getPassword();
                String confirmPassword = registerView.getConfirmarPassword();

                if (!password.equals(confirmPassword)) {
                    registerView.mostrarError("Las contraseñas no coinciden.");
                    return;
                }

                try {
                    boolean registrado = AuthService.registrar(cedula, correo, telefono, password);
                    if (registrado) {
                        registerView.mostrarExito(); // Este método ya navega a la vista de login
                    } else {
                        registerView.mostrarError("La cédula ya está registrada.");
                    }
                } catch (IllegalArgumentException | IOException ex) {
                    registerView.mostrarError("Error en el registro: " + ex.getMessage());
                }
            });

            // Lógica del Monedero
            monederoView.addRecargarListener(e -> {
                try {
                    double monto = Double.parseDouble(monederoView.getMontoRecarga());
                    if (currentUser instanceof Comensal) {
                        monederoView.simularPagoMovil();
                        ((Comensal) currentUser).getMonedero().recargar(monto);
                        monederoView.setSaldo(((Comensal) currentUser).getMonedero().getSaldo());
                    }
                } catch (NumberFormatException ex) {
                    monederoView.mostrarMensaje("Por favor, ingrese un monto numérico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    monederoView.mostrarMensaje(ex.getMessage(), "Error de Recarga", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Lógica para Cargar Menú (Admin)
            cargarMenuView.addCargarMenuListener(e -> {
                try {
                    String nombre = cargarMenuView.getTitulo();
                    double costo = Double.parseDouble(cargarMenuView.getCosto());
                    String descripcion = cargarMenuView.getDescripcion();
                    if (cargarMenuView.getFecha() == null) {
                        cargarMenuView.mostrarError("Debe seleccionar una fecha.");
                        return;
                    }
                    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(cargarMenuView.getFecha());

                    bandejaService.crearBandeja(nombre, costo, fecha, descripcion);
                    cargarMenuView.mostrarExito("Plato añadido al menú correctamente.");

                } catch (NumberFormatException ex) {
                    cargarMenuView.mostrarError("El costo debe ser un número válido.");
                } catch (IllegalArgumentException | IOException ex) {
                    cargarMenuView.mostrarError("Error al crear la bandeja: " + ex.getMessage());
                }
            });

            // Lógica para Costos Fijos (Admin)
            costosFijosView.addGuardarListener(e -> {
                try {
                    double manoObra = Double.parseDouble(costosFijosView.getManoObra());
                    double mantenimiento = Double.parseDouble(costosFijosView.getMantenimiento());
                    double alquiler = Double.parseDouble(costosFijosView.getAlquiler());

                    costoFijoService.guardarCostos(manoObra, mantenimiento, alquiler);
                    costosFijosView.setTotal(costoFijoService.getCostoFijoTotal());
                    costosFijosView.mostrarExito("Costos fijos guardados con éxito.");

                } catch (NumberFormatException ex) {
                    costosFijosView.mostrarError("Todos los campos deben ser números válidos.");
                } catch (IllegalArgumentException | IOException ex) {
                    costosFijosView.mostrarError("Error al guardar: " + ex.getMessage());
                }
            });

            // Lógica para Costos Variables (Admin)
            costoVariableView.addGuardarListener(e -> {
                try {
                    double proteinas = Double.parseDouble(costoVariableView.getProteinas());
                    double carbohidratos = Double.parseDouble(costoVariableView.getCarbohidratos());
                    double energia = Double.parseDouble(costoVariableView.getEnergia());
                    String tipoBandeja = costoVariableView.getTipoBandeja();
                    if (costoVariableView.getFecha() == null) {
                        costoVariableView.mostrarError("Debe seleccionar una fecha.");
                        return;
                    }
                    java.time.LocalDate fecha = costoVariableView.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    costoVariableService.guardarCostos(proteinas, carbohidratos, energia, fecha, tipoBandeja);
                    costoVariableView.setTotal(costoVariableService.getCostoVariableTotal());
                    costoVariableView.mostrarExito("Costos variables guardados con éxito.");

                } catch (NumberFormatException ex) {
                    costoVariableView.mostrarError("Todos los campos de costo deben ser números válidos.");
                } catch (IllegalArgumentException | IOException ex) {
                    costoVariableView.mostrarError("Error al guardar: " + ex.getMessage());
                }
            });

            // --- NAVEGACIÓN DESDE MENÚS (Actualizando vistas antes de mostrarlas) ---
            // La navegación se define en los propios paneles de menú, pero aquí se podría añadir lógica
            // para actualizar las vistas antes de mostrarlas. Por ejemplo:
            // En MenuUsuarioPanel, el botón "Ver Monedero" podría hacer:
            // botonVerMonedero.addActionListener(e -> {
            //     if (currentUser instanceof Comensal) {
            //         monederoView.setSaldo(((Comensal) currentUser).getMonedero().getSaldo());
            //     }
            //     mainFrame.setContentPanel(monederoView);
            // });
            // Por simplicidad, esta lógica se puede añadir directamente en los listeners de los menús si se prefiere.


            // --- 3. INICIO DE LA APLICACIÓN ---

            // Establece el panel inicial
            mainFrame.setContentPanel(loginView);

            // Hace visible la ventana principal
            mainFrame.mostrarVentana();
        });
    }
}

