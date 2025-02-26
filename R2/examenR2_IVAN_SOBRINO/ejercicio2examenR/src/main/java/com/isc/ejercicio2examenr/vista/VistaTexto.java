/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ejercicio2examenr.vista;

import com.isc.ejercicio2examenr.controlador.Controlador;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 26 feb 2025
 */
public class VistaTexto implements InterfazVista{

    private Controlador controlador;
    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));    
    
    public void procesaNuevaOperacion(){
        
        int operacion = 1;
        
        switch(operacion){
            
            case 0 ->
                System.exit(0);
            
            case 1 -> 
                controlador.actionPerformed(new ActionEvent(this, operacion, ANIO));
            
        }
        
    }
    
    @Override
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    @Override
    public void arranca() {
        procesaNuevaOperacion();
    }

    @Override
    public int leeAño() {
        System.out.println("Inserta un año (en formato yy)");
        Scanner scanner = new Scanner(System.in);
        
        int anio = scanner.nextInt();
        
        return anio;
    }

    @Override
    public void escribeResultado(String s) {
        System.out.println(s);
    }

}
