/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.mmc.ejercicio3examen.vista;

import com.mmc.ejercicio3examen.controlador.Controlador;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public interface InterfazVista {
    
    void arranca();
    void setControlador(Controlador controlador);
    public String leeString();
    public int leeInt();
    
    
    static final String INTRODUCIR_DATOS = "Pide los datos a introducir";
    static final String MOSTRAR_DATOS = "Muestra el xml";

}
