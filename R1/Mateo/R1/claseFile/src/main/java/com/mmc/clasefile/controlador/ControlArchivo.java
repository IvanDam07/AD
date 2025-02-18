/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.clasefile.controlador;

import com.mmc.clasefile.modelo.Archivo;
import com.mmc.clasefile.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 20 sept 2024
 *
 */
public class ControlArchivo implements ActionListener {
    
    private final InterfazVista vista;
    private final Archivo modelo;
    
    /**
     * Constructor del controlador
     * @param vista vista del programa
     * @param modelo modelo del programa
     */
    public ControlArchivo(InterfazVista vista, Archivo modelo){
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControladorArchivo(this);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        switch (evento.getActionCommand()){
            
            case InterfazVista.MOSTRARCONTENIDO -> {
                
                String directorioPadre = vista.getRuta();
                File carpetaPadre = new File(directorioPadre);
                
                modelo.mostrarContenido(carpetaPadre);
                      
            }
            
            case InterfazVista.RENOMBRAR -> {
                
                String directorioPadre = vista.getRuta();
                modelo.renombrarArchivo(directorioPadre);
                
            }
            
            case InterfazVista.ELIMINAR -> {
                
                String directorioPadre = vista.getRuta();
                modelo.eliminar(directorioPadre);
                
            }
            
        }
        
    }

}
