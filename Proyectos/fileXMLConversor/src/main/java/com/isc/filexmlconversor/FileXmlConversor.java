/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.filexmlconversor;

import com.isc.filexmlconversor.modelo.Conversor;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class FileXmlConversor {

    public static void main(String[] args) {
        String origenDatos = "./resources/alumnos.xml";
        String hojaEstilos = "./resources/alumnosPlantilla.xsl";
        String htmlDestino = "./resources/outputHTML.html";
        Conversor modelo = new Conversor(origenDatos, hojaEstilos, htmlDestino);
        modelo.ConvertirAHTML();
    }
}
