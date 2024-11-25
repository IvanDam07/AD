/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.filexml;

import com.isc.filexml.modelo.Empleado;
import com.isc.filexml.modelo.GestionContenidoDOM;
import java.util.List;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class FileXML {

    public static void main(String[] args) {
        String generalName = "Empleados";
    GestionContenidoDOM modeloDOM = new GestionContenidoDOM(generalName);
    
    modeloDOM.createNewEmpleado(1, "Ramírez", 7, 1200.0, "Consultor");
    modeloDOM.createNewEmpleado(2, "Chavez", 10, 500.0, "Administrador");
    modeloDOM.createNewEmpleado(3, "Gascón", 21, 600.0);

    
    modeloDOM.addCargoToAllEmpleados();
    modeloDOM.modifySalarioFromEmpleado(1, 1000.0);
    
    modeloDOM.generateFileFromDOM("./Resources/"+ generalName +".xml");
    
    System.out.println("\n");
    
    List<Empleado> empleList = modeloDOM.getEmpleados();
    for (Empleado tempEmple : empleList) {
      System.out.println(tempEmple.toString());
    }
    }
}
