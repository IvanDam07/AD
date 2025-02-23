/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mmc.examenr4_mateomolina;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MMC by Mateo Molina Campos
 */
public class EXAMENR4_MATEOMOLINA {
    
    static String driver;
    static Connection conexion;

    public static void main(String[] args) {
        
        abrirConexion();
        //InsertaVuelosEjemplo();
        ModificarPiloto(2,"05737437",12,345);
        VisualizarVuelo(2);
        cerrarConexion();
        
    }
    
    
    
    private static void abrirConexion(){

        try {
            driver = "oracle.jdbc.driver.OracleDriver";
            String urlconnection = "jdbc:oracle:thin:@localhost:1521/FREE";

            Properties propiedades = new Properties();

            propiedades = new Properties();
            propiedades.setProperty("user", "dam2jpa");
            propiedades.setProperty("password", "dam2jpa");
            propiedades.setProperty("bbdd", "free");
        
            Class.forName(driver);
            conexion = DriverManager.getConnection(urlconnection, propiedades);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EXAMENR4_MATEOMOLINA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EXAMENR4_MATEOMOLINA.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
    
    private static void cerrarConexion(){
        
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(EXAMENR4_MATEOMOLINA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void InsertaVuelosEjemplo() {
        
        try {
            String sql = "INSERT INTO T_VUELOS_MERCANCIAS VALUES("
                    + "?,"
                    + "?,"
                    + "NEW PILOTO(?,?,?),"
                    + "?,"
                    + "?"
                    + ")";
            
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            PreparedStatement sentencia2 = conexion.prepareStatement(sql);
           
            
            sentencia.setInt(1, 1);
            sentencia.setString(2, "Iberia");
            sentencia.setString(3,"06848543A");
            sentencia.setInt(4,8);
            sentencia.setInt(5,300);
            sentencia.setInt(6,1238);
            sentencia.setInt(7,25);
            
            sentencia2.setInt(1, 2);
            sentencia2.setString(2, "Iberia");
            sentencia2.setString(3,null); //aqui le meto el piloto a null
            sentencia2.setString(4,null);
            sentencia2.setString(5,null);
            sentencia2.setInt(6,1238);
            sentencia2.setInt(7,25);
            
            sentencia.executeUpdate();
            sentencia.close();
            
            sentencia2.executeUpdate();
            sentencia2.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EXAMENR4_MATEOMOLINA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void ModificarPiloto(int idVuelo, String dni, int horas_de_vuelo, int horas_de_vuelo_anual){
        try {
            String sql = "UPDATE T_VUELOS_MERCANCIAS VU SET VU.PILOTO_AVION = PILOTO(?,?,?) WHERE VU.IDVUELO = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setInt(4,idVuelo);
            sentencia.setString(1,dni);
            sentencia.setInt(2,horas_de_vuelo);
            sentencia.setInt(3,horas_de_vuelo_anual);
            
            sentencia.executeUpdate();
            sentencia.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EXAMENR4_MATEOMOLINA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private static void VisualizarVuelo(int idVuelo){
        
        try {
            Statement stmt = conexion.createStatement();
            
            String sql = "SELECT VU.IDVUELO,VU.PILOTO_AVION.DNI,VU.COSTE_TRANSPORTE() FROM T_VUELOS_MERCANCIAS VU WHERE IDVUELO = ?";
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            
            sentencia.setInt(1,idVuelo);
            
            ResultSet rset = sentencia.executeQuery();
            //me da error tanto al intentar comprobarlo asi como con el optional, por lo que he optado por no comprobar si no viene
            //if (rset.last()){
            int contador = 0;
                while(rset.next()){

                    int id = rset.getInt(1);
                    String dni_piloto = rset.getString(2);
                    int coste = rset.getInt(3);

                    System.out.println("Id vuelo: "+id+" Dni piloto: "+dni_piloto+" coste: "+coste);
                    contador ++;
                }
                
                if(contador == 0){ //se que no es correcto, pero es un 'apaño' para que avise al usuario
                    System.out.println("No existe ese vuelo");
                }
            //} else {
               // System.out.println("No existe ese id");
           // }
            
            rset.close();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(EXAMENR4_MATEOMOLINA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   
    
    
    
}