/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Lista2;

/**
 *
 * @author agustin.caceres
 */
public class ArbolGen {
    private NodoGen raiz;
    
    public ArbolGen() {
        this.raiz = null;
    }
    
    private NodoGen obtenerNodo(NodoGen n, Object buscado) {
        /* metodo PRIVADO que busca un elemento y devuelve el nodo */
        NodoGen resultado = null;
        if (n != null) {
            if (n.getElemento().equals(buscado)) {
                //si el buscado es n, lo devuelve
                resultado = n;
            } else {
                //no es el buscado: busca primero en el hijo izquierdo
                resultado = obtenerNodo(n.getHijoIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getHermanoDerecho(), buscado);
                }
            }
        }
        return resultado;
    }
    
    public Object padre(Object buscado) {
        Object elemPadre = null;        
        if (this.raiz != null) {
            if (!(this.raiz.getElemento().equals(buscado))) {
                NodoGen nPadre = obtenerPadre(this.raiz, buscado);
                if(nPadre != null) {
                    elemPadre = nPadre.getElemento();
                }
            }
        }
        return elemPadre;
    }
    
    private NodoGen obtenerPadre(NodoGen n, Object buscado) {
        //Dado un elemento retorna el nodo padre del nodo que contiene ese elemento
        NodoGen nPadre = null; 
        if(n != null) {            
            nPadre = obtenerPadre(n.getHijoIzquierdo(), buscado);
            if(nPadre == null) {
                NodoGen hijo = n.getHijoIzquierdo(); 
                while((hijo!=null)) {
                    if(hijo.getElemento().equals(buscado)) { 
                        nPadre = n;      
                        hijo = null;  
                    } else {          
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            }                   
        }
        return  nPadre;
    }   
 
    public boolean insertar(Object newElem, Object elemPadre) {
        boolean exito = true;
        if(this.raiz == null) {
            this.raiz = new NodoGen(newElem, null, null);
        } else {
            NodoGen nPadre = obtenerNodo(this.raiz, elemPadre);
            if(nPadre != null) {
                NodoGen nuevo = new NodoGen(newElem,null,null);
                   if(nPadre.getHijoIzquierdo() == null) {
                       nPadre.setHijoIzquierdo(nuevo);
                   } else {
                       nuevo.setHermanoDerecho(nPadre.getHijoIzquierdo());
                       nPadre.setHijoIzquierdo(nuevo);
                   }
            } else {
                exito = false;
            }
        }
        return exito;
    }
    
    public boolean pertenece(Object buscado) {
        return perteneceAux(this.raiz, buscado);
    }
    
    private boolean perteneceAux(NodoGen n, Object buscado) {
        boolean per = false;
        if(n != null) {
            if (n.getElemento().equals(buscado)) {
                per = true;
            } else {
                if(n.getHijoIzquierdo() != null) {     
                    per = perteneceAux(n.getHijoIzquierdo(), buscado);
                    if(!per) {
                        per = perteneceAux(n.getHermanoDerecho(), buscado);
                    }   
                }   
            }                
        }
        return per;
    }
    
    public Lista ancestros(Object elem) {
        Lista lis = new Lista();
        ancestrosAux(this.raiz, lis, elem);
        lis.eliminar(1);
        return lis;
    }
    private void ancestrosAux(NodoGen n, Lista lis, Object elem) {
        if (n != null) {
            if (n.getElemento().equals(elem)) {
                lis.insertar(n.getElemento(), lis.longitud() + 1);
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && lis.esVacia()) {
                    ancestrosAux(hijo, lis, elem);
                    hijo = hijo.getHermanoDerecho();
                }
                if (!lis.esVacia()) {
                    lis.insertar(n.getElemento(), lis.longitud() + 1);
                }
            }
        }
    }
    
    public boolean esVacio() {
        return this.raiz==null;
    }
    
    public int altura() {
        int alt = -1;
        if(this.raiz != null) {
            alt = alturaNodo(this.raiz);
        }
        return alt;
    }

    private int alturaNodo(NodoGen nodo) {
        int alt = 0;
       if(nodo.getHijoIzquierdo() != null) {     
            int aux = 1;
            alt = alturaNodo(nodo.getHijoIzquierdo())+1;
            NodoGen hijo = nodo.getHijoIzquierdo().getHermanoDerecho();
            while(hijo != null) {                
                aux = alturaNodo(hijo);                   
                hijo = hijo.getHermanoDerecho();        
            }  
            if(aux>alt) {
                alt = aux;                
            }
        }   
        return alt;
    }
    
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }
    
    private Lista listarPreordenAux(NodoGen n, Lista lis) {
        if (n != null) {
            lis.insertar(n.getElemento(), lis.longitud()+1);
            NodoGen hijo = n.getHijoIzquierdo();  
            while (hijo != null) {   
                listarPreordenAux(hijo, lis);   
                hijo = hijo.getHermanoDerecho();
            }
        }
        return lis;  
        
    }
      
    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }
    
    private Lista listarInordenAux(NodoGen n, Lista lis) {
        if (n != null) {
            listarInordenAux(n.getHijoIzquierdo(),lis);
            lis.insertar(n.getElemento(), lis.longitud()+1);         
            if(n.getHijoIzquierdo() != null) {   
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();     
                while (hijo != null) {         
                    listarInordenAux(hijo, lis);                      
                    hijo = hijo.getHermanoDerecho();    
                }             
            }
        
        }
        return lis;          
    }
    
    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }  
    
    private void listarPosordenAux(NodoGen n, Lista lis) {
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                listarPosordenAux(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
            lis.insertar(n.getElemento(), lis.longitud() + 1);
        }
    }
    
    public Lista listarPorNiveles() {
        Lista lis = new Lista();
        Cola col = new Cola();
        NodoGen aux = this.raiz;
        col.poner(aux);
        while (!(col.esVacia())) {
            NodoGen nuevo = (NodoGen) col.obtenerFrente();
            lis.insertar(nuevo.getElemento(), lis.longitud() + 1);
            aux = (NodoGen) col.obtenerFrente();
            col.sacar();
            aux = aux.getHijoIzquierdo();
            while (aux != null) {
                col.poner(aux);
                aux = aux.getHermanoDerecho();
            }
        }
        return lis;
    }
    
    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        if(this.raiz != null) {
            clon.raiz = cloneAux(this.raiz);
        }
        return clon;
    }
    
    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nuevo;
        nuevo = new NodoGen(nodo.getElemento(),null,null);
        if(nodo.getHijoIzquierdo() != null) {
            nuevo.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo()));
        }
        if(nodo.getHermanoDerecho() != null) {
            nuevo.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }
        return nuevo;
    }  
    
    public void vaciar() {
         this.raiz = null;
    }
    
    public String toString() {
        return toStringAux(this.raiz);
    }   
    
    private String toStringAux(NodoGen nodo) {
        String cad = "\n";
        if (nodo != null) {
            cad += "[Nodo: " + nodo.getElemento() + ", Hijos: ";
            
            NodoGen hijo = nodo.getHijoIzquierdo();
            if (hijo == null) {
                cad += "-";
            }
            while (hijo != null) {
                cad += hijo.getElemento();
                hijo = hijo.getHermanoDerecho();
                if (hijo != null) {
                    cad += ", ";
                }
            }
            cad += "]";
            hijo = nodo.getHijoIzquierdo();
            while (hijo != null) {
                cad += toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return cad;
    }
    
    public boolean sonFrontera(Lista lis) {      
        boolean exito;
        if (this.raiz == null || lis.esVacia()) {
            exito = false;
        } else {
            exito =  sonFronteraAux(this.raiz, lis);
        }
        return exito;
    } 
    
    private boolean sonFronteraAux(NodoGen n, Lista lis) {
        boolean exito = true;
        if (n.getHijoIzquierdo() == null) {
            if (lis.localizar(n.getElemento()) < 0) {
                exito = false;
            }
        } else {
            NodoGen hijo = n.getHijoIzquierdo();
            while (exito && hijo.getHermanoDerecho() != null) {
                exito = sonFronteraAux(hijo, lis);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return exito;
    }
    
    public int nivel(Object e) {
        return nivelAux(this.raiz, e);
    }
    
    private int nivelAux(NodoGen n, Object elemento) {
        int niv = -1;
        if(n != null) {
            if (n.getElemento().equals(elemento)) {
                niv = 0;
            } else {                
                NodoGen aux = n.getHijoIzquierdo();              
                while(aux != null) {             
                    niv = nivelAux(aux, elemento); 
                    if(niv != -1) {
                        aux = null;
                    } else {
                        aux = aux.getHermanoDerecho();
                    }
                }  
                if (niv > -1) {
                    niv++;
                }
            }            
        }     
        return niv;
    }
    
    public int grado() {
        int grado;
        if(this.raiz != null) {
            grado = gradoAux(this.raiz);
        } else {
            grado = -1;
        }
        return grado;
    }
    
    private int gradoAux(NodoGen n) {
        int grado = 0;
        NodoGen hijo = n.getHijoIzquierdo();
        NodoGen nAux = n.getHijoIzquierdo();
        int aux;
        while (hijo != null) {
            grado++;
            hijo = hijo.getHermanoDerecho();
        }
        while (nAux != null) {
            aux = gradoAux(nAux);
            if (aux > grado) {
                grado = aux;
            }
            nAux = nAux.getHermanoDerecho();
        }
        return grado;
    }
    
    public int gradoSubarbol(Object elem) {
        int grado;
        NodoGen raizSubArbol = obtenerNodo(this.raiz, elem);
        if(raizSubArbol != null) {
            grado = gradoAux(raizSubArbol);
        } else {
            grado = -1;
        }
        return grado;
    }  
    
    public boolean equals(ArbolGen unArbol) {
        return equalsAux(this.raiz, unArbol.raiz);
    }
    
    public boolean equalsAux(NodoGen n, NodoGen x) {
        boolean exito;
        if (n == null) {
            if (x == null) {
                exito = true;
            } else {
                exito = false;
            }
        } else {
            if (x != null) {
                if (n.getElemento().equals(x.getElemento())) {
                    NodoGen nHijo, xHijo;
                    nHijo = n.getHijoIzquierdo();
                    xHijo = x.getHijoIzquierdo();
                    exito = equalsAux(nHijo, xHijo);
                    while (exito != false && nHijo != null && xHijo != null) {
                        nHijo = nHijo.getHermanoDerecho();
                        xHijo = xHijo.getHermanoDerecho();
                        exito = equalsAux(nHijo, xHijo);
                    }
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

}
