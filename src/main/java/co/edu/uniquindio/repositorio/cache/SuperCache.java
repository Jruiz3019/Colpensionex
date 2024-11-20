/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.repositorio.cache;

import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.modelo.SolicitudTraslado;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Clase que actúa como una caché en memoria para cotizantes y solicitudes de traslado.
 */
public class SuperCache {
    // Mapas para almacenar los datos en memoria
    private final ConcurrentHashMap<Integer, Cotizante> cacheCotizantes = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Integer, SolicitudTraslado> cacheSolicitudes = new ConcurrentHashMap<>();

    /**
     * Cargar una lista de cotizantes en la caché.
     */
    public void cargarCotizantes(List<Cotizante> cotizantes) {
        for (Cotizante cotizante : cotizantes) {
            cacheCotizantes.put(cotizante.getId(), cotizante);
        }
        System.out.println("Cotizantes cargados en caché: " + cacheCotizantes.size());
    }

    /**
     * Cargar una lista de solicitudes en la caché.
     */
    public void cargarSolicitudes(List<SolicitudTraslado> solicitudes) {
        for (SolicitudTraslado solicitud : solicitudes) {
            cacheSolicitudes.put(solicitud.getIdCotizante(), solicitud);
        }
        System.out.println("Solicitudes cargadas en caché: " + cacheSolicitudes.size());
    }

    /**
     * Obtener todos los cotizantes desde la caché.
     */
    public List<Cotizante> getCotizantes() {
        return new ArrayList<>(cacheCotizantes.values());
    }

    /**
     * Obtener todas las solicitudes desde la caché.
     */
    public List<SolicitudTraslado> getSolicitudes() {
        return new ArrayList<>(cacheSolicitudes.values());
    }

    /**
     * Actualizar un cotizante en la caché.
     */
    public void actualizarCotizante(Cotizante cotizante) {
        if (cotizante == null) {
            System.out.println("No se puede actualizar un cotizante nulo.");
            return;
        }
        cacheCotizantes.put(cotizante.getId(), cotizante);
        System.out.println("Cotizante actualizado en caché: " + cotizante.getId());
    }

    /**
     * Agregar una nueva solicitud a la caché.
     */
    public void agregarSolicitud(SolicitudTraslado solicitud) {
        if (solicitud == null) {
            System.out.println("No se puede agregar una solicitud nula.");
            return;
        }
        cacheSolicitudes.put(solicitud.getIdCotizante(), solicitud);
        System.out.println("Solicitud agregada a la caché: " + solicitud.getIdCotizante());
    }
    
    
}

