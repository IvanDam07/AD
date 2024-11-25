/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.isc.filestreamcaracteres.vista;

import java.io.IOException;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 2 oct 2024
 */
public interface interfazVista {
    void mostrarMenu() throws IOException;
    
    void leerArchivo() throws IOException;
    
    void leerArrayCaracteres() throws IOException;
    
    void leerBuffered() throws IOException;
    
    void escribirCaracter() throws IOException;
    
    void escribirArrayCaracteres() throws IOException;
}
