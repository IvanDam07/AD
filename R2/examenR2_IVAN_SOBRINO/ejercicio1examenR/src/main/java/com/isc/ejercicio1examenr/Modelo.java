/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ejercicio1examenr;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
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
 * Created on 26 feb 2025
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
    
    public void insertarIncidencias(int n_incidencia, String descripcion, String estado, Date fecha_apertura, int coste, int horas, String dni) {
        
        try {
            utilidadInsert("insert into INCIDENCIAS values(?,?,?,?,?,?,?)",n_incidencia,descripcion,estado,fecha_apertura,coste,horas,dni);
        } catch(SQLException ex) {
            switch(ex.getErrorCode()){
                case 0001 -> System.out.println("La incidencia ya existe");
                    
                case 2291 -> System.out.println("El trabajador al que se le asigna la incidencia no existe");
                
                default -> System.out.println("Ha habido un error en la base de datos");
            }
        }
    }
    
    public void modificarIncidenciasSinResolver(Date p_fecha_verificacion) {
        try {
            utilidadUpdateQuery("UPDATE INCIDENCIAS SET ESTADO = 'URGENTE' WHERE ESTADO='APLAZADA' AND (?-FECHA_APERTURA)>30",p_fecha_verificacion);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarFilaDatosIncidencias(int p_nincidencia) {
        try {
            Optional<ResultSet> rs = utilidadSelect("Select d.N_INCIDENCIA, d.ESTADO, o.NOMBRE, o.PUESTO from incidencias d, trabajadores o WHERE N_INCIDENCIA = ? AND d.DNI = o.DNI", p_nincidencia);
            
            if(!rs.get().last()) {
                System.out.println("No existe el número de incidencia");
            } else {
                rs.get().beforeFirst();
                
                if(rs.isPresent()) {
                    while(rs.get().next()) {
                        System.out.println("NUMERO DE INCIDENCIA: "+rs.get().getInt("N_INCIDENCIA") +
                                "; ESTADO: "+rs.get().getString("ESTADO")+
                                "; NOMBRE: "+rs.get().getString("NOMBRE")+
                                "; PUESTO: "+rs.get().getString("PUESTO"));
                    }
                }
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void mostrarTodosDatos(String p_dni_trabajador, int p_año) {
        int numFilas = 0;
        int sumaHorasTrabajo = 0;
        
        try {
            
            Optional<ResultSet> rs = utilidadSelect("Select * from INCIDENCIAS where ESTADO='CERRADA' AND DNI=? AND EXTRACT(YEAR FROM FECHA_APERTURA) = ?", p_dni_trabajador,p_año);
            
            if(rs.isPresent()) {
                while(rs.get().next()) {
                    System.out.println("NÚMERO DE INCIDENCIA: "+rs.get().getInt("N_INCIDENCIA")+
                            "; DESCRIPCIÓN: "+rs.get().getString("DESCRIPCION")+
                            "; ESTADO: "+rs.get().getString("ESTADO")+
                            "; FECHA APERTURA: "+rs.get().getDate("FECHA_APERTURA")+
                            "; COSTE: "+rs.get().getDouble("COSTE")+
                            "; HORAS: "+rs.get().getInt("HORAS")+
                            "; DNI: "+rs.get().getString("DNI"));
                    
                    sumaHorasTrabajo += rs.get().getInt("HORAS");
                }
                
                numFilas = utilidadObtenerNumeroFilasDevueltas(rs);
                
                if(numFilas > 0) {
                    System.out.println("Las horas totales de trabajo son: "+sumaHorasTrabajo);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void incidenciaFacturacion() {
        
        try {
            
            Optional<ResultSet> rs = utilidadSelect("SELECT * FROM INCIDENCIAS WHERE ESTADO='CERRADA'");
            
            if(rs.isPresent()) {
                ResultSet resultSet = rs.get();
                
                while(resultSet.next()) {
                    double coste = resultSet.getDouble("COSTE");                    
                    
                    double costeSubida = coste * 1.1;
                    
                    resultSet.updateDouble("COSTE", costeSubida);
                    
                    resultSet.updateRow();
                    
                    utilidadInsert("Insert into FACTURACIONINCIDENCIAS VALUES(?,?,?,?,?,?,?)",resultSet.getInt("N_INCIDENCIA"),resultSet.getString("DESCRIPCION")+
                            resultSet.getString("ESTADO"),resultSet.getDate("FECHA_APERTURA"),resultSet.getDouble("COSTE"),resultSet.getInt("HORAS")+
                                    resultSet.getString("DNI"));
                }
            }
        } catch(SQLException ex) {
            System.out.println("Ha ocurrido un error: " +ex);
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
    
    /*
    private Date convertirFecha(String fecha){
        java.util.Date fechaUtil = null;
        try {
            SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");    
            fechaUtil = s.parse(fecha);
            
        } catch (ParseException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new java.sql.Date(fechaUtil.getTime());
    }
    */
}
