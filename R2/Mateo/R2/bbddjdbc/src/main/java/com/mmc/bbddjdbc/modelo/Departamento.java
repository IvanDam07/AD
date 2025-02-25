/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.bbddjdbc.modelo;

import com.mmc.bbddjdbc.Bbddjdbc;
import com.mmc.bbddjdbc.bbdd.OperacionesBBDD;
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
            
            bbdd.insert("insert into departamentos values (?,?,?)",this.dept_no, this.dnombre, this.loc);
            
        } catch (SQLException ex) {
            Logger.getLogger(Bbddjdbc.class.getName()).log(Level.SEVERE, null, ex);
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
            
            rs = bbdd.select("SELECT * FROM departamentos");
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    public static void delete(OperacionesBBDD bbdd, int n_dep){
        
        try {
            
            bbdd.delete("DELETE FROM departamentos WHERE dept_no = ?",n_dep);
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @Override
    public String toString() {
        return "Departamento{" + "dept_no=" + dept_no + ", dnombre=" + dnombre + ", loc=" + loc + '}';
    }
    
    
    
    

}
