/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.clasefile.controlador;

import com.isc.clasefile.modelo.Archivo;
import com.isc.clasefile.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 20 sept 2024
 */
public class ControlArchivo implements ActionListener{
    
    private final InterfazVista vista;
    private final Archivo modelo;
    
    public ControlArchivo(InterfazVista vista, Archivo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        //this.vista.arranca();
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String ruta = vista.getRuta();  // Obtener ruta de la vista
        String nombre = vista.getNombre();  // Obtener nombre del archivo
        modelo.setRuta(ruta);  // Establecer la ruta
        modelo.setNombre(nombre);  // Establecer el nombre

        switch (evento.getActionCommand()) {
            case InterfazVista.CREARARCHIVOENCARPETA -> {
                modelo.crearArchivo();  // Crear el archivo
            }
            case InterfazVista.RENOMBRARARCHIVO -> {
                String nombreActual = vista.getNombreActual();
                String nuevoNombre = vista.getNuevoNombre();
                modelo.renombrarArchivo(nombreActual, nuevoNombre);  // Llama al método para renombrar
            }
            case InterfazVista.COPIARARCHIVO -> {
                String rutaOrigen = vista.getRutaOrigen();
                String rutaDestino = vista.getRutaDestino();
                modelo.copiarArchivo(rutaOrigen, rutaDestino);
            }
            case InterfazVista.MOVERARCHIVO -> {
                String rutaOrigen = vista.getRutaOrigen();
                String rutaDestino = vista.getRutaDestino();
                modelo.moverArchivo(rutaOrigen, rutaDestino);
            }
            case InterfazVista.LISTARCONTENIDO -> {
                ruta = vista.getRutaDirectorio();
                modelo.listarContenidoDirectorio(ruta);
            }
        }
    }
}
