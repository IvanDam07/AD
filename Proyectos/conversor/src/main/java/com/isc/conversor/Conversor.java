/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.conversor;
import java.util.*;
/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 */
public class Conversor {

    public static void main(String[] args) {
        double cantidad;
        int eleccion;
        double resultado;
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Dime la cantidad que quieres convertir:");
        cantidad = teclado.nextDouble();
        System.out.println("Indica la operación que quiere realizar:");
        System.out.println("1: convertir euros a pesetas");
        System.out.println("2: convertir pesetas a euros");
        System.out.println("0: salir");
        
        eleccion = teclado.nextInt();
        
        switch (eleccion) {
            case 1:
                resultado = eurosAPesetas(cantidad);
                System.out.println(cantidad + "€ son " + resultado + "pesetas.");
                break;
            case 2:
                resultado = pesetasAEuros (cantidad);
                System.out.println(cantidad + " pesetas son " + resultado + "€.");
                break;
            default: 
                System.out.println("Has salido del conversor.");
                break;
        }
    }
    
    /**
     * Método que pasa de euros a pesetas
     * @param cantidad cantidad de euros a convertir
     * @return devuelve la cantidad en pesetas
     */
    public static double eurosAPesetas (double cantidad) {
        return cantidad * 166.386;
    }
    
    /**
     * Método que pasa de pesetas a euros
     * @param cantidad cantidad de pesetas a convertir
     * @return devuelve la cantidad en euros
     */
    public static double pesetasAEuros (double cantidad) {
        return cantidad / 166.386;
    }
}
