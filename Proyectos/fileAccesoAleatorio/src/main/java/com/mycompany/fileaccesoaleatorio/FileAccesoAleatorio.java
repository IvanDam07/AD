/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.fileaccesoaleatorio;

import com.mycompany.fileaccesoaleatorio.modelo.Borrado;
import com.mycompany.fileaccesoaleatorio.modelo.Empleado;
import com.mycompany.fileaccesoaleatorio.modelo.Escritura;
import com.mycompany.fileaccesoaleatorio.modelo.Lectura;
import java.util.List;

/**
 *
 * @author Ivan Sobrino
 */
public class FileAccesoAleatorio {

    public static void main(String[] args) {
        Escritura modeloE = new Escritura("archivo_empleados.dat");
        
        Empleado empleado = new Empleado("Pérez", 3, 2000);
        modeloE.escribirEmpleadoFinalArchivo(empleado);
        
        empleado = new Empleado("García", 2, 2500);
        modeloE.escribirEmpleadoFinalArchivo(empleado);
        
        empleado = new Empleado("Robledo", 1, 2500);
        modeloE.escribirEmpleadoFinalArchivo(empleado);
        
        
        Lectura modeloL = new Lectura("archivo_empleados.dat");
        System.out.println(modeloL.lecturaEmpleado(3).toString());
        
        Borrado modeloB = new Borrado("archivo_empleados.dat");
        modeloB.borradoRegistro(2);
        
        List<Empleado> lista = modeloL.lecturaTodosLosEmpleados();
        System.out.println(lista.toString());
    }
}
