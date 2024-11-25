/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.clasefile.controlador;

import com.isc.clasefile.modelo.Carpeta;
import com.isc.clasefile.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Encargada de comunicar la vista con el modelo
 * Como esta clase está interesada en procesar un evento de acción entonces
 * debe implementar la interfaz ActionListener 
 * 
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 18 sept 2024
 */
public class ControlCarpeta implements ActionListener{
    
    private final InterfazVista vista;
    private final Carpeta modelo;

    public ControlCarpeta(InterfazVista vista, Carpeta modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        String ruta = vista.getRuta();
        modelo.setRuta(ruta);
        
        switch (evento.getActionCommand()) {
            case InterfazVista.CREARCARPETACONRUTACOMPLETA -> {
                modelo.crearCarpeta();
            }
            case InterfazVista.CREARCARPETACONRUTAPADREYNOMBRE -> {
                String nombre = vista.getNombre();
                modelo.crearCarpeta(nombre);
            }
            case InterfazVista.CREARCARPETACONFILEPADREYNOMBRE -> {
                String nombre = vista.getNombre();
                modelo.crearCarpeta(modelo.getFileDeRuta(),nombre);
            }
            case InterfazVista.MOSTRARCONTENIDOCARPETA -> {
                modelo.mostrarContenidoCarpeta();  // Llama al método para mostrar el contenido del directorio
            }
            case InterfazVista.ELIMINARARCHIVOODIRECTORIO -> {
                modelo.eliminarArchivoODirectorio();
            }
            /* 
            case InterfazVista.MOSTRARCONTENIDOCARPETA -> {
                modelo.mostrarContenidoCarpeta();
            }
            case InterfazVista.ELEMINARARCHIVOODIRECTORIO -> {
                eliminarArchivoODirectorio();
            }
            */
        }
    }
    
}
