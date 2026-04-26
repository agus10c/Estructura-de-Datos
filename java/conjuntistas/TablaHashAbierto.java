/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

import lineales.dinamicas.Nodo;

/**
 *
 * @author acace
 */
public class TablaHashAbierto {
    private int TAMANIO = 20;
    private Nodo[] tabla;
    private int cant;
    
    public TablaHashAbierto() {
        this.tabla = new Nodo[TAMANIO];
        cant = 0;
    }
    
    public boolean insertar(Object nuevoElem) {
        boolean exito = false;
        int pos = nuevoElem.hashCode()%this.TAMANIO;
        Nodo aux = this.tabla[pos];
        while (!exito && aux != null) {
            exito = aux.getElemento().equals(nuevoElem);
            aux = aux.getEnlace();
        }
        if (!exito) {
            this.tabla[pos] = new Nodo(nuevoElem,this.tabla[pos]);
            this.cant++;
        }
        return exito;
    }
    
    public boolean eliminar(Object elem) {
        boolean exito = false;
        int pos = elem.hashCode()%this.TAMANIO;
        Nodo aux1 = this.tabla[pos];
        Nodo aux2 = aux1;
        while (!exito && aux1 != null) {
            exito = aux1.getElemento().equals(elem);
            if(!exito) {
                aux2 = aux1;
            }
            aux1 = aux1.getEnlace();
        }
        if (exito) {
            aux2.setEnlace(aux1);
            this.cant--;
        }
        return exito;
    }
    
    public boolean pertenece(Object elem) {
        boolean encontrado = false;
        int pos = elem.hashCode()%this.TAMANIO;
        Nodo aux = this.tabla[pos];
        while (!encontrado && aux != null) {
            encontrado = aux.getElemento().equals(elem);
            aux = aux.getEnlace();
        }
        
        return encontrado;
    }
    
    public boolean esVacio() {
        return cant == 0;
    }
}
