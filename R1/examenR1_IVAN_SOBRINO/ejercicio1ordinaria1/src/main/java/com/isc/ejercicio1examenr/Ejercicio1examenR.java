/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.ejercicio1examenr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Ejercicio1examenR {

    public static void main(String[] args) {
        String nombreArchivo = "prueba.txt";
        String ruta = "./PRUEBA";
        String rutaCopia = "./Destino";        
        
        double tamanio = ocupaEjercicio1(nombreArchivo, ruta, rutaCopia);
        System.out.println("El tamaño total de los archivos llamados " +nombreArchivo+ " es " +tamanio);        
    }
    
    public static double ocupaEjercicio1(String nombreArchivo, String rutaPartida, String rutaCopia) {
        File destino = new File(rutaCopia);
        File partida = new File(rutaPartida);
        
        double tamanio = 0;
        
        if(!destino.exists()) {
            //destino.mkdir();
            System.out.println("Error. La carpeta destino no existe.");
        }
        File[] archivosIguales = null; 
        tamanio = utilidadBusquedaRecursiva(partida, nombreArchivo, destino, tamanio, archivosIguales);        
        File archivo;
        for (int i = 0; i<archivosIguales.length; i++) {
            if (archivosIguales[i+1].length() > archivosIguales[i].length()) {
                archivo = archivosIguales[i];
            }
        }
        
        //utilidadMoverArchivo(archivo, destino);
        return tamanio;
    }
    
    public static double utilidadBusquedaRecursiva(File directorio, String nombreArchivo, File desino, double tamanioTotal, File[] archivosIguales){
        File [] contenido = directorio.listFiles();
        
        //File f : contenido
        for (int i = 0; i < contenido.length; i++) {
            //System.out.println("Buscando en: "+f.getPath());
            if( contenido[i].isDirectory()){
                utilidadBusquedaRecursiva(contenido[i],nombreArchivo,desino,tamanioTotal, archivosIguales);
            }else if(contenido[i].isFile() && contenido[i].getName().equals(nombreArchivo)){
                archivosIguales[i] = contenido[i];
                //System.out.println("He encontrado el archivo: "+f.getName());
                tamanioTotal = tamanioTotal + contenido[i].length();
                //Lo suma bien, pero cuando sale del for devuelve solo el tamaño de un archivo
                //System.out.println(tamanioTotal);                
            }            
        }             
        
        //He pensado en guardar en un array todos los archivos que se llamen igual. Después recorrerlos comparándolos y el que más peso tenga, llamar al método utilidadMoverArchivo
        
        
//        for(File f : contenido){
//            
//            //System.out.println("Buscando en: "+f.getPath());
//            if( f.isDirectory()){
//                utilidadBusquedaRecursiva(f,nombreArchivo,desino,tamanioTotal);
//            }else if(f.isFile() && f.getName().equals(nombreArchivo)){
//                archivosIguales[] = f;
//                //System.out.println("He encontrado el archivo: "+f.getName());
//                tamanioTotal = tamanioTotal + f.length();
//                //Lo suma bien, pero cuando sale del for devuelve solo el tamaño de un archivo
//                //System.out.println(tamanioTotal);                
//            }            
//        }
        return tamanioTotal;
    }
    
     /**
     * Mueve un archivo de una ruta origen a una ruta destino
     * @param rutaOrigen
     * @param rutaDestino 
     */
    ///Falta cambiar cosas del método para que funcione 
    public void utilidadMoverArchivo(String rutaOrigen, String rutaDestino){
        
        //creamos los objetos 
        File archivoOrigen = new File(rutaOrigen);
        File archivoDestino = new File(rutaDestino);
        
        
        //comprobamos si existe el arch origen
        if(archivoOrigen.exists() && archivoOrigen.isFile()){
            
            //intentamos mover el archivo
            if(archivoOrigen.renameTo(archivoDestino)) {
                
                System.out.println("Archivo movido exitosamente a " + rutaDestino);
                
            } else {
                
                System.out.println("Error al mover el archivo");
                
            }
            
        } else { 
            
            System.out.println("El archivo origen no existe");
            
        }
        
        
        
    }
}
