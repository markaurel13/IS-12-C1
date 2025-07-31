/*
 * panel inicial para elegir entre el flujo de iniciar sesion o ir a procesar el pago
 * esta interface es innecesaria en la realidad de la ejecucion pero se coloca solo para usos demostrativo
 */
package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class seleccionInicio extends JPanel {
    private BotonPanel botonIniciarSesion;
    private BotonPanel botonPagarServicio;

    public seleccionInicio(MyFrame frame) {

        this.setBackground(Color.decode("#ffffff"));

        botonIniciarSesion = new BotonPanel("Iniciar Sesión", 220, 40);
        botonIniciarSesion.setPreferredSize(new Dimension(220,40));

        botonPagarServicio = new BotonPanel("Pagar Servicio", 220, 40);
        botonPagarServicio.setPreferredSize(new Dimension(220,40));

        this.add(botonIniciarSesion);
        this.add(botonPagarServicio);
    }

    public void addLoginFlowListener(ActionListener listener) { botonIniciarSesion.addActionListener(listener); }
    public void addPaymentFlowListener(ActionListener listener) { botonPagarServicio.addActionListener(listener); }
}
