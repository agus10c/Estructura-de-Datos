/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lineales.dinamicas;

/**
 *
 * @author acace
 */
public class Lista {
    private Nodo cabecera;
    private int longitud;
    
    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }
    
    public boolean insertar(Object nuevoElem, int pos) {
        boolean exito = true;
        if (pos<1 || pos>this.longitud+1) {
            exito = false;
        } else {
            if(pos==1) {
                this.cabecera = new Nodo(nuevoElem,this.cabecera);
                this.longitud++;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while(i<pos-1) {
                    aux = aux.getEnlace();
                    i++;
                }
                Nodo nuevo = new Nodo(nuevoElem, aux.getEnlace());
                aux.setEnlace(nuevo);
                this.longitud++;
            }
        }
        return exito;
    }
    
    public boolean eliminar(int pos) {
        boolean exito = true;
        if (pos < 1 || pos > this.longitud) {
            exito = false;
        } else {
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
                this.longitud--;
            } else {
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                if (aux.getEnlace() != null) {
                    aux.setEnlace(aux.getEnlace().getEnlace());
                    this.longitud--;
                } else {
                    aux.setEnlace(null);
                    this.longitud--;
                }
            }
        }
        return exito;
    }
    
    public Object recuperar(int pos) {
        Object elem = null;
        if (pos < 1 || pos > this.longitud) {  
            elem = null;
        } else {
            Nodo aux = this.cabecera;
            int i = 1;
            while(i!=pos) {       
                aux = aux.getEnlace();                  
                i++;                
            }
            elem = aux.getElemento();
        }
        return elem;
    }
    
    public int localizar(Object elem) {
        int pos = 1;
        Nodo aux = this.cabecera;
        while(aux != null) { 
            if(aux.getElemento() != elem) {
                aux = aux.getEnlace();
                pos++;
            } else { 
                aux = null;
            }         
        } 
        if(pos>this.longitud) { 
            pos = -1;
        }
        return pos;
    }
    
    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }
    
    public boolean esVacia() {
        return this.cabecera==null;
    }
    
    public int longitud() {
        return this.longitud;
    }
    
    public Lista clone() {
        Lista clon = new Lista();
        if(this.cabecera != null) {
            clon.cabecera = cloneAux(this.cabecera);
        }
        clon.longitud =this.longitud;
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
    
    public void invertir() {
        if(this.cabecera != null) {
            if(this.cabecera.getEnlace() != null) {
                this.cabecera = invertirAux(this.cabecera);
            }
        }
    }
    
    private Nodo invertirAux(Nodo n) {
        Nodo invertido;
        if(n.getEnlace().getEnlace() == null) {
            n.getEnlace().setEnlace(n);
            invertido = n.getEnlace();
            this.cabecera.setEnlace(null);
        } else {
            invertido = invertirAux(n.getEnlace());
            n.getEnlace().setEnlace(n);
        }
        return invertido;
    }
    
    public void eliminarRepetidos(Object x) {
        int i;
        if (this.cabecera != null) {
            Nodo aux = this.cabecera.getEnlace();
            while (this.cabecera.getElemento().equals(x)) {
                this.cabecera = aux;
                aux.getEnlace();
            }
            Nodo aux2 = this.cabecera;
            while (aux != null) {
                while (aux.getElemento().equals(x) && aux != null) {
                    aux = aux.getEnlace();
                }
                if(aux == null) {
                    aux2.setEnlace(aux);
                } else {
                   aux2.setEnlace(aux); 
                   aux2 = aux;
                   aux = aux.getEnlace();
                }
            }           
        }
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
    
    public Lista obtenerMultiplos(int num) {
        Lista lis = new Lista();
        lis.cabecera = obtenerMultiplosAux(this.cabecera, num, 1);
        return lis;
    }
    
    private Nodo obtenerMultiplosAux(Nodo n, int num, int pos) {
        Nodo nuevo;
        if(n != null) {
            if(pos%num == 0) {
                nuevo = new Nodo(n.getElemento(), obtenerMultiplosAux(n.getEnlace(), num, pos++));
            } else {
                nuevo = obtenerMultiplosAux(n.getEnlace(), num, pos++);
            }
        } else {
            nuevo = null;
        }
        return nuevo;
    }
    
    public void eliminarApariciones(Object elem) {
        boolean continuar;
        if (this.cabecera != null) {
            if(this.cabecera.getElemento().equals(elem)) {
                continuar = true;
            } else {
                continuar = false;
            }
        } else {
            continuar = false;
        }
        while(continuar && this.cabecera.getElemento().equals(elem)) {
            this.cabecera = this.cabecera.getEnlace();
            if (this.cabecera != null) {
                if (!this.cabecera.getElemento().equals(elem)) {
                    continuar = false;
                }
            } else {
                continuar = false;
            }
        }
        if(this.cabecera != null) {
            eliminarAparicionesAux(this.cabecera, elem);
        }   
    }
    
    private void eliminarAparicionesAux(Nodo n, Object elem) {
        if(n.getEnlace() != null) {
            if(n.getEnlace().getElemento().equals(elem)) {
                n.setEnlace(n.getEnlace().getEnlace());
                eliminarAparicionesAux(n, elem);
            } else {
                eliminarAparicionesAux(n.getEnlace(), elem);
            }
        }
    }
    
    public void cambiarPosicion(int pos1, int pos2) {
        if (pos1 >= 1 && pos1 <= this.longitud && pos2 >= 1 && pos2 <= this.longitud) {
            Object elem;
            int pos = 1;
            Nodo aux = this.cabecera;
            Nodo n;
            if(pos1 < pos2) {
                if(pos1 == 1) {
                    n = null;
                    elem = this.cabecera.getElemento();
                } else {
                    pos = 2;
                    aux = this.cabecera;
                    while(pos1 != pos) {
                        aux = aux.getEnlace();
                        pos++;
                    }
                    elem = aux.getEnlace().getElemento();
                    n = aux;
                    aux = aux.getEnlace();
                }
                while(pos2 != pos) {
                    aux = aux.getEnlace();
                    pos++;
                }
                aux.setElemento(elem);
                if(n == null) {
                    this.cabecera = this.cabecera.getEnlace();
                } else {
                    n.setEnlace(n.getEnlace().getEnlace());
                }
            } else if(pos1 > pos2){
                while(pos2 != pos) {
                    aux = aux.getEnlace();
                    pos++;
                }
                n = aux;
                pos++;
                while(pos1 != pos) {
                    aux = aux.getEnlace();
                    pos++;
                }
                elem = aux.getEnlace().getElemento();
                n.setElemento(elem);
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
        }
    }
    
}
