/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.servicio;
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




public class LlenarReportes {

    public static void main(String[] args) {
        try {
            // Instanciar caché y cargar datos
            SuperCache superCache = new SuperCache();
            List<Cotizante> cotizantes = superCache.getCotizantes(); // Asegúrate de que los cotizantes estén cargados

            // Generar reportes llenos con datos
            llenarReporteHabilitados(cotizantes, "reporte_habilitados.csv");
            llenarReporteInhabilitados(cotizantes, "reporte_inhabilitados.csv");
            llenarReporteListaNegra(cotizantes, "reporte_lista_negra.csv");

            System.out.println("¡Reportes llenados exitosamente!");

        } catch (Exception e) {
            System.err.println("Error al llenar los reportes: " + e.getMessage());
        }
    }

    private static void llenarReporteHabilitados(List<Cotizante> cotizantes, String archivo) {
        try {
            List<Cotizante> habilitados = cotizantes.stream()
                    .filter(c -> !c.isEmbargable() && !c.isEstadoMora())
                    .collect(Collectors.toList());
            escribirEnArchivo(habilitados, archivo);
        } catch (Exception e) {
            System.err.println("Error llenando el reporte de habilitados: " + e.getMessage());
        }
    }

    private static void llenarReporteInhabilitados(List<Cotizante> cotizantes, String archivo) {
        try {
            List<Cotizante> inhabilitados = cotizantes.stream()
                    .filter(c -> c.isEmbargable() || c.isEstadoMora())
                    .collect(Collectors.toList());
            escribirEnArchivo(inhabilitados, archivo);
        } catch (Exception e) {
            System.err.println("Error llenando el reporte de inhabilitados: " + e.getMessage());
        }
    }

    private static void llenarReporteListaNegra(List<Cotizante> cotizantes, String archivo) {
        try {
            List<Cotizante> listaNegra = cotizantes.stream()
                    .filter(Cotizante::isEmbargable)
                    .collect(Collectors.toList());
            escribirEnArchivo(listaNegra, archivo);
        } catch (Exception e) {
            System.err.println("Error llenando el reporte de lista negra: " + e.getMessage());
        }
    }

    private static void escribirEnArchivo(List<Cotizante> cotizantes, String archivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/" + archivo))) {
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();
            for (Cotizante c : cotizantes) {
                bw.write(c.toString());
                bw.newLine();
            }
        }
    }
}
