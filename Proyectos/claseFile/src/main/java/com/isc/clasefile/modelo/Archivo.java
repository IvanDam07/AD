/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.clasefile.modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 20 sept 2024
 */
public class Archivo {
    
    private String nombre;
    private String ruta;
    
    /**
     * Constructor vacío
     */
    public Archivo() {
        
    }
    
    /**
     * Constructor de la clase
     * 
     * @param nombre Nombre del archivo
     * @param ruta Ruta de la carpeta donde se creará el archivo
     */
    public Archivo (String nombre, String ruta) {
        this.nombre = nombre;
        this.ruta = ruta;
    }
    
    /**
     * Método que te devuelve el nombre del archivo
     * @return Nombre del archivo
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método que te devuelve la ruta donde se creará el archivo
     * @return Ruta donde se creará el archivo
     */
    public String getRuta() {
        return ruta;
    }
    
    /**
     * Método que modifica el nombre del archivo
     * @param nombre Nombre que tendrá el archivo
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Método que modifica la ruta donde estará el archivo
     * @param ruta Ruta donde estará el archivo
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    /**
     * Crea un archivo en la ruta correspondiente
     */
    public void crearArchivo() {
        File archivoNuevo = new File(ruta + File.separator + nombre);
        
        try {
            if(archivoNuevo.createNewFile()) {
                System.out.println("El archivo se creó correctamente.");
            } else {
                System.out.println("El archivo ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo.");
            e.printStackTrace();
        }
    }
    
    /**
     * Renombra el archivo
    */
    public void renombrarArchivo (String nombreActual, String nuevoNombre) {
        File archivoActual = new File(ruta, nombreActual);
        File archivoNuevo= new File(ruta, nuevoNombre);
        
        if (archivoActual.exists() && archivoActual.isFile()) {
            if (archivoActual.renameTo(archivoNuevo)) {
                System.out.println("Archivo renombrado de " +nombreActual+ " a " 
                    +nuevoNombre);
            } else {
                System.out.println("No se pudo renombrar el archivo");
            }
        } else {
            System.out.println("El archivo no existe.");
        }        
    }
    
    public void copiarArchivo (String rutaOrigen, String rutaDestino) {
        Path origen = new File(rutaOrigen).toPath();
        Path destino = new File(rutaDestino).toPath();

        try {
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado con éxito de " + rutaOrigen + 
                    " a " + rutaDestino);
        } catch (IOException e) {
            System.out.println("Error al copiar el archivo: " + e.getMessage());
        }
    }
    
    public void moverArchivo (String rutaOrigen, String rutaDestino) {
        Path origen = new File(rutaOrigen).toPath();
        Path destino = new File(rutaDestino).toPath();

        try {
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido con éxito de " + rutaOrigen + 
                    " a " + rutaDestino);
        } catch (IOException e) {
            System.out.println("Error al mover el archivo: " + e.getMessage());
        }
    }
    //DESDE EL MODELO NO SE PUEDEN HACER SYSTEM.OUT
}
