/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conjuntistas;

/**
 *
 * @author acace
 */
public class TablaHashCerrado {
    private int TAMANIO = 20;
    private CeldaHash[] tabla;
    private int cant;
    private int VACIO = 0;
    private int OCUPADO = 1;
    private int BORRADO = -1;
    
    public TablaHashCerrado() {
        this.tabla = new CeldaHash[TAMANIO];
        cant = 0;
    }
    
    public boolean insertar(Object nuevoElem) {
        boolean exito = false;
        int pos = nuevoElem.hashCode()%this.TAMANIO;
        int incremento;
        int intento = 1;
        while (!exito && intento<=this.TAMANIO && this.tabla[pos].getEstado() != VACIO) {
            
        }
        
        return exito;
    }
    
    public boolean eliminar(Object elem) {
        boolean exito = false;
        return exito;
    }
    
    public boolean pertenece(Object elem) {
        boolean encontrado = false;
        return encontrado;
    }
    
    public boolean esVacio() {
        return cant == 0;
    }
}
