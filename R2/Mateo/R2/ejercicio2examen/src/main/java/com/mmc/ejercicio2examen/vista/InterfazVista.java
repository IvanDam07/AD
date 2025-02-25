/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio2examen.vista;

import com.mmc.ejercicio2examen.controlador.Controlador;
import java.sql.Date;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 27 nov 2024
 *
 */
public interface InterfazVista {
    
    void setControlador (Controlador c);
    
    void arranca();
    
    void escribeResultado(String s);
    
    static final String FECHA = "Solicitud de fecha";

    public Date leeFecha();

    

}
