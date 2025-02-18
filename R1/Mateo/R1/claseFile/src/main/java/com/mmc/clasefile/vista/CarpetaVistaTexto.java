package com.mmc.clasefile.vista;

import com.mmc.clasefile.controlador.ControlArchivo;
import com.mmc.clasefile.controlador.ControlCarpeta;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 18 sept 2024
 *
 */
public class CarpetaVistaTexto implements InterfazVista{
    
    private ControlCarpeta controladorCarpeta;
    private ControlArchivo controladorArchivo;
    private final BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
    
    
    /**
     * Solicita una opción al usuario
     *
     * @return Devuelve un entero con la opción elegida por el usuario
     * @exception IOException Si se produce un error en la entrada/salida
     * @exception NumberFormatException Si el formato del número no es correcto
     */
    private int leeOpcion() {
        String s = null;
        try {
            s = in.readLine();
            return Integer.parseInt(s);
        } catch (IOException | NumberFormatException e) {
            operacionIncorrecta();
            return 0;
        }
    }
    
    
    /**
     * Procesa el caso de que introduzcamos una opción que no sea una de las
     * indicadas
     */
    private void operacionIncorrecta() {
        System.out.print("Operación incorrecta. ");
        procesaNuevaOperacion();
    }
    
    
    /**
     * Procesa la opción elegida por el usuario
     */
    private void procesaNuevaOperacion() {
        int operacion;
        solicitaOperacion();
        operacion = leeOpcion();
        switch (operacion) {
            case 0 -> {
                System.out.println("Adiós.");
                System.exit(0);
            }
            case 1 -> 
                controladorCarpeta.actionPerformed(new ActionEvent(this, operacion, CREARCARPETACONRUTACOMPLETA));
                
            case 2 ->
                controladorCarpeta.actionPerformed(new ActionEvent(this,operacion,CREARCARPETACONRUTAPADREYNOMBRE));
                
            case 3 ->
                controladorCarpeta.actionPerformed(new ActionEvent(this,operacion,CREARCARPETACONFILEPADREYNOMBRE));
                
            case 4 ->
                controladorArchivo.actionPerformed(new ActionEvent(this, operacion, MOSTRARCONTENIDO));
                
            case 5 ->
                controladorArchivo.actionPerformed(new ActionEvent(this, operacion, ELIMINAR));
                
            case 6 ->
                controladorArchivo.actionPerformed(new ActionEvent(this, operacion, RENOMBRAR));
                
            default ->
                operacionIncorrecta();
        }
        procesaNuevaOperacion();
    }
    
    
    /**
     * Muestra el menú con las opciones
     */
    private void solicitaOperacion() {
        System.out.println("Indica la operación que quiere realizar:");
        System.out.println("1: crear carpeta pasando la ruta completa");
        System.out.println("2: crear carpeta pasando la ruta padre y el nombre de la carpeta");
        System.out.println("3: crear carpeta pasando un file y el nombre de la carpeta");
        System.out.println("4: Mostrar el contenido de un direcorio dado");
        System.out.println("5: Borrar contenido de una carpeta");
        System.out.println("6: Renombrar un archivo o carpeta");
        System.out.println("0: salir");
    }
    

    @Override
    public void setControladorCarpeta(ControlCarpeta c) {
        
        this.controladorCarpeta = c;
        
    }
    
    @Override
    public void setControladorArchivo(ControlArchivo c) {
        
        this.controladorArchivo = c;
        
    }

    
    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }
    

    @Override
    public String getRuta() {
        System.out.println("Ruta a seleccionar");
        return leeString();
    }
    
    @Override
    public String getNombre(){
        System.out.println("Introduce el nombre de la carpeta");
        return leeString();
    }
    
    
    private String leeString(){
        
        String s = null;
        
        try{
            
            s = in.readLine();
            return s;
            
        } catch (IOException e){
            
            System.out.println("Error en la cadena introducida");
            return leeString();
            
        }
    }

}
