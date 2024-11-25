/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.filestreambytes;

import com.isc.filestreambytes.modelo.Copia;
import com.isc.filestreambytes.modelo.Escritura;
import com.isc.filestreambytes.modelo.Lectura;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class FileStreamBytes {

    public static void main(String[] args) {
        //Escritura modeloE = new Escritura("archivo_binario.bin");
        //modeloE.escribirDatosSimples();
        
        //Lectura modeloL = new Lectura("archivo_binario.bin");
        //System.out.println(modeloL.lecturaDatosSimple());
        
        Copia modeloC = new Copia("C:\\Users\\b15-12m\\Documents\\Documentos\\colorful-planets.jpg", "./imagen1.jpg");
        modeloC.copiarArchivo();
    }
}
