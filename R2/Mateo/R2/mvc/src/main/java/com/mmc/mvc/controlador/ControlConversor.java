/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.mvc.controlador;

import com.mmc.mvc.modelo.ConversorEurosPesetas;
import com.mmc.mvc.vista.InterfazVista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 sept 2024
 *
 */
public class ControlConversor implements ActionListener{

    private final InterfazVista vista;
    private final ConversorEurosPesetas modelo;

    
    public ControlConversor(InterfazVista vista, ConversorEurosPesetas modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        this.vista.setControlador(this);
        this.vista.arranca();
    }

    
    
    @Override
    public void actionPerformed(ActionEvent evento) {
        double cantidad = vista.getCantidad();
        double comision = vista.getComision();
        
        switch(evento.getActionCommand()) {
            
            case InterfazVista.AEUROS -> vista.escribeCambio(cantidad + " pesetas son: "+ modelo.pesetasAeuros(cantidad,comision)+ " €");
            case InterfazVista.APESETAS -> vista.escribeCambio(cantidad + " € son: "+ modelo.eurosApesetas(cantidad,comision)+ " pesetas");
            default -> vista.escribeCambio("Error en la conversión");
        }
    }

}
