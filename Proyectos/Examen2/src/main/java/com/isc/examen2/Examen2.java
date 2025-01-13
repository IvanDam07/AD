/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.examen2;

import com.isc.examen2.modelo.Dron;
import com.isc.examen2.modelo.Modelo;
import java.sql.ResultSet;
import java.util.Optional;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Examen2 {
    
    private static Modelo bbdd = Modelo.utilidadGetInstance();
    private static Optional<ResultSet> rs;
    
    public static void main(String[] args) {
        bbdd.utilidadAbrirConexion();
        
        Dron dron = new Dron();
        
        //dron.insertarDron(bbdd, "Serie1", "Toshiba", "Modelo1", 0.3, 10, 3,"Securitas", 1.5, 1.2, "07/05/22", "12345678L");
        dron.eliminaFilasDronesObsoletos(bbdd, 5.0, "10/10/23");
        //dron.mostrarFilaDatosDrones(bbdd, "Serie1");
        //dron.mostrarTodosDatos(bbdd, "01/08/22", "05/08/22");
        
        
        bbdd.utilidadCerrarConexion();
    }
}
