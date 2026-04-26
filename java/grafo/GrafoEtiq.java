
package grafo;


public class GrafoEtiq {
    
    private NodoVert inicio;
    
    GrafoEtiq() {
        this.inicio = null; 
    }      
    
    private NodoVert ubicarVertice(Object buscado) {
        //Busca hasta encontrar el vertice buscado en la lista de vertice. 
        //Devuelve null si no lo encuentra.
        NodoVert aux = this.inicio;
        while (aux != null && !aux.getElem().equals(buscado)) {
            aux = aux.getSigVert();
        }
        return aux;
    }
    
    public boolean insertarVertice(Object nuevoVertice) {
        /* Dado un elemento de TipoVertice se lo agrega a la estructura controlando que no se inserten
        vértices repetidos. Si puede realizar la inserción devuelve verdadero, en caso contrario devuelve falso.*/
        boolean exito;
        NodoVert aux = this.ubicarVertice(nuevoVertice);
        if (aux == null) {
            this.inicio = new NodoVert(nuevoVertice, this.inicio);
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }
    
     public boolean eliminarVertice(Object vertice) {
        return eliminarVerticeAux(this.inicio, null, vertice);
    }

    private boolean eliminarVerticeAux(NodoVert nVertice, NodoVert nVerticeAnterior, Object vertice) {
        boolean exito = false;
        if (nVertice != null) {
            if (nVertice.getElem().equals(vertice)) {
                eliminarArcos(nVertice);
                if (nVerticeAnterior == null) {
                    this.inicio = nVertice.getSigVert();
                } else {
                    nVerticeAnterior.setSigVert(nVertice.getSigVert());
                }
                exito = true;
            } else {
                exito = eliminarVerticeAux(nVertice.getSigVert(), nVertice, vertice);
            }
        }
        return exito;
    }
    
    public boolean insertarArco(Object origen, Object destino, Object etiqueta) {
        /* Dados dos elementos de TipoVertice (origen y destino) agrega el arco en la estructura, sólo si
        ambos vértices ya existen en el grafo. Si puede realizar la inserción devuelve verdadero, en caso
        contrario devuelve falso.*/
        boolean exito = false;
        NodoVert aux = this.inicio;
        NodoVert nOrigen = null;
        NodoVert nDestino = null;
        while (((nOrigen == null) || (nDestino == null)) && (aux != null)) {
            if (aux.getElem().equals(origen)) {
                nOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                nDestino = aux;
            }
            aux = aux.getSigVert();
        }
        if (nOrigen != null && nDestino != null) {
            insertarAdyacente(nOrigen, nDestino, etiqueta);
            insertarAdyacente(nDestino, nOrigen, etiqueta);
            exito = true;
        }
        return exito;
    }

    private void insertarAdyacente(NodoVert n, NodoVert nEnlace, Object etiq) {
        //Inserta el nodo nVertice en la lista de adyacentes del nodo n
        if (n != null) {
            if (n.getPrimerAdy() == null) {
                n.setPrimerAdy(new NodoAdy(nEnlace, null, etiq));
            } else {
                insertarAdyacenteAux(n.getPrimerAdy(), nEnlace, etiq);
            }
        }
    }

    private void insertarAdyacenteAux(NodoAdy nAdyacente, NodoVert nEnlace, Object etiq) {
        //Modulo recursivo para insertar el nodo nVertice en la lista de adyacentes del nodo n
        if (nAdyacente != null) {
            if (nAdyacente.getSigAdy() == null) {
                nAdyacente.setSigAdy(new NodoAdy(nEnlace, null, etiq));
            } else {
                insertarAdyacenteAux(nAdyacente.getSigAdy(), nEnlace, etiq);
            }
        }
    }
    
    private void eliminarArcos(NodoVert n) {
        //Modulo para eliminar los arcos del nodo "n"
        NodoAdy nAdyacente = n.getPrimerAdy();
        while (nAdyacente != null) {
            eliminarAdyacente(nAdyacente.getVertice(), n.getElem());
            n.setPrimerAdy(nAdyacente.getSigAdy());
            nAdyacente = nAdyacente.getSigAdy();
        }
    }
    
     public boolean eliminarArco(Object origen, Object destino) {
        /* Dados dos elementos de TipoVertice (origen y destino) se quita de la estructura el arco que une
        ambos vértices. Si el arco existe y se puede realizar la eliminación con éxito devuelve verdadero, en
        caso contrario devuelve falso.*/
        boolean exito = false;
        NodoVert aux = this.inicio;
        NodoVert nOrigen = null;
        NodoVert nDestino = null;
        while (((nOrigen == null) || (nDestino == null)) && (aux != null)) {
            if (aux.getElem().equals(origen)) {
                nOrigen = aux;
            }
            if (aux.getElem().equals(destino)) {
                nDestino = aux;
            }
            aux = aux.getSigVert();
        }
        if (nOrigen != null && nDestino != null) {
            exito = eliminarAdyacente(nOrigen, destino);
            if (exito) {
                exito = eliminarAdyacente(nDestino, origen);
            }
        }
        return exito;
    }

    private boolean eliminarAdyacente(NodoVert n, Object buscado) {
        //Confirma la eliminacion del nodo adyacente "buscado" de la lista de adyacentes del nodo "n"
        boolean exito = false;
        if (n != null) {
            NodoAdy nAdy = n.getPrimerAdy();
            if (nAdy != null) {
                if (nAdy.getVertice().getElem().equals(buscado)) {
                    n.setPrimerAdy(nAdy.getSigAdy());
                    exito = true;
                } else {
                    NodoAdy aux = nAdy.getSigAdy();
                    while(aux != null && !exito) {
                        if(aux.getVertice().getElem().equals(buscado)) {
                            nAdy.setSigAdy(aux.getSigAdy());
                            exito = true;
                        } else {
                            nAdy = aux;
                            aux = aux.getSigAdy();
                        }
                    }
                }
            }
        }
        return exito;
    }
    
    public boolean existeVertice(Object buscado) {
        boolean exito = false;
        NodoVert aux = this.inicio;
        while (aux != null && !exito) {
            if(aux.getElem().equals(buscado)) {
                exito = true;
            } else {
               aux = aux.getSigVert(); 
            }  
        }
        return exito;
    }   
    
    public boolean existeArco(Object origen, Object destino) {
        boolean exito = false;
        NodoVert nVert = ubicarVertice(origen);
        if(nVert != null) {
            NodoAdy nAdy = nVert.getPrimerAdy();
            while(nAdy != null && !exito) {
                if(nAdy.getVertice().getElem().equals(destino)) {
                    exito = true;
                } else {
                    nAdy = nAdy.getSigAdy();
                }
            }
        }
        return exito;
    }
    
    public boolean esVacio() {
        return this.inicio == null;
    }
    
}
