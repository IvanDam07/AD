/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.filestreamcaracteres.controlador;

import com.isc.filestreamcaracteres.modelo.Fichero;
import com.isc.filestreamcaracteres.modelo.Lectura;
import com.isc.filestreamcaracteres.modelo.Escritura;
import java.io.IOException;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 2 oct 2024
 */
public class controlFileStreams {
    
    private Fichero modelo;
    private Lectura modeloLectura;
    private Escritura modeloEscritura;
    
    public controlFileStreams(Fichero modelo, Lectura modeloLectura, Escritura modeloEscritura) {
        this.modelo = modelo;
        this.modeloEscritura = modeloEscritura;
        this.modeloLectura = modeloLectura;
    }
    
    public void leerArchivo() throws IOException {
        String contenido = modeloLectura.leerArrayCaracteres(); //modelo.leerStreamCaracteres();
        System.out.println("Contenido del archivo:");
        System.out.println(contenido);
    }

    public void escribirCaracter(char caracter, boolean sobreescribe) {
        modeloEscritura.escribirCaracter(caracter, sobreescribe); //modelo.escribirStreamCaracteres(caracter, sobreescribe);
    }

    public void escribirArrayCaracteres(char[] caracteres, boolean sobreescribe) {
        modeloEscritura.escribirArrayCaracteres(caracteres, sobreescribe); //modelo.escribirStreamArrayCaracteres(caracteres, sobreescribe);
    }

    public void leerBuffered() {
        modeloLectura.leerCaracteresBufferReader();  //modelo.leerStreamBuffered();
    }
    
    public void leerArrayCaracteres() throws IOException {
        modeloLectura.leerArrayCaracteres();  //modelo.leerStreamArrayCaracteres();
    }
    
    public void encriptarArchivo() {
        modeloEscritura.encriptarFichero();
    }

    public void desencriptarArchivo() {
        modeloEscritura.desencriptarFichero();
    }
}
