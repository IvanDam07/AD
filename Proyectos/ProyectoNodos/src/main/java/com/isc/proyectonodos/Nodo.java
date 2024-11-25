/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isc.proyectonodos;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0 Created on 12 sept 2024
 */
public class Nodo<T> {

    //Atributos
    private T elemento;
    private T dato;
    private Nodo<T> siguiente; //Apunta el siguiente nodo

    /**
     * Constructor por defecto
     */
    public Nodo() {
        siguiente = null;
    }

    //Metodos
    public T getElemento() {
        return elemento;
    }

    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    @Override
    public String toString() {
        return elemento + "\n";
    }

    /**
     * Le pasamos un dato al nodo
     *
     * @param p
     */
    public Nodo(T p) {
        siguiente = null;
        dato = p;
    }

    /**
     * Le pasamos un dato y su siguiente nodo al nodo
     *
     * @param t Dato a insertar
     * @param siguiente Su sisguiente nodo
     */
    public Nodo(T t, Nodo<T> siguiente) {
        this.siguiente = siguiente;
        dato = t;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}
