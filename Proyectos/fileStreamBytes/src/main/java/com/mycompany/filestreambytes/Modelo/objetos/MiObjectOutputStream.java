/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.filestreambytes.Modelo.objetos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Ivan Sobrino
 */
public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }
    
    protected MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }
    
    // La cabecera se crea cada vez que se pone new ObjectOutputStream(fichero)
    // Redefinimos el siguiente método de escribir la cabecera para que no haga nada,
    protected void writeStreamHeader() throws IOException {
        
    }
}