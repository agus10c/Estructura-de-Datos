
package Buffread;


public class ArbolAVLDicc {
    private NodoAVLDicc raiz;
    
    public ArbolAVLDicc() {
        this.raiz = null;
    }
    
    public int balance(NodoAVLDicc n) {
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
    
    private void balancear(NodoAVLDicc n) {
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
    
    private void rotarIzquierda(NodoAVLDicc r) {
        NodoAVLDicc h = r.getDerecho();
        NodoAVLDicc temp = r.getIzquierdo();
        h.setIzquierdo(r);
        r.setDerecho(temp);
    }
    
    private void rotarDerecha(NodoAVLDicc r) {
        NodoAVLDicc h = r.getIzquierdo();
        NodoAVLDicc temp = r.getDerecho();
        h.setDerecho(r);
        r.setIzquierdo(temp);
    }
    
    public boolean insertar(Comparable clave, Object elemento) {
        /*Recibe un elemento y lo agrega en el árbol de manera ordenada. Si el elemento ya se encuentra
        en el árbol no se realiza la inserción. Devuelve verdadero si el elemento se agrega a la estructura y
        falso en caso contrario.*/
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoAVLDicc(clave, elemento, null, null);
        } else {
            exito = insertarAux(this.raiz, clave, elemento);
        }
        return exito;
    }
    
    private boolean insertarAux(NodoAVLDicc n, Comparable clave, Object elemento) {
        boolean exito = true;
        if(clave.compareTo(n.getClave())==0) {
            exito = false;
        } else if(clave.compareTo(n.getClave())<0){
            if(n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), clave, elemento);
                balancear(n);
                n.recalcularAltura();
            } else {
                n.setIzquierdo(new NodoAVLDicc(clave, elemento, null, null));
            }
        } else {
            if(n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), clave, elemento);
                balancear(n);
                n.recalcularAltura();
            } else {
                n.setDerecho(new NodoAVLDicc(clave, elemento, null, null));
            }
        }
        return exito;
    }
    
    public boolean eliminar(Comparable clave) {
        return eliminarAux(this.raiz, clave, null);
    }
    
    private boolean eliminarAux(NodoAVLDicc n, Comparable clave, NodoAVLDicc nPadre) {
        boolean exito = true;
        if (n != null) {
            if (clave.compareTo(n.getClave()) == 0) {
                if (n.getIzquierdo() == null && n.getDerecho() == null) {
                    eliminarCaso1(n, clave, nPadre);
                } else if (n.getIzquierdo() != null && n.getDerecho() != null) {
                    eliminarCaso3(n, clave, nPadre);
                } else {
                    eliminarCaso2(n, clave, nPadre);
                }
            } else if (clave.compareTo(n.getClave()) < 0) {
                exito = eliminarAux(n.getIzquierdo(), clave, n);
                if (exito) {
                    balancear(n);
                    n.recalcularAltura();
                }
            } else {
                exito = eliminarAux(n.getDerecho(), clave, n);
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
    
    private void eliminarCaso1(NodoAVLDicc n, Comparable clave, NodoAVLDicc nPadre) {
        //Es hoja
        if (nPadre == null) {
            this.raiz = null;
        } else {
            if (clave.compareTo(n.getClave()) < 0) {
                nPadre.setIzquierdo(null);
            } else {
                nPadre.setDerecho(null);
            }
        }
    }
    
    private void eliminarCaso2(NodoAVLDicc n, Comparable clave, NodoAVLDicc nPadre) {
        //Tiene un solo hijo
        if (n.getIzquierdo() != null) {
            if (nPadre == null) {
                this.raiz = null;
            } else {
                if (clave.compareTo(n.getClave()) < 0) {
                    nPadre.setIzquierdo(n.getIzquierdo());
                } else {
                    nPadre.setDerecho(n.getIzquierdo());
                }
            }
        } else {
            if (nPadre == null) {
                this.raiz = null;
            } else {
                if (clave.compareTo(n.getClave()) < 0) {
                    nPadre.setIzquierdo(n.getDerecho());
                } else {
                    nPadre.setDerecho(n.getDerecho());
                }
            }
        }
    }
    
    private void eliminarCaso3(NodoAVLDicc n, Comparable clave, NodoAVLDicc nPadre) {
        //tiene ambos hijos
        NodoAVLDicc candidato = n.getIzquierdo();
        if (candidato.getDerecho() != null) {
            NodoAVLDicc padreCandidato = n.getIzquierdo();
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
    
    public boolean pertenece(Comparable clave) {
        return perteneceAux(this.raiz, clave);
    }
    
    private boolean perteneceAux(NodoAVLDicc n, Comparable clave) {
        boolean per = false;
        if(clave.compareTo(n.getClave())==0) {
            per = true;
        } else {
            if(clave.compareTo(n.getClave())<0) {
                if(n.getIzquierdo()!=null) {
                    perteneceAux(n.getIzquierdo(), clave);
                }
            } else {
                if(n.getDerecho()!=null) {
                    perteneceAux(n.getDerecho(), clave);
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
    
    private void listarAux(NodoAVLDicc nodo, Lista lis) {
        if(nodo != null) {
            listarAux(nodo.getIzquierdo(),  lis);
            lis.insertar(nodo.getClave(), lis.longitud()+1);  
            listarAux(nodo.getDerecho(), lis);
        }
    }
    
    public Lista listarRango(Comparable claveMin, Comparable claveMax) {
        Lista lis = new Lista();
        listarRangoAux(this.raiz,lis, claveMin, claveMax);
        return lis;
    }
    
    private void listarRangoAux(NodoAVLDicc nodo, Lista lis, Comparable elemMin, Comparable elemMax) {
        if (nodo != null) {
            if (elemMin.compareTo(nodo.getClave()) > 0) {
                listarAux(nodo.getDerecho(), lis);
            }
            if (elemMin.compareTo(nodo.getClave()) <= 0 && elemMax.compareTo(nodo.getClave()) >= 0) {
                lis.insertar(nodo.getElemento(), lis.longitud() + 1);
            }
            if (elemMax.compareTo(nodo.getClave()) < 0) {
                listarAux(nodo.getIzquierdo(), lis);
            }
        }
    }
    
}    