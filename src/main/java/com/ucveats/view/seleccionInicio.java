/*
 * panel inicial para elegir entre el flujo de iniciar sesion o ir a procesar el pago
 * esta interface es incesesaria en la realidad de la ejecusion pero se coloca solo para usos demostrativo
 */
package com.ucveats.view;

import javax.swing.*;
import com.ucveats.Main;
import java.awt.*;

public class seleccionInicio extends JPanel {

    

    public seleccionInicio(MyFrame frame) {


        this.setBackground(Color.decode("#ffffff"));

        BotonPanel botonIniciarSesion = new BotonPanel("Iniciar Sesión", 220, 40);
        botonIniciarSesion.setPreferredSize(new Dimension(220,40));
        botonIniciarSesion.addActionListener(e -> {
            Main.seleccionador = 1;
            Main.main(null);
        });

        BotonPanel botonRegistro = new BotonPanel("Pagar Servicio", 220, 40);
        botonRegistro.setPreferredSize(new Dimension(220,40));
        botonRegistro.addActionListener(e -> {
            Main.seleccionador = 2;
            Main.main(null);
        });

        this.add(botonIniciarSesion);
        this.add(botonRegistro);
    }
}
