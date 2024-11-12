/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.examenjo.vista;

import com.mycompany.examenjo.controlador.ControladorExamen;
import com.mycompany.examenjo.modelo.Universidad;

/**
 *
 * @author Ivan Sobrino
 */
public interface InterfazVista {

    void arranca();

    void setControladorE(ControladorExamen controlador);

    public String leeString();

    public Universidad getUniversidad();

    static final String CREARCRPETAS = "Crea 2 carpetas en la carpeta del proyecto";
    static final String ALTADATOSCARRERASUNIVERSITARIAS = "Dar de alta una universidad";
    static final String GENERARARCHIVOXML = "Genera un archivo XML de carreras universitarias";
    static final String GENERARPLANTILLAXSL = "Genera un plantilla XSL";
    static final String MODIFICARCARRERA = "Modificar datos de una carrera universitaria";
    static final String GENERARPAGINAWEB = "Genera una pagina web a partir del XML";

    public int leeId();
}
