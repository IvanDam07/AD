/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.ejercicio1examen;

import java.io.File;
import static java.io.File.separator;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 13 nov 2024
 */
public class Modelo {

    /**
     * Método que busca el nombre de un archivo y lo copia
     *
     * @param nombreArchivo El nombre del archivo a copiar
     * @param rutaPartida La ruta en la que se encuentra el archivo
     * @param rutaCopia La ruta en la que se copiará el archivo
     */
    public void busquedaEjercicio1(String nombreArchivo, String rutaPartida, String rutaCopia) {

        //Carpetas con las rutas
        File archivoOrigen = new File(rutaPartida);
        File archivoDestino = new File(rutaCopia);       
        
        FileInputStream input = null;
        FileOutputStream out = null;

        File carpeta = new File(rutaCopia);
        //Si no existe la carpeta destino, la crea
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        if (archivoOrigen.exists() && archivoOrigen.isDirectory()) {
            
            System.out.println("La carpeta ORIGEN es un directorio y existe.");
            
            File[] contenido = archivoOrigen.listFiles();

            if (contenido != null) {
                
                System.out.println("Hay contenido en la carpeta, se está buscando.");
                
                for (File archivo : contenido) {
                    
                    if (archivo.isDirectory()) {
                       System.out.println("Entro al bucle del directorio");
                       boolean encontrado = utilidadRecorrerDirectorio(archivo);
                       
                       if (encontrado) {
                           if (archivo.getName().equals(nombreArchivo)) {
                            
                            System.out.println("Se está copiando.");
                            utilidadCopiarArchivo(rutaPartida, rutaCopia, nombreArchivo);
                            System.out.println("Se ha copiado");
                        }
                       }
                    } else if (archivo.isFile()) {
                        
                        System.out.println("El archivo a copiar es un file.");
                        
                        if (archivo.getName().equals(nombreArchivo)) {
                            
                            System.out.println("Se está copiando.");
                            utilidadCopiarArchivo(rutaPartida, rutaCopia, nombreArchivo);
                            System.out.println("Se ha copiado");
                        }
                    }
                }
            }
        }
    }
    
    public void utilidadCopiarArchivo(String rutaPartida, String rutaCopia, String nombre) {
        File archivoOrigen2 = new File(rutaPartida+ separator+nombre);
        File archivoDestino2 = new File(rutaCopia+ separator+nombre);
        
        FileInputStream input = null;
        FileOutputStream out = null;
        
        if (archivoOrigen2.exists() && archivoOrigen2.isFile()) {
            
            try {
            
            input = new FileInputStream(archivoOrigen2);
            out = new FileOutputStream(archivoDestino2);
            
            byte[] buffer = new byte[1024];
            int bytesLeidos;
            
            while((bytesLeidos = input.read(buffer)) > 0) {
                out.write(buffer,0,bytesLeidos);
            }
            
            System.out.println("Archivo copiado con éxito.");
            
            } catch (IOException e) {
                System.out.println("Error al copiar el archivo: " + e.getMessage());
            }
            
        } else {
            
            System.out.println("El archivo origen no existe o no es valido");
            
        }
    }
    
    public boolean utilidadRecorrerDirectorio(File archivo) {
        File[] contenido = archivo.listFiles();
        
        for(File archivo2 : contenido) {
            
            if (archivo2.isDirectory()) {
                utilidadRecorrerDirectorio(archivo2);
            } else if (archivo2.isFile()) {
                
                return true;
            }
        }
        return false;
    }

}
