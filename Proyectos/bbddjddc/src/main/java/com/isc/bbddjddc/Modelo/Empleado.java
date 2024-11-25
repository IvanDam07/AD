/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.bbddjddc.Modelo;

import com.isc.bbddjddc.bbdd.OperacionesBBDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 6 nov 2024
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

    public Empleado() {

    }

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

    @Override
    public String toString() {
        return "Empleado{" + "emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir + ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept_no=" + dept_no + '}';
    }

    public void insertar(OperacionesBBDD bbdd) {
        try {
            bbdd.insert("insert into Empleados values(?,?,?,?,?,?,?,?)", this.emp_no, this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectById(OperacionesBBDD bbdd, int n) {
        try {
            Optional<ResultSet> rs = bbdd.select("SELECT * FROM empleados WHERE dept_no = ?", n);

            if (rs.isPresent()) {
                while (rs.get().next()) {
                    this.emp_no = (rs.get().getInt("emp_no"));
                    this.apellido = (rs.get().getString("apellido"));
                    this.oficio = (rs.get().getString("oficio"));
                    this.dir = (rs.get().getInt("dir"));
                    this.fecha_alt = (rs.get().getString("fecha_alt"));
                    this.salario = (rs.get().getDouble("salario"));
                    this.comision = (rs.get().getDouble("comision"));
                    this.dept_no = (rs.get().getInt("dept_no"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Optional<ResultSet> selectAll(OperacionesBBDD bbdd) {
        Optional<ResultSet> rs = null;

        try {
            rs = bbdd.select("SELECT * FROM empleados");
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public static void mostrarResultSet(Optional<ResultSet> rs) {
        try {
            if (rs.isPresent()) {

                while (rs.get().next()) {
                    System.out.println("Número empleado: " + rs.get().getInt("emp_no")
                            + " Apellido: " + rs.get().getString("apellido")
                            + " Oficio: " + rs.get().getString("oficio")
                            + " Dir: " + rs.get().getInt("dir")
                            + "Fecha de Alta: " + rs.get().getString("fecha_alt")
                            + "Salario: " + rs.get().getDouble("salario")
                            + "Comision: " + rs.get().getDouble("comision")
                            + "Número departamento: " + rs.get().getInt("dept_no"));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(OperacionesBBDD bbdd) {
        try {
            bbdd.update("UPDATE empleados SET apellido=?, oficio=?, dir=?, fecha_alt=?, salario=?, comision=?, dept_no=? WHERE emp_no=?",
                    this.emp_no,
                    this.apellido,
                    this.oficio,
                    this.dir,
                    this.fecha_alt,
                    this.salario,
                    this.comision,
                    this.dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void delete(OperacionesBBDD bbdd, int emp_no) {
        try {
            bbdd.delete("DELETE empleados WHERE emp_no=?",emp_no);
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
