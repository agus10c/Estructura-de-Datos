/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Lista2;

/**
 *
 * @author acace
 */
public class ArbolBB {
    private NodoABB raiz;
    
    public ArbolBB() {
        this.raiz = null;
    }
    
    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if(this.raiz == null) {
            this.raiz = new NodoABB(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }
    
    private boolean insertarAux(NodoABB n, Comparable elem) {
        boolean exito = true;
        if(elem.compareTo(n.getElemento())==0) {
            exito = false;
        } else if(elem.compareTo(n.getElemento())<0){
            if(n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elem);
            } else {
                n.setIzquierdo(new NodoABB(elem, null, null));
            }
        } else {
            if(n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), elem);
            } else {
                n.setDerecho(new NodoABB(elem, null, null));
            }
        }
        return exito;
    }
    
    public boolean eliminar(Comparable elem) {
        return eliminarAux(this.raiz, elem, null);
    }
    
    private boolean eliminarAux(NodoABB n, Comparable elem, NodoABB nPadre) {
        boolean exito = true;
        if (n != null) {
            if (elem.compareTo(n.getElemento()) == 0) {
                if (n.getIzquierdo() == null && n.getDerecho() == null) {
                    eliminarCaso1(n, elem, nPadre);
                } else if (n.getIzquierdo() != null && n.getDerecho() != null) {
                    eliminarCaso3(n, elem, nPadre);
                } else {
                    eliminarCaso2(n, elem, nPadre);
                }
            } else if (elem.compareTo(n.getElemento()) < 0) {
                exito = eliminarAux(n.getIzquierdo(), elem, n);
            } else {
                exito = eliminarAux(n.getDerecho(), elem, n);
            }
        } else {
            exito = false;
        }
        return exito;
    }
    
    private void eliminarCaso1(NodoABB n, Comparable elem, NodoABB nPadre) {
        //Es hoja
        if (nPadre == null) {
            this.raiz = null;
        } else {
            if (elem.compareTo(n.getElemento()) < 0) {
                nPadre.setIzquierdo(null);
            } else {
                nPadre.setDerecho(null);
            }
        }
    }
    
    private void eliminarCaso2(NodoABB n, Comparable elem, NodoABB nPadre) {
        //Tiene un solo hijo
        if (n.getIzquierdo() != null) {
            if (nPadre == null) {
                this.raiz = null;
            } else {
                if (elem.compareTo(n.getElemento()) < 0) {
                    nPadre.setIzquierdo(n.getIzquierdo());
                } else {
                    nPadre.setDerecho(n.getIzquierdo());
                }
            }
        } else {
            if (nPadre == null) {
                this.raiz = null;
            } else {
                if (elem.compareTo(n.getElemento()) < 0) {
                    nPadre.setIzquierdo(n.getDerecho());
                } else {
                    nPadre.setDerecho(n.getDerecho());
                }
            }
        }
    }
    
    private void eliminarCaso3(NodoABB n, Comparable elem, NodoABB nPadre) {
        //Tiene ambos hijos
        NodoABB candidato = n.getIzquierdo();
        if (candidato.getDerecho() != null) {
            NodoABB padreCandidato = n.getIzquierdo();
            while (candidato.getDerecho() != null) {
                padreCandidato = candidato;
                candidato = candidato.getDerecho();
            }
            n.setElemento(candidato.getElemento());
            padreCandidato.setDerecho(null);
        } else {
            n.setElemento(candidato.getElemento());
            n.setIzquierdo(candidato.getIzquierdo());
        }
    }
    
    public boolean pertenece(Comparable elem) {
        boolean per;
        if(this.raiz != null) {
            per = perteneceAux(this.raiz, elem);
        } else {
            per = false;
        }
        return per;
    }
    
    private boolean perteneceAux(NodoABB n, Comparable elem) {
        boolean per = false;
        if(elem.compareTo(n.getElemento())==0) {
            per = true;
        } else {
            if(elem.compareTo(n.getElemento())<0) {
                if(n.getIzquierdo()!=null) {
                    perteneceAux(n.getIzquierdo(), elem);
                }
            } else {
                if(n.getDerecho()!=null) {
                    perteneceAux(n.getDerecho(), elem);
                }
            }          
        }
        return per;
    }
    
    public boolean esVacio() {
        return this.raiz==null;
    }
    
    public Lista listar() {
        Lista lis = new Lista();
        listarAux(this.raiz,lis);
        return lis;
    }
    
    private void listarAux(NodoABB nodo, Lista lis) {
        if(nodo != null) {
            listarAux(nodo.getIzquierdo(),  lis);
            lis.insertar(nodo.getElemento(), lis.longitud()+1);  
            listarAux(nodo.getDerecho(), lis);
        }
    }
    
    public Lista listarRango(Comparable elemMin, Comparable elemMax) {
        Lista lis = new Lista();
        listarRangoAux(this.raiz,lis, elemMin, elemMax);
        return lis;
    }
    
    private void listarRangoAux(NodoABB nodo, Lista lis, Comparable elemMin, Comparable elemMax) {
        if (nodo != null) {
            if (elemMin.compareTo(nodo.getElemento()) > 0) {
                listarAux(nodo.getDerecho(), lis);
            }
            if (elemMin.compareTo(nodo.getElemento()) <= 0 && elemMax.compareTo(nodo.getElemento()) >= 0) {
                lis.insertar(nodo.getElemento(), lis.longitud() + 1);
            }
            if (elemMax.compareTo(nodo.getElemento()) < 0) {
                listarAux(nodo.getIzquierdo(), lis);
            }
        }
    }
    
    public Object minimoElem() {
        NodoABB nMin = this.raiz;
        while(nMin.getIzquierdo() != null) {
            nMin = nMin.getIzquierdo();
        }
        return nMin.getElemento();
    }
    
    public Object maximoElem() {
        NodoABB nMax = this.raiz;
        while(nMax.getDerecho() != null) {
            nMax = nMax.getDerecho();
        }
        return nMax.getElemento();
    }
    
}
