/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.examenr1ivan_sobrino.modelo;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 23 oct 2024
 */
public class Universidad {
    
    private int ID;
    private String nombreCarrera;
    private String ciudad;
    private double notaCorte;

    public Universidad(int ID, String nombreCarrera, String ciudad, double notaCorte) {
        this.ID = ID;
        this.nombreCarrera = nombreCarrera;
        this.ciudad = ciudad;
        this.notaCorte = notaCorte;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getNotaCorte() {
        return notaCorte;
    }

    public void setNotaCorte(double notaCorte) {
        this.notaCorte = notaCorte;
    }
    
}
