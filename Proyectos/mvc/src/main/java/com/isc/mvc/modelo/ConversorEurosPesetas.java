/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.mvc.modelo;

/**
 * Clase para utilizar el conversor de euros a pesetas
 * 
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 13 sept 2024
 */
public class ConversorEurosPesetas extends Conversor{
   public ConversorEurosPesetas() {
       super(166.386D);
   } 
   
   public double eurosApesetas(double cantidad) {
       return eurosAmoneda(cantidad);
   }
   
   public double pesetasAeuros(double cantidad) {
       return monedaAeuros(cantidad);
   }
}
