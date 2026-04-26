/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Lista2;

/**
 *
 * @author acace
 */
public class ArbolBin {
    private NodoArbol raiz;
    
    public ArbolBin() {
        this.raiz = null;
    }
    
    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        NodoArbol resultado = null; 
        if(n != null) {
            if(n.getElemento().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if(resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }
       
    public boolean insertar(Object nuevoElem, Object elemPadre, char lugar) {
        boolean exito = true;
        if(this.raiz == null) {
            this.raiz = new NodoArbol(nuevoElem, null, null);
        } else {
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);
            if(nPadre != null) {
                if(lugar=='I' && nPadre.getIzquierdo()==null) {
                    nPadre.setIzquierdo(new NodoArbol(nuevoElem, null, null));
                } else if(lugar=='D' && nPadre.getDerecho()==null) {
                    nPadre.setDerecho(new NodoArbol(nuevoElem, null, null));
                } else {
                    exito = false;
                }     
            } else {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean esVacio() {
        return this.raiz==null;
    }
    
    public Object padre(Object elem) {
        Object elemPadre = null;
        NodoArbol nPadre = obtenerNodoPadre(this.raiz,elem);
        if(nPadre != null) {
            elemPadre = nPadre.getElemento();
        } else {
            elemPadre = null;
        }      
        return elemPadre;
    }
    
    private NodoArbol obtenerNodoPadre(NodoArbol n, Object buscado) {
        //Dado un elemento retorna el nodo padre del nodo que contiene ese elemento
        NodoArbol nPadre = null; 
        if(n != null) {
            if(n.getIzquierdo() != null) {
                if(n.getIzquierdo().getElemento().equals(buscado)) {
                    nPadre = n;
                }
            } 
            if (nPadre == null) {
                if (n.getDerecho() != null) {
                    if (n.getDerecho().getElemento().equals(buscado)) {
                        nPadre = n;
                    }
                }
            }   
            if (nPadre == null) {
                nPadre = obtenerNodoPadre(n.getIzquierdo(), buscado);
                if (nPadre == null) {
                    nPadre = obtenerNodoPadre(n.getDerecho(), buscado);
                }
            }     
        }
        return  nPadre;
    }
    
    public int altura() {
        int alt;
        if(this.raiz != null ) {
            alt = alturaNodo(this.raiz);
        } else {
            alt = -1;
        }
        return alt;
    }
    
    private int alturaNodo(NodoArbol nodo) {
        int alt = 0, aux = 0;     
        if(nodo.getIzquierdo() != null) {
            alt = 1 + alturaNodo(nodo.getIzquierdo());
        } 
        if (nodo.getDerecho() != null) {
            aux = 1 + alturaNodo(nodo.getDerecho());
        }
        if(alt>aux) {
            alt = aux;
        }
        return alt;
    }
    
    public int nivel(Object elem) {
        return nivelAux(this.raiz, elem);
    }   
    
    public int nivelAux(NodoArbol n, Object elem) {
        int niv = -1;
        if(n != null) {
            if(n.getElemento().equals(elem)) {
                niv = 0;
            } else {
                niv = nivelAux(n.getIzquierdo(), elem);
                if(niv > -1) {
                    niv++;
                } else {
                    niv = nivelAux(n.getDerecho(), elem);
                    if(niv > -1) {
                        niv++;
                    }
                }     
            }
        }
        return niv;
    }
    
    public void vaciar() {
        this.raiz = null;
    }
    
    public ArbolBin clone() {
        ArbolBin clon = new ArbolBin();
        if(this.raiz != null) {
            clon.raiz = cloneAux(this.raiz);
        }
        return clon;
    }
    
    private NodoArbol cloneAux(NodoArbol nodo) {
        NodoArbol nuevo;
        nuevo = new NodoArbol(nodo.getElemento(),null,null);
        if(nodo.getIzquierdo() != null) {
            nuevo.setIzquierdo(cloneAux(nodo.getIzquierdo()));
        }
        if(nodo.getDerecho() != null) {
            nuevo.setDerecho(cloneAux(nodo.getDerecho()));
        }
        return nuevo;
    }
    
    public String toString() {
        String s = "";
        Cola col = new Cola();
        if (this.raiz != null) {
            col.poner(this.raiz);
            NodoArbol nodo;
            while (!(col.esVacia())) {
                nodo = (NodoArbol) col.obtenerFrente();
                col.sacar();
                s += "[Nodo: " + nodo.getElemento();
                if (nodo.getIzquierdo() != null) {
                    s += ", HI: " + nodo.getIzquierdo().getElemento();
                    col.poner(nodo.getIzquierdo());
                } else {
                    s += ", HI: null";
                }
                if (nodo.getDerecho() != null) {
                    s += ", HD: " + nodo.getDerecho().getElemento();
                    col.poner(nodo.getDerecho());
                } else {
                    s += ", HD: null";
                }
                s += "]" + "\n";
            }
        } else {
            s = "[]";
        }
        return s;
    }
    
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }
    
    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        if(nodo != null) {
            lis.insertar(nodo.getElemento(), lis.longitud()+1);
            listarPreordenAux(nodo.getIzquierdo(),lis);
            listarPreordenAux(nodo.getDerecho(),lis);
        }
    }
    
    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }
    
    private void listarInordenAux(NodoArbol nodo, Lista lis) {
        if(nodo != null) {
            listarInordenAux(nodo.getIzquierdo(),  lis);
            lis.insertar(nodo.getElemento(), lis.longitud()+1);  
            listarInordenAux(nodo.getDerecho(), lis);
        }
    }
    
    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }
    
    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if(nodo != null) {
            listarPosordenAux(nodo.getIzquierdo(),  lis); 
            listarPosordenAux(nodo.getDerecho(), lis);           
            lis.insertar(nodo.getElemento(), lis.longitud()+1); 
        }
    }
    
    public Lista listarPorNiveles() {
        NodoArbol nodo;
        Lista lis = new Lista();
        Cola col = new Cola();
        if(this.raiz != null) {
            col.poner(this.raiz);
        } 
        while(!(col.esVacia())) {
            nodo = (NodoArbol) col.obtenerFrente();
            col.sacar();
            lis.insertar(nodo.getElemento(), lis.longitud()+1); 
            if(nodo.getIzquierdo() != null) {
                col.poner(nodo.getIzquierdo());
            }
            if(nodo.getDerecho()!= null) {
                col.poner(nodo.getDerecho());
            }
        }
        return lis;
    }
    
    public Lista frontera() {
        Lista lis = new Lista();
        if(this.raiz != null) {
            lis = fronteraAux(this.raiz);
        }
        return lis ;
    }
    
    public Lista fronteraAux(NodoArbol n) {
        Lista lis = new Lista();       
        if(n.getIzquierdo() != null) {       
            int aux = 1;
            Lista lisAux = fronteraAux(n.getIzquierdo());
            while(aux<=lisAux.longitud()) {         
                lis.insertar(lisAux.recuperar(aux),lis.longitud()+1); 
                aux++;
            }   
        } else {
            if(n.getDerecho()== null) { 
                lis.insertar(n.getElemento(), lis.longitud()+1);
            }
        }   
        if(n.getDerecho() != null) { 
            int aux = 1;
            Lista lisAux = fronteraAux(n.getDerecho());
            while(aux<=lisAux.longitud()) {     
                lis.insertar(lisAux.recuperar(aux),lis.longitud()+1);
                aux++;
            }     
        }
        return lis;
    }
        
}
