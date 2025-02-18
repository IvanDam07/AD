package com.mmc.clasefile.controlador;

import com.mmc.clasefile.modelo.Archivo;
import com.mmc.clasefile.modelo.Carpeta;
import com.mmc.clasefile.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Encargada de comunicar la vista con el modelo
 * Como esta clase está interesada en procesar un evento de acción, entonces debe implementar la interfaz ActionListener
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 18 sept 2024
 *
 */
public class ControlCarpeta implements ActionListener{
    
    private final InterfazVista vista;
    private final Carpeta modelo;

    public ControlCarpeta(InterfazVista vista, Carpeta modelo) {
        
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControladorCarpeta(this);
        this.vista.arranca();
        
    }
     

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        String ruta = vista.getRuta();
        modelo.setRuta(ruta);
        
        switch (evento.getActionCommand()){
            
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
            
    }
   }
}
