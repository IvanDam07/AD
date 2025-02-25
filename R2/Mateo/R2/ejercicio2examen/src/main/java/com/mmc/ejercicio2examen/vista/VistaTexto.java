/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio2examen.vista;

import com.mmc.ejercicio2examen.controlador.Controlador;
import static com.mmc.ejercicio2examen.vista.InterfazVista.FECHA;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 27 nov 2024
 *
 */
public class VistaTexto implements InterfazVista{
    
    private Controlador controlador; 
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    @Override
    public Date leeFecha(){
        
        System.out.println("Inserta una fecha (con / y en formato dd/MM/yyyy)");
        
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fecha = null;
        
        String fechaString = null;
        
        try {
            
            fechaString = in.readLine();
            
            //cojo el string de arriba y lo parseo a fecha
            fecha = s.parse(fechaString);
            
            //la transformo a tipo sql y lo devuevlo
            java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
            
            return fechaSql;
            
        } catch (IOException | ParseException ex) {
            Logger.getLogger(VistaTexto.class.getName()).log(Level.SEVERE, null, ex);
            
            return leeFecha();
        }
        
    }
    
    public void procesaNuevaOperacion(){
        
        int operacion = 1;
        
        switch(operacion){
            
            case 0 ->
                System.exit(0);
            
            case 1 -> 
                controlador.actionPerformed(new ActionEvent(this, operacion, FECHA));
            
        }
        
    }
        

    @Override
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public void escribeResultado(String s) {
        System.out.println(s);
        
    }
    

}
