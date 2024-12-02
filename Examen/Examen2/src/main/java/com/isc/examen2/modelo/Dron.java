/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.examen2.modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 27 nov 2024
 */
public class Dron {

    private String nserie;
    private String marca;
    private String modelo;
    private double peso;
    private int potencia;
    private int ecinetica;
    private String aseguradora;
    private double horas;
    private double autonomia;
    private Date adquisicion;
    private String dni;

    public Dron(String nserie, String marca, String modelo, double peso, int potencia, int ecinetica, String aseguradora, double horas, double autonomia, Date adquisicion, String dni) {
        this.nserie = nserie;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.potencia = potencia;
        this.ecinetica = ecinetica;
        this.aseguradora = aseguradora;
        this.horas = horas;
        this.autonomia = autonomia;
        this.adquisicion = adquisicion;
        this.dni = dni;
    }

    public Dron(String nserie, String marca, String modelo, double peso, int potencia, int ecinetica, double horas, double autonomia, Date adquisicion) {
        this.nserie = nserie;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.potencia = potencia;
        this.ecinetica = ecinetica;
        this.horas = horas;
        this.autonomia = autonomia;
        this.adquisicion = adquisicion;
    }

    public Dron() {
    }

    public String getNserie() {
        return nserie;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPeso() {
        return peso;
    }

    public int getPotencia() {
        return potencia;
    }

    public int getEcinetica() {
        return ecinetica;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public double getHoras() {
        return horas;
    }

    public double getAutonomia() {
        return autonomia;
    }

    public Date getAdquisicion() {
        return adquisicion;
    }

    public String getDni() {
        return dni;
    }

    public void setNserie(String nserie) {
        this.nserie = nserie;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public void setEcinetica(int ecinetica) {
        this.ecinetica = ecinetica;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public void setHoras(double horas) {
        this.horas = horas;
    }

    public void setAutonomia(double autonomia) {
        this.autonomia = autonomia;
    }

    public void setAdquisicion(Date adquisicion) {
        this.adquisicion = adquisicion;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    /*-------------------------------------------------------------------------------------*/
    public void insertarDron(Modelo bbdd, String nserie, String marca, String modelo, double peso, int potencia, int ecinetica, String aseguradora, double horas, double autonomia, String adquisicion, String dni) {
        try {

            //Vemos si existe el dron
//            String serieSQL = "SELECT COUNT(*) FROM drones WHERE nserie = ?";
//            Optional<ResultSet> rsSerie = bbdd.utilidadInsert(serieSQL, nserie);
//            if (rsSerie.isPresent()) {
//                rsSerie.get().next();
//                if(rsSerie.get().getInt(1) == 0) {
//                    System.out.println("Error: Ya existe este dron.");
//                }
//            }
            //Vemos si existe el operador que pilotará el dron
//            String dniSQL = "SELECT COUNT(*) FROM drones WHERE dni = ?";
//            Optional<ResultSet> rsDni = bbdd.utilidadInsert(dniSQL, dni);
//            if (rsDni.isPresent()) {
//                rsDni.get().next();
//                if(rsDni.get().getInt(1) > 0) {
//                    System.out.println("Error: No existe el operador que pilotará este dron.");
//                }
//            }            
            //Inserción
            Optional<ResultSet> rs = bbdd.utilidadInsert("INSERT INTO drones VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?)",
                    nserie, marca, modelo, peso, potencia, ecinetica, aseguradora, horas, autonomia, adquisicion, dni);

            if (rs.isPresent()) {
                System.out.println("Dron insertado correctamente.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminaFilasDronesObsoletos(Modelo bbdd, double p_horas, String p_fecha_verificacion) {
        try {
            SimpleDateFormat s = new SimpleDateFormat("DD/MM/YYYY");
            java.util.Date fechaUtil = null;
            String fecha = p_fecha_verificacion;
            fechaUtil = s.parse(fecha);
            java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());

            try {
                bbdd.utilidadDelete("DELETE FROM drones WHERE adquisicion < TO_DATE(?, 'DD/MM/YYYY') AND horas > ?", p_fecha_verificacion, p_horas);
            } catch (SQLException ex) {
                Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarFilaDatosDrones(Modelo bbdd, String p_nserie) {
        String sql = "SELECT d.nserie, d.horas, d.autonomia, d.adquisicion, o.dni, o.nombre, o.correo, o.telefono FROM drones d, operadores o WHERE d.nserie = ?";
        try {
            Optional<ResultSet> rs = bbdd.utilidadInsert(sql, p_nserie);

            if (rs.isPresent()) {
                while (rs.get().next()) {
                    System.out.println("Nserie: " + rs.get().getString("nserie")
                            + ", Horas: " + rs.get().getDouble("horas")
                            + ", Autonomia: " + rs.get().getDouble("autonomia")
                            + ", Adquisicion: " + rs.get().getDate("adquisicion")
                            + ", Dni: " + rs.get().getString("dni")
                            + ", Nombre: " + rs.get().getString("nombre")
                            + ", Correo: " + rs.get().getString("correo")
                            + ", Telefono: " + rs.get().getInt("telefono"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarTodosDatos(Modelo bbdd, String p_fecha_inicio_adquisicion, String p_fecha_fin_adquisicion) {
        try {
            String sql = "SELECT nserie, marca, modelo, peso, potencia, ecinetica, aseguradora, horas, autonomia, adquisicion, dni FROM drones WHERE adquisicion BETWEEN TO_DATE(?, 'DD/MM/YY') AND TO_DATE(?, 'DD/MM/YY')";
            Optional<ResultSet> rs = bbdd.utilidadSelect(sql, p_fecha_inicio_adquisicion, p_fecha_fin_adquisicion);

            if (rs.isPresent()) {
                while (rs.get().next()) {
                    System.out.println("Nserie: " + rs.get().getString("nserie")
                            + ", Marca: " + rs.get().getString("marca")
                            + ", Modelo: " + rs.get().getString("modelo")
                            + ", Peso: " + rs.get().getDouble("peso")
                            + ", Potencia: " + rs.get().getInt("potencia")
                            + ", Ecinetica: " + rs.get().getInt("ecinetica")
                            + ", Aseguradora: " + rs.get().getString("aseguradora")
                            + ", Horas: " + rs.get().getDouble("horas")
                            + ", Autonomia: " + rs.get().getDouble("autonomia")
                            + ", Adquisicion: " + rs.get().getDate("date")
                            + ", Dni: " + rs.get().getString("dni"));
                }
            }
            
            Optional<ResultSet> rsFilas = utilidadSelectAll(bbdd);
            if (rs.isPresent()) {
                int totalFilas = utilidadObtenerNumeroFilas(rs.get());
                System.out.println("Total de filas: " + totalFilas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public static Optional<ResultSet> utilidadSelectAll(Modelo bbdd) {

        Optional<ResultSet> rs = null;

        try {

            rs = bbdd.utilidadSelect("SELECT * FROM drones");

        } catch (SQLException ex) {
            Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);

        }

        return rs;

    }
    
    public static int utilidadObtenerNumeroFilas(ResultSet rs) {
        int numFilas = 0;
        try {
            rs.last();
            numFilas = rs.getRow();
            rs.beforeFirst();
        } catch (SQLException ex) {
            Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numFilas;
    }
    /*
    
    Dates also have a numeric value. You can use the TO_NUMBER and TO_DATE 
    functions to change a value from a DATE to an INTEGER or an INTEGER to a DATE for comparison.
    
        public static void delete(OperacionesBBDD bbdd, int id_empleado) {

        try {

            bbdd.delete("DELETE FROM empleados WHERE emp_no = ?", id_empleado);

        } catch (SQLException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     */
}




//PARA INSERTAR OPERADOR: INSERT INTO operadores VALUES ('12345678L','JORGE','UN','15/02/2022','correo@gmail.com',111222333);
