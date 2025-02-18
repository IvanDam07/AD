/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio3examen.controlador;

import com.mmc.ejercicio3examen.modelo.Modelo;
import com.mmc.ejercicio3examen.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Controlador implements ActionListener{
    
    private final InterfazVista vista;
    private final Modelo modelo;
    
    public Controlador(InterfazVista vista, Modelo modelo){
        
        this.modelo = modelo;
        this.vista = vista;
        
        this.vista.setControlador(this);
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case InterfazVista.INTRODUCIR_DATOS -> {
                modelo.añadirDatos(vista.leeString(), vista.leeInt() ,vista.leeString());
            }
            case InterfazVista.MOSTRAR_DATOS -> {
                modelo.mostrarDatos("yes");
            }
        }
    }
}
