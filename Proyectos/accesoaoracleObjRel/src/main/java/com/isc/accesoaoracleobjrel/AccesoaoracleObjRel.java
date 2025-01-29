/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.isc.accesoaoracleobjrel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 */
public class AccesoaoracleObjRel {

    static String driver;
    static Connection conexion;
    
    public static void main(String[] args) {
        abrirConexion();
        
        //insertarPrepared();
        realizaConsulta();

        cerrarConexion();
    }
    
    public static void abrirConexion(){        
        try {            
            driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost;1521/FREE";
            
            Properties propiedades = new Properties();
            
            propiedades.setProperty("user", "dam2jpa"); //cambiar dam2 por examen
            propiedades.setProperty("password", "dam2jpa"); //cambiar dam2 por examen            
            
            Class.forName(driver);
            conexion = DriverManager.getConnection(urlconnection, propiedades);
                       
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AccesoaoracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void cerrarConexion(){
        
        try {
            
            conexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoaoracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void insertarPrepared() {
        try {
            String sql = "INSERT INTO alumnos VALUES("
                    + "new Persona("
                    + "?,"
                    + "?,"
                    + "new Direccion(?,?,?),"
                    + "?"
                    + "))";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            java.sql.Date fecha = Date.valueOf("2023-12-13");
            
            sentencia.setInt(1, 66);
            sentencia.setString(2, "Ana Gómez");
            sentencia.setString(3, "Calatrava");
            sentencia.setString(4, "Ciudad Real");
            sentencia.setInt(5, 13003);
            sentencia.setDate(6, fecha);
            
            sentencia.executeUpdate();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(AccesoaoracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void realizaConsulta() {
        
        try {
            Statement stmt = conexion.createStatement();
            
            ResultSet rset = stmt.executeQuery("select codigo, nombre, direc from alumnos");
            
            while (rset.next()) {
                String codigo = rset.getString(1);
                String nombre = rset.getString(2);
                
                java.sql.Struct objeto = (java.sql.Struct) rset.getObject(3);
                
                String ciudad = "";
                
                if (objeto != null) {
                    Object[] atributos = objeto.getAttributes();
                    
                    ciudad = (String) atributos[1];
                }
                
                System.out.println("Código: " + codigo + " Nombre: " + nombre + " Dirección: " + ciudad);
            }
            
            rset.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccesoaoracleObjRel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
