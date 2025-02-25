/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.bbddjdbc.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 4 nov 2024
 *
 */
public class OperacionesBBDD {
    
    private final String driver;
    private final String urlconnection;
    
    private static OperacionesBBDD operacionesBBDD;
    
    private Connection conexion;
    private PreparedStatement preparedStatement;
    
    
    private OperacionesBBDD(){
        
        driver = "org.sqlite.JDBC";
        urlconnection = "jdbc:sqlite:./bbdd2/ejemplo.db";
        
    }
    
    
    public static OperacionesBBDD getInstance(){
        
        if(operacionesBBDD == null){
            
            operacionesBBDD = new OperacionesBBDD();
            
        }
        
        return operacionesBBDD;
    }
    
    
    public void abrirConexion(){
        
        try {
            
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void cerrarConexion(){
        
        try {
            
            this.conexion.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //con el optional indicamos que el resultset que nos devuelve todos los datos puede devolvernos los datos o nulo
    //los 3 puntos del object indica que peude recibir una serie de objetos
    public Optional<ResultSet> insert(String insertSQL,Object... params) throws SQLException{
        
        //el return generated keys devuelve la clave generada
        preparedStatement = conexion.prepareStatement(insertSQL,PreparedStatement.RETURN_GENERATED_KEYS);
        
        
        //insert into Depatrtamentos values(?,?,?)"
        //por cada iteracion añade el valor que le pasamos
        
        for(int i=0; i<params.length; i++){
            
            preparedStatement.setObject(i+1, params[i]);
            
        }
        
        preparedStatement.executeUpdate();
        
        return Optional.of(preparedStatement.getGeneratedKeys());
        
    }
    
    //aqui en estos pone el throws para lanzar hacia arriba la excepcion y que la trate otro metodo que no sea el execute query
    private ResultSet executeQuery(String querySQL, Object... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(querySQL);
        
        
        
        for(int i=0; i<params.length; i++){
            
            preparedStatement.setObject(i+1, params[i]);
            
        }
        
        return preparedStatement.executeQuery();
        
    }
    
    
    public Optional<ResultSet> select(String querySQL, Object... params) throws SQLException{
        
        return Optional.of(executeQuery(querySQL, params));
        
    }
    
    
    //metodo que nos va a servir para ejecutar el update y el delete, pq ambos tienen cosas en comun
    private int updateQuery(String genericSQL, Object... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(genericSQL);
        
        for(int i=0; i<params.length; i++){
            
            preparedStatement.setObject(i+1, params[i]);
            
        }
        
        return preparedStatement.executeUpdate();
        
    }
    
    
    public int update(String genericSQL, Object... params) throws SQLException{
        
        return updateQuery(genericSQL, params);
        
    }
    
    
    public int delete (String genericSQL, Object... params) throws SQLException{
        
        return updateQuery(genericSQL, params);
        
    }

}
