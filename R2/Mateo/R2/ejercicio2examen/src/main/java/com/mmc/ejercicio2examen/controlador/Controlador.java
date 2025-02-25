/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio2examen.controlador;

import com.mmc.ejercicio2examen.modelo.Modelo;
import com.mmc.ejercicio2examen.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 27 nov 2024
 *
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
    public void actionPerformed(ActionEvent evento) {
        
        Date fecha = vista.leeFecha();
        
        switch(evento.getActionCommand()){
            
            case InterfazVista.FECHA -> vista.escribeResultado(modelo.llamarProcedimiento(fecha)+" operadores han sido movidos");
            
        }
        
    }

}
