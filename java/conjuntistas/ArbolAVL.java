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
public class ArbolAVL {
    private NodoAVL raiz;
    
    public ArbolAVL() {
        this.raiz = null;
    }
    
    public int balance(NodoAVL n) {
        int balance;     
        if(n.getIzquierdo() != null) {     
            if(n.getDerecho() != null) {    
                balance = n.getIzquierdo().getAltura() - n.getDerecho().getAltura();             
            } else {
                balance = n.getIzquierdo().getAltura()+1; 
            }  
        } else {
            if(n.getDerecho() != null) { 
                balance = (-1) - n.getDerecho().getAltura(); 
            } else {
                balance = 0;
            }
        }
        return balance;
    }
    
    private void balancear(NodoAVL n) {
        if(balance(n) < -1) {
            if(balance(n.getDerecho()) <= 0) {         
                rotarIzquierda(n);      
            } else {  
                rotarDerecha(n.getDerecho());
                rotarIzquierda(n);
            }
        }
        if(balance(n) > 1) {
            if(balance(n.getIzquierdo()) >= 0) {  
                rotarDerecha(n.getIzquierdo());
            } else {
                rotarIzquierda(n);
                rotarDerecha(n);
            }
        }
    }
    
    private void rotarIzquierda(NodoAVL r) {
        NodoAVL h = r.getDerecho();
        NodoAVL temp = r.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
    }
    
    private void rotarDerecha(NodoAVL r) {
        NodoAVL h = r.getIzquierdo();
        NodoAVL temp = r.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
    }
    
    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if(this.raiz == null) {
            this.raiz = new NodoAVL(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }
    
    private boolean insertarAux(NodoAVL n, Comparable elem) {
        boolean exito = true;
        if(elem.compareTo(n.getElemento())==0) {
            exito = false;
        } else if(elem.compareTo(n.getElemento())<0){
            if(n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elem);
                balancear(n);
                n.recalcularAltura();
            } else {
                n.setIzquierdo(new NodoAVL(elem, null, null));
            }
        } else {
            if(n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), elem);
                balancear(n);
                n.recalcularAltura();
            } else {
                n.setDerecho(new NodoAVL(elem, null, null));
            }
        }
        return exito;
    }
    
    public boolean eliminar(Comparable elem) {
        return eliminarAux(this.raiz, elem, null);
    }
    
    private boolean eliminarAux(NodoAVL n, Comparable elem, NodoAVL nPadre) {
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
                if (exito) {
                    balancear(n);
                    n.recalcularAltura();
                }
            } else {
                exito = eliminarAux(n.getDerecho(), elem, n);
                if (exito) {
                    balancear(n);
                    n.recalcularAltura();
                }
            }
        } else {
            exito = false;
        }
        return exito;
    }
    
    private void eliminarCaso1(NodoAVL n, Comparable elem, NodoAVL nPadre) {
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
    
    private void eliminarCaso2(NodoAVL n, Comparable elem, NodoAVL nPadre) {
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
    
    private void eliminarCaso3(NodoAVL n, Comparable elem, NodoAVL nPadre) {
        //tiene ambos hijos
        NodoAVL candidato = n.getIzquierdo();
        if (candidato.getDerecho() != null) {
            NodoAVL padreCandidato = n.getIzquierdo();
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
        return perteneceAux(this.raiz, elem);
    }
    
    private boolean perteneceAux(NodoAVL n, Comparable elem) {
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
    
    private void listarAux(NodoAVL nodo, Lista lis) {
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
    
    private void listarRangoAux(NodoAVL nodo, Lista lis, Comparable elemMin, Comparable elemMax) {
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
        NodoAVL nMin = this.raiz;
        while(nMin.getIzquierdo() != null) {
            nMin = nMin.getIzquierdo();
        }
        return nMin.getElemento();
    }
    
    public Object maximoElem() {
        NodoAVL nMax = this.raiz;
        while(nMax.getDerecho() != null) {
            nMax = nMax.getDerecho();
        }
        return nMax.getElemento();
    }
}
