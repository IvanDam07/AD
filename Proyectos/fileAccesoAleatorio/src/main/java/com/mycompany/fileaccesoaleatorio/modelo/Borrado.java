/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fileaccesoaleatorio.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class Borrado extends FicheroEmpleados {
    
    public Borrado(String ruta){
        super(ruta);
    }
    
    
    /**
     * Realiza un borrado logico del registro dado un identificador (marca el identificador a 0)
     * @param identificador
     */
    public void borradoRegistro(int identificador) {
        
        // RamdomAccessFile para acceso a un fichero de forma aleatoria
        RandomAccessFile randomFile = null; 
        int posicion = 0;
        Empleado empleado = new Empleado();
        
        try {
            
            randomFile = new RandomAccessFile(getRuta(), "rw");
            
            // La posicion se calcula a partir del Id. 
            // Imagina que el Id es 3 y que la longitud del registro es 40 (nos lo da getLONGITUD_TOTAL) => (3-1) * 40 = 80.
            // Los datos del empleado 3 estarían a partir de la posición 80
            posicion = (identificador-1) * super.getLONGITUD_TOTAL();
            
            // Verificamos que el id corresponde a una posición menor que la longitud del archivo
            if (posicion < randomFile.length()) {  
                
                randomFile.seek(posicion);
                
                //le ponemos el identificador a 0
                empleado.setIdentificador(0);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                randomFile.close();
            } catch (IOException ex) {
                Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    //mismo metodo que el de arriba pero mejorado
    public void borrarEmpleado(long identificador) {
    RandomAccessFile randomFile = null;
    int posicion = 0;

    try {
        randomFile = new RandomAccessFile(getRuta(), "rw");

        // Calculamos la posición del empleado con el identificador dado
        posicion = (int) ((identificador - 1) * super.getLONGITUD_TOTAL());

        // Verificamos si la posición es válida (dentro del archivo)
        if (posicion < randomFile.length()) {
            randomFile.seek(posicion); // Vamos a la posición del registro

            // Leemos el identificador actual para verificar que existe un empleado
            long idActual = randomFile.readLong();

            // Si el identificador coincide, lo sobrescribimos con 0
            if (idActual == identificador) {
                randomFile.seek(posicion); // Volvemos al inicio del registro
                randomFile.writeLong(0); // Marcamos el identificador como 0
                System.out.println("El empleado con identificador " + identificador + " ha sido borrado lógicamente.");
            } else {
                System.out.println("El registro no coincide con el identificador proporcionado.");
            }
        } else {
            System.out.println("El registro con el identificador proporcionado no existe.");
        }

    } catch (FileNotFoundException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            
            randomFile.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

}