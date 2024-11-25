/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.filestreamcaracteres;

import com.isc.filestreamcaracteres.controlador.controlFileStreams;
import com.isc.filestreamcaracteres.modelo.Fichero;
import com.isc.filestreamcaracteres.modelo.Escritura;
import com.isc.filestreamcaracteres.modelo.Lectura;
import com.isc.filestreamcaracteres.vista.interfazVista;
import com.isc.filestreamcaracteres.vista.vistaTextoFileStreams;
import java.io.IOException;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class FileStreamCaracteres {

    public static void main(String[] args) throws IOException {
        
        Fichero modelo = new Fichero("./texto");
        Escritura modeloEscritura = new Escritura(modelo.getRuta());
        Lectura modeloLectura = new Lectura(modelo.getRuta());
        //String texto;
        //texto = modelo.leerStreamCaracteres();
        //System.out.println(texto);
        
        //modelo.leerStreamArrayCaracteres();
        
        //modelo.leerStreamBuffered();
        
        //modelo.escribirStreamCaracteres('A', true);
        
        //char[] caracteres = {'H','o','l','a'};
        //modelo.escribirStreamArrayCaracteres(caracteres, false); //Si pasas true, se añade. Si pasa false, se sobreescribe
        
        modeloEscritura.escribirBufferedPrintCaracteres("Hoy empezamos de nuevo", true);
        modeloLectura.leerCaracteresBufferReader();
        
        //controlFileStreams controlador = new controlFileStreams(modelo);
        //interfazVista vista = new vistaTextoFileStreams(controlador);
        
        //vista.mostrarMenu();
        
        modeloEscritura.encriptarFichero();
    }
}
