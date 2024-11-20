/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.servicio;
import co.edu.uniquindio.repositorio.cache.SuperCache;
import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.repositorio.csv.RepositorioCotizantes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jorge
 */

public class ProcesadorArchivosMasivos {
    public static void main(String[] args) {
        String carpetaMasivos = "C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/masivos";

        try {
            File carpeta = new File(carpetaMasivos);
            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".csv"));

            if (archivos == null || archivos.length == 0) {
                System.out.println("No se encontraron archivos en la carpeta: " + carpetaMasivos);
                return;
            }

            System.out.println("Archivos encontrados: " + archivos.length);

            // Crear SuperCache
            SuperCache cache = new SuperCache();

            // Cargar datos de cada archivo en el cache
            for (File archivo : archivos) {
                RepositorioCotizantes repositorio = new RepositorioCotizantes(archivo.getAbsolutePath());
                List<Cotizante> cotizantes = repositorio.leerTodasLasFilas();
                cache.cargarCotizantes(cotizantes);

                System.out.println("Archivo procesado: " + archivo.getName() + " - Cotizantes cargados: " + cotizantes.size());
            }

            // Verificar datos en el cache
            List<Cotizante> cotizantesCache = cache.getCotizantes();
            System.out.println("\n=== Total Cotizantes en Cache ===");
            System.out.println("Total: " + cotizantesCache.size());
            System.out.println("Ejemplo de cotizantes:");
            cotizantesCache.stream().limit(5).forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Error al procesar los archivos masivos: " + e.getMessage());
        }
    }
}
