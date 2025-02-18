/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.fileaccesoaleatorio;

import com.mmc.fileaccesoaleatorio.modelo.Borrado;
import com.mmc.fileaccesoaleatorio.modelo.Empleado;
import com.mmc.fileaccesoaleatorio.modelo.Escritura;
import com.mmc.fileaccesoaleatorio.modelo.Lectura;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Break4Learning by Javier García-Retamero Redondo
 */
public class Fileaccesoaleatorio {

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
