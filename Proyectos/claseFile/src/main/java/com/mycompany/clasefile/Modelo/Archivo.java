/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clasefile.Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivan Sobrino
 */
public class Archivo {

    private String directorioPadre;
    private String nombreArchivo;

    private Scanner scanner = new Scanner(System.in);

    public Archivo(String directorioPadre, String nombreArchivo) {
        this.directorioPadre = directorioPadre;
        this.nombreArchivo = nombreArchivo;
    }

    public Archivo() {

    }

    public String getDirectorioPadre() {
        return directorioPadre;
    }

    public void setDirectorioPadre(String directorioPadre) {
        this.directorioPadre = directorioPadre;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     *
     * Metodo para crear un archivo en la ruta indicada, utiliza el atributo de
     * la clase
     *
     * @param nombre nombre del archivo a crear
     *
     */
    public void crearArchivo(String directorioPadre, String nombreArchivo) {
        File archivo = new File(directorioPadre, nombreArchivo);

        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muestra el contenido de un directorio indicando si lo que hay dentro en
     * un directorio o un archivo. (Considero mas correcto ponerlo aquí que en
     * la clase archivo).
     *
     * @param carpeta directorio del que vamos a mostrar el contenido
     */
    public void mostrarContenido(File carpeta) {
        String[] contenido = carpeta.list();
        String contenidoArray = Arrays.toString(contenido);

        System.out.println(contenidoArray);
    }

    public void mostrarContenidoDetallado(String rutaDirectorio) {

        File carpeta = new File(rutaDirectorio);

        if (carpeta.exists() && carpeta.isDirectory()) {

            File[] contenido = carpeta.listFiles();

            if (contenido != null) {

                for (File archivo : contenido) {

                    if (archivo.isDirectory()) {

                        System.out.println(archivo.getName() + " es un directorio.");

                    } else if (archivo.isFile()) {

                        System.out.println(archivo.getName() + " es un archivo.");

                        System.out.println("Tamaño en bytes: " + archivo.length());
                        System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
                    }
                }
            } else {
                System.out.println("La ruta proporcionada no es válida.");
            }
        }
    }

    public void eliminar(String directorio) {

        File elemento = new File(directorio);

        if (elemento.isFile() && elemento.exists()) {

            elemento.delete();

            System.out.println("Fichero borrado.");

        } else if (elemento.isDirectory()) {

            File[] contenido = elemento.listFiles();

            for (File archivo : contenido) {

                if (archivo.isFile()) {
                    archivo.delete();
                } else {
                    String direct = archivo.getPath();
                    eliminar(direct);
                }
            }

            elemento.delete();

            System.out.println("Directorio borrado.");

        } else if (!elemento.exists()) {

            System.out.println("No existe");
        }
    }

    /**
     * Método para cambiar el nombre de un archivo
     *
     * @param ruta La ruta del directorio donde se encuentra el archivo
     * @param nombreArchivo El nombre actual del archivo
     * @param nuevoNombre El nuevo nombre que se le dará al archivo
     * @return boolean Devuelve true si el archivo se renombró con éxito, false
     * en caso contrario
     */
    public boolean renombrarArchivo(String ruta, String nombreArchivo, String nuevoNombre) {
        // Crear un objeto File con la ruta y nombre del archivo actual
        File archivoActual = new File(ruta, nombreArchivo);

        // Crear un objeto File con la ruta y el nuevo nombre del archivo
        File archivoNuevo = new File(ruta, nuevoNombre);

        // Verificar si el archivo actual existe
        if (!archivoActual.exists()) {
            System.out.println("El archivo " + nombreArchivo + " no existe en la ruta especificada.");
            return false;
        }

        // Intentar renombrar el archivo
        if (archivoActual.renameTo(archivoNuevo)) {
            System.out.println("Archivo renombrado con éxito. Nuevo nombre: " + nuevoNombre);
            return true;
        } else {
            System.out.println("No se pudo renombrar el archivo. Puede que el nuevo nombre ya exista o no tenga permisos.");
            return false;
        }
    }
    
    public void copiarArchivo(String rutaOrigen, String rutaDestino) {
        
        File archivoOrigen = new File(rutaOrigen);
        File archivoDestino = new File(rutaDestino);
        
        FileInputStream input = null;
        FileOutputStream out = null;
        
        if (archivoOrigen.exists() && archivoOrigen.isFile()) {
            
            try {
            
            input = new FileInputStream(archivoOrigen);
            out = new FileOutputStream(archivoDestino);
            
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
