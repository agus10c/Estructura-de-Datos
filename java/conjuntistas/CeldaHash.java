/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

/**
 *
 * @author acace
 */
public class CeldaHash {
    private Object elem;
    private int estado;
    
    public CeldaHash() { 
        elem = null;
        estado = 0;
    }
    
    public Object getElem() {
        return this.elem;
    }
    
    public int getEstado() {
        return this.estado;
    }
    
    public void setElem(Object nuevoElem) {
        this.elem = nuevoElem;
    }
    
    public void setEstado(int nuevoEst) {
        this.estado = nuevoEst;
    }
}
