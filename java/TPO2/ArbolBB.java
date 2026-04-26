/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

import lineales.dinamicas.Lista;

/**
 *
 * 
 */
public class ArbolBB {

    private NodoABB raiz;

    //constructor
    public ArbolBB() {
        this.raiz = null;
    }

    public boolean insertar(Comparable elem) {
        boolean exito = true;
        if (this.raiz == null) {
            this.raiz = new NodoABB(elem, null, null);
        } else {
            exito = insertarAux(this.raiz, elem);
        }
        return exito;
    }

    private boolean insertarAux(NodoABB n, Comparable elem) {
        boolean exito = true;
        if (elem.compareTo(n.getElemento()) == 0) {
            //Reporta error:elemento repetido
            exito = false;
        } else if (elem.compareTo(n.getElemento()) < 0) {
            if (n.getIzquierdo() != null) {
                exito = insertarAux(n.getIzquierdo(), elem);
            } else {
                n.setIzquierdo(new NodoABB(elem, null, null));
            }
        } else {
            if (n.getDerecho() != null) {
                exito = insertarAux(n.getDerecho(), elem);
            } else {
                n.setDerecho(new NodoABB(elem, null, null));
            }
        }
        return exito;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public boolean pertenece(Comparable elem) {
        return obtenerNodo(this.raiz, elem) != null;
    }

    private NodoABB obtenerNodo(NodoABB n, Comparable buscado) {
        //metodo PRIVADO que busca el elemento y devuelve el nodo que
        //lo contiene. Si no se encuentra buscado devuelve null
        NodoABB resultado = null;
        if (n != null) {
            if (n.getElemento().compareTo(buscado) == 0) {
                //si el buscado es n, lo devuelve
                resultado = n;
            } else {
                //no es el buscado: busca el primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoABB nodo, Lista lis) {

        if (nodo != null) {

            listarInordenAux(nodo.getIzquierdo(), lis);

            lis.insertar(nodo.getElemento(), lis.longitud() + 1);

            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public Comparable minimoElem() {
        Comparable temp = null;
        if (!esVacio()) {
            temp = minimoAux(this.raiz);
        }
        return temp;
    }

    private Comparable minimoAux(NodoABB n) {
        Comparable elem;
        if (n.getIzquierdo() != null) {
            elem = minimoAux(n.getIzquierdo());
        } else {
            elem = n.getElemento();
        }
        return elem;
    }

    public Comparable maximoElem() {
        Comparable elem = null;
        if (this.raiz != null) {
            NodoABB aux = this.raiz;
            while (aux.getIzquierdo() != null) {
                aux = aux.getIzquierdo();
            }
            elem = aux.getElemento();
        }
        return elem;
    }

    //caso 1: eliminar 1 hoja
    //caso 2: eliminar un nodo que tiene un solo hijo
    //caso 3: eliminar un nodo que tiene 2 hijos
    public boolean eliminar(Comparable elemento) {
        boolean exito = false;
        if (this.raiz != null) {
            exito = eliminarAux(this.raiz, elemento, null);
        }
        return exito;
    }

    private boolean eliminarAux(NodoABB n, Comparable elem, NodoABB padre) {
        boolean exito = false;
        if (n.getElemento().compareTo(elem) == 0) {
            exito = true;
            if (n.getIzquierdo() == null && n.getDerecho() == null) {
                caso1(n, padre);
            } else {
                if (n.getIzquierdo() != null && n.getDerecho() != null) {
                    //caso3;
                } else {
                    if (n.getIzquierdo() != null) {
                        caso2(n, padre, n.getIzquierdo());
                    } else {
                        caso2(n, padre, n.getDerecho());
                    }
                }
            }

        } else {
            if (n.getElemento().compareTo(elem) > 0) {
                eliminarAux(n.getIzquierdo(), elem, n);
            } else {
                eliminarAux(n.getDerecho(), elem, n);
            }
        }
        return exito;
    }

    private void caso1(NodoABB n, NodoABB padre) {
        if (padre == null) {
            this.raiz = null;
        } else {
            if (padre.getIzquierdo().getElemento().compareTo(n) == 0) {
                padre.setIzquierdo(null);
            } else {
                padre.setDerecho(null);
            }
        }
    }

    private void caso2(NodoABB n, NodoABB padre, NodoABB hijo) {
        if (padre == null) {
            if (this.raiz.getIzquierdo() != null) {
                this.raiz = n.getIzquierdo();
            } else {
                this.raiz = n.getDerecho();
            }
        } else {
            if (padre.getIzquierdo().getElemento().compareTo(n) == 0) {
                padre.setIzquierdo(hijo);
            } else {
                padre.setDerecho(hijo);
            }
        }
    }

    private void caso3() {

    }

    public String toString() {

        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoABB nodo) {
        String cadenaAux = "", cad = "Arbol vacio";
        if (nodo != null) {
            cad = "";
            cad += "\n" + nodo.getElemento() + " ";
            if (nodo.getIzquierdo() != null) {
                cad += "HI: " + nodo.getIzquierdo().getElemento() + " ";
            } else {
                cad += "HI: - ";
            }
            if (nodo.getDerecho() != null) {
                cad += "HD: " + nodo.getDerecho().getElemento() + "\n";
            } else {
                cad += "HD: - \n";
            }

            if (nodo.getIzquierdo() != null) {
                cadenaAux = toStringAux(nodo.getIzquierdo());
                cad += cadenaAux;
            }
            if (nodo.getDerecho() != null) {
                cadenaAux = toStringAux(nodo.getDerecho());
                cad += cadenaAux;
            }

        }
        return cad;
    }

    

}
