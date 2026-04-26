/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Buffread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class br {
    static private ArbolAVLDicc ciudades;
    
    public static void cargarCiudad(String codPostal, String nomCiudad, String nomProvincia) {
        //creo una ciudad
        int postalInt = Integer.parseInt(codPostal);
        Ciudad c = new Ciudad(postalInt , nomCiudad, nomProvincia);
        //la inserto en el ArbolAVLDicc Ciudades
        if (ciudades.insertar(postalInt, c)) {
            System.out.println("Se inserto la ciudad " + nomCiudad + " exitosamente");
        } else {
            System.out.println("No se inserto la ciudad " + nomCiudad + " exitosamente");
        }
    }

    public static void main(String[] args) {
        ciudades = new ArbolAVLDicc();
        String file = "C:\\Users\\acace\\OneDrive\\Documentos\\NetBeansProjects\\EstructuraDeDatos\\src\\main\\java\\Buffread\\carga.txt";
        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String linea;
            // Leer el archivo línea por línea
            while((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
                String[] pal = linea.split(";");
                switch (pal[0]) {
                    case "C":
                        cargarCiudad(pal[1], pal[2], pal[3]);
                        break;
                    case "S":
                        System.out.println("cargar solicitud");
                        break;
                    case "R":
                        System.out.println("cargar ruta");
                        break;
                    case "P":
                        System.out.println("cargar cliente");;
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo: " + e.getMessage());
        }
    }
    
}

