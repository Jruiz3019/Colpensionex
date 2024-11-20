/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.servicio;
import estructuras.ListaNegraCotizantes;
import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.repositorio.cache.SuperCache;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author Jorge
 */





public class GeneradorReportesFinales {
    public static void main(String[] args) {
        try {
           
            SuperCache superCache = new SuperCache();
            // Aquí se supone que ya están los cotizantes cargados en el cache.

            // Obtener los cotizantes desde la caché
            List<Cotizante> cotizantes = superCache.getCotizantes();

            // Generar reportes
            generarReporteHabilitados(cotizantes, "reporte_habilitados.csv");
            generarReporteInhabilitados(cotizantes, "reporte_inhabilitados.csv");
            generarReporteListaNegra(cotizantes, "reporte_lista_negra.csv");

            System.out.println("¡Reportes finales generados exitosamente!");

        } catch (Exception e) {
            System.err.println("Error al generar los reportes: " + e.getMessage());
        }
    }

    private static void generarReporteHabilitados(List<Cotizante> cotizantes, String rutaArchivo) throws IOException {
        List<Cotizante> habilitados = cotizantes.stream()
                .filter(c -> !c.isEmbargable() && !c.isEstadoMora())
                .collect(Collectors.toList());
        escribirReporte(habilitados, rutaArchivo, "Habilitados");
    }

    private static void generarReporteInhabilitados(List<Cotizante> cotizantes, String rutaArchivo) throws IOException {
        List<Cotizante> inhabilitados = cotizantes.stream()
                .filter(c -> c.isEstadoMora() || c.isEmbargable())
                .collect(Collectors.toList());
        escribirReporte(inhabilitados, rutaArchivo, "Inhabilitados");
    }

    private static void generarReporteListaNegra(List<Cotizante> cotizantes, String rutaArchivo) throws IOException {
        List<Cotizante> listaNegra = cotizantes.stream()
                .filter(Cotizante::isEmbargable)
                .collect(Collectors.toList());
        escribirReporte(listaNegra, rutaArchivo, "Lista Negra");
    }

    private static void escribirReporte(List<Cotizante> cotizantes, String rutaArchivo, String tipoReporte) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/" + rutaArchivo))) {
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();
            for (Cotizante c : cotizantes) {
                bw.write(c.toString()); // Asegúrate de que Cotizante tenga un método toString adecuado
                bw.newLine();
            }
            System.out.println("Reporte generado: " + tipoReporte);
        }
    }
}

