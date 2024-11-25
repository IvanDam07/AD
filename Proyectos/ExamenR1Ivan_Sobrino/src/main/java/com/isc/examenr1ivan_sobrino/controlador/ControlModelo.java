/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.examenr1ivan_sobrino.controlador;

import com.isc.examenr1ivan_sobrino.modelo.Modelo;
import com.isc.examenr1ivan_sobrino.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 23 oct 2024
 */
public class ControlModelo implements ActionListener{
    
    private final InterfazVista vista;
    private final Modelo modelo;
    
    public ControlModelo (InterfazVista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        this.vista.arranca();
    }
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        
        switch(evento.getActionCommand()) {
            case InterfazVista.CREARESTRUCTURADECARPETAS -> {
                modelo.CrearEstructuraDeCarpetas("ORIGEN", "DESTINO");
            }
            //case IntefazVista.
        }
    }
}
