/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.servicio;
import co.edu.uniquindio.modelo.Cotizante;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Jorge
 */



public class GeneradorReportes {

    /**
     * Genera un reporte CSV con cotizantes habilitados para traslado.
     */
    public static void generarReporteHabilitados(List<Cotizante> cotizantes, String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();
            for (Cotizante cotizante : cotizantes) {
                if (!cotizante.isEstadoMora() && !cotizante.isEmbargable()) {
                    bw.write(cotizante.getId() + "," +
                             cotizante.getNombre() + "," +
                             cotizante.getFechaNacimiento() + "," +
                             cotizante.getSexo() + "," +
                             cotizante.getValorUnitario() + "," +
                             cotizante.isEstadoMora() + "," +
                             cotizante.isEmbargable());
                    bw.newLine();
                }
            }
            System.out.println("Reporte de habilitados generado en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar el reporte de habilitados: " + e.getMessage());
        }
    }

    /**
     * Genera un reporte CSV con cotizantes inhabilitados para traslado.
     */
    public static void generarReporteInhabilitados(List<Cotizante> cotizantes, String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();
            for (Cotizante cotizante : cotizantes) {
                if (cotizante.isEstadoMora() || cotizante.isEmbargable()) {
                    bw.write(cotizante.getId() + "," +
                             cotizante.getNombre() + "," +
                             cotizante.getFechaNacimiento() + "," +
                             cotizante.getSexo() + "," +
                             cotizante.getValorUnitario() + "," +
                             cotizante.isEstadoMora() + "," +
                             cotizante.isEmbargable());
                    bw.newLine();
                }
            }
            System.out.println("Reporte de inhabilitados generado en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar el reporte de inhabilitados: " + e.getMessage());
        }
    }

    /**
     * Genera un reporte CSV con cotizantes en la lista negra.
     */
    public static void generarReporteListaNegra(Iterable<Cotizante> listaNegra, String rutaArchivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();
            for (Cotizante cotizante : listaNegra) {
                bw.write(cotizante.getId() + "," +
                         cotizante.getNombre() + "," +
                         cotizante.getFechaNacimiento() + "," +
                         cotizante.getSexo() + "," +
                         cotizante.getValorUnitario() + "," +
                         cotizante.isEstadoMora() + "," +
                         cotizante.isEmbargable());
                bw.newLine();
            }
            System.out.println("Reporte de lista negra generado en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al generar el reporte de lista negra: " + e.getMessage());
        }
    }
}
