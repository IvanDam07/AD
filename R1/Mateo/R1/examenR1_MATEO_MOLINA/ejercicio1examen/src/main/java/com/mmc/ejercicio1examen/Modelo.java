/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio1examen;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Modelo {
    
    public Modelo(){}
    
    public static void busquedaEjercicio1(String nombreArchivo, String rutaPartida, String rutaCopia){
        
            File rCopia = new File(rutaCopia);
            File archivo = new File(nombreArchivo);
            Path pCopia = Paths.get(rutaCopia);
            Path pPartida = Paths.get(rutaPartida);
            
            FileInputStream input = null;
            FileOutputStream out = null;
            
            if(!Files.exists(pCopia)){
                rCopia.mkdir();
            }
            
            
            if(Files.exists(pPartida)){
            
                pCopia = Paths.get(rutaCopia + "\\" +nombreArchivo);
                if(!Files.exists(pCopia)){
                    
                    try {
                        
                        input = new FileInputStream(pPartida+"/"+archivo);
                        out = new FileOutputStream(rCopia+"/"+archivo+"_copia.txt");
                        
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
                    System.out.println("El archivo existe");
                }
        }
    }
}
