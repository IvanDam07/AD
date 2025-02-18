package com.mmc.clasefile.modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;


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
     * @param directorioPadre nueva ruta
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
     * Metodo para crear un archivo en la ruta indicada, utiliza el atributo de la clase
     * 
     * @param nombreArchivo nombre del archivo a crear
     * @param directorioPadre directorio en el que se va a crear el archivo
     */
    public void crearArchivo(String directorioPadre, String nombreArchivo) {
        
        Path p = Paths.get(directorioPadre);
        
        if(Files.exists(p)){
            
            try {
                p = Paths.get(directorioPadre + "\\" +nombreArchivo);
                
                if(!Files.exists(p)){
                    Files.createFile(p);
                    
                } else {
                    System.out.println("El archivo existe");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }          
        } else {
            System.out.println("No existe el directorio");
        } 
    }
    
    
    public String crearArchivo(String nombreArchivo){
        
        Path p = Paths.get(nombreArchivo);
        
        String confirmacion = "";
        
        if(Files.exists(p)) {
            
            confirmacion = "El directorio existe";
            
        } else {
            
            try {
                
                Path pCreacion = Files.createDirectories(p);
                confirmacion = "El directorio ya existe";
                
            } catch (IOException e) {
                
                e.printStackTrace();
                
            }
        
        }
        
        return confirmacion;
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
    
    
    public void mostrarContenido(String directorioPadre){
        
        //File contenido = new File(ruta);
        //File[] lista = contenido.listFiles();
        //int cont = 0;
        //while(cont < lista.length){
        //   if(lista[cont].isDirectory()){
        //        System.out.println(lista[cont].getName() + " \"Es un directorio\"");
        //   }else{
        //        System.out.println(lista[cont].getName() + " \"Es un archivo\"" + " " + lista[cont].getAbsolutePath());
        //   }
        //   cont++;
        //}
        Path dir = Paths.get(directorioPadre);
        
        try {
            Files.list(dir).forEach(path -> {
                
                System.out.println(path.getFileName()+ " --- " + path.getFileSystem());
                
            });
        } catch (IOException ex) {
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
    }
    
    
    /**
     * Muestra el contenido de un directorio, indica si lo que ha leido es un archivo o un directorio y muestra info del archi
     * @param rutaDirectorio 
     */
    public void mostrarContenidoDetallado(String rutaDirectorio){
        
        Path directorio = Paths.get(rutaDirectorio);
        
        // Verificamos si la ruta existe y es un directorio
    if (Files.exists(directorio) && Files.isDirectory(directorio)) {
        
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directorio)) {
            
            for (Path archivo : stream) {
                
                if (Files.isDirectory(archivo)) {
                    System.out.println(archivo.getFileName() + " es un directorio.");
                    
                } else if (Files.isRegularFile(archivo)) {
                    System.out.println(archivo.getFileName() + " es un archivo.");
                    
                    // Con esto obtenemos mas info del archivo (no es necesario como tal)
                    BasicFileAttributes attr = Files.readAttributes(archivo, BasicFileAttributes.class);
                    
                    System.out.println("Tamaño: " + attr.size() + " bytes");
                    System.out.println("Fecha de creación: " + attr.creationTime());
                    System.out.println("Última modificación: " + attr.lastModifiedTime());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el contenido del directorio: " + e.getMessage());
        }
    } else {
        System.out.println("El directorio no existe o no es válido.");
    }
        
    }
    
    
    /**
     * Metodo que borra ficheros y directorios
     * @param ruta ruta donde queremos borrar
     * @param nombre del archivo que vamos a borrar
     */
    public void eliminar(String ruta, String nombre){
        
        /*
        File condenado = new File(ruta);
        File[] listaCondenado = condenado.listFiles();
        for(File i:listaCondenado){
            if(i.isDirectory() && i.getName().equalsIgnoreCase(nombre)){
                File[] listD = i.listFiles();
                for(File f:listD){
                    f.delete();
                }
                i.delete();
            }else if(i.getName().equalsIgnoreCase(nombre)){
                i.delete();
            }
        }*/
        
        Path p = Paths.get(ruta, nombre);
        
        if(Files.exists(p)){
            
            try {
                
                Files.list(p).forEach(element -> {
                    
                    if(!Files.isDirectory(element)){
                        
                        try{
                            
                            Files.delete(element);
                            
                        }catch(IOException ex){
                            
                            ex.getMessage();
                        }
                        
                    }
                });
                Files.delete(p);
            } catch (DirectoryNotEmptyException e) {
                
                System.out.println("No esta vacia");
                
            } catch (IOException ex){
                
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        
    }
    
    
    /**
     * Método para cambiar el nombre de un archivo
     * @param ruta
     */
    public void renombrarArchivo(String ruta, String nuevoNombre){
        
         /*
        File contenido = new File(ruta);
        
        File newF = new File(newNombre);
        boolean cambiado =contenido.renameTo(newF);
                       
        if(cambiado){
            System.out.println("El nombre se cambió satisfactoriamente");
        }*/ 
        
        Path o = Paths.get(ruta);
        Path d = o.resolveSibling(nuevoNombre);
        
        try {
            
            Files.move(o,d);
            
        } catch (IOException ex) {
            
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            
        }
            
            
    }
    
    
    public void copiarArchivo (String ruta, String rutaDestino){
        /*
        try{
            File inFile = new File(ruta);
            File outFile = new File(rutaDestino);
            
            FileInputStream in = new FileInputStream(inFile);
            FileOutputStream out = new FileOutputStream(outFile);
            
            int c;
            while((c = in.read()) != -1){
                out.write(c);
            }
        }catch(Exception e){
            System.out.println(e);
        }*/
        
        Path rO = Paths.get(ruta);
        Path rD = Paths.get(rutaDestino);
        
        try {
            
            Files.copy(rO, rD);
            
        } catch (IOException ex) {
            
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
    public void moverArchivo (String ruta, String rutaDestino){
        /*
        File origin = new File(ruta);
        File Destiny = new File (rutaDestino);
        
        try{
            boolean movido = origin.renameTo(Destiny);
            
            if(movido){
                System.out.println("Se ha movido satisfactoriamente");
            }
        }catch(Exception e){
            e.printStackTrace();
        }*/
        
        Path o = Paths.get(ruta);
        Path d = Paths.get(rutaDestino);
        
        try {
            
            Files.move(o,d);
            
        } catch (IOException ex) {
            
            Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
}
