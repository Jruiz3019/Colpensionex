package co.edu.uniquindio;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import co.edu.uniquindio.repositorio.cache.GestorCache;
import co.edu.uniquindio.repositorio.cache.SuperCache;
import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.modelo.SolicitudTraslado;
import co.edu.uniquindio.repositorio.csv.RepositorioCotizantes;
import co.edu.uniquindio.repositorio.csv.RepositorioSolicitudes;

import co.edu.uniquindio.servicio.GeneradorReportes;
import estructuras.ListaNegraCotizantes;

public class Aplicacion {

    public static void main(String[] args) throws IOException {


        // Rutas de los archivos CSV
        String rutaCotizantes = "cotizantes.csv";
        String rutaSolicitudes = "solicitudes.csv";

        // Crear repositorios
        RepositorioCotizantes repositorioCotizantes = new RepositorioCotizantes(rutaCotizantes);
        RepositorioSolicitudes repositorioSolicitudes = new RepositorioSolicitudes(rutaSolicitudes);

        // Crear SuperCache y GestorCache
        SuperCache superCache = new SuperCache();
        GestorCache gestorCache = new GestorCache(superCache, repositorioCotizantes, repositorioSolicitudes);

        // Probar obtener cotizantes desde la caché
        System.out.println("Cotizantes en caché:");
        for (Cotizante cotizante : gestorCache.obtenerCotizantesDesdeCache()) {
            System.out.println(cotizante);
        }

        // Probar agregar un nuevo cotizante
        Cotizante nuevoCotizante = new Cotizante(1001, "Juan Pérez", java.time.LocalDate.of(1990, 5, 15), 'M', 30000.0, false, false);
        gestorCache.actualizarCotizanteEnCache(nuevoCotizante);

        // Probar agregar una nueva solicitud
        SolicitudTraslado nuevaSolicitud = new SolicitudTraslado(1001, true, "Aprobada para traslado");
        gestorCache.agregarSolicitudEnCache(nuevaSolicitud);

        // Verificar solicitudes en la caché
        System.out.println("Solicitudes en caché:");
        for (SolicitudTraslado solicitud : gestorCache.obtenerSolicitudesDesdeCache()) {
            System.out.println(solicitud);
        }
    }
    


//    
//        try {
//            // Crear instancia del repositorio con la ruta al archivo CSV
//            RepositorioCotizantes repositorioCotizantes = new RepositorioCotizantes("C:\\Users\\Jorge\\OneDrive\\Escritorio\\colpensionex\\colpensionex\\cotizantes.csv");
//
//            // Leer todas las filas del archivo CSV
//            List<Cotizante> cotizantes = repositorioCotizantes.leerTodasLasFilas();
//
//            // Imprimir cotizantes cargados
//            System.out.println("Cotizantes cargados:");
//            for (Cotizante cotizante : cotizantes) {
//                System.out.println(cotizante);
//            }
//
//        } catch (Exception e) {
//            System.err.println("Error al cargar cotizantes: " + e.getMessage());
//        }
//    }
//    
//    try {
//            // Ruta del archivo de cotizantes
//            String rutaCotizantes = "C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/cotizantes.csv";
//
//            // Cargar cotizantes desde el repositorio
//            RepositorioCotizantes repositorioCotizantes = new RepositorioCotizantes(rutaCotizantes);
//            List<Cotizante> cotizantes = repositorioCotizantes.leerTodasLasFilas();
//
//            // Mostrar los cotizantes cargados
//            System.out.println("=== Cotizantes Cargados ===");
//            for (Cotizante cotizante : cotizantes) {
//                System.out.println(cotizante);
//            }
//
//            // Crear lista negra
//            ListaNegraCotizantes listaNegra = new ListaNegraCotizantes();
//            for (Cotizante cotizante : cotizantes) {
//                if (cotizante.isEmbargable()) {
//                    listaNegra.agregarCotizante(cotizante);
//                }
//            }
//
//            // Generar reportes
//            System.out.println("Generando reportes...");
//            GeneradorReportes.generarReporteHabilitados(cotizantes, "C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/reporte_habilitados.csv");
//            GeneradorReportes.generarReporteInhabilitados(cotizantes, "C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/reporte_inhabilitados.csv");
//            GeneradorReportes.generarReporteListaNegra(listaNegra, "C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/reporte_lista_negra.csv");
//
//            System.out.println("¡Reportes generados exitosamente!");
//
//        } catch (Exception e) {
//            System.err.println("Error durante la ejecución: " + e.getMessage());
//        }
//    }
}



