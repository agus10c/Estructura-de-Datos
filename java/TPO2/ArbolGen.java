package jerarquicas;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;

/**
 * *********** Autores ***********
 * - Daniel Carrasco, FAI-2840
 * - Agustin Caceres FAI-2993
 * - Jonathan maximiliano cabrera, 108665
 */


public class ArbolGen {
    private NodoGen raiz;

    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elem, Object elemPadre) {
        boolean exito = false;
        if (this.raiz != null) {
            NodoGen nPadre = buscarNodo(this.raiz, elemPadre);
            if (nPadre != null) {
                NodoGen nuevo = new NodoGen(elem, null, null);
                if (nPadre.getHijoIzquierdo() == null) {
                    nPadre.setHijoIzquierdo(nuevo);
                } else {
                    NodoGen hijo = nPadre.getHijoIzquierdo();
                    while (hijo.getHermanoDerecho() != null) {
                        hijo = hijo.getHermanoDerecho();
                    }
                    hijo.setHermanoDerecho(nuevo);
                }
                exito = true;
            }
        } else {
            this.raiz = new NodoGen(elem, null, null);
            exito = true;
        }
        return exito;
    }

    private NodoGen buscarNodo(NodoGen n, Object elem) {
        NodoGen encontrado = null;
        if (n != null) {
            if (n.getElemento().equals(elem)) {
                encontrado = n;
            } else {
                if (n.getHijoIzquierdo() != null) {
                    encontrado = buscarNodo(n.getHijoIzquierdo(), elem);
                }
                if (encontrado == null) {
                    if (n.getHijoIzquierdo() != null) {
                        NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                        while (hijo != null && encontrado == null) {
                            encontrado = buscarNodo(hijo, elem);
                            hijo = hijo.getHermanoDerecho();
                        }
                    }
                }
            }
        }
        return encontrado;
    }

    public boolean pertenece(Object elem) {
        return buscarNodo(this.raiz, elem) != null;
    }

    public Lista ancestros(Object elem) {
        Lista salida = new Lista();
        listarAncestros(this.raiz, elem, salida, 1);
        return salida;
    }

    private boolean listarAncestros(NodoGen n, Object elem, Lista ls, int i) {
        boolean encontrado = false;
        if (n != null) {
            ls.insertar(n.getElemento(), i);
            if (n.getElemento().equals(elem)) {
                ls.eliminar(i);
                encontrado = true;
            } else {
                if (n.getHijoIzquierdo() != null) {
                    encontrado = listarAncestros(n.getHijoIzquierdo(), elem, ls, i + 1);
                }
                if (!encontrado) {
                    if (n.getHijoIzquierdo() != null) {
                        NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                        while (hijo != null && !encontrado) {
                            encontrado = listarAncestros(hijo, elem, ls, i + 1);
                            hijo = hijo.getHermanoDerecho();
                        }
                    }
                }
                if (!encontrado) {
                    ls.eliminar(i);
                }
            }
        }
        return encontrado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            altura = alturaAux(this.raiz);
        }
        return altura;
    }

    private int alturaAux(NodoGen n) {
        int alturaNodo = 0, alturaHIzq = 0, alturaHDer;
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                alturaHIzq = 1 + alturaAux(n.getHijoIzquierdo());
            }
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    alturaHDer = 1 + alturaAux(hijo);
                    if (alturaHIzq < alturaHDer) {
                        alturaHIzq = alturaHDer;
                    }
                    hijo = hijo.getHermanoDerecho();
                }
            }
            alturaNodo = alturaHIzq;
        }
        return alturaNodo;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        if (this.raiz != null) {
            nivel = nivelAux(this.raiz, elem);
            if (nivel != -1) {
                nivel--;
            }
        }
        return nivel;
    }

    private int nivelAux(NodoGen n, Object elem) {
        int nivelNodo = -1;
        if (n != null) {
            if (n.getElemento().equals(elem)) {
                nivelNodo = 0;
            } else {
                if (n.getHijoIzquierdo() != null) {
                    nivelNodo = nivelAux(n.getHijoIzquierdo(), elem);
                    if (nivelNodo == -1) {
                        NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                        while (hijo != null && nivelNodo == -1) {
                            nivelNodo = nivelAux(hijo, elem);
                            hijo = hijo.getHermanoDerecho();
                        }
                    }
                }
            }
            if (nivelNodo != -1) {
                nivelNodo++;
            }
        }
        return nivelNodo;
    }

    public Object padre(Object elem) {
        Object elemPadre = null;
        if (this.raiz != null) {
            if (!this.raiz.getElemento().equals(elem)) {
                elemPadre = padreAux(this.raiz, elem);
            }
        }
        return elemPadre;
    }

    private Object padreAux(NodoGen n, Object elem) {
        Object elemPadre = null;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && elemPadre == null) {
                if (hijo.getElemento().equals(elem)) {
                    elemPadre = n.getElemento();
                }
                hijo = hijo.getHermanoDerecho();
            }
            if (elemPadre == null) {
                hijo = n.getHijoIzquierdo();
                while (hijo != null && elemPadre == null) {
                    elemPadre = padreAux(hijo, elem);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return elemPadre;
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista ls) {
        ls.insertar(n.getElemento(), ls.longitud() + 1);
        if (n.getHijoIzquierdo() != null) {
            listarPreordenAux(n.getHijoIzquierdo(), ls);
        }

        if (n.getHijoIzquierdo() != null) {
            NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
            while (hijo != null) {
                listarPreordenAux(hijo, ls);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                listarInordenAux(n.getHijoIzquierdo(), ls);
            }
            ls.insertar(n.getElemento(), ls.longitud() + 1);
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarInordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        listarPosordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPosordenAux(NodoGen n, Lista ls) {
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                listarPosordenAux(n.getHijoIzquierdo(), ls);
            }
            if (n.getHijoIzquierdo() != null) {
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                while (hijo != null) {
                    listarPosordenAux(hijo, ls);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            ls.insertar(n.getElemento(), ls.longitud() + 1);
        }
    }

    public Lista listarPorNiveles() {
        Lista salida = new Lista();
        listarPorNivelAux(this.raiz, salida);
        return salida;
    }

    private void listarPorNivelAux(NodoGen n, Lista ls) {
        Cola q1 = new Cola();
        q1.poner(n);
        while (!q1.esVacia()) {
            NodoGen nActual = (NodoGen) q1.obtenerFrente();
            q1.sacar();
            ls.insertar(nActual.getElemento(), ls.longitud() + 1);
            NodoGen hijo = nActual.getHijoIzquierdo();
            while (hijo != null) {
                q1.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public ArbolGen clone() {
        ArbolGen clon = new ArbolGen();
        clon.raiz = cloneAux(this.raiz);
        return clon;
    }

    private NodoGen cloneAux(NodoGen n) {
        NodoGen nuevo = null;
        if (n != null) {
            nuevo = new NodoGen(n.getElemento(), null, null);
            if (n.getHijoIzquierdo() != null) {
                nuevo.setHijoIzquierdo(cloneAux(n.getHijoIzquierdo()));
            }
            if (n.getHermanoDerecho() != null) {
                nuevo.setHermanoDerecho(cloneAux(n.getHermanoDerecho()));
            }
        }
        return nuevo;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            // visita del nodo n
            s += n.getElemento().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElemento().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }

            //comienza recorrido de los hijos de n llamando recursivamente
            //para que cada hijo agregue su subcadena a la general
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }


    public int grado() {
        return gradoAux(this.raiz);
    }

    private int gradoAux(NodoGen n) {
        int grado = -1;
        if (n != null) {
            if (n.getHijoIzquierdo() != null) {
                int gAux = gradoAux(n.getHijoIzquierdo());
                NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                int aux = 1;
                while (hijo != null) {
                    aux++;
                    grado = gradoAux(hijo);
                    hijo = hijo.getHermanoDerecho();
                }
                if (grado < aux) {
                    grado = aux;
                }
                if (grado < gAux) {
                    grado = gAux;
                }
            } else {
                grado = 0;
            }
        }
        return grado;
    }

    public int gradoSubarbol(Object elem) {
        return gradoAux(buscarNodo(this.raiz, elem));
    }


    public Lista frontera() {
        Lista salida = new Lista();
        fronteraAux(this.raiz, salida, 1);
        return salida;
    }

    private void fronteraAux(NodoGen n, Lista lis, int i) {
        if (n != null) {
            if (n.getHijoIzquierdo() == null) {
                lis.insertar(n.getElemento(), i);
                i++;
            } else {
                fronteraAux(n.getHijoIzquierdo(), lis, i);
            }

            if (n.getHermanoDerecho() != null) {
                fronteraAux(n.getHermanoDerecho(), lis, i);
            }
        }
    }

    public boolean verificarPatron(Lista lisPatron) {
        return verificarPatronAux(this.raiz, lisPatron, 1);
    }

    private boolean verificarPatronAux(NodoGen n, Lista lis, int i) {
        boolean valido = false;
        if (n != null) {
            if (n.getElemento().equals(lis.recuperar(i))) {
                if (n.getHijoIzquierdo() == null) {
                    valido = true;
                } else {
                    i++;
                    valido = verificarPatronAux(n.getHijoIzquierdo(), lis, i);
                }
            } else {
                if (n.getHermanoDerecho() != null) {
                    valido = verificarPatronAux(n.getHermanoDerecho(), lis, i);
                }
            }
        }
        return valido;
    }

    public Lista listaQueJustificaLaAltura() {
        Lista lisAltura;
        lisAltura = listarAltura(this.raiz, (new Lista()), (new Lista()), 1);
        return lisAltura;
    }

    private Lista listarAltura(NodoGen n, Lista lis, Lista lisMasLarga, int i) {
        if (n != null) {
            lis.insertar(n.getElemento(), i);
            if (n.getHijoIzquierdo() == null) {
                if (lisMasLarga.longitud() < lis.longitud()) {
                    lisMasLarga = lis;
                }
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null) {
                    lisMasLarga = listarAltura(hijo, lis, lisMasLarga, i++);
                    hijo = hijo.getHermanoDerecho();
                }
            }
            lis.eliminar(i);
        }
        return lisMasLarga;
    }

    public boolean equals(ArbolGen unArbol) {
        return equalsAux(this.raiz, unArbol.raiz);
    }

    private boolean equalsAux(NodoGen n, NodoGen n2) {
        boolean igual = true;
        if (n != null && n2 != null) {
            if (n.getElemento().equals(n2.getElemento())) {
                igual = equalsAux(n.getHijoIzquierdo(), n2.getHijoIzquierdo());
            } else {
                igual = false;
            }

            if (igual) {
                igual = equalsAux(n.getHermanoDerecho(), n2.getHermanoDerecho());
            }
        } else {
            if (n != null || n2 != null) {
                igual = false;
            }
        }
        return igual;
    }

    public boolean sonFrontera(Lista lista) {
        return sonFronteraAux(this.raiz, lista);
    }

    private boolean sonFronteraAux(NodoGen n, Lista lis) {
        boolean igual = true;
        if (n != null) {
            if (n.getHijoIzquierdo() == null) {
                if (lis.localizar(n.getElemento()) <= 0) {
                    igual = false;
                }
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null && igual) {
                    igual = sonFronteraAux(hijo, lis);
                    hijo = hijo.getHermanoDerecho();
                }
            }
        }
        return igual;
    }
    
    public boolean verificarCamino(Lista lis) {
        return verificarCaminoAux(this.raiz, lis, 1);
    } 
    
    private boolean verificarCaminoAux(NodoGen n, Lista lis, int pos) {
        boolean exito = true;
        if (!lis.esVacia()) {
            if (n != null) {
                if (n.getElemento().equals(lis.recuperar(pos))) {
                    if (n.getHijoIzquierdo() != null) {
                        exito = verificarCaminoAux(n.getHijoIzquierdo(), lis, pos++);
                        NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
                        while (!exito && hijo != null) {
                            exito = verificarCaminoAux(hijo, lis, pos++);
                            hijo = hijo.getHermanoDerecho();
                        }
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
