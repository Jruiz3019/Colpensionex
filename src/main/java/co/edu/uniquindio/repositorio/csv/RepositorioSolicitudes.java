/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.repositorio.csv;
import co.edu.uniquindio.modelo.SolicitudTraslado;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;
/**
 *
 * @author Jorge
 */

public class RepositorioSolicitudes extends RepositorioBase<SolicitudTraslado> {
    private final String rutaArchivo;

    public RepositorioSolicitudes(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

   @Override
public LinkedList<SolicitudTraslado> leerTodasLasFilas() {
    LinkedList<SolicitudTraslado> solicitudes = new LinkedList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        br.readLine(); // Saltar el encabezado
        while ((linea = br.readLine()) != null) {
            try {
                String[] datos = linea.split(",");
                // Validar si los datos tienen el formato correcto antes de procesarlos
                if (datos.length < 3) {
                    System.err.println("Línea mal formateada, ignorando: " + linea);
                    continue;
                }
                int idCotizante = Integer.parseInt(datos[0].trim());
                boolean aprobado = Boolean.parseBoolean(datos[1].trim().toLowerCase()); // Convertir a minúsculas para evitar errores
                String comentarios = datos[2].trim();

                // Crear y agregar la solicitud a la lista
                SolicitudTraslado solicitud = new SolicitudTraslado(idCotizante, aprobado, comentarios);
                solicitudes.add(solicitud);

            } catch (NumberFormatException e) {
                System.err.println("Error al convertir los datos numéricos en la línea: " + linea);
            } catch (Exception e) {
                System.err.println("Error inesperado al procesar la línea: " + linea);
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + rutaArchivo);
        e.printStackTrace();
    }
    return solicitudes;
}

   @Override
public void escribirFila(SolicitudTraslado solicitud) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) { // true para agregar al archivo existente
        bw.write(solicitud.getIdCotizante() + "," +
                 solicitud.isAprobado() + "," +
                 solicitud.getComentarios());
        bw.newLine();
    } catch (IOException e) {
        System.err.println("Error al escribir en el archivo: " + e.getMessage());
    }
}
}

