/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.filestreambytes;

import com.mmc.filestreambytes.modelo.Copia;
import com.mmc.filestreambytes.modelo.Escritura;
import com.mmc.filestreambytes.modelo.Lectura;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class Filestreambytes {

    public static void main(String[] args) {
        
        //Escritura modeloE = new Escritura("archivo_binario.bin");
        //modeloE.escribirDatosSimples();
        
        //Lectura modeloL = new Lectura("archivo_binario.bin");
        //System.out.println(modeloL.leerDatosSimple());
        
        Copia modeloC = new Copia("foto.jpg","foto_copia.jpg");
        modeloC.copiarArchivo();
        
    }
}
