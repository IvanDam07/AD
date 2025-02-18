package com.mmc.clasefile.modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 20 sept 2024
 *
 */
public class Archivo {
    
    public String directorioPadre;
    public String nombreArchivo;
    
    private Scanner t = new Scanner(System.in);
    
    
    /**
     * Constructor de la clase que crea un archivo en la ubicacion y nombre que le demos
     * @param ruta ruta del archivo
     * @param nombreArchivo nombre del archivo
     */
    public Archivo(String directorioPadre, String nombreArchivo){
        this.directorioPadre = directorioPadre;
        this.nombreArchivo = nombreArchivo;
    }
    
    
    /**
     * Constructor vacío
     */
    public Archivo(){
        
    }
    
    
    /** 
     * @return devuelve la ruta
     */
    public String getDirectorioPadre(){
        return directorioPadre;
    }
    
    
    /**
     * @param ruta nueva ruta
     */
    public void setDirectorioPadre(String directorioPadre){
        this.directorioPadre = directorioPadre;
    }
    
    
    /**
     * @return el nombre del archivo
     */
    public String getNombreArchivo(){
        return nombreArchivo;
    }
    
    
    /**
     * @param nombreArchivo nuevo nombre del archivo
     */
    public void setNombreArchivo(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }
    
    
    
    /**
     * 
     * Metodo para crear un archivo en la ruta indicada, utiliza el atributo de la clase
     * 
     * @param nombre nombre del archivo a crear
     * 
     */
    public void crearArchivo(String directorioPadre, String nombreArchivo) {
        
        
    }
    
    
    /**
     * Muestra el contenido de un directorio indicando si lo que hay dentro en un directorio o un archivo. (Considero mas correcto ponerlo aquí que en la clase archivo).
     * @param carpeta directorio del que vamos a mostrar el contenido
     */
    public void mostrarContenido(File carpeta){
        
        String[] contenido = carpeta.list();
        String contenidoArray = Arrays.toString(contenido);
        
        System.out.println(contenidoArray);
        
    }
    
    
    /**
     * Método que muestra el contenido de un directorio, nos dice si es un archivo o un directorio y si es un archivo nos muestra algo de informacion sobre el
     * 
     * @param rutaDirectorio directorio de la carpeta que vamos a mostrar la información
     */
    public void mostrarContenidoDetallado(String rutaDirectorio){
        
        //creo un objeto file con la ruta que recibo
        File carpeta = new File(rutaDirectorio);
        
        //Compruebo si el directorio existe y es realmente un directorio
        if (carpeta.exists() && carpeta.isDirectory()){
            
            //obtengo los archivso y directorios que hay dentro del directorio
            File[] contenido = carpeta.listFiles();
            
            //si el directorio no esta vacio:
            if(contenido != null){
                
                for( File archivo : contenido) {
                    
                    if( archivo.isDirectory()){
                        
                        System.out.println(archivo.getName() + " es un directorio.");
                        
                    } else if (archivo.isFile()){
                        
                        System.out.println(archivo.getName() + " es un archvivo");
                        
                        //como es un archivo, mostramos mas info del archivo
                        System.out.println("Tamaño: " +archivo.length() + " bytes");
                        System.out.println("Ruta: " +archivo.getAbsolutePath());
                        
                    }
                    
                }
                
            } else {
                System.out.println("La ruta proporcionada no es un directorio válido");
            }
            
        }
    }
    
    
    /**
     * Metodo que borra ficheros y directorios
     * Si es un directorio que contiene otros directorios, borra el contenido de estos de manera recursiva
     * Cuando deja de encontrar directorios dentro de directorios, borra el contenido
     * de estos y el directorio padre
     * @param directorio 
     */
    public void eliminar(String directorio){
        
        /*
        Le pongo elemento porque puede ser una carpeta, un archivo...
        */
        File elemento = new File(directorio);
        
        if(elemento.isFile() && elemento.exists()){
            
            elemento.delete();
            
            System.out.println("Fichero borrado");
            
        } else if (elemento.isDirectory()) {
            
            File[] listaFicheros = elemento.listFiles();
            
            for (File archivo:listaFicheros){
                
                if (archivo.isFile()) {
                    
                    archivo.delete();
                    
                } else {
                    
                    String direct = archivo.getPath();
                    eliminar(direct);
                    
                }  
            }
            
            elemento.delete();
            
            System.out.println("Directorio borrado");
            
            
        } else if (!elemento.exists()) {
            
            System.out.println("No existe");
            
        }
        
    }
    
    
    /**
     * Método para cambiar el nombre de un archivo
     * @param ruta
     */
    public void renombrarArchivo(String ruta){
        
        File archivo = new File(ruta);
        
        System.out.println("¿Cómo quieres renombrar el archivo?");
        String nombre = t.nextLine();
        
        File archivoNuevo;
        
        if(archivo.getParent() == null){
            
            archivoNuevo = new File(nombre);
            
        } else {
            
            archivoNuevo = new File(archivo.getParent()+File.separator+nombre);
            
        }
        
        archivo.renameTo(archivoNuevo);
        
    }
    
    
    /**
     * Metodo que permite copiar un archivo de una ruta origen a una ruta destino
     * @param rutaOrigen ruta origen del archivo
     * @param rutaDestino ruta a la que vamos a copiar el archivo
     */
    public void copiarArchivo (String rutaOrigen, String rutaDestino){
        
        //Creo dos objetos file, uno para el origen y otro para el destino
        File archivoOrigen = new File(rutaOrigen);
        File archivoDestino = new File(rutaDestino);
        
        //Creamos el fileinput y fileoutput
        FileInputStream input = null;
        FileOutputStream out = null;
        
        if (archivoOrigen.exists() && archivoOrigen.isFile()) {
        
            //Compruebo que el archivo de origen exista (y q sea un archivo)
            try {

                input = new FileInputStream(archivoOrigen);
                out = new FileOutputStream(archivoDestino);

                //Buffer para pasar los datos
                byte[] buffer = new byte[1024];
                int bytesLeidos;

                //Leo el origen y lo escribo en destino
                while((bytesLeidos = input.read(buffer)) > 0){

                    out.write(buffer,0,bytesLeidos);

                }

                System.out.println("Archivo copiado exitoso");

            } catch (IOException e) {
                System.out.println("Error al copiar el archivo: " + e.getMessage());
            }
            
        } else {
            
            System.out.println("El archivo origen no existe o no es valido");
            
        }
        
    }
    
    
    /**
     * Mueve un archivo de una ruta origen a una ruta destino
     * @param rutaOrigen
     * @param rutaDestino 
     */
    public void moverArchivo(String rutaOrigen, String rutaDestino){
        
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
