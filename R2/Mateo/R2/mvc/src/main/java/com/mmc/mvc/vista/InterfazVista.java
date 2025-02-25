/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mmc.mvc.vista;

import com.mmc.mvc.controlador.ControlConversor;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 sept 2024
 *
 */
public interface InterfazVista {

    void setControlador(ControlConversor c);
    
    void arranca();
    
    double getCantidad();
    
    double getComision();
    
    void escribeCambio(String s);
    
    /*
    Constantes para las operaciones
    */
    static final String AEUROS = "Pesetas a Euros";
    static final String APESETAS = "Euros a Pesetas";
    
}
