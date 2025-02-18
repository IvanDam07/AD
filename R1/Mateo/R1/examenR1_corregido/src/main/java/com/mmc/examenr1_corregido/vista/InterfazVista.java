/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mmc.examenr1_corregido.vista;

import com.mmc.examenr1_corregido.controlador.Controlador;
import com.mmc.examenr1_corregido.modelo.Universidad;

/**
 *
 * @author mmc20
 */
public interface InterfazVista {
    
    void arranca();
    void setControlador(Controlador controlador);
    public String leeString();
    public Universidad getUniversidad();
    public int leeId();
    
    
    static final String CREARCRPETAS = "Crea 2 carpetas en la carpeta del proyecto";
    static final String ALTADATOSCARRERASUNIVERSITARIAS = "Dar de alta una universidad";
    static final String GENERARARCHIVOXML = "Genera un archivo XML de carreras universitarias";
    static final String GENERARPLANTILLAXSL = "Genera un plantilla XSL";
    static final String MODIFICARCARRERA = "Modificar datos de una carrera universitaria";
    static final String GENERARPAGINAWEB = "Genera una pagina web a partir del XML";


}
