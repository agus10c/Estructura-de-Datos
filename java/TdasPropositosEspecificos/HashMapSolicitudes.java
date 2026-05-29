package Estructuras;

import Clases.SolicitudDeViaje;
import java.util.HashMap;
import java.util.Iterator;

// Decidi utilizar un HashMap<String, Lista> para almacenar las solicitudes de viaje donde la clave va a ser una combinacion 
// de los codigos postales de la ciudad de origen y la ciudad de destino(Ej : 8300-5000) y el valor es una Lista con las solicitudes correspomdientes.
// Esto porque el acceso al hashmap(O(1)) es eficiente para la búsqueda de todas las solicitudes entre dos ciudades.
public class HashMapSolicitudes {

    //es un tipo de implementacion de  Mapeo a muchos
    private HashMap<String, Lista> hashSolicitudes = new HashMap<>();

    public HashMapSolicitudes() {
    }

    public boolean insertar(SolicitudDeViaje solicitud) {
        boolean exito = false;
        if (solicitud != null) {
            String clave = solicitud.getCiudadOrigen() + "-" + solicitud.getCiudadDestino();
            if (!hashSolicitudes.containsKey(clave)) { // Si no existe la clave, crear lista
                hashSolicitudes.put(clave, new Lista());
            }
            Lista lista = hashSolicitudes.get(clave);
            if (lista.insertar(solicitud, lista.longitud() + 1)) {
                exito = true;
            }
        }
        return exito;
    }

    private int buscarSolicitud(Lista lista, int num) {
        int pos = 1;
        boolean encontrado = false;
        if (lista != null) {
            SolicitudDeViaje solicitud = null;
            while (!encontrado && pos <= lista.longitud()) {
                solicitud = (SolicitudDeViaje) lista.recuperar(pos);
                if (solicitud.getNumero() == num) {
                    encontrado = true;
                } else {
                    pos++;
                }
            }
        }
        if (!encontrado) {
            pos = -1;
        }
        return pos;
    }

    public boolean eliminar(int co, int cd, int numSolicitud) {
        boolean exito = false;
        Lista lista = hashSolicitudes.get(co + "-" + cd);
        int pos = buscarSolicitud(lista, numSolicitud);
        if (pos > 0) {
            lista.eliminar(pos);
            exito = true;
        }
        return exito;
    }

    public SolicitudDeViaje obtener(int co, int cd, int num) {
        Lista lis = this.hashSolicitudes.get(co + "-" + cd);
        SolicitudDeViaje solicitud = null;
        boolean encontrado = false;
        if (lis != null) {
            int pos = 1;
            while (!encontrado && pos <= lis.longitud()) {
                solicitud = (SolicitudDeViaje) lis.recuperar(pos);
                if (solicitud.getNumero() == num) {
                    encontrado = true;
                } else {
                    pos++;
                }
            }
        }
        return solicitud;
    }

    public Lista obtenerSolicitudesEntreCiudades(int co, int cd) {
        return hashSolicitudes.get(co + "-" + cd);
    }

    public void eliminarSolicitudesPorCiudad(int codigoPostal) {
        String cod = String.valueOf(codigoPostal);
        Iterator<String> it = hashSolicitudes.keySet().iterator(); //keyset() Devuelve un conjunto (Set) con TODAS las claves del HashMap.
        // iterator() crea un objeto Iterator preparado para recorrer la colección.
        while (it.hasNext()) { //hasNext() verifica si queda otro elemento
            String clave = it.next(); //next() devuelve el elemento y avanza el iterador al siguiente elemento

            String[] partes = clave.split("-");

            String origen = partes[0];
            String destino = partes[1];

            if (origen.equals(cod) || destino.equals(cod)) {
                it.remove(); //elimina el ultimo elemento retornado por next
            }
        }
    }

    public Lista listarPosiblesSolicitudesIntermedias(Lista camino, double espacioDisponible) {
        Lista posibles = new Lista();
        int i = 1;
        while (i <= camino.longitud() - 1) {
            int origen = (int) camino.recuperar(i);
            int j = i + 1;
            while (j <= camino.longitud()) {
                int destino = (int) camino.recuperar(j);
                Lista solicitudes = obtenerSolicitudesEntreCiudades(origen, destino);
                int k = 1;
                while (k <= solicitudes.longitud()) {
                    SolicitudDeViaje s = (SolicitudDeViaje) solicitudes.recuperar(k);
                    if (s.getMetrosCubicos() <= espacioDisponible) {
                        posibles.insertar(s, posibles.longitud() + 1);
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        return posibles;
    }

    public boolean verificarCaminoPerfecto(Lista camino, double espacioDisponible) {
        boolean perfecto = true;
        int i = 1;
        while (i <= camino.longitud() - 1) {
            int origen = (int) camino.recuperar(i);
            int j = i + 1;
            boolean existeSolicitud = false;
            while (j <= camino.longitud() && !existeSolicitud) {
                int destino = (int) camino.recuperar(j);
                Lista solicitudes = obtenerSolicitudesEntreCiudades(origen, destino);
                int k = 1;
                while (k <= solicitudes.longitud() && !existeSolicitud) {
                    SolicitudDeViaje s = (SolicitudDeViaje) solicitudes.recuperar(k);
                    if (s.getMetrosCubicos() <= espacioDisponible) {
                        existeSolicitud = true;
                    }
                    k++;
                }
                j++;
            }
            if (!existeSolicitud) {
                perfecto = false;
            }
            i++;
        }
        return perfecto;
    }

    @Override
    public String toString() {
        String cadena = "";
        for (String clave : hashSolicitudes.keySet()) {
            cadena += "Ciudad de origen-destino: " + clave + "\n";
            Lista lista = hashSolicitudes.get(clave);
            for (int i = 1; i <= lista.longitud(); i++) {
                cadena += "  " + lista.recuperar(i) + "\n";
            }
            cadena += "\n";
        }
        return cadena;
    }
}
