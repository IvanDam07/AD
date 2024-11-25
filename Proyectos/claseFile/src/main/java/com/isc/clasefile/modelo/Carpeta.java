/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.clasefile.modelo;

import java.io.File;

/**
 * Clase encargada de la lógica de la aplicación
 * 
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 18 sept 2024
 */
public class Carpeta {
   
    private String ruta;
    
    /**
     * Constructor de la clase
     * 
     * @param ruta Ruta de la carpeta
     */
    public Carpeta(String ruta) {
        this.ruta = ruta;
    }
    
    /**
     * Constructor vacío
     */
    public Carpeta() {
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el constructor al cual le pasamos la ruta completa
     */
    public void crearCarpeta() {
        File directorioNuevo = new File(ruta);
        directorioNuevo.mkdir();
    }
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el constructor al cual le pasamos la ruta del directorio
     * padre y el nombre del nuevo directorio
     * 
     * @param nombreDirectorio Nombre del nuevo directorio
     */
    public void crearCarpeta(String nombreDirectorio) {
        File directorioNuevo = new File(ruta,nombreDirectorio);
        directorioNuevo.mkdir();
    }
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el constructor al cual le pasamos la ruta del directorio raiz
     * y el nombre del nuevo directorio
     * @param directorioRaiz Directorio raiz
     * @param nombreDirectorio Nombre del nuevo directorio 
     */
    public void crearCarpeta(File directorioRaiz, String nombreDirectorio) {
        File directorioNuevo = new File(directorioRaiz,nombreDirectorio);
        directorioNuevo.mkdir();
    }
    
    /**
     * Devuelve en un File la ruta de la carpeta
     * 
     * @return File que apunta a la carpeta
     */
    public File getFileDeRuta() {
        return new File(ruta);
    }
    
    /**
     * Muestra el contenido del directorio en la ruta indicada.
     * Indica si es un archivo o directorio y muestra información adicional de los archivos.
     */
    public void mostrarContenidoCarpeta() {
        File directorio = new File(ruta);
        if (directorio.isDirectory()) {
            File[] archivos = directorio.listFiles();
            if (archivos != null && archivos.length > 0) {
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        System.out.println("[Directorio] " + archivo.getName());
                    } else {
                        System.out.println("[Archivo] " + archivo.getName() + " - Tamaño: " + archivo.length() 
                                + " bytes - Última modificación: " + archivo.lastModified());
                    }
                }
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            System.out.println("La ruta no es un directorio válido.");
        }
    }
    
    public void eliminarArchivoODirectorio() {
        File elemento = new File(ruta);
        
        if (!elemento.exists()) {
            System.out.println("El archivo o directorio no existe.");
            return;
        }
        
        if (elemento.isFile()) {
            if (elemento.delete()) {
                System.out.println("Archivo eliminado " + elemento.getName());
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else if (elemento.isDirectory()) {
            File[] archivos = elemento.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        archivo.delete(); //Borramos cada archivo
                        System.out.println("Archivo eliminado: " + archivo.getName());
                    }
                }
            }
            if (elemento.delete()) {
                System.out.println("Directorio eliminado: " + elemento.getName());
            } else {
                System.out.println("No se pudo eliminar el directorio.");
            }
        }
    }
    //DESDE EL MODELO NO SE PUEDEN HACER SYSTEM.OUT
}
