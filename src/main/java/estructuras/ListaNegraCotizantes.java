/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import co.edu.uniquindio.modelo.Cotizante;
import java.util.Iterator;

/**
 * Lista enlazada personalizada para manejar cotizantes en la lista negra.
 */
public class ListaNegraCotizantes implements Iterable<Cotizante> {
    private Nodo cabeza; // Nodo inicial de la lista
    private Nodo cola;   // Nodo final de la lista

    // Clase interna que representa un nodo en la lista enlazada
    private static class Nodo {
        Cotizante cotizante;
        Nodo siguiente;

        Nodo(Cotizante cotizante) {
            this.cotizante = cotizante;
        }
    }

    // Agregar un cotizante al final de la lista
    public void agregarCotizante(Cotizante cotizante) {
        if (cotizante == null) {
            throw new IllegalArgumentException("El cotizante no puede ser nulo.");
        }
        Nodo nuevoNodo = new Nodo(cotizante);
        if (cabeza == null) {
            cabeza = cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }
    }

    // Eliminar un cotizante de la lista
    public void eliminarCotizante(Cotizante cotizante) {
        if (cotizante == null || cabeza == null) return;

        if (cabeza.cotizante.equals(cotizante)) {
            cabeza = cabeza.siguiente;
            if (cabeza == null) cola = null;
            return;
        }

        Nodo actual = cabeza;
        while (actual.siguiente != null && !actual.siguiente.cotizante.equals(cotizante)) {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null) {
            actual.siguiente = actual.siguiente.siguiente;
            if (actual.siguiente == null) cola = actual;
        }
    }

    // Buscar un cotizante por su ID
    public Cotizante buscarCotizante(int id) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.cotizante.getId() == id) {
                return actual.cotizante;
            }
            actual = actual.siguiente;
        }
        return null; // No encontrado
    }

    // Contar el número de cotizantes en la lista
    public int contarElementos() {
        int contador = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }
        return contador;
    }

    // Iterador para recorrer todos los cotizantes
    @Override
    public Iterator<Cotizante> iterator() {
        return new Iterator<Cotizante>() {
            private Nodo actual = cabeza;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public Cotizante next() {
                Cotizante cotizante = actual.cotizante;
                actual = actual.siguiente;
                return cotizante;
            }
        };
    }

    /**
     * Iterador personalizado para recorrer solo los cotizantes embargables.
     */
    public Iterator<Cotizante> iteradorEmbargables() {
        return new Iterator<Cotizante>() {
            private Nodo actual = cabeza;

            @Override
            public boolean hasNext() {
                while (actual != null && !actual.cotizante.isEmbargable()) {
                    actual = actual.siguiente;
                }
                return actual != null;
            }

            @Override
            public Cotizante next() {
                Cotizante cotizante = actual.cotizante;
                actual = actual.siguiente;
                return cotizante;
            }
        };
    }
}

