/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.mvc.modelo;

/**
 * 
 *  Clase para utilizar el conversor de euros a pesetas
 * 
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 sept 2024
 *
 */
public class ConversorEurosPesetas extends Conversor{
    
    
    public ConversorEurosPesetas() {
        super(166.386D); //la D es para indicar que es un double
    }
    
    /**
     * Metodo 
     * @param cantidad
     * @param comision
     * @return 
     */
    public double eurosApesetas(double cantidad, double comision){
        return eurosAmoneda(cantidad, comision);
    }
    
    /**
     * 
     * @param cantidad
     * @param comision
     * @return 
     */
    public double pesetasAeuros(double cantidad, double comision){
        return monedaAeuros(cantidad, comision);
    }

}
