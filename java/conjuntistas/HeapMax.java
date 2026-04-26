/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

/**
 *
 * @author acace
 */
public class HeapMax {
    
    private Comparable[] heap;
    private int ultimo;
    private int TAMANIO = 20;
    
    HeapMax() {
        this.heap = new Comparable[TAMANIO];
        this.ultimo = 0;
    }
    
    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if(this.ultimo == 0) {
            this.heap [1] =  elem;
        } else {
            if(this.ultimo+1 < this.TAMANIO -1) {
                this.ultimo++;
                this.heap[ultimo] = elem;
                hacerSubir(this.ultimo);
            } else {
                exito = false;
            }
            
        }
        return exito;
    }
    
    private void hacerSubir(int posH) {
        int posP;
        Comparable temp = this.heap[posH];
        boolean salir = false;
        while(!salir) {
            posP = (int)posH/2;
            if(posH>=1) {
                if(this.heap[posP].compareTo(temp)>0) {
                    this.heap[posH] = this.heap[posP];
                    this.heap[posP] = temp;
                    posH = posP;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }  
        }
    }
    
    public boolean eliminarCima() {
        boolean exito;
        if(this.ultimo == 0) {
            exito = false;
        } else {
            this.heap[1] = this.heap[ultimo];
            this.ultimo--;
            hacerBajar(1);
            exito = true;
        }
        return exito;
    }
    
    private void hacerBajar(int posPadre) {
        int posH;
        Comparable temp = this.heap[posPadre];
        boolean salir = false;
        while(!salir) {
            posH = posPadre*2;
            if(posH<=this.ultimo) {
                if(posH<this.ultimo) {
                    if(this.heap[posH+1].compareTo(this.heap[posH])>0) {
                        posH++;
                    }
                }
                if(this.heap[posH].compareTo(temp)>0) {
                    this.heap[posPadre] = this.heap[posH];
                    this.heap[posH] = temp;
                    posPadre = posH;
                } else {
                    salir = true;
                }
            } else {
                salir = true;
            }  
        }
    }
    
    public Comparable recuperarCima() {
        Comparable r = null;
        if(this.ultimo != 0) {
            r = this.heap[1];
        }
        return r;
    }
    
    public boolean esVacio() { 
        return this.ultimo == 0;
    }
}
