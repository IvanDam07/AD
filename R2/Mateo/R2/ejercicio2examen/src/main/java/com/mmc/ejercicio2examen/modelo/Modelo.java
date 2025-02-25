/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio2examen.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 27 nov 2024
 *
 */
public class Modelo {

     private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";
    
    private static Modelo operacionesBBDD;
    
    private Connection conexion;
    private Properties propiedades;
    private PreparedStatement preparedStatement;
    
    private static DatabaseMetaData dbmd;
    
    
    public Modelo() {}
    
    
    public static Modelo getInstance(){
        
        if(operacionesBBDD == null){
            
            operacionesBBDD = new Modelo();
            
        }
        
        return operacionesBBDD;
    }
    
    //-------------------------------------CONEXION------------------------------------------------------------------//
    public Connection utilidadGetConexion(){
        return conexion;
    }
    
    
    public void utilidadAbrirConexion(){
        
        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "examen");
            this.propiedades.setProperty("password", "examen");
            this.propiedades.setProperty("bbdd", "free");
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            
            //Para la info de la conexion
            dbmd = conexion.getMetaData();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void utilidadCerrarConexion(){
        
        try {
            
            this.conexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public int llamarProcedimiento(Date p_fecha){
        
        CallableStatement llamada;
             
        int nOperadoresMovidos = 0;
        
         try {

             String sql = "{? = call F_PASO_A_RENOVACION(?)}";
             
             llamada = utilidadGetConexion().prepareCall(sql);
             
             llamada.setDate(2,p_fecha);
             
             llamada.registerOutParameter(1, Types.INTEGER);
             
             llamada.executeUpdate();
             
             nOperadoresMovidos = llamada.getInt(1);  
             
             
         } catch (SQLException ex) {
             
             switch(ex.getErrorCode()){
                 
                 case 2292:
                     System.out.println("No es posible mover a algún operador debido a la conexion entre tablas operadores y drones");
                     
                 default:
                     System.out.println("Ha ocurrido un error en la bbdd");
                 
             }
             
         }
         return nOperadoresMovidos;
       }
        
         
    
    
    
    //------------------------------------------- UTILIDAD ----------------------------------------------//metodo para hacer el select, llamo a executeQuery
    public Optional<ResultSet> utilidadSelect(String querySQL, Object... params) throws SQLException{
        
        return Optional.of(utilidadExecuteQuery(querySQL, params));
        
    }
    
    //metodo al que llamo desde el select
    private ResultSet utilidadExecuteQuery(String querySQL, Object... params) throws SQLException{
        
        //le pongo el prepared statement con typescrollsensitive y conurupdatable por si me hace falta moverme por el rs en algun momento
        preparedStatement = conexion.prepareStatement(querySQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        for(int i=0; i<params.length; i++){
            
            preparedStatement.setObject(i+1, params[i]); 
        }
        
        return preparedStatement.executeQuery();
    }    
    
}
