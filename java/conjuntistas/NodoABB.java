/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

/**
 *
 * @author acace
 */
public class NodoABB {
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    public NodoABB(Comparable elem, NodoABB izquierdo, NodoABB derecho) {
        this.elem = elem;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    
    public Comparable getElemento() {
        return this.elem;
    }
    
    public NodoABB getIzquierdo() {
        return this.izquierdo;
    }
    
    public NodoABB getDerecho() {
        return this.derecho;
    }
    
    public void setElemento(Comparable elem) {
        this.elem = elem;
    }
    
    public void setIzquierdo(NodoABB izquierdo) {
        this.izquierdo = izquierdo;
    }
    
    public void setDerecho(NodoABB derecho) {
        this.derecho = derecho;
    }
    
}
