/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.examenr2_mateo_molina;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class Ejercicio1examen {
    
    private static Modelo m = Modelo.getInstance();

    public static void main(String[] args) {
        
        //apertura de conexion
        m.utilidadAbrirConexion();
        
//----------------------- PREPARACIÓN DE FECHAS PARAS LAS LLAMADAS ---------------------------------
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date fecha = null;
        java.util.Date fecha2 = null;
        
        try {
            fecha = s.parse("02/03/2000");
            fecha2 = s.parse("07/12/2012");
            
        } catch (ParseException ex) {
            Logger.getLogger(Ejercicio1examen.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fecha.getTime());
        java.sql.Date fechaSql2 = new java.sql.Date(fecha2.getTime());
       
        
//-------------------------- LLAMADAS SOLICITADAS --------------------------------------------------
        m.insertarFilaDrones("A2","Toshiba","E501",0.25,100,10,"Ocaso",4,3,fechaSql,"05123456A");
        
        m.mostrarFilaDatosDrones("A2");
        
        m.eliminarFilasDronesObsoletos(3,fechaSql);
        
        m.mostarTodosDatos(fechaSql,fechaSql2);
        
        m.cursoRenovacion();
        
        //cierre de conexion
        m.utilidadCerrarConexion();
        
    }
}
