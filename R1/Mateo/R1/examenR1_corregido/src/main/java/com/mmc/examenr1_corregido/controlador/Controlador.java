/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mmc.examenr1_corregido.controlador;

import com.mmc.examenr1_corregido.modelo.Modelo;
import com.mmc.examenr1_corregido.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mmc20
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
            case InterfazVista.CREARCRPETAS -> {
                modelo.CrearEstructuraDeCarpetas();
            }
            case InterfazVista.ALTADATOSCARRERASUNIVERSITARIAS -> {
                modelo.AltaDatosCarrerasUniversitarias();
            }
            case InterfazVista.GENERARARCHIVOXML -> {
                modelo.GeneraXMLCarrerasUniversitarias();
            }
            case InterfazVista.MODIFICARCARRERA -> {
               
                modelo.ModificaCarreraUniversitaria(vista.leeId(), vista.leeString());
            }
            case InterfazVista.GENERARPLANTILLAXSL -> {
               
                modelo.generarXSL();
            }
            case InterfazVista.GENERARPAGINAWEB -> {
               
                modelo.convertirAHTML();
            }
        }
    }
    
}
