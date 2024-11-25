/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.clasefile;

import com.isc.clasefile.controlador.ControlArchivo;
import com.isc.clasefile.controlador.ControlCarpeta;
import com.isc.clasefile.modelo.Archivo;
import com.isc.clasefile.modelo.Carpeta;
import com.isc.clasefile.vista.CarpetaVistaTexto;
import com.isc.clasefile.vista.InterfazVista;

/**
 * Clase ejemplo para mostrar el funcionamiento de la clase File
 * 
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 18 sept 2024
 */
public class Clasefile {

    public static void main(String[] args) {
        
        InterfazVista vista = new CarpetaVistaTexto();
        
        Carpeta modeloCarpeta = new Carpeta();
        Archivo modeloArchivo = new Archivo();
        
        ControlCarpeta controlCarpeta = new ControlCarpeta(vista, modeloCarpeta);
        ControlArchivo controlArchivo = new ControlArchivo(vista, modeloArchivo);
        
        vista.arranca();
    }
}
