/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.servicio;
import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.repositorio.csv.RepositorioCotizantes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Jorge
 */

public class GeneradorCaracterizacion {

    public static void main(String[] args) {
        try {
            // Ruta del archivo de cotizantes
            String rutaCotizantes = "C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/cotizantes.csv";

            // Cargar cotizantes desde el repositorio
            RepositorioCotizantes repositorioCotizantes = new RepositorioCotizantes(rutaCotizantes);
            List<Cotizante> cotizantes = repositorioCotizantes.leerTodasLasFilas();

            // Generar archivos de caracterización
            generarArchivoCaracterizacion(cotizantes, "Fiscalia", "fiscalia.csv", c -> c.isEstadoMora());
            generarArchivoCaracterizacion(cotizantes, "Procuraduria", "procuraduria.csv", c -> !c.isEstadoMora() && !c.isEmbargable());
            generarArchivoCaracterizacion(cotizantes, "Contraloria", "contraloria.csv", Cotizante::isEmbargable);

            System.out.println("¡Archivos de caracterización generados exitosamente!");

        } catch (Exception e) {
            System.err.println("Error durante la generación de archivos: " + e.getMessage());
        }
    }

    private static void generarArchivoCaracterizacion(List<Cotizante> cotizantes, String entidad, String archivoNombre, java.util.function.Predicate<Cotizante> criterio) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/" + archivoNombre))) {
            // Escribir encabezado
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();

            // Filtrar y escribir cotizantes
            for (Cotizante cotizante : cotizantes) {
                if (criterio.test(cotizante)) {
                    bw.write(cotizante.toString()); // Asegúrate de que Cotizante tenga un método toString correcto
                    bw.newLine();
                }
            }
            System.out.println("Archivo generado: " + entidad);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo " + archivoNombre + ": " + e.getMessage());
        }
    }
}

