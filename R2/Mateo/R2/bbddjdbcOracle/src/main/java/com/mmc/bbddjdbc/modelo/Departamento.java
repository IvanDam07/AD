/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.bbddjdbc.modelo;

import com.mmc.bbddjdbc.Bbddjdbc;
import com.mmc.bbddjdbc.bbdd.OperacionesBBDD;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
public class Departamento {
    
    private int dept_no;
    private String dnombre;
    private String loc;
    
    
    public Departamento(int id, String nombre, String localizacion){
        this.dept_no = id;
        this.dnombre = nombre;
        this.loc = localizacion;
    }
    
    public Departamento(){
        
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }

    public String getDnombre() {
        return dnombre;
    }

    public void setDnombre(String dnombre) {
        this.dnombre = dnombre;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
    
    
    public void insertar(OperacionesBBDD bbdd) {
        
        try {
            
            bbdd.insert("insert into departamentos d values (?,?,?)",this.dept_no, this.dnombre, this.loc);
            
        } catch (SQLException ex) {
            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //insertar pero con un result set TERMINAR!!!
    public void insertar(ResultSet rs){
        
        try {

            rs.moveToInsertRow();
            
            rs.afterLast();
            
            rs.updateString("dnombre", "Departamento2");
            rs.updateString("loc", "Daimiel2");
            
            rs.insertRow();
            
            rs.moveToCurrentRow();
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void selectById(OperacionesBBDD bbdd, int n) {
        
        try {
            //le ponemos la interrogacion y luego le pasamos el parametro para que no sufra una inyeccion, es mas seguro
            Optional<ResultSet> rs = bbdd.select("SELECT * FROM departamentos WHERE dept_no = ?", n);
            
            //con el ispresent vemos si ha llegado el result set
            if(rs.isPresent()){
                
                while(rs.get().next()){
                    
                    //cogemos el valor de rs y se lo asignamos a dept_no
                    this.dept_no = (rs.get().getInt("dept_no"));
                    
                    //aquí hacemos lo mismo con el nobmre
                    this.dnombre = (rs.get().getString("dnombre"));
                    
                    this.loc = (rs.get().getString("loc"));
                           
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        
        Optional<ResultSet> rs = null;
        
        try {
            
            rs = bbdd.select("SELECT d.* FROM departamentos d");
            
        } catch (SQLException e) {

            System.out.println ("Ha ocurrido un error:");
            System.out.println ("Mensaje: " +e.getMessage());
            System.out.println ("SQL Estado: " +e.getSQLState());
//            System.out.println ("Código de error: " +e.getErrorCode()); //de esta forma saldria el codigo de error 
            
            //con esto capturamos el error y mostramos lo que queramos segun el error que sea, asi no sale ORA - y no se sabe que bbdd es
            switch(e.getErrorCode()){
                
                case 942:
                    System.out.println("La tabla no existe");
                
            }

        }
        
        return rs;
        
    }
    
    
    public static void mostrarResultSet(Optional<ResultSet> rs){
        
        try {
            if(rs.isPresent()){           
            
                while(rs.get().next()){
                    
                    //podriamos haberlo metido en un objeto y devolver el objeto
                    System.out.println("Numero departamento: " + rs.get().getInt("dept_no") +
                            "; Nombre: " + rs.get().getString("dnombre") +
                            "; Localidad: " + rs.get().getString("loc"));
                }              
            }
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public void update(OperacionesBBDD bbdd){
        
        try {
            
            bbdd.update("UPDATE departamentos SET dnombre = ?, loc = ? WHERE dept_no = ?", this.dnombre, this.loc, this.dept_no);
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //metodo actualizar pero pasandole un result set para actualizar en el mismo result set
    public void update(ResultSet rs){
        
        try {
            
            rs.beforeFirst();
            
            while(rs.next()){
                
                rs.updateString("dnombre", this.dnombre);
                rs.updateString("loc", this.loc);
                
                rs.updateRow();
                
            }        
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void delete(OperacionesBBDD bbdd, int n_dep){
        
        try {
            
            bbdd.delete("DELETE FROM departamentos WHERE dept_no = ?",n_dep);
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static String pNombreDepart(OperacionesBBDD bbdd, int dep){
        
        String salida_return = "";
        
        try {
            //preparo el string para la llamada
            String sql = "{call p_nombre_depart(?,?)}";
            
            //creamos objeto llamando al metodo prepareCall
            CallableStatement llamada = bbdd.getConexion().prepareCall(sql);
            
            //le damos valor al departamento de entrada
            llamada.setInt(1,dep);
            
            //registramos los parametro de salida del procedimeinto
            llamada.registerOutParameter(2,Types.VARCHAR);
            
            //Hago llamada al procedimiento
            llamada.executeUpdate();
            
            //Recogemos el valor devuelto a traves de parametros
            salida_return = llamada.getString(2);
            
            
        } catch (SQLException ex) {
            
            switch(ex.getErrorCode()){
                
                case 1403:
                    System.out.println("No existe el departamento solicitado");            
            }
            
        }
        return salida_return;
    }
    
    
    /**
     * Llamada a la función f_nombre_depart, almacenada en la bbdd 
     * 
     * @param bbdd Para realizar la conexión a la bbdd
     * @param dept_no Número del departamento que le pasaremos a la función
     * @return  Nombre del departamento
     */
    public static String fNombreDepart(OperacionesBBDD bbdd, int dept_no) {
        CallableStatement llamada;
        String dnombre = null;
        try {     
            String sql = "{?=call f_nombre_depart (?)}";
            llamada=bbdd.getConexion().prepareCall(sql);
            llamada.setInt(2, dept_no);
            llamada.registerOutParameter(1, Types.VARCHAR);
            llamada.executeUpdate();
            dnombre = llamada.getString(1);
                     
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  dnombre;
    }
    
    
    //LLAMADA A PROCEDIMIENTO Y A FUNCION DE LA MASTERCLASS
    
    //procedimiento p_subida-sal
    public static void p_subida_sal(OperacionesBBDD bbdd, int dept_no, int subidaSalario){
        
        CallableStatement llamada;
        
        try {
            
            String sql = "{call p_subida_sal(?,?)}";
            
            llamada = bbdd.getConexion().prepareCall(sql);
            
            //lo hacemos asi pq no devuelve nada, si devolviese, el 1 para lo que devuelve y 2 y 3 para los param de entrada del procedure
            llamada.setInt(1, dept_no);
            llamada.setInt(2, subidaSalario);
            
            llamada.executeUpdate();

            //no hacemos ningun get porque no devuelve nada
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    //funcion f_n_empleado
    public static int f_n_empleado(OperacionesBBDD bbdd, int dept_no){
        
        CallableStatement llamada;
            
        int nEmpleados = 0;
        
        try {
            
            String sql = "{?=call f_n_empleado (?)}";
            
            llamada = bbdd.getConexion().prepareCall(sql);
            
            llamada.setInt(2, dept_no);
            
            llamada.registerOutParameter(1, Types.INTEGER);
            
            //Arriba ponemos el tipo que va a devolver, y, antes de guardar en la variable, ejecutamos
            llamada.executeUpdate();
            
            //aqui nos devolvera el numero de empleados
            nEmpleados = llamada.getInt(1);
   
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nEmpleados;
        
    }
    
    
    
    
    @Override
    public String toString() {
        return "Departamento{" + "dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + '}';
    }
    
    
    
    

}
