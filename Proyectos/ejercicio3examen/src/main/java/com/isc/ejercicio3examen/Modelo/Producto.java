/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.ejercicio3examen.Modelo;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 13 nov 2024
 */
public class Producto {
    private long identificador;
    private String nombreProducto;
    private int cantidad;
    private String supermercado;
    
    public Producto(String nombreProduco, int cantidad, String supermercado) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.supermercado = supermercado;
    }
    
    public Producto() {
        
    }

    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

    public long getIdentificador() {
        return identificador;
    }

    
    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    @Override
    public String toString() {
        return "Producto{" + "identificador=" + identificador + ", nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + ", supermercado=" + supermercado + '}';
    }

    public static boolean validarProducto(Producto producto) {
    if (producto.getNombreProducto()== null || producto.getNombreProducto().isEmpty()) {
        System.out.println("El nombre del producto no puede estar vacío.");
        return false;
    }
    if (producto.getCantidad() < 0) {
        System.out.println("La cantidad del producto debe ser positivo.");
        return false;
    }
    if (producto.getSupermercado()==null || producto.getSupermercado().isEmpty()) {
        System.out.println("El nombre del supermercado no puede estar vacío.");
        return false;
    }
    return true;
}
    
    
    
    
}
