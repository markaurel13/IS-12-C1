package com.ucveats;

import com.ucveats.controller.AuthService;
import com.ucveats.controller.BandejaService;
import com.ucveats.controller.costoFijoService;
import com.ucveats.controller.costoVariableService;
import com.ucveats.model.Bandeja;
import com.ucveats.model.Comensal;
import com.ucveats.model.Usuario;
import com.ucveats.model.Merma;
import com.ucveats.view.*;

import com.ucveats.view.CargarMermaUI;
import com.ucveats.view.MyFrame;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.List;

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

            // Vista Principal (Ventana)
            MyFrame mainFrame = new MyFrame("UCVeats", 400, 620);

            // Controladores (Servicios)
            BandejaService bandejaService = new BandejaService();
            costoFijoService costoFijoService = new costoFijoService();
            costoVariableService costoVariableService = new costoVariableService();

            // Vistas (Paneles)
            seleccionInicio seleccionInicioView = new seleccionInicio(mainFrame);
            inicioSesionInterface loginView = new inicioSesionInterface(mainFrame);
            registroInterface registerView = new registroInterface(mainFrame);
            HomePage homeView = new HomePage(mainFrame);
            MonederoUI monederoView = new MonederoUI(mainFrame);
            verMenuInterface menuView = new verMenuInterface(mainFrame);
            CargarMenuAdmin cargarMenuView = new CargarMenuAdmin(mainFrame);
            CostosFijosUI costosFijosView = new CostosFijosUI(mainFrame);
            costoVariableUI costoVariableView = new costoVariableUI(mainFrame);
            cobroServicio cobroServicioView = new cobroServicio(mainFrame);
            CargarMermaUI cargarMermaView = new CargarMermaUI(mainFrame);

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
                        homeView.setNombreUsuario(user.getNombre() + " " + user.getApellido()); // Personaliza la bienvenida

                        // Configura el menú y la vista según el rol del usuario
                        if (user.getRol() == Usuario.RolUsuario.ADMIN) {
                            menuAdminPanel.setDatosUsuario(user.getNombre() + " " + user.getApellido(), "Administrador");
                            mainFrame.setFloatingMenuPanel(menuAdminPanel);
                        } else {
                            // Para comensal, usamos el rol específico de la UCV
                            String rolMostrado = user.getRolUcv().substring(0, 1).toUpperCase() + user.getRolUcv().substring(1);
                            menuUsuarioPanel.setDatosUsuario(user.getNombre() + " " + user.getApellido(), rolMostrado);
                            mainFrame.setFloatingMenuPanel(menuUsuarioPanel);
                        }
                        // Muestra el botón de menú, que ya tiene su funcionalidad asignada.
                        mainFrame.setMenuButtonVisible(true);
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
                        registerView.mostrarExito(); // Ahora solo muestra el mensaje y limpia los campos.
                        mainFrame.setContentPanel(loginView); // El controlador se encarga de la navegación.
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
                        double nuevoSaldo = ((Comensal) currentUser).getMonedero().getSaldo();
                        monederoView.setSaldo(nuevoSaldo);

                        // FIX: Persistir el cambio en el archivo
                        try {
                            AuthService.actualizarUsuario(currentUser);
                            monederoView.mostrarMensaje("Recarga exitosa. Su nuevo saldo es: " + nuevoSaldo, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } catch (IOException ex) {
                            monederoView.mostrarMensaje("No se pudo guardar el nuevo saldo: " + ex.getMessage(), "Error de Guardado", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    monederoView.mostrarMensaje("Por favor, ingrese un monto numérico válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    monederoView.mostrarMensaje(ex.getMessage(), "Error de Recarga", JOptionPane.ERROR_MESSAGE);
                }
            });

            cargarMenuView.addCargarMenuListener(e -> {
                try {
                    String nombre = cargarMenuView.getTitulo();
                    int numeroBandejas = cargarMenuView.getNumeroBandejas();
                    String tipoBandeja = cargarMenuView.getTipoBandeja();
                    //double costo = Double.parseDouble(cargarMenuView.getCosto());
                    String descripcion = cargarMenuView.getDescripcion();
                    if (cargarMenuView.getFecha() == null) {
                        cargarMenuView.mostrarError("Debe seleccionar una fecha.");
                        return;
                    }
                    String fecha = new SimpleDateFormat("dd/MM/yyyy").format(cargarMenuView.getFecha());

                    bandejaService.crearBandeja(nombre, numeroBandejas, fecha, descripcion, tipoBandeja);
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

            // Lógica para Cargar Merma
            menuAdminPanel.addCargarMermaListener(e -> {
                 mainFrame.setContentPanel(cargarMermaView);
                 mainFrame.hideFloatingMenu();
            });

            // --- NAVEGACIÓN CENTRALIZADA DESDE MENÚS ---

            // Navegación Menú Comensal
            menuUsuarioPanel.addVerMenuListener(e -> {
                menuView.mostrarMenu(bandejaService.getBandejas());
                List<Bandeja> bandejas = bandejaService.getBandejas();
                if (bandejas != null && !bandejas.isEmpty()) {
                    double precio = bandejas.get(0).getCostoEspecifico(bandejas.get(0).getCosto(), currentUser.getRolUcv());
                    menuView.setPrecio(precio);
                } else {
                    menuView.setPrecio(0.0);
                }
                mainFrame.setContentPanel(menuView);
                mainFrame.hideFloatingMenu();
            });

            menuUsuarioPanel.addVerMonederoListener(e -> {
                if (currentUser instanceof Comensal) {
                    monederoView.setSaldo(((Comensal) currentUser).getMonedero().getSaldo());
                }
                mainFrame.setContentPanel(monederoView);
                mainFrame.hideFloatingMenu();
            });

            menuUsuarioPanel.addCerrarSesionListener(e -> {
                currentUser = null;
                mainFrame.setContentPanel(loginView);
                mainFrame.setMenuButtonVisible(false); // Simplemente oculta el botón
                mainFrame.hideFloatingMenu();
            });

            // Navegación Menú Admin
             menuAdminPanel.addCostosFijosListener(e -> {
                costosFijosView.setTotal(costoFijoService.getCostoFijoTotal());
                mainFrame.setContentPanel(costosFijosView);
                mainFrame.hideFloatingMenu();
            });

            menuAdminPanel.addCostosFijosListener(e -> {
                costosFijosView.setTotal(costoFijoService.getCostoFijoTotal());
                mainFrame.setContentPanel(costosFijosView);
                mainFrame.hideFloatingMenu();
            });

            menuAdminPanel.addCostosVariablesListener(e -> {
                costoVariableView.setTotal(costoVariableService.getCostoVariableTotal());
                mainFrame.setContentPanel(costoVariableView);
                mainFrame.hideFloatingMenu();
            });

            menuAdminPanel.addCargarMenuListener(e -> {
                mainFrame.setContentPanel(cargarMenuView);
                mainFrame.hideFloatingMenu();
            });

            menuAdminPanel.addCargarMermaListener(e -> {
                 mainFrame.setContentPanel(cargarMermaView);
                 mainFrame.hideFloatingMenu();
            });
            
            menuAdminPanel.addCerrarSesionListener(e -> {
                currentUser = null;
                mainFrame.setContentPanel(loginView);
                mainFrame.setMenuButtonVisible(false); // Simplemente oculta el botón
                mainFrame.hideFloatingMenu();
            });

            // --- 3. INICIO DE LA APLICACIÓN ---

            // Lógica de la pantalla de selección inicial
            seleccionInicioView.addLoginFlowListener(e -> {
                mainFrame.setContentPanel(loginView);
            });

            seleccionInicioView.addPaymentFlowListener(e -> {
                // Aquí podrías añadir lógica para cargar los costos antes de mostrar la vista
                // cobroServicioView.setCostos(...);
                mainFrame.setContentPanel(cobroServicioView);
            });

            // Establece el panel inicial
            mainFrame.setContentPanel(seleccionInicioView);

            // Hace visible la ventana principal
            mainFrame.mostrarVentana();
        });
    }
}