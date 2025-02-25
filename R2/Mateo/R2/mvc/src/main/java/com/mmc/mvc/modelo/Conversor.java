/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.mvc.modelo;

/**
 *
 * Clase encargada de la lógica de negocio de la aplicación
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 sept 2024
 *
 */
public class Conversor {
    
    /*
        Cantidad de la moneda destino a la cual equivale un euro
    */
    private final double cambio;

    
    public Conversor(double cambio) {
        this.cambio = cambio;
    }
    
    /**
     * Convierte los euros a una moneda cualquiera utilizando el cambio
     * @param cantidad  cantidad de euros que queremos pasar a la nueva moneda
     * @param comision
     * @return          cantidad equivalente en la moneda destino
     */
    public double eurosAmoneda (double cantidad, double comision){ 
        return (cantidad*cambio)*((100-comision)/100);
    }    
    
    /**
     * Convierte una cantidad de una moneda cualquiera a euros utilizando el cambio
     * @param cantidad  cantidad de moneda destino que queremos pasar a euros
     * @param comision
     * @return          cantidad equivalente en euros
     */
    public double monedaAeuros (double cantidad, double comision){
        return (cantidad/cambio)*((100-comision)/100);
    }
    

}
