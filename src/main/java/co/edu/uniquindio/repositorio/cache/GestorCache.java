package co.edu.uniquindio.repositorio.cache;

import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.modelo.SolicitudTraslado;
import co.edu.uniquindio.repositorio.csv.RepositorioCotizantes;
import co.edu.uniquindio.repositorio.csv.RepositorioSolicitudes;

import java.util.List;

/**
 * Clase que coordina la interacción entre la caché y los repositorios.
 */
public class GestorCache {
    private final SuperCache superCache;
    private final RepositorioCotizantes repositorioCotizantes;
    private final RepositorioSolicitudes repositorioSolicitudes;

    public GestorCache(SuperCache superCache, RepositorioCotizantes repositorioCotizantes, RepositorioSolicitudes repositorioSolicitudes) {
        this.superCache = superCache;
        this.repositorioCotizantes = repositorioCotizantes;
        this.repositorioSolicitudes = repositorioSolicitudes;
        cargarDatosEnCache();
    }

    /**
     * Carga todos los datos desde los repositorios en la caché.
     */
    private void cargarDatosEnCache() {
        System.out.println("Cargando datos en la caché...");

        // Cargar cotizantes
        List<Cotizante> cotizantes = repositorioCotizantes.leerTodasLasFilas();
        if (cotizantes != null && !cotizantes.isEmpty()) {
            superCache.cargarCotizantes(cotizantes);
            System.out.println("Cotizantes cargados: " + cotizantes.size());
        } else {
            System.out.println("No se encontraron cotizantes para cargar.");
        }

        // Cargar solicitudes
        List<SolicitudTraslado> solicitudes = repositorioSolicitudes.leerTodasLasFilas();
        if (solicitudes != null && !solicitudes.isEmpty()) {
            superCache.cargarSolicitudes(solicitudes);
            System.out.println("Solicitudes cargadas: " + solicitudes.size());
        } else {
            System.out.println("No se encontraron solicitudes para cargar.");
        }
    }

    /**
     * Obtiene la lista de cotizantes desde la caché.
     */
    public List<Cotizante> obtenerCotizantesDesdeCache() {
        return superCache.getCotizantes();
    }

    /**
     * Obtiene la lista de solicitudes desde la caché.
     */
    public List<SolicitudTraslado> obtenerSolicitudesDesdeCache() {
        return superCache.getSolicitudes();
    }

    /**
     * Actualiza un cotizante en la caché y en el repositorio correspondiente.
     */
public void actualizarCotizanteEnCache(Cotizante cotizante) {
    if (cotizante == null) {
        System.out.println("No se puede actualizar un cotizante nulo.");
        return;
    }

    // Validar si el cotizante ya existe en la caché
    boolean existeEnCache = superCache.getCotizantes().stream()
                                      .anyMatch(c -> c.getId() == cotizante.getId());
    if (existeEnCache) {
        System.out.println("El cotizante con ID " + cotizante.getId() + " ya existe en la caché. No se agregará nuevamente.");
        return;
    }

    // Agregar a la caché y escribir en el archivo
    superCache.actualizarCotizante(cotizante);

    // Validar si el cotizante ya está en el archivo antes de escribir
    List<Cotizante> cotizantesEnArchivo = repositorioCotizantes.leerTodasLasFilas();
    boolean existeEnArchivo = cotizantesEnArchivo.stream()
                                                 .anyMatch(c -> c.getId() == cotizante.getId());
    if (existeEnArchivo) {
        System.out.println("El cotizante con ID " + cotizante.getId() + " ya existe en el archivo. No se escribirá nuevamente.");
        return;
    }

    repositorioCotizantes.escribirFila(cotizante);
    System.out.println("Cotizante actualizado correctamente: " + cotizante.getId());
}



    /**
     * Agrega una nueva solicitud de traslado a la caché y al repositorio.
     */
    public void agregarSolicitudEnCache(SolicitudTraslado solicitud) {
        if (solicitud == null) {
            System.err.println("No se puede agregar una solicitud nula.");
            return;
        }

        try {
            // Agregar en caché
            superCache.agregarSolicitud(solicitud);

            // Agregar en repositorio
            repositorioSolicitudes.escribirFila(solicitud);
            System.out.println("Solicitud agregada correctamente: " + solicitud.getIdCotizante());
        } catch (Exception e) {
            System.err.println("Error al agregar la solicitud: " + e.getMessage());
        }
    }
    
    
}
