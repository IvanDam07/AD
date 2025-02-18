package com.mmc.clasefile.modelo;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase encargada de la lógica de la aplicación
 * 
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 18 sept 2024
 *
 */
public class Carpeta {
    
    private String ruta;
    
    
/**
 * Constructor de la clase
 * @param ruta de la carpeta
 */
    public Carpeta(String ruta) {
        this.ruta = ruta;
    }

    
    /**
     * Constructor vacío
     */
    public Carpeta() {
    }

    
    /**
     * 
     * @return 
     */
    public String getRuta() {
        return ruta;
    }

    
    /**
     * 
     * @param ruta 
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el atributo de la clase
     */
    public void crearCarpeta(){
        
            File directorioNuevo = new File(ruta);
            directorioNuevo.mkdir();
        
    }
    
    
    /**
     * Crea un directorio en la ruta indicada
     * Utiliza el constructor al cual le pasamos la ruta del directorio padre y el nombre del nuevo directorio
     * 
     * @param nombreDirectorio Nombre del nuevo directorio
     */
    public void crearCarpeta(String nombreDirectorio){
        
        File directorioNuevo = new File(ruta,nombreDirectorio);
        directorioNuevo.mkdir();
        
    }
    
    
    public void crearCarpeta(File directorioRaiz, String nombreDirectorio){
        
        File directorioNuevo = new File(directorioRaiz,nombreDirectorio);
        directorioNuevo.mkdir();
    }
    
    
    /**
     * Devuelve en un File la ruta de la carpeta
     * @return File que apunta a la carpeta
     */
    public File getFileDeRuta(){
        File directorioRaiz = new File(ruta);
        return directorioRaiz;
    }
    
    
    public void borrarTodo(String ruta) {
        
         /*
        File file = new File(ruta);
        if(file.isDirectory() && file.exists()){
            File[] list = file.listFiles();
            for(File i:list){
                String path = i.getPath();
                if(i.isDirectory() && i.exists()){
                    borrarTodo(i.getPath());
                }else{
                   i.delete();
                }   
            }
            file.delete();
        }*/
        
        Path d = Paths.get(ruta);
        
        if(Files.isDirectory(d)){
            
            try{
                
                Files.list(d).forEach(arch -> {
                    
                    if(Files.isDirectory(arch)){
                        
                        borrarTodo(arch.toString());
                        
                    }else{
                        
                        try {
                            
                            Files.deleteIfExists(arch);
                            
                        } catch (IOException ex) {
                            Logger.getLogger(Carpeta.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                
                Files.deleteIfExists(d);
                
            }catch(IOException ex){
                Logger.getLogger(Archivo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            
            System.out.println("El nombre introducido no es un directorio o el directorio no existe.");
        }      
       
        
    }
    
    
    
    
    

}
