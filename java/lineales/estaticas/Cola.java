/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lineales.estaticas;

/**
 *
 * @author agustin.caceres
 */
public class Cola {
    private Object[] arreglo;
    private int frente;
    private int fin;
    private static final int Tamanio = 10;
    
    public Cola() {
        this.arreglo = new Object[Tamanio];
        this.frente = 0;
        this.fin = 0;
    }
    
    public boolean poner(Object nuevoElem) {
        boolean exito;
        if ((this.fin+1)%Tamanio == this.frente) {
             exito = false;
        } else {
            this.arreglo[this.fin] = nuevoElem;
            this.fin = (this.fin+1)%Tamanio;
            exito = true;
        }
        return exito;
    }
    
    public boolean sacar() {
        boolean exito;
        if (frente==fin) {
            exito = false;
        } else {
          this.arreglo[frente] = null;
          this.frente = (this.frente+1)%Tamanio;
          exito =true;
        }
        return exito;
    }
    
    public Object obtenerFrente() {
        Object r;
        if (this.frente == this.fin) {
            r = null;
        } else {
            r = this.arreglo[this.frente];
        }  
        return r;
    }
    
    public boolean esVacia() {
        return frente==fin;
    }
    
    public void vaciar() {
        if (this.frente < this.fin) {
            while (this.frente < this.fin) {
                this.fin--;
                arreglo[fin] = null;
                arreglo[frente] = null;
                this.frente++;
            }
            this.frente = 0;
            this.fin = 0;
        } else if(this.frente>this.fin) {
            while(this.frente < this.Tamanio){
                this.arreglo[this.frente] = null;
                this.frente++;
            } 
            while(this.fin >= 0) {
                this.arreglo[this.fin] = null;
                this.fin--;
            }
            this.frente = 0;
            this.fin = 0;
        }       
    }
    
    public Cola clone() {
        Cola clon = new Cola();
        clon.arreglo = this.arreglo.clone();
        clon.frente = this.frente;
        clon.fin = this.fin;
        return clon;
    }
    
    public String toString() {
        //Muestra todos los elementos del arreglo.
        int i;
        String s = "[";
        if (this.frente != this.fin) {
            for (i = 0; i < Tamanio; i++) {
                if (this.arreglo[i] == null) {
                    s += "-";
                } else {
                    s += this.arreglo[i];
                }  
                if (i+1 != Tamanio) {
                    s += ", ";
                }
            }
        }
        s += "]";
        return s;
    }
    
    public String toString2() {
        //Muestra los elementos de la Cola.
        String cadena = "[";
        if (this.frente < this.fin) {
            int i;
            for (i = this.frente; i < this.fin; i++) {
                cadena += this.arreglo[i];
                if (i+1 != this.fin) {
                    cadena += ", ";
                }
            }
        } else if(this.frente>this.fin) {
            int i;
            for (i = this.frente; i < this.Tamanio; i++) {
                cadena += this.arreglo[i];
                if (i+1 == this.Tamanio) {
                    if (this.fin != 0) {
                        cadena += ", ";
                    }
                } else {
                    cadena += ", ";
                }
            }
            for (i = 0; i < this.fin; i++) {
                cadena += this.arreglo[i];
                if (i+1 != this.fin) {
                    cadena += ", ";
                }
            }     
        }
        cadena += "]";
        return cadena;     
    }

}
