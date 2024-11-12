/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ej1prepexamen.modelo;

/**
 *
 * @author Ivan Sobrino
 */
public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    
    public Producto(int id, String nombre, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", stock=" + stock + '}';
    }
    
    public static boolean validarProducto(Producto producto) {
    if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
        System.out.println("El nombre del producto no puede estar vacío.");
        return false;
    }
    if (producto.getPrecio() < 0) {
        System.out.println("El precio del producto debe ser positivo.");
        return false;
    }
    if (producto.getStock() < 0) {
        System.out.println("El stock del producto no puede ser negativo.");
        return false;
    }
    return true;
}

}
