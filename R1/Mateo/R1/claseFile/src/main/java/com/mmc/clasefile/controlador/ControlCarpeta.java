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

    
    /**Inicia desde el controlador el proceso
     * 
     * @param vista vista de la aplicación
     * @param modelo modelo de la aplicación
     */
    public ControlCarpeta(InterfazVista vista, Carpeta modelo) {
        
        this.vista = vista;
        this.modelo = modelo;
        
        //Cofiguramos los listener de la vista indicándole que los métodos a ejecutar están en el controlador
        this.vista.setControladorCarpeta(this);
        
        //Arrancamos la interfaz gráfica
        this.vista.arranca();
        
    }
     
    
    /**
    * Este método se ejecuta automáticamente cuando se produce el evento que provoca la acción
    * 
    * @param evento Evento que ha provocado que este metodo se ejecute 
    */
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
