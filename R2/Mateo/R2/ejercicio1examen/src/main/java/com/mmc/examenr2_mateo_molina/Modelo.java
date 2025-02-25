/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.examenr2_mateo_molina;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    
    
    private Modelo() {}
    
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
            
            //en este caso no nos es necesario podria quitarlo ya que no lo usamos
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
    
    //--------------------------------------------FIN CONEXION---------------------------------------------------------//
    
    //Método insertar fila drones, creo un metodo insert y lo llamo desde este para que sea más legible
    public void insertarFilaDrones(String nSerie, String marca, String modelo, double peso, int potencia, int ecinetica, String aseguradora, double horas, double autonomia, 
            Date adquisicion, String DNI){
        
        try {
            utilidadInsert("insert into DRONES values (?,?,?,?,?,?,?,?,?,?,?)",nSerie,marca,modelo,peso,potencia,ecinetica,aseguradora,horas,autonomia,adquisicion,DNI);
           
            
        } catch (SQLException ex) {
            
            switch(ex.getErrorCode()){
                case 0001 -> System.out.println("El identificador del dron ya existe");
                    
                case 2291 -> System.out.println("El operador que pilotará el dron no existe");
                
                default -> System.out.println("Ha habido un error en la base de datos");
            }
            
            
        }
        
    }
    
    public void eliminarFilasDronesObsoletos(double p_horas, Date p_fecha_verificacion) {
        try {
            utilidadDelete("DELETE FROM drones WHERE ADQUISICION < ? AND HORAS > ?",p_fecha_verificacion, p_horas);
            
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarFilaDatosDrones(String p_Nserie){
        
        try {
            Optional<ResultSet> rs = utilidadSelect("Select d.NSERIE, d.HORAS, d.AUTONOMIA, d.ADQUISICION, o.DNI, o.NOMBRE, o.CORREO, o.TELEFONO"
                    + " from drones d, operadores o WHERE NSERIE LIKE ? AND d.DNI = o.DNI",p_Nserie);
            
            
            if(!rs.get().last()){
                
                System.out.println("No existe el dron indicado");
                
            } else {
                
                rs.get().beforeFirst();
                
                if(rs.isPresent()){
                
                    while(rs.get().next()){
                    
                        System.out.println("NÚMERO DE SERIE: "+rs.get().getString("NSERIE") +
                                "; HORAS: "+rs.get().getDouble("HORAS") +
                                "; AUTONOMÍA: "+rs.get().getDouble("AUTONOMIA") +
                                "; FECHA ADQUISICIÓN: "+rs.get().getDate("ADQUISICION") +
                                "; DNI OPERADOR: "+rs.get().getString("DNI") +
                                "; NOMBRE OPERADOR: "+rs.get().getString("NOMBRE") +
                                "; CORREO OPERADOR: "+rs.get().getString("CORREO") +
                                "; TELÉFONO OPERADOR: "+rs.get().getInt("TELEFONO"));

                    }   
                } 
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
    }
    
    public void mostarTodosDatos(Date p_fecha_inicio_adquisicion, Date p_fecha_fin_adquisicion) {
        double sumaHorasVuelo = 0;
        int numFilas = 0;
        
        try {
            
            Optional<ResultSet> rs = utilidadSelect("Select * from drones where ADQUISICION between ? AND ?",p_fecha_inicio_adquisicion,p_fecha_fin_adquisicion);
            
            if(rs.isPresent()){
                
                    while(rs.get().next()){
                    
                        System.out.println("NÚMERO DE SERIE: "+rs.get().getString("NSERIE") +
                                "; MARCA: "+rs.get().getString("MARCA") +
                                "; MODELO: "+rs.get().getString("MODELO") +
                                "; PESO: "+rs.get().getDouble("PESO") +
                                "; POTENCIA: "+rs.get().getInt("POTENCIA") +
                                "; ECINETICA: "+rs.get().getInt("ECINETICA") +
                                "; ASEGURADORA: "+rs.get().getString("ASEGURADORA") +
                                "; HORAS: "+rs.get().getDouble("HORAS") +
                                "; AUTONOMÍA: "+rs.get().getDouble("AUTONOMIA") +
                                "; ADQUISICION: "+rs.get().getDate("ADQUISICION") +
                                "; DNI OPERADOR: "+rs.get().getString("DNI"));
                        
                        sumaHorasVuelo += rs.get().getDouble("HORAS");

                    }
                    
                    numFilas = utilidadObtenerNumeroFilasDevueltas(rs);
                    
                    if(numFilas > 0){
                        System.out.println("Las horas totales de vuelo de este dron son: "+sumaHorasVuelo);
                    }
                    
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cursoRenovacion(){
        
        try {
            
            Optional<ResultSet> rs = utilidadSelect("SELECT * FROM OPERADORESRENOVACION");
            
            if(rs.isPresent()){
                
                ResultSet resultSet = rs.get();
                
                while(resultSet.next()){
                    
                    Date fecha = resultSet.getDate("CADUCIDAD");
                    fecha.setYear(fecha.getYear() + 1);
                    
                    resultSet.updateDate("CADUCIDAD",fecha);
                    
                    resultSet.updateRow();
                    
                    utilidadInsert("Insert into OPERADORES VALUES (?,?,?,?,?,?)",resultSet.getString("DNI"),resultSet.getString("NOMBRE"),resultSet.getString("LICENCIA"),
                            resultSet.getDate("CADUCIDAD"),resultSet.getString("CORREO"),resultSet.getInt("TELEFONO"));
                    
                }
                
            }
            
        } catch (SQLException ex) {
            
            //No soy capaz de modificar correctamente, por lo que he capturado el error que me da y dejo indicado lo que he podido hacer.
            switch(ex.getErrorCode()){
                
                case 17076:
                    System.out.println("No es posible modificar las fechas");
                    
                default:
                    System.out.println("Ha ocurrido un error");
                
            }
            
        }
        
    }
    
    //-------------------------------------------- MÉTODOS ADICIONALES ----------------------------------------------------//
    public Optional<ResultSet> utilidadInsert(String insertSQL,Object... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(insertSQL,PreparedStatement.RETURN_GENERATED_KEYS);
        
        for(int i=0; i<params.length; i++){
            
            preparedStatement.setObject(i+1, params[i]);
            
        }
        
        preparedStatement.executeUpdate();
        
        return Optional.of(preparedStatement.getGeneratedKeys());
        
    }
    
    public int utilidadDelete(String genericSQL, Object... params) throws SQLException {
        
        return utilidadUpdateQuery(genericSQL, params);
        
    }
    
    //este método sirve para update y delete pq los dos tienes cosas en comun
    private int utilidadUpdateQuery(String genericSQL, Object... params) throws SQLException{
        
        preparedStatement = conexion.prepareStatement(genericSQL);
        
        for(int i=0; i<params.length; i++){
            
            preparedStatement.setObject(i+1, params[i]);
            
        }
        
        return preparedStatement.executeUpdate();
        
    }

    //metodo para hacer el select, llamo a executeQuery
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
    
    //metodo que nos devolvera cuantas filas tiene el rs
    public int utilidadObtenerNumeroFilasDevueltas(Optional<ResultSet> rs) {

        int rows = 0;
        
        try {
 
            if (rs.get().last()) {
                rows = rs.get().getRow();
                // Nos volvemos asituar en el primero por si queremos seguir trabajandocon el resulset
                rs.get().beforeFirst();
            }

        System.out.println("El número de filas devueltas es:" + rows);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rows;
    }
    
}
