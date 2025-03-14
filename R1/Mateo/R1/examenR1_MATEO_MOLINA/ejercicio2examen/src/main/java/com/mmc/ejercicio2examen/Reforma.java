/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mmc.ejercicio2examen;

/**
 *
 * @author MMC by Mateo Molina Campos
 * @version 1.0
 * Created on 13 nov 2024
 *
 */
public class Reforma {
    
    private long id;
    private String descripcion;
    private String direccion;
    private double coste;

    public Reforma(long id, String descripcion, String direccion, double coste) {
        this.id = id;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.coste = coste;
    }
    
    public Reforma(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }
    
    
    

}
