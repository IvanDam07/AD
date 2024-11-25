/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.isc.examenr1ivan_sobrino.vista;

import com.isc.examenr1ivan_sobrino.controlador.ControlModelo;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 23 oct 2024
 */
public interface InterfazVista {
    
    void setControlador(ControlModelo cM);
    void arranca();
    
    static final String CREARESTRUCTURADECARPETAS = "Crea estructura de carpetas";
    static final String ALTADATOSCARRERA = "Da de altal los datos de una carrera";
    static final String GENERARXML = "Genera archivo XML con Carreras Universitarias";
    static final String GENERARXLS = "Genera archivo XLS";
    static final String MODIFICARCARRERA = "Modifca una Carrera Universitaria";
    static final String GENERARPAGINAWEB = "Genera una página web con Carreras Universitarias";
}
