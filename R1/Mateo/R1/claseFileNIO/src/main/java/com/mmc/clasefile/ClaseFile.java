package com.mmc.clasefile;

import com.mmc.clasefile.controlador.ControlArchivo;
import com.mmc.clasefile.modelo.Carpeta;
import com.mmc.clasefile.vista.CarpetaVistaTexto;
import com.mmc.clasefile.vista.InterfazVista;
import com.mmc.clasefile.controlador.ControlCarpeta;
import com.mmc.clasefile.modelo.Archivo;

/**
 * Clase ejemplo para mostrar el funcionamiento de la clase file
 * 
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 18 sept 24
 */
public class ClaseFile {

    public static void main(String[] args) {
        
        InterfazVista vista = new CarpetaVistaTexto();
        
        Carpeta modeloCarpeta = new Carpeta();   
        ControlCarpeta controlCarpeta = new ControlCarpeta(vista, modeloCarpeta);
        
        
        Archivo modeloArchivo = new Archivo();
        ControlArchivo controlArchivo = new ControlArchivo(vista, modeloArchivo);
        
        vista.arranca();
        
    }
}
