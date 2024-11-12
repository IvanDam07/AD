/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clasefile;

import com.mycompany.clasefile.Controlador.ControlCarpeta;
import com.mycompany.clasefile.Modelo.Carpeta;
import com.mycompany.clasefile.Vista.CarpetaVistaTexto;
import com.mycompany.clasefile.Vista.InterfazVista;

/**
 *
 * @author Ivan Sobrino
 */
public class ClaseFile {

    public static void main(String[] args) {
        /* 
            La vista
         */
        //Interfaz vista = new CarpetaVista();          //-- Modo gráfico
        InterfazVista vista = new CarpetaVistaTexto();  //-- Modo texto

        /* 
            El modelo
         */
        Carpeta modelo = new Carpeta();
        
        /*
            Creamos el controlador pasándole la vista y el modelo
         */
        ControlCarpeta control = new ControlCarpeta(vista, modelo);
    }
}
