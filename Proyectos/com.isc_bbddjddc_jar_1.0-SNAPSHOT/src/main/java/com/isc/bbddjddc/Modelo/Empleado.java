/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.bbddjddc.Modelo;

import com.isc.bbddjddc.Bbddjddc;
import com.isc.bbddjddc.bbdd.OperacionesBBDD;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
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
    private Date fecha_alt;
    private double salario;
    private double comision;
    private int dept_no;

    public Empleado(int emp_no, String apellido, String oficio, int dir, Date fecha_alt, double salario, double comision, int dept_no) {
        this.emp_no = emp_no;
        this.apellido = apellido;
        this.oficio = oficio;
        this.dir = dir;
        this.fecha_alt = fecha_alt;
        this.salario = salario;
        this.comision = comision;
        this.dept_no = dept_no;
    }

    public Empleado() {

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

    public Date getFecha_alt() {
        return fecha_alt;
    }

    public void setFecha_alt(Date fecha_alt) {
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
    public void insertar(OperacionesBBDD bbdd) {

        try {

            bbdd.insert("insert into empleados values (?,?,?,?,?,?,?,?)", this.emp_no, this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no);

        } catch (SQLException ex) {
            Logger.getLogger(Bbddjddc.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Seleccionar un empleado por su identificador
    public void selectById(OperacionesBBDD bbdd, int id_empleado) {

        try {

            Optional<ResultSet> rs = bbdd.select("SELECT * FROM empleados WHERE emp_no = ?", id_empleado);

            if (rs.isPresent()) {

                while (rs.get().next()) {

                    this.emp_no = (rs.get().getInt("emp_no"));
                    this.apellido = (rs.get().getString("apellido"));
                    this.oficio = (rs.get().getString("oficio"));
                    this.dir = (rs.get().getInt("dir"));
                    this.fecha_alt = (rs.get().getDate("fecha_alt"));
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
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);

        }

        return rs;

    }

    public static void mostrarResultSet(Optional<ResultSet> rs) {

        try {
            if (rs.isPresent()) {

                while (rs.get().next()) {

                    System.out.println("Número empleado: " + rs.get().getInt("emp_no")
                            + "; apellido: " + rs.get().getString("apellido")
                            + "; oficio: " + rs.get().getString("oficio")
                            + "; dir: " + rs.get().getInt("dir")
                            + "; fecha de alta: " + rs.get().getDate("fecha_alt")
                            + "; salario: " + rs.get().getDouble("salario")
                            + "; comision: " + rs.get().getDouble("comision")
                            + "; numero departamento: " + rs.get().getInt("dept_no"));
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Departamento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(OperacionesBBDD bbdd) {

        try {

            bbdd.update("UPDATE empleados SET apellido = ?, oficio = ?, dir = ?, fecha_alt = ?, salario = ?, comision = ?, dept_no = ? WHERE emp_no = ?",
                    this.apellido, this.oficio, this.dir, this.fecha_alt, this.salario, this.comision, this.dept_no, this.emp_no);

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Metodo actualizar, pero pasandole un result set para actualizar en el mismo result set
    public void update(ResultSet rs) {

        try {
            rs.beforeFirst();

            while (rs.next()) {

                rs.updateString("apellido", this.apellido);
                rs.updateString("oficio", this.oficio);
                rs.updateInt("dir", this.dir);
                rs.updateDate("fecha_alt", this.fecha_alt);
                rs.updateDouble("salario", this.salario);
                rs.updateDouble("comision", this.comision);
                rs.updateInt("dept_no", this.dept_no);

                rs.updateRow();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void delete(OperacionesBBDD bbdd, int id_empleado) {

        try {

            bbdd.delete("DELETE FROM empleados WHERE emp_no = ?", id_empleado);

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Optional<ResultSet> obtenerEmpleadoPorDepartamentos(OperacionesBBDD bbdd, int dept_no) {
        try {
            return bbdd.select("SELECT apellido, oficio, salario FROM empleados WHERE dept_no = ?", dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            return Optional.empty();
        }
    }

    /*
    Método que muestra el apellido, salario y nombre de departamento del empleado con maximo salario
     */
    public void obtenerEmpleadoConMayorSalario(OperacionesBBDD bbdd) {
        try {

            Optional<ResultSet> rs = bbdd.select("SELECT e.apellido, e.salario, d.dnombre FROM empleados e, departamentos d WHERE e.dept_no = d.dept_no AND e.salario = (SELECT MAX(salario) FROM empleados");

            if (rs.isPresent()) {
                while (rs.get().next()) {
                    System.out.println("Apellido: " + rs.get().getString("apellido")
                            + ", Salario: " + rs.get().getString("salario")
                            + ", Nombre departamento: " + rs.get().getString("dnom"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarEmpleadoEnDepartamento(OperacionesBBDD bbdd, int emp_no, String nombre, String trabajo, int jefe, String fechaAlta, double salario, double comision, int dept_no) {
        try {
            Optional<ResultSet> rs = bbdd.insert("INSERT INTO empleados VALUES (?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)",
                    emp_no, nombre, trabajo, jefe, fechaAlta, salario, comision, dept_no);

            // Si necesitas imprimir o verificar el resultado:
            if (rs.isPresent()) {
                System.out.println("Empleado insertado correctamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarSalario(OperacionesBBDD bbdd, int dept_no, double salario) {
        try {
            bbdd.update("UPDATE empleados SET salario = salario + ? WHERE dept_no = ?", salario, dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarSalarioPorcentaje(OperacionesBBDD bbdd, int dept_no, double porcentajeSalario) {
        try {
            bbdd.update("UPDATE empleados SET salario = salario * ((100 + ?)/100 WHERE dept_no = ?", porcentajeSalario, dept_no);
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertarEmpleadoEnDepartamentoMejorado(OperacionesBBDD bbdd, int emp_no, String nombre, String trabajo, int jefe, String fechaAlta, double salario, double comision, int dept_no) {
        try {
            // 1. Comprobar si el departamento existe
            String deptSQL = "SELECT COUNT(*) FROM departamentos WHERE dept_no = ?";
            Optional<ResultSet> rsDept = bbdd.insert(deptSQL, dept_no); // Usamos insert porque no tienes un método consultar

            if (rsDept.isPresent()) {
                rsDept.get().next();
                if (rsDept.get().getInt(1) == 0) {
                    System.out.println("Error: El departamento con ID " + dept_no + " no existe.");
                    return;
                }
            }

            // 2. Comprobar si el número de empleado ya existe
            String empSQL = "SELECT COUNT(*) FROM empleados WHERE emp_no = ?";
            Optional<ResultSet> rsEmp = bbdd.insert(empSQL, emp_no);

            if (rsEmp.isPresent()) {
                rsEmp.get().next();
                if (rsEmp.get().getInt(1) > 0) {
                    System.out.println("Error: Ya existe un empleado con el número " + emp_no);
                    return;
                }
            }

            // 3. Comprobar que el salario es mayor o igual que 0
            if (salario <= 0) {
                System.out.println("Error: El salario debe ser mayor que 0.");
                return;
            }

            // 4. Comprobar si el director (jefe) existe
            String jefeSQL = "SELECT COUNT(*) FROM empleados WHERE emp_no = ?";
            Optional<ResultSet> rsJefe = bbdd.insert(jefeSQL, jefe);

            if (rsJefe.isPresent()) {
                rsJefe.get().next();
                if (rsJefe.get().getInt(1) == 0) {
                    System.out.println("Error: El director con ID " + jefe + " no existe.");
                    return;
                }
            }

            // 5. Comprobar que el apellido y el oficio no sean nulos
            if (nombre == null || trabajo == null) {
                System.out.println("Error: El nombre y el trabajo no pueden ser nulos.");
                return;
            }

            // 6. Fecha de alta (fecha actual)
            String currentDate = java.time.LocalDate.now().toString();  // Fecha en formato YYYY-MM-DD

            // 7. Realizar la inserción
            String insertSQL = "INSERT INTO empleados (emp_no, nombre, trabajo, jefe, fecha_alta, salario, comision, dept_no) "
                    + "VALUES (?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?)";

            Optional<ResultSet> rsInsert = bbdd.insert(insertSQL, emp_no, nombre, trabajo, jefe, currentDate, salario, comision, dept_no);

            if (rsInsert.isPresent()) {
                System.out.println("Empleado insertado correctamente.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Empleado{" + "emp_no=" + emp_no + ", apellido=" + apellido + ", oficio=" + oficio + ", dir=" + dir + ", fecha_alt=" + fecha_alt + ", salario=" + salario + ", comision=" + comision + ", dept_no=" + dept_no + '}';
    }

    public void llamarPSubidaSal(OperacionesBBDD bbdd, int dept_no, double subida) {
        try {
            String sql = "{call p_subida_sal(?, ?)}";
            CallableStatement cs = bbdd.getConexion().prepareCall(sql);
            cs.setInt(1, dept_no);
            cs.setDouble(2, subida);
            cs.executeUpdate();
            System.out.println("Salarios actualizados correctamente.");
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void mostrarNominaEmpleados(OperacionesBBDD bbdd) {
        try {
            String sql = "SELECT e.apellido, e.salario, e.comision, "
                    + "f_nomina(e.salario, e.comision, 20) as nomina "
                    + "FROM empleados e";
            Optional<ResultSet> rs = bbdd.select(sql);

            if (rs.isPresent()) {
                while (rs.get().next()) {
                    System.out.println(
                            "Apellido: " + rs.get().getString("apellido")
                            + ", Salario: " + rs.get().getDouble("salario")
                            + ", Comisión: " + rs.get().getDouble("comision")
                            + ", Nómina: " + rs.get().getDouble("nomina")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static int obtenerNumeroFilas(ResultSet rs) {
        int numFilas = 0;
        try {
            rs.last();
            numFilas = rs.getRow();
            rs.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numFilas;
    }


}
