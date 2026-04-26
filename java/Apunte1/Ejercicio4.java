/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apunte1;

/**
 *
 * @author acace
 */
public class Ejercicio4 {
    public static int primeraLetra(String cadena,char letra, int posicion) {
        if (cadena.charAt(posicion)!= letra && posicion<cadena.length()) {
            posicion = primeraLetra(cadena,letra, posicion+1);
        } 
        if(posicion>cadena.length()) {
            posicion = -1;
        }
        return posicion;
    }
    public static int ultimaLetra(String cadena,char letra, int posicion) {
        if (cadena.charAt(posicion)!= letra && posicion>=0) {
            posicion = primeraLetra(cadena,letra, posicion-1);
        } 
        if(posicion>cadena.length()) {
            posicion = -1;
        }
        return posicion;
    }
    public static String invertirCadena(String cadena, String cadenaInv, int posicion) {
        if (posicion<cadena.length()) {
            cadenaInv = invertirCadena(cadena, cadenaInv, posicion+1)+cadena.charAt(posicion);
        }  
        return cadenaInv;
    }
    public static String cadenaMasInversa(String cadena) {
        String cadMasInv;
        return (cadena+invertirCadena(cadena,"",0));
    }
    public static void main(String[] args) {
        System.out.println(invertirCadena("12adfa","",0));
    }
    
}
