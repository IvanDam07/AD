/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.bbddjdbc.modelo;

import com.mmc.bbddjdbc.Bbddjdbc;
import com.mmc.bbddjdbc.bbdd.OperacionesBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
public class Empleado {
    
    private int emp_no;
    private String apellido;
    private String oficio;
    private int dir;
    private String fecha_alt;
    private double salario;
    private double comision;
    private int dept_no;

    public Empleado(int emp_no, String apellido, String oficio, int dir, String fecha_alt, double salario, double comision, int dept_no) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;
    }
    
    public Empleado(){
        
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public String getFecha_alt() {
        return fecha_alt;
    }

    public void setFecha_alt(String fecha_alt) {
        this.fecha_alt = fecha_alt;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public int getDept_no() {
        return dept_no;
    }

    public void setDept_no(int dept_no) {
        this.dept_no = dept_no;
    }
    
    
    //Insertar en la tabla empleados
    public void insertar(OperacionesBBDD bbdd){
        
        
        try {
            
            Optional <ResultSet> rs = bbdd.select("select * from empleados where dir = ?", this.dir);
            
            if(!rs.get().next()) { 
                throw new SQLException("DIRECTOR");    
            }
            
            if (this.salario < 0){ 
                throw new SQLException("SALARIO");
            }
            
            if(this.apellido == null){
                throw new SQLException("APELLIDO");
            }
            
            if(this.oficio == null){
                throw new SQLException("OFICIO");
            }
            
            rs = bbdd.select("select * from departamentos where dept_no = ?",this.dept_no);
            
            if(!rs.get().next()) {
                throw new SQLException("DEPARTAMENTO");
            }
            
            String fechaActual = LocalDateTime.now().getDayOfMonth()+"/"+LocalDateTime.now().getMonthValue()+"/"+LocalDateTime.now().getYear();
            
            if(!this.fecha_alt.equals(fechaActual)){
                throw new SQLException("FECHA");
            }
            
            
            
            bbdd.insert("insert into empleados values (?,?,?,?,?,?,?,?)" ,this.emp_no, this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no);
            
            
        } catch (SQLException ex) {
            
            switch(ex.getErrorCode()){
                
                case 19 -> System.out.println("El id del empleado ya existe");
                
            }
            
            switch(ex.getMessage()){
                
                case "DIRECTOR" -> System.out.println("El director no existe");
                
                case "SALARIO" -> System.out.println("El salario debe ser positivo");
                
                case "APELLIDO" -> System.out.println("El apellido no puede ser nulo");
                
                case "OFICIO" -> System.out.println("El oficio no puede ser nulo");
                
                case "DEPARTAMENTO" -> System.out.println("El departamento no existe");
                
                case "FECHA" -> System.out.println("La fecha debe ser la de hoy");
             }
        }
        
    }
    
    
    //Seleccionar un empleado por su identificador
    public void selectById(OperacionesBBDD bbdd, int id_empleado){
        
        try {
            
            Optional<ResultSet> rs = bbdd.select("SELECT * FROM empleados WHERE emp_no = ?",id_empleado);
            
            if(rs.isPresent()){
                
                while(rs.get().next()){
                    
                    this.emp_no = (rs.get().getInt("emp_no"));
                    this.apellido = (rs.get().getString("apellido"));
                    this.oficio = (rs.get().getString("oficio"));
                    this.dir = (rs.get().getInt("dir"));
                    this.salario = (rs.get().getDouble("salario"));
                    this.comision = (rs.get().getDouble("comision"));
                    this.dept_no = (rs.get().getInt("dept_no"));                    
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd){
        
        Optional<ResultSet> rs = null;
        
        try {
            
            rs = bbdd.select("SELECT * FROM empleados");
            
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        return rs;
        
    }
    
    
    public static void mostrarResultSet(Optional<ResultSet> rs){
        
        try {
            if(rs.isPresent()){
            
                while(rs.get().next()){
                
                    System.out.println("Número empleado: " + rs.get().getInt("emp_no") +
                            "; apellido: " + rs.get().getString("apellido") +
                            "; oficio: " + rs.get().getString("oficio") +
                            "; dir: " + rs.get().getInt("dir") +
                            "; fecha de alta: " + rs.get().getString("fecha_alt") +
                            "; salario: " + rs.get().getDouble("salario") + 
                            "; comision: "+ rs.get().getDouble("comision") + 
                            "; numero departamento: " + rs.get().getInt("dept_no"));
                }
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    
    public void update(OperacionesBBDD bbdd){
        
        
        try {
            
            bbdd.update("UPDATE empleados SET apellido = ?, oficio = ?, dir = ?, fecha_alt = ?, salario = ?, comision = ?, dept_no = ? WHERE emp_no = ?",
                    this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no, this.emp_no);
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public static void delete(OperacionesBBDD bbdd, int id_empleado){
        
        try {
            
            bbdd.delete("DELETE FROM empleados WHERE emp_no = ?", id_empleado);
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /*
    Método que realiza una consulta que obtenga el apellido, el oficio y el salario de los empleados de un determinado departamento
    */
    public static void selectApellidoOficioSalario (OperacionesBBDD bbdd, int n_dep){
    
        double salarioAcum = 0;
    int nEmpleados = 0;
    boolean hayEmpleados = false;

    try {
        Optional<ResultSet> rs = bbdd.select(
            "SELECT e.APELLIDO, e.OFICIO, e.SALARIO " +
            "FROM empleados e, departamentos d " +
            "WHERE e.dept_no = ? AND e.dept_no = d.dept_no", 
            n_dep
        );

        if (rs.isPresent()) {
            while (rs.get().next()) {
                hayEmpleados = true;

                // Procesar los resultados
                System.out.println(""
                    + "apellido: " + rs.get().getString("apellido") +
                    "; oficio: " + rs.get().getString("oficio") +
                    "; salario: " + rs.get().getDouble("salario"));

                salarioAcum += rs.get().getDouble("salario");
                nEmpleados++;
            }

            // Verificar si se encontraron empleados
            if (hayEmpleados) {
                System.out.println("El departamento " + n_dep + " tiene " + nEmpleados +
                    " empleados y su salario medio es: " + (salarioAcum / nEmpleados));
            } else {
                System.out.println("No existe el departamento o no tiene empleados.");
            }
        } else {
            System.out.println("La consulta no devolvió ningún resultado.");
        }
    } catch (SQLException ex) {
        Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    }
    
    
    /*
    Método que muestra el apellido, salario y nombre de departamento del empleado con maximo salario
    */
    public void selectInfoEmpleMaxSalario (OperacionesBBDD bbdd){
        
        
        try {
            
            //este select admite parametros, pero no es obligatorio ponerlos, como en este caso, que no es necesario
            Optional<ResultSet> rs = bbdd.select("SELECT e.apellido, e.salario, d.dnombre FROM empleados e, departamentos d WHERE "
                    + "e.dept_no = d.dept_no AND salario = (SELECT MAX(salario) FROM empleados)");
            
            if(rs.isPresent()){
                
                while(rs.get().next()){
                    
                    System.out.println(""
                            + "apellido: " + rs.get().getString("apellido")
                            + "; salario: " + rs.get().getDouble("salario")
                            + "; dnombre: " + rs.get().getString("dnombre"));
                    
                }
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /*
    Este metodo recibe un porcentaje para modificar el salario (en negativo si lo quiere bajar) y el numero de departamento de los empleados a los que se les modificara
    */
    public void actualizarSalarioEmpleadosDeUnDepartamento(OperacionesBBDD bbdd, double modificacionSalario, int dept_no){
        
        
        try {
            
            bbdd.update("UPDATE empleados SET salario = salario * ((100 + ?)/100) WHERE dept_no = ?",modificacionSalario,dept_no);
            
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @Override
    public String toString() {
        return "Empleado{" + "emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir + ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept_no=" + dept_no + '}';
    }

}
