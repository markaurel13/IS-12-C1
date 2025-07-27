package com.ucveats.view;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class elementoMenu extends JPanel {
        private ImageIcon iconoPlato;
        private JLabel labelIcono;
        private JPanel NoImagen;
        private JLabel nombrePlato;
        private JLabel descripcionPlato;

        public elementoMenu(String rutaIcono, String nombre, String descripcion) {

            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBackground(Color.decode("#ffffff"));
            
            URL imageUrl = getClass().getResource(rutaIcono);
            if (imageUrl != null) {
                // Opcional: Redimensionar iconos de elementos del menú si son muy grandes
                Image img = new ImageIcon(imageUrl).getImage();
                // Por ejemplo, escalar a 200x200. Ajusta el tamaño según tus iconos y diseño.
                Image scaledImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH); 
                this.iconoPlato = new ImageIcon(scaledImg);
            } else {
                System.err.println("Advertencia: Imagen no encontrada en el classpath: " + rutaIcono);
                this.iconoPlato = null;
                
            }
            
            if (iconoPlato == null) {
                NoImagen = new JPanel(new BorderLayout());
                NoImagen.setPreferredSize(new Dimension(200, 200));
                NoImagen.setMinimumSize(new Dimension(200, 200));
                NoImagen.setMaximumSize(new Dimension(200, 200));
                NoImagen.setBackground(Color.RED);
                JLabel texto = new JLabel("Icono no disponible");
                texto.setForeground(Color.WHITE);
                texto.setFont(new Font("Segoe UI", Font.ITALIC, 16));
                texto.setHorizontalAlignment(SwingConstants.CENTER);
                NoImagen.add(texto, BorderLayout.CENTER);
            }else{
                this.labelIcono = new JLabel(iconoPlato);
                this.labelIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
            }


            this.nombrePlato = new JLabel(nombre);
            this.descripcionPlato = new JLabel(descripcion);

            this.nombrePlato.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.nombrePlato.setFont(new Font("Segoe UI", Font.BOLD, 16));
            this.nombrePlato.setForeground(Color.decode("#2f3829"));

            this.descripcionPlato.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.descripcionPlato.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            this.descripcionPlato.setForeground(Color.decode("#2f3829"));

            this.add(Box.createVerticalStrut(10));
            if (iconoPlato == null) {
                this.add(NoImagen);
            } else {
                this.add(labelIcono);  
            }
            this.add(Box.createVerticalStrut(10));
            this.add(nombrePlato);
            this.add(Box.createVerticalStrut(10));
            this.add(descripcionPlato);
            this.add(Box.createVerticalStrut(10));
        }
    }