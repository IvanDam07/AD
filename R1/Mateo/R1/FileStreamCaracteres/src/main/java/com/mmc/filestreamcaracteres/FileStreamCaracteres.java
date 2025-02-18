/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.filestreamcaracteres;

import com.mmc.filestreamcaracteres.controlador.ControladorFileStream;
import com.mmc.filestreamcaracteres.modelo.Escritura;
import com.mmc.filestreamcaracteres.modelo.Lectura;
import com.mmc.filestreamcaracteres.modelo.Fichero;
import com.mmc.filestreamcaracteres.vista.InterfazVista;
import com.mmc.filestreamcaracteres.vista.VentanaTexto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class FileStreamCaracteres {

    public static void main(String[] args) {

        InterfazVista vista = new VentanaTexto();
        
        String ruta = vista.getRuta();
        
        Escritura escritura = new Escritura(ruta);
        Lectura lectura = new Lectura(ruta);
        Fichero fichero = new Fichero(ruta);
        
        ControladorFileStream controlador = new ControladorFileStream(vista, fichero, escritura, lectura);
    
        vista.arranca();
        
        
        
    }
        
}
