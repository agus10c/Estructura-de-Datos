/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lineales.estaticas;

/**
 *
 * @author acace
 */
public class Pila {
    private Object[] arreglo;
    private int tope;
    private static final int Tamanio = 10;
   
    public Pila() {
    this.arreglo = new Object[Tamanio];
    this.tope = -1;
    }
   
    public boolean apilar(Object nuevoElemento) {
        boolean exito;
        if (this.tope+1 >= this.Tamanio) {
            exito = false;
        } else {
            this.tope++;
            this.arreglo[tope] = nuevoElemento;
            exito = true;
        }
        return exito;
    }
   
    public boolean desapilar() {
        boolean exito;
        if (this.tope == -1) {
            exito = false;
        } else {
            this.arreglo[tope] = null;
            this.tope--;
            exito = true;
        }
        return exito;
    }
   
    public Object obtenerTope() {
        Object r;
        if(this.tope == -1) {
            r = null;
        } else {
            r = this.arreglo[tope];
        }
        return r;
    }
   
    public boolean esVacia() {
        return this.tope==-1;
    }  
   
    public void vaciar() {
        int i;
        for (i=this.tope; i>=0; i--) {
            this.arreglo[i] = null;
        }
        this.tope = -1;
    }
    public Pila clone() {
        Pila clon = new Pila();
        int i;
        for (i=0; i<=this.tope; i++ ) {
            clon.arreglo[i] = this.arreglo[i];
        }
        clon.tope = this.tope;
        return clon;
    }
    
    public String toString() {
        //Muestra todos los elementos del arreglo.
        int i;
        String s = "[";
        if (this.tope != -1) {
            for (i = 0; i < Tamanio; i++) {
                if (this.arreglo[i] == null) {
                    s += "-";
                } else {
                    s += this.arreglo[i];
                }
                if (i + 1 != Tamanio) {
                    s += ", ";
                }
            }
        }
        s += "]";
        return s;
    }
    
    public String toString2() {
        //Muestra los elementos de la Pila.
        int i;
        String cadena = "[";
        if(this.tope!=-1) {
            for (i=0; i<=this.tope; i++) {        
                cadena += this.arreglo[i];
                if (i != this.tope) {
                    cadena += ", ";
                }  
            }
        }
        cadena += "]";
        return cadena;     
    }

}
