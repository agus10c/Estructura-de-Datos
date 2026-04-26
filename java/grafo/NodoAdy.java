
package grafo;

public class NodoAdy {
    
    private NodoVert vertice;
    private NodoAdy sigAdy;
    private Object etiqueta;
    
    NodoAdy(NodoVert vert, NodoAdy ady, Object etiqueta) {
        this.vertice = vert;
        this.sigAdy = ady;
        this.etiqueta = etiqueta;
    }
    
    public NodoVert getVertice() {
        return this.vertice;
    }
    
    public void setVertice(NodoVert vert) {
        this.vertice = vert;
    }
    
    public NodoAdy getSigAdy() {
        return this.sigAdy;
    }
    
    public void setSigAdy(NodoAdy ady) {
        this.sigAdy = ady;
    }
}
