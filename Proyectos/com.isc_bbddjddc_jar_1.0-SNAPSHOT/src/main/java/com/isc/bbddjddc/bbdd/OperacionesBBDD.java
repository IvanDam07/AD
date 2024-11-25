/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.bbddjddc.bbdd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 6 nov 2024
 */
public class OperacionesBBDD {

    private final String driver = "oracle.jdbc.driver.OracleDriver";
    private final String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";
    
    private static OperacionesBBDD operacionesBBDD;
    
    private Connection conexion;
    private Properties propiedades;
    private PreparedStatement preparedStatement;
    
    private static DatabaseMetaData dbmd;
    
    
    private OperacionesBBDD(){}
    
    
    public static OperacionesBBDD getInstance(){
        
        if(operacionesBBDD == null){
            
            operacionesBBDD = new OperacionesBBDD();
            
        }
        
        return operacionesBBDD;
    }
    
    public Connection getConexion(){
        return conexion;
    }
    
    
    public void abrirConexion(){
        
        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "dam2");
            this.propiedades.setProperty("password", "dam2");
            this.propiedades.setProperty("bbdd", "free");
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            
            //Para la info de la conexion
            dbmd = conexion.getMetaData();
            
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
    //los 3 puntos del object indica que puede recibir una serie de objetos
    public Optional<ResultSet> insert(String insertSQL,Object... params) throws SQLException{
        
        //el return generated keys devuelve la clave generada
        preparedStatement = conexion.prepareStatement(insertSQL,PreparedStatement.RETURN_GENERATED_KEYS,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        
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
        
        //le cambiamos el preparedStatement con el sensitive y el updatable para que podamos movernos por el result set, hacer un last() o un beforeFirst()
        preparedStatement = conexion.prepareStatement(querySQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        
        
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
    
    
    /*
    * Muestra informacion sobre la conexion a la bbdd
    */
    public void obtenerInformacionDeConexion() {
                
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
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    /**
     * Muestra información de las tablas del usuario conectado a la bbdd
     */
    public void obtenerInformacionDeLasTablas() {
        try {
            ResultSet rs;
            String[] tipos = {"TABLE"};
            
            rs = dbmd.getTables(this.propiedades.getProperty("bbdd").toUpperCase(), 
                                this.propiedades.getProperty("user").toUpperCase(),
                                null, 
                                tipos);
            
            String nombre_usuario;
            String nombre_tabla;
            
            while (rs.next()){
                nombre_usuario = rs.getString("TABLE_SCHEM");
                nombre_tabla = rs.getString("TABLE_NAME");
                
                System.out.println("USUARIO:" +nombre_usuario+ " TABLA:" + nombre_tabla);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Muestra información sobre las columnas de una tabla
     * 
     * @param nombreTabla Nombre de la tabla de la cual queremos obtener información de sus columnas
     */
    public void obtenerInformacionDeLasColumnas(String nombreTabla) {
        try {
            ResultSet rs;
            
            rs = dbmd.getColumns(this.propiedades.getProperty("bbdd").toUpperCase(), 
                                 this.propiedades.getProperty("user").toUpperCase(), 
                                 nombreTabla.toUpperCase(), 
                                 null);
            
            String nombre_tabla;
            String nombre_columna;
            
            while (rs.next()){
                nombre_tabla = rs.getString("TABLE_NAME");
                nombre_columna = rs.getString("COLUMN_NAME");
                
                System.out.println("TABLA:" +nombre_tabla+ " COLUMN:" + nombre_columna);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Obtiene información sobre el ResultSet
     * 
     * @param rs ResultSet sobre el cual queremos obtener información
     */
    public void obtenerInformacionDelResultSet(Optional<ResultSet> rs) {
        
        try {
            
            ResultSetMetaData rsmd = rs.get().getMetaData();
            
            //Obtiene el número de columnas devueltas por la tabla
            int numColumnas = rsmd.getColumnCount();
            
            //Obtiene el nombre de la columna de la posición "i"
            String nombre_columna = rsmd.getColumnName(2);
            
            //Obtiene el tipo de datos de la columna de la posición "i"
            String tipo_columna = rsmd.getColumnTypeName(2);
            
            //Obtiene "0" si la columna de la posición "i" puede contener valores nulos
            int valores_nulos = rsmd.isNullable(2);
            
            //Obtiene el máximo número de caracteres de la columna de la posición "i"
            int tamaño_columna = rsmd.getColumnDisplaySize(2);
            
            System.out.println("Numero de columnas devueltas:" + numColumnas);
            System.out.println("Nombre de la columna 2:" + nombre_columna);
            System.out.println("Tipo de la columna 2:" + tipo_columna);
            System.out.println("Tamaño de la columna 2:" + tamaño_columna);
            System.out.println("Acepta nulos:" + ((valores_nulos==1)?"Si":"No"));
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Obtiene el número de filas de un ResultSet
     * 
     * @param rs ResultSet del cual queremos obtener el número de filas 
     */
    public int obtenerNumeroFilasDevueltas(Optional<ResultSet> rs) {
 
        
        int rows = 0;
        
        try {
            //Solo para tener en cuenta que este método necesitará un conjunto de resultados sensible al desplazamiento.
            //El valor predeterminado es FORWARD (ADELANTE) y el uso de este método generará una excepción.
            //Para poder hacer el last() o beforeFirst la siguiente instrucción debe modificarse
            //preparedStatement = conexion.prepareStatement(querySQL);
            //por
            //preparedStatement = conexion.prepareStatement(querySQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
                       
            //int rows = 0; //0 porque si rs.last no funciona no entraría en el if y entonces es que no ha devuelto datos
            
            if (rs.get().last()) {
                rows = rs.get().getRow();
                // Nos volvemos asituar en el primero por si queremos seguir trabajandocon el resulset
                rs.get().beforeFirst();
            }

        System.out.println("El número de filas devueltas es:" + rows);
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }
    
    
    /**
     * Permite saber si el driver soporta actualización sobre el ResultSet
     * 
     * @return Si soporta o no
     */
    public boolean obtenerInformacionOperacionesResultSet(){
        try {
            boolean isUpdatable = dbmd.supportsResultSetConcurrency(
                    ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
 
            if (!isUpdatable) {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}


/*
public void abrirConexion(){
        
        try {
            this.propiedades = new Properties();
            this.propiedades.setProperty("user", "dam2");
            this.propiedades.setProperty("password", "dam2");
            Class.forName(driver);
            this.conexion = DriverManager.getConnection(urlconnection, propiedades);
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OperacionesBBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
