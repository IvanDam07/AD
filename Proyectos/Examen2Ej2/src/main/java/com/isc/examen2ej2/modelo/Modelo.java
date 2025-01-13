/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.examen2ej2.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 27 nov 2024
 */
public class Modelo {
    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";

    private static Modelo modelo;

    private Connection conexion;
    private Properties propiedades;
    private PreparedStatement preparedStatement;

    private static DatabaseMetaData dbmd;

    private Modelo() {
    }

    public static Modelo utilidadGetInstance() {

        if (modelo == null) {

            modelo = new Modelo();

        }

        return modelo;
    }

    public Connection utilidadGetConexion() {
        return conexion;
    }

    public void utilidadAbrirConexion() {

        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "examen"); //cambiar dam2 por examen
            this.propiedades.setProperty("password", "examen"); //cambiar dam2 por examen
            this.propiedades.setProperty("bbdd", "free");
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);

            //Para la info de la conexion
            dbmd = conexion.getMetaData();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void utilidadCerrarConexion() {

        try {

            this.conexion.close();

        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //con el optional indicamos que el resultset que nos devuelve todos los datos puede devolvernos los datos o nulo
    //los 3 puntos del object indica que puede recibir una serie de objetos
    public Optional<ResultSet> utilidadInsert(String insertSQL, Object... params) throws SQLException {

        //el return generated keys devuelve la clave generada
        preparedStatement = conexion.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        //ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE

        //insert into Departamentos values(?,?,?)"
        //por cada iteracion añade el valor que le pasamos
        for (int i = 0; i < params.length; i++) {

            preparedStatement.setObject(i + 1, params[i]);

        }

        preparedStatement.executeUpdate();

        return Optional.of(preparedStatement.getGeneratedKeys());

    }

    //aqui en estos pone el throws para lanzar hacia arriba la excepcion y que la trate otro metodo que no sea el execute query
    private ResultSet utilidadExecuteQuery(String querySQL, Object... params) throws SQLException {

        //le cambiamos el preparedStatement con el sensitive y el updatable para que podamos movernos por el result set, hacer un last() o un beforeFirst()
        preparedStatement = conexion.prepareStatement(querySQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

        for (int i = 0; i < params.length; i++) {

            preparedStatement.setObject(i + 1, params[i]);

        }

        return preparedStatement.executeQuery();

    }

    public Optional<ResultSet> utilidadSelect(String querySQL, Object... params) throws SQLException {

        return Optional.of(utilidadExecuteQuery(querySQL, params));

    }

    //metodo que nos va a servir para ejecutar el update y el delete, pq ambos tienen cosas en comun
    private int utilidadUpdateQuery(String genericSQL, Object... params) throws SQLException {

        preparedStatement = conexion.prepareStatement(genericSQL);

        for (int i = 0; i < params.length; i++) {

            preparedStatement.setObject(i + 1, params[i]);

        }

        return preparedStatement.executeUpdate();

    }

    public int utilidadUpdate(String genericSQL, Object... params) throws SQLException {

        return utilidadUpdateQuery(genericSQL, params);

    }

    public int utilidadDelete(String genericSQL, Object... params) throws SQLException {

        return utilidadUpdateQuery(genericSQL, params);

    }
    
    /*
    * Muestra informacion sobre la conexion a la bbdd
    */
    public void utilidadObtenerInformacionDeConexion() {
                
        try {
            //Nombre del SGBD
            String nombre = dbmd.getDatabaseProductName();
            
            //Driver utilizado:
            String driver = dbmd.getDriverName();
            
            //Dirección para acceder a la bbdd:
            String url = dbmd.getURL();
            
            //Nombre del usuario:
            String usuario = dbmd.getUserName();
            
            System.out.println("Nombre del SGBD:" + nombre);
            System.out.println("Driver:" + driver);
            System.out.println("Url:" + url);
            System.out.println("Usuario:" + usuario);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void llamarProcedimiento(Modelo bbdd, String p_fecha) {
        try {
            String sql = "{call f_paso_a_renovacion(?)}";
            CallableStatement cs = bbdd.utilidadGetConexion().prepareCall(sql);
            cs.setInt(1, p_fecha);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
