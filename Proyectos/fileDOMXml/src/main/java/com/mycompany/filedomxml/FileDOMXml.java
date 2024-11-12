/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.filedomxml;

import com.mycompany.filedomxml.modelo.GestionContenidoDOM;
import org.w3c.dom.Element;

/**
 *
 * @author Ivan Sobrino
 */
public class FileDOMXml {

    public static void main(String[] args) {
        GestionContenidoDOM modelo = new GestionContenidoDOM("Empleados");
        
        Element elem = modelo.addNodo("Empleado");
        modelo.addNodoYTexto("identificador","1", elem);
        
        Element elem2 = modelo.addNodo("Empleado");
        modelo.addNodoYTexto("identificador","2", elem2);
        
        Element elem3 = modelo.addNodo("Empleado");
        modelo.addNodoYTexto("identificador","3", elem3);
        
        
        modelo.generarArchivoDelDOM("./resources/Empleados.xml","yes");


        modelo.cargarArchivoEnMemoria("./resources/Empleados.xml");
        
        modelo.addCargoAEmpleados();
        
        //System.out.println(modelo.getElementPrincipal());
        
//        List<Empleado> empleList = modelo.getEmpleados();
//        
//        for(Empleado emple : empleList){
//            
//            System.out.println(emple);
//            
//        }
        
//        modelo.mostrarPantalla("yes");
//        modelo.quitarElementoDeEmpleados("Cargo");
//        modelo.mostrarPantalla("yes");
        
        modelo.modificarSalarioEmpleado(1, "500");
        modelo.mostrarPantalla("yes");
        modelo.modificarSalarioEmpleado_2(1,"1000");
        modelo.mostrarPantalla("yes");
    }
}
