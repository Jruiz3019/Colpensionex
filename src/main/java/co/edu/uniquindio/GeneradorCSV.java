/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Jorge
 */


public class GeneradorCSV {

    public static void main(String[] args) {
        try {
            generarArchivoCotizantes("C:\\Users\\Jorge\\OneDrive\\Escritorio\\colpensionex\\colpensionex\\cotizantes.csv");
            generarArchivoSolicitudes("C:\\Users\\Jorge\\OneDrive\\Escritorio\\colpensionex\\colpensionex\\solicitudes.csv");
            System.out.println("¡Archivos CSV generados exitosamente!");
        } catch (Exception e) {
            System.err.println("Error al generar los archivos CSV: " + e.getMessage());
        }
    }

    /**
     * Genera el archivo cotizantes.csv con datos de ejemplo.
     */
    public static void generarArchivoCotizantes(String rutaArchivo) throws IOException {
        String[] cotizantes = {
                "1,Juan Perez,1985-05-20,M,30000.00,true,false",
                "2,Maria Gomez,1990-07-15,F,25000.00,false,false",
                "3,Carlos Ruiz,2000-01-10,M,40000.00,true,true"
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezado
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();
            // Escribir datos
            for (String cotizante : cotizantes) {
                bw.write(cotizante);
                bw.newLine();
            }
        }
    }

    /**
     * Genera el archivo solicitudes.csv con datos de ejemplo.
     */
    public static void generarArchivoSolicitudes(String rutaArchivo) throws IOException {
        String[] solicitudes = {
                "1,true,Aprobado para traslado",
                "2,false,En revisión",
                "3,true,Aprobado con observaciones"
        };

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezado
            bw.write("IDCotizante,Aprobado,Comentarios");
            bw.newLine();
            // Escribir datos
            for (String solicitud : solicitudes) {
                bw.write(solicitud);
                bw.newLine();
            }
        }
    }
}


