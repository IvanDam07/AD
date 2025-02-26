/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.isc.ejercicio2examenr.vista;

import com.isc.ejercicio2examenr.controlador.Controlador;
import java.sql.Date;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 26 feb 2025
 */
public interface InterfazVista {

    void setControlador(Controlador c);
    
    void arranca();
    
    //void esribeResultado(String s);
    
    static final String ANIO = "Solicitud año";
    
    public int leeAño();

    public void escribeResultado(String string);
}
