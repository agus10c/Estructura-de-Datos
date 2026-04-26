/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parcialPractica;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

/**
 *
 * @author acace
 */
public class Ejercicio1 {
    public static Lista invertirConVocales(Cola q) {
        Lista lis = new Lista();
        Cola aux = new Cola();
        Cola clon = q.clone();
        while(!clon.esVacia()) {
            boolean continuar, tieneVocales = false;
            continuar = !clon.obtenerFrente().equals('#');
            while(continuar) {
                Object x = clon.obtenerFrente();
                aux.poner(x);
                if(x.equals('a') || x.equals('e') || x.equals('i') || x.equals('o') || x.equals('u')
                        || x.equals('A') || x.equals('E') || x.equals('I') || x.equals('O') || x.equals('U')) {
                    tieneVocales = true;
                }
                clon.sacar();
                if(clon.esVacia()) {
                    continuar = false;
                } else {
                    if(clon.obtenerFrente().equals('#')) {
                        continuar = false;
                    }
                }
            }
            if(tieneVocales) {
                int pos = lis.longitud()+1;
                while(!aux.esVacia()) {
                    lis.insertar(aux.obtenerFrente(), pos);
                    aux.sacar();
                }
            } else {
                while(!aux.esVacia()) {
                    lis.insertar(aux.obtenerFrente(), lis.longitud()+1);
                    aux.sacar();
                }
            }
            if(!clon.esVacia()) {
                lis.insertar('#', lis.longitud()+1);
                clon.sacar();
            } 
        } 
        return lis;
    }
    
    public static void main(String[] args) {
        Cola col = new Cola();
        col.poner('a');
        col.poner('b');
        col.poner('c');
        col.poner('d');
        col.poner('e');
        col.poner('f');
        col.poner('#');
        col.poner('a');
        col.poner('b');
        col.poner('c');
        col.poner('d');
        col.poner('#');
        col.poner('q');
        col.poner('w');
        col.poner('r');
        col.poner('t');
        col.poner('y');
        col.poner('#');
        col.poner('s');
        col.poner('j');
        System.out.println("Cola: ");
        System.out.println(col.toString());
        System.out.println("Invertir con vocales: ");
        System.out.println(invertirConVocales(col).toString());
    }
}
