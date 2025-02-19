/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.isc.ejercicio3ordinaria1;

import java.util.Date;
import java.util.Scanner;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Ejercicio3ordinaria1 {

    public static void main(String[] args) {

        utilidadDOM utilidad = new utilidadDOM("lanzamiento");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Dime un id");
        String id = scanner.next();
        System.out.println("Dime una fecha de lanzamiento (ddMMyyyy");
        String fecha = scanner.next();        
        System.out.println("Dime un lugar de lanzamiento");
        String lugar = scanner.next();
        System.out.println("Dime las horas de vuelo estimadas");
        String horas = scanner.next();
        
        //Se añade al archivo
        Element elem = utilidad.addNodo("lanzamiento");
        utilidad.addNodoYTexto("id", id, elem);
        utilidad.addNodoYTexto("fecha_lanzamiento", fecha, elem);
        utilidad.addNodoYTexto("lugar_lanzamiento", lugar, elem);
        utilidad.addNodoYTexto("horas_vuelo_estimadas", horas, elem);
        //Archivo DOM mostrar por pantalla
        utilidad.generarArchivodelDOM("./RESOURCES/publicacionLanzamientos.xml");
    }

}
