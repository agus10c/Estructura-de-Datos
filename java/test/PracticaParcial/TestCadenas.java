/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.PracticaParcial;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Lista;
import lineales.dinamicas.Pila;

/**
 *
 * @author acace
 */
public class TestCadenas {

    public static Cola generar(Cola c1) {
        Cola colaClon, colaAux = new Cola(), ret = new Cola();
        colaClon = c1.clone();
        Pila pilaAux = new Pila();
        while (!colaClon.esVacia()) {
            boolean continuar = !colaClon.obtenerFrente().equals('#');
            while (continuar) {
                ret.poner(colaClon.obtenerFrente());
                pilaAux.apilar(colaClon.obtenerFrente());
                colaAux.poner(colaClon.obtenerFrente());
                colaClon.sacar();
                if(colaClon.esVacia()) {
                    continuar = false;
                } else {
                    if(colaClon.obtenerFrente().equals('#')) {
                        continuar = false;
                    }
                }
            }
            while (!pilaAux.esVacia()) {
                ret.poner(pilaAux.obtenerTope());
                pilaAux.desapilar();
            }
            while (!colaAux.esVacia()) {
                ret.poner(colaAux.obtenerFrente());
                colaAux.sacar();
            }
            if (!colaClon.esVacia()) {
                ret.poner('#');
                colaClon.sacar();
            }
        }
        return ret;
    }

    public static boolean verificarBalanceo(Cola q) {
        Cola colaClon = q.clone();
        Lista listaAux = new Lista();
        boolean exito, continuar;
        int pos;
        while (!colaClon.esVacia()) {
            if (colaClon.obtenerFrente().equals('{') || colaClon.obtenerFrente().equals('[') || colaClon.obtenerFrente().equals('(')
                    || colaClon.obtenerFrente().equals('}') || colaClon.obtenerFrente().equals(']') || colaClon.obtenerFrente().equals(')')) {
                listaAux.insertar(colaClon.obtenerFrente(), 1);
            }
            colaClon.sacar();
        }
        continuar = !listaAux.esVacia();
        while (continuar && !listaAux.esVacia()) {
            switch ((char) listaAux.recuperar(1)) {
                case '}':
                    listaAux.eliminar(1);
                    pos = listaAux.localizar('{');
                    if (pos > 0) {
                        listaAux.eliminar(pos);
                    } else {
                        continuar = false;
                    }
                    break;
                case ']':
                    listaAux.eliminar(1);
                    pos = listaAux.localizar('[');
                    if (pos > 0) {
                        listaAux.eliminar(pos);
                    } else {
                        continuar = false;
                    }
                    break;
                case ')':
                    listaAux.eliminar(1);
                    pos = listaAux.localizar('(');
                    if (pos > 0) {
                        listaAux.eliminar(pos);
                    } else {
                        continuar = false;
                    }
                    break;
                default:
                    continuar = false;
                    break;
            }

        }
        if (listaAux.esVacia()) {
            exito = true;
        } else {
            exito = false;
        }
        return exito;
    }
    
    public static void main(String[] args) {
        System.out.println("TEST verificarBalanceo: \n");
        Cola c1 = new Cola(), c2 = new Cola();
        c1.poner('{');
        c1.poner('5');
        c1.poner('+');
        c1.poner('[');
        c1.poner('8');
        c1.poner('*');
        c1.poner('9');
        c1.poner('-');
        c1.poner('(');
        c1.poner('4');
        c1.poner('/');
        c1.poner('2');
        c1.poner(')');
        c1.poner('+');
        c1.poner('7');
        c1.poner(']');
        c1.poner('-');
        c1.poner('1');
        c1.poner('}');
        System.out.println("Cola 1:");
        System.out.println(c1.toString());
        System.out.println("verificar Balance --> "+verificarBalanceo(c1));
        c2.poner('{');
        c2.poner('5');
        c2.poner('+');
        c2.poner('8');
        c2.poner('*');
        c2.poner('9');
        c2.poner('-');
        c2.poner('(');
        c2.poner('4');
        c2.poner('/');
        c2.poner('2');
        c2.poner(')');
        c2.poner('+');
        c2.poner('7');
        c2.poner(']');
        c2.poner('-');
        c2.poner('1');
        c2.poner('}');
        System.out.println("Cola 2:");
        System.out.println(c2.toString());
        System.out.println("verificar Balance --> "+verificarBalanceo(c2));
        System.out.println("\n ----------------------------------------------------------- \n");
        System.out.println("TEST generar: \n");
        Cola c3 = new Cola();
        c3.poner('A');
        c3.poner('B');
        c3.poner('#');
        c3.poner('C');
        c3.poner('#');
        c3.poner('D');
        c3.poner('E');
        c3.poner('F');
        System.out.println("Cola:");
        System.out.println(c3.toString());
        System.out.println("generarCola: ");
        System.out.println(generar(c3).toString());   
    }
}
