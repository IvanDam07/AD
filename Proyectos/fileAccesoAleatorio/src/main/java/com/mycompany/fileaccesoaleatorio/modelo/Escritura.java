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
public class Escritura extends FicheroEmpleados{
  
    public Escritura(String ruta) {
         super(ruta);
    }
       
    /**
     * Añade un empleado al final del archivo
     * 
     * @param empleado Empleado con los datos a añadir
     */
    public void escribirEmpleadoFinalArchivo(Empleado empleado){
        
        // RamdomAccessFile para acceso a un fichero de forma aleatoria       
        RandomAccessFile randomFile = null;
        // Posición inicial para el caso de que el archivo esté vacío
        long posicion = 0; 
        // Buffer para escribir el apellido
        StringBuffer bufferStr = null;
        
        try {
              
            randomFile = new RandomAccessFile(getRuta(), "rw");
            
            // Si el archivo no está vacío nos posicionamos en la última posición
            if (randomFile.length()!=0) {
               posicion = (randomFile.length());
            }
            
            // Nos situamos en la posición en la que vamos a escribir 
            randomFile.seek(posicion);
            
            // Calculamos y escribimos el identificador del empleado
            // Imagina que la posicion es 120 y el tamaño del registro 40 (nos lo da getLONGITUD_TOTAL) => El id sería 120/40+1= 4 
            // El id sería el 4 porque ya hay 3 registros anteriores de 40 bytes cada uno.            
            randomFile.writeLong((posicion/super.getLONGITUD_TOTAL()) + 1);   
            
            // Escribimos el apellido del empleado
            bufferStr = new StringBuffer(empleado.getApellido());
            bufferStr.setLength(super.getCARACTERES_APELLIDO()); // Fijamos el tamaño del buffer
            randomFile.writeChars(bufferStr.toString());
            
            // Escribimos el número del departamento
            randomFile.writeInt(empleado.getDepartamento());
            
            // Escribimos el salario
            randomFile.writeDouble(empleado.getSalario());        
            
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
    
    
    //que el proyecto almacene la información de un empleado segun un identificador dado
    public void modificarEmpleado(Empleado empleado) {
        
    // RandomAccessFile para acceso a un fichero de forma aleatoria
    RandomAccessFile randomFile = null;
    
    int posicion = 0;
    StringBuffer bufferStr = null;
    
    try {
        randomFile = new RandomAccessFile(getRuta(), "rw");

        // Calculamos la posición del empleado con el identificador dado
        posicion = (int) ((empleado.getIdentificador() - 1) * super.getLONGITUD_TOTAL());

        // Verificamos si la posición es válida (dentro del archivo)
        if (posicion < randomFile.length()) {
            
            randomFile.seek(posicion); // Vamos a la posición del registro

            // Sobrescribimos los nuevos datos del empleado
            randomFile.writeLong(empleado.getIdentificador());

            // Escribimos el apellido
            bufferStr = new StringBuffer(empleado.getApellido());
            bufferStr.setLength(super.getCARACTERES_APELLIDO()); // Fijamos el tamaño del buffer
            randomFile.writeChars(bufferStr.toString());

            // Escribimos el número del departamento
            randomFile.writeInt(empleado.getDepartamento());

            // Escribimos el salario
            randomFile.writeDouble(empleado.getSalario());
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

    
    
    public void modificarApellido(long identificador, String nuevoApellido) {
        
    RandomAccessFile randomFile = null;
    int posicion = (int) ((identificador - 1) * super.getLONGITUD_TOTAL());
    byte[] cadena = new byte[super.getLONGITUD_APELLIDO_EN_BYTES()];

    try {
        randomFile = new RandomAccessFile(getRuta(), "rw");

        // Verificamos que la posición es válida
        if (posicion < randomFile.length()) {
            randomFile.seek(posicion);

            // Leemos el identificador existente
            long idExistente = randomFile.readLong();

            // Solo modificamos si el identificador coincide
            if (idExistente == identificador) {
                // Modificamos el apellido
                StringBuffer bufferStr = new StringBuffer(nuevoApellido);
                bufferStr.setLength(super.getCARACTERES_APELLIDO());
                
                // Posición del apellido: posicion + longitud del id
                randomFile.seek(posicion); // Aquí especificamos el tamaño del long directamente
                randomFile.writeChars(bufferStr.toString());
                System.out.println("Apellido del empleado con ID " + identificador + " modificado a: " + nuevoApellido);
            } else {
                System.out.println("No se encontró el empleado con ID: " + identificador);
            }
        } else {
            System.out.println("ID fuera de los límites del archivo.");
        }
    } catch (FileNotFoundException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            if (randomFile != null) {
                randomFile.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Escritura.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


}