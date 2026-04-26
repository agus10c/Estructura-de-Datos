/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

/**
 *
 * @author acace
 */
public class NodoAVL {
    private Comparable elem;
    private NodoAVL izquierdo;
    private NodoAVL derecho;
    private int altura;
    
    public NodoAVL(Comparable elem, NodoAVL izquierdo, NodoAVL derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    
    public Comparable getElemento() {
        return this.elem;
    }
    
    public NodoAVL getIzquierdo() {
        return this.izquierdo;
    }
    
    public NodoAVL getDerecho() {
        return this.derecho;
    }
    
    public void setElemento(Comparable elem) {
        this.elem = elem;
    }
    
    public void setIzquierdo(NodoAVL izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public void setDerecho(NodoAVL derecho) {
        this.derecho = derecho;
    }
    
    public int getAltura() {
        return this.altura;
    }
    
    public void recalcularAltura() {
        if(this.izquierdo!=null) {
            if(this.derecho!=null) {
                if(this.derecho.getAltura()<this.izquierdo.getAltura()) {
                    this.altura = this.izquierdo.getAltura()+1;
                } else {
                    this.altura = this.derecho.getAltura()+1;
                }
            } else {
                this.altura = this.izquierdo.getAltura()+1;
            }
        } else {
            if(this.derecho!=null) {
                this.altura = this.derecho.getAltura()+1;
            } else {
                this.altura = 0;
            }
        }
    }
    
}
