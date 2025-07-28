/*
 * hecho por luisangel, la idea de esta clase es tener utilidades para imagenes, las inherentes al proyecto serian cargar la imagen 
 * que ya esta implementada y faltaria implementar la comparacion con la imagen cargada y la base de datos para el reconocimiento facial
 */
package com.ucveats.controller;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
//import javax.swing.JOptionPane; // Para mostrar mensajes de error si es necesario
import java.awt.Component; // Importa Component para el parámetro parent
//import javax.swing.JLabel; // Para mostrar mensajes de error si es necesario




public final class ImageUtilities {

    private ImageUtilities() {
        // Constructor privado para evitar instanciación
    }


    public static File selectorImagen(Component padre) {
        // 1. Crear una instancia de JFileChooser
        JFileChooser selectorArchivo = new JFileChooser();
        selectorArchivo.setDialogTitle("Seleccionar Imagen");

        //Filtrar tipos de archivos (ej. solo archivos de texto)
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de imagen", "jpg", "jpeg", "png");
        selectorArchivo.addChoosableFileFilter(filtro);
        selectorArchivo.setFileFilter(filtro); // Establecer el filtro por defecto

        // 2. Mostrar el cuadro de diálogo de selección de archivo
        // showOpenDialog() es para seleccionar un archivo para abrir/cargar
        // showSaveDialog() es para seleccionar un lugar para guardar un archivo
        int resultado = selectorArchivo.showOpenDialog(padre); // 'this' se refiere a la ventana JFrame

        // 3. Procesar la selección del usuario
        if (resultado == JFileChooser.APPROVE_OPTION) {
            // El usuario seleccionó un archivo y presionó "Abrir"
            File archivoSelecionado = selectorArchivo.getSelectedFile();
            //String rutaArchivo = archivoSelecionado.getAbsolutePath();
            //status.setText("<html><p style='width: 250px;'>Archivo seleccionado: " + archivoSelecionado.getAbsolutePath()+ "</p></html>");
            System.out.println("Archivo seleccionado: " + archivoSelecionado.getAbsolutePath());
            return archivoSelecionado; 

            // Aquí es donde típicamente leerías el archivo
            // y operar el archivo seleccionado


        } else if (resultado == JFileChooser.CANCEL_OPTION) {
            // El usuario cerró el diálogo sin seleccionar un archivo
            //status.setText("Selección de archivo cancelada.");
            System.out.println("Selección de archivo cancelada.");
            return null; // Devuelve null en caso de cancelación
        }
        return null; // Devuelve null en caso de cancelación
    }
    
}
