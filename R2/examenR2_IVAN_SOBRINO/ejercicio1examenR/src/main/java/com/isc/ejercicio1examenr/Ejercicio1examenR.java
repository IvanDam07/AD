/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.ejercicio1examenr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class Ejercicio1examenR {

    private static Modelo m = Modelo.getInstance();
    
    public static void main(String[] args) {
        m.utilidadAbrirConexion();
        
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat s2 = new SimpleDateFormat("yy");
        java.util.Date fecha = null;
        java.util.Date fecha2 = null;
        
        try {
            fecha = s.parse("26/05/25");
            fecha2 = s2.parse("24");
        } catch (ParseException ex) {
            Logger.getLogger(Ejercicio1examenR.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        java.sql.Date fechaSql2 = new java.sql.Date(fecha2.getTime());
        
        //m.insertarIncidencias(5, "Prueba incidencia", "APLAZADA", fechaSql, 3000, 5, "11223344C"); //FUNCIONA
        //m.modificarIncidenciasSinResolver(fechaSql); //FUNCIONA
        //m.mostrarFilaDatosIncidencias(1); //FUNCIONA
        //m.mostrarTodosDatos("11223344C", 24); //no da error, pero no lo coge
        m.incidenciaFacturacion();
        m.utilidadCerrarConexion();
    }
}
