/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Apunte1;

/**
 *
 * @author acace
 */
public class Ejercicio6 {
    public static void menoresQuePormedio(int[] arreglo, int i, double promedio) {
         if(i<arreglo.length) {
             promedio = (promedio+arreglo[i])/6;
             menoresQuePormedio(arreglo,i,promedio);
             if(arreglo[i]<promedio) {
                 System.out.println(arreglo[i]);
             }
         } 
    }
    public static void main(String[] args) {
        
    }
            
}
