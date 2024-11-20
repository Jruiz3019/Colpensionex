/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio;
import co.edu.uniquindio.repositorio.csv.RepositorioCotizantes;
import co.edu.uniquindio.repositorio.csv.RepositorioSolicitudes;
import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.modelo.SolicitudTraslado;

import java.time.LocalDate;
import java.util.List;
/**
 *
 * @author Jorge
 */


public class PruebaIntegracion {
    public static void main(String[] args) {
        try {
            // Ruta de los archivos generados
            String rutaCotizantes = "C:\\Users\\Jorge\\OneDrive\\Escritorio\\colpensionex\\colpensionex\\cotizantes.csv";
            String rutaSolicitudes = "C:\\Users\\Jorge\\OneDrive\\Escritorio\\colpensionex\\colpensionex\\solicitudes.csv";

            // Crear repositorios
            RepositorioCotizantes repositorioCotizantes = new RepositorioCotizantes(rutaCotizantes);
            RepositorioSolicitudes repositorioSolicitudes = new RepositorioSolicitudes(rutaSolicitudes);

            // Leer y mostrar cotizantes
            System.out.println("=====================Cotizantes Cargados ==================================");
            List<Cotizante> cotizantes = repositorioCotizantes.leerTodasLasFilas();
            cotizantes.forEach(System.out::println);

            // Leer y mostrar solicitudes
            System.out.println("\n======================== Solicitudes Cargadas ========================");
            List<SolicitudTraslado> solicitudes = repositorioSolicitudes.leerTodasLasFilas();
            solicitudes.forEach(System.out::println);

            // Agregar un nuevo cotizante
            System.out.println("\n=================== Agregando Nuevo Cotizante ======================");
            Cotizante nuevoCotizante = new Cotizante(
                    1001, "Laura Gomez", LocalDate.of(1995, 8, 10), 'F', 32000.0, false, false
            );
            repositorioCotizantes.escribirFila(nuevoCotizante);
            System.out.println("Nuevo cotizante agregado: " + nuevoCotizante);

            // Agregar una nueva solicitud
            System.out.println("\n===================== Agregando Nueva Solicitud ========================");
            SolicitudTraslado nuevaSolicitud = new SolicitudTraslado(1001, true, "Aprobado con observaciones");
            repositorioSolicitudes.escribirFila(nuevaSolicitud);
            System.out.println("Nueva solicitud agregada: " + nuevaSolicitud);

            // Verificar que los nuevos datos se agregaron correctamente
            System.out.println("\n============================ Verificando Nuevos Datos ========================");

            System.out.println("Cotizantes Actualizados:");
            repositorioCotizantes.leerTodasLasFilas().forEach(System.out::println);

            System.out.println("\nSolicitudes Actualizadas:");
            repositorioSolicitudes.leerTodasLasFilas().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


