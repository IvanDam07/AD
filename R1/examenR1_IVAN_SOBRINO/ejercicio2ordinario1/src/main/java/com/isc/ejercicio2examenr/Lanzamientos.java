/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ejercicio2examenr;

import java.util.Date;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 19 feb 2025
 */
public class Lanzamientos {
    private int id;
    private String fecha_lanzamiento;
    private String lugar_lanzamiento;
    private int horas_vuelo_estimadas;

    public Lanzamientos() {
    }
        
    public Lanzamientos(int id, String fecha_lanzamiento, String lugar_lanzamiento, int horas_vuelo_estimadas) {
        this.id = id;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.lugar_lanzamiento = lugar_lanzamiento;
        this.horas_vuelo_estimadas = horas_vuelo_estimadas;
    }

    public int getId() {
        return id;
    }

    public String getFecha_lanzamiento() {
        return fecha_lanzamiento;
    }

    public String getLugar_lanzamiento() {
        return lugar_lanzamiento;
    }

    public int getHoras_vuelo_estimadas() {
        return horas_vuelo_estimadas;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha_lanzamiento(String fecha_lanzamiento) {
        this.fecha_lanzamiento = fecha_lanzamiento;
    }

    public void setLugar_lanzamiento(String lugar_lanzamiento) {
        this.lugar_lanzamiento = lugar_lanzamiento;
    }

    public void setHoras_vuelo_estimadas(int horas_vuelo_estimadas) {
        this.horas_vuelo_estimadas = horas_vuelo_estimadas;
    }
    
    
}
