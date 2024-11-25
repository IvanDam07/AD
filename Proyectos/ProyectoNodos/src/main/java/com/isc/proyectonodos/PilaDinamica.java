/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.isc.proyectonodos;

/**
 *
 * @author ISC by Iván Sobrino Calzado
 * @version 1.0
 * Created on 12 sept 2024
 */
public class PilaDinamica<T> {
    //Atributos
    private Nodo<T> top; //Ultimo nodo que se ha incluido
    private int tamanio;
    
    //Constructores
    public PilaDinamica() {
        top=null; //No hay elementos
        this.tamanio = 0;
    }
    
    //Metodos
    /**
     * Indica si esta vacia o no
     * @return
     */
    public boolean isEmpty() {
        return top == null;
    }
    
    public int size() {
        return this.tamanio;
    }
    
    public T top() {
        if (isEmpty()) {
            return null;
        }else {
            return top.getElemento();
        }
    }
    
    public T pop() {
        if(isEmpty()) {
            return null;
        }else {
            T elemento = top.getElemento();
            Nodo<T> aux = top.getSiguiente();
            top = null;
            top = aux;
            this.tamanio--;
            return elemento;
        }
    }
    
    public T push(T elemento) {
        Nodo<T> aux = new Nodo<>(elemento, top);
        top = aux;
        this.tamanio++;
        return aux.getElemento();
    }
    
    public String toString() {
        if(isEmpty()) {
            return "La pila esta vacia";
        }else{
            
            String resultado="";
            Nodo<T>aux = top;
            while(aux!=null) {
                resultado += aux.toString();
                aux = aux.getSiguiente();
            }
            
            return resultado;
        }
    }
}
