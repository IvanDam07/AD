/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ejercicio2examenr.controlador;

import com.isc.ejercicio2examenr.modelo.Modelo;
import com.isc.ejercicio2examenr.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 26 feb 2025
 */
public class Controlador implements ActionListener{

    private final InterfazVista vista;
    private final Modelo modelo;
    
    public Controlador(InterfazVista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        this.vista.arranca(); 
       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int anio = vista.leeAño();
        
        switch(e.getActionCommand()){
            
            case InterfazVista.ANIO ->  vista.escribeResultado(modelo.llamarFuncion(anio)+" sueldo total.");
            
        }
    }

}
