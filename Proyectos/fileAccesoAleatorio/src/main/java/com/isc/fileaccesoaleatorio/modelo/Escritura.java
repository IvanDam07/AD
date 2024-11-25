/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.fileaccesoaleatorio.modelo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 14 oct 2024
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
            randomFile.writeLong(posicion/super.getLONGITUD_TOTAL() + 1);   
            
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
    
    public void modificarApellidoEmpleado(int inputId, String nuevoApellido) throws IOException {
    RandomAccessFile randomFile = null;
    StringBuffer buffer = null;
    
    try {
      randomFile = new RandomAccessFile(getRuta(), "rw");
      while (randomFile.getFilePointer() < randomFile.length()) {
        long pos = randomFile.getFilePointer();
        long idActual = randomFile.readLong();
            
        if (idActual == inputId) {
          randomFile.seek(pos + LONGITUD_IDENTIFICADOR);
                
          buffer = new StringBuffer(nuevoApellido);
          buffer.setLength(super.getCARACTERES_APELLIDO());
          randomFile.writeChars(buffer.toString());
        }
            
        randomFile.skipBytes(LONGITUD_TOTAL - LONGITUD_IDENTIFICADOR);
      }
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Archivo no encontrado", ex);
    } catch (IOException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Error de E/S", ex);
    } finally {
      if (randomFile != null) {
        try {
          randomFile.close();
        } catch (IOException ex) {
          Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Error al cerrar el archivo", ex);
        }
      }
    }
  }
    
    public void borradoLogico (int inputId) throws IOException {
    RandomAccessFile randomFile = null;
    long posicion = (identificador-1)*getLONGITUD_TOTAL();
    try {
      randomFile = new RandomAccessFile(getRuta(), "rw");
      
      randomFile.seek(posicion);//posiciono el cursor donde quiero escribir
      
      randomFile.writeLong(0L); //posición/longitudTotal + 1
      
      
    } catch (FileNotFoundException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Archivo no encontrado", ex);
    } catch (IOException ex) {
      Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Error de E/S", ex);
    } finally {
      if (randomFile != null) {
        try {
          randomFile.close();
        } catch (IOException ex) {
          Logger.getLogger(Lectura.class.getName()).log(Level.SEVERE, "Error al cerrar el archivo", ex);
        }
      }
    } 
  }
}