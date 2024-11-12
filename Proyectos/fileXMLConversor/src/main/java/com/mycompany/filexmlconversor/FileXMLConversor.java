/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.filexmlconversor;

import com.mycompany.filexmlconversor.modelo.Conversor;

/**
 *
 * @author Ivan Sobrino
 */
public class FileXMLConversor {

    public static void main(String[] args) {
        String ficheroXML = "./Resources/Empleados.xml";
        String plantillaXSL = "./Resources/plantillaEmpleados.xsl";
        String ficheroHTML = "./Resources/outputHTML.html";

        Conversor modeloConversor = new Conversor(ficheroXML, plantillaXSL, ficheroHTML);
        modeloConversor.ConvertToHTML();
    }
}
