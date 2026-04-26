/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lineales.dinamicas;

/**
 *
 * @author acace
 */
public class Lista2 {
    private Nodo cabecera;
    public Lista2() {
        this.cabecera = null;
    }
    
    public boolean insertar(Object nuevoElem, int pos) {
        boolean exito = true;
        if (pos<1 || pos>this.longitud()+1) {
            exito = false;
        } else {
            if(pos==1) {
                this.cabecera = new Nodo(nuevoElem,this.cabecera);
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while(i<pos-1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
            }
        }
        return exito;
    }
    
    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos<1 || pos>this.longitud()) {
            exito = false;
        } else {        
            if(pos==1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
               Nodo aux = this.cabecera;             
               int i = 1;
               while(i<pos-1) {             
                   aux = aux.getEnlace();                  
                   i++;                
               }
               if(aux.getEnlace() != null) {
                   aux.setEnlace(aux.getEnlace().getEnlace());
               } else {
                   aux.setEnlace(null);
               }
                
            }  
        }
        return exito;
    }
    
    public Object recuperar(int pos) {
        Object r = null;
        if (pos>=1 && pos<=this.longitud()) {  
            Nodo aux = this.cabecera;
            int i = 1;
            while(i<pos) {       
                aux = aux.getEnlace();                  
                i++;                
            }
            r = aux.getElemento();
        } 
        return r;
    }
    
    public int localizar(Object elem) {
        int pos = 1;
        Nodo aux = this.cabecera;
        boolean continuar = true;
        while(continuar) {           
            if(aux != null) {
                if(aux.getElemento().equals(elem)) {
                    continuar = false;
                } else {
                    pos ++;
                    aux = aux.getEnlace();
                }
            } else {
                continuar = false;
                pos = -1;
            }         
        } 
        return pos;
    }
    
    public void vaciar() {
        cabecera = null;
    }
    
    public boolean esVacia() {
        return cabecera==null;
    }
    
    public int longitud() {
        int longitud = 0;
        Nodo aux = this.cabecera; 
        while(aux != null) {
            longitud++;
            aux = aux.getEnlace();
        }
        return longitud;
    }
    
    public Lista2 clone() {
        Lista2 clon = new Lista2();
        if(this.cabecera != null) {
            clon.cabecera = cloneAux(this.cabecera);
        }
        return clon;
    }
    
    private Nodo cloneAux(Nodo nodo) {
        Nodo nuevo;
        nuevo = new Nodo(nodo.getElemento(),null);
        if(nodo.getEnlace() != null) {
            nuevo.setEnlace(cloneAux(nodo.getEnlace()));
        } 
        return nuevo;
    }
    
    public String toString() {
        String cadena = "[";
        Nodo aux = this.cabecera;
            while(aux != null) {
                cadena = cadena+aux.getElemento(); 
                aux = aux.getEnlace();
                if (aux != null) {
                    cadena += ", ";
                }          
            }
            cadena += "]";
        return cadena;
    }
     
    public boolean verificarRepetidos() {   
        boolean exito = true;
        Object elem;             
        Nodo aux, n = this.cabecera;
        while(n != null && exito) {  
            elem = n.getElemento();
            aux = n.getEnlace();
            while(aux != null && exito) {      
                if(aux.getElemento().equals(elem)) {                 
                    exito = false;                     
                } 
                aux = aux.getEnlace();
            }
            n = n.getEnlace();
        }
        return exito;
    }
    
}
