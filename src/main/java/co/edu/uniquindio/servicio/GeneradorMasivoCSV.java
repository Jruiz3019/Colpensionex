/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.servicio;

import co.edu.uniquindio.modelo.Cotizante;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;



/**
 *
 * @author Jorge
 */



public class GeneradorMasivoCSV {
    public static void main(String[] args) {
        String carpetaDestino = "C:/Users/Jorge/OneDrive/Escritorio/colpensionex/colpensionex/masivos";
        int totalArchivos = 10000;
        int datosPorArchivo = 100;

        try {
            // Crear carpeta si no existe
            File carpeta = new File(carpetaDestino);
            if (!carpeta.exists()) {
                if (carpeta.mkdirs()) {
                    System.out.println("Carpeta creada: " + carpetaDestino);
                } else {
                    System.err.println("No se pudo crear la carpeta: " + carpetaDestino);
                    return;
                }
            }

            for (int i = 1; i <= totalArchivos; i++) {
                String rutaArchivo = carpetaDestino + "/cotizantes_" + i + ".csv";
                generarArchivoCSV(rutaArchivo, datosPorArchivo);
                if (i % 1000 == 0) {
                    System.out.println("Archivos generados: " + i);
                }
            }
            System.out.println("¡Archivos masivos generados exitosamente!");
        } catch (IOException e) {
            System.err.println("Error al generar archivos masivos: " + e.getMessage());
        }
    }

    private static void generarArchivoCSV(String rutaArchivo, int cantidadDatos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            // Escribir encabezado
            bw.write("ID,Nombre,FechaNacimiento,Sexo,ValorUnitario,EstadoMora,Embargable");
            bw.newLine();

            Random random = new Random();
            for (int i = 1; i <= cantidadDatos; i++) {
                int id = random.nextInt(1000000);
                String nombre = "Cotizante" + id;
                LocalDate fechaNacimiento = LocalDate.of(
                        1950 + random.nextInt(50), 1 + random.nextInt(12), 1 + random.nextInt(28));
                char sexo = random.nextBoolean() ? 'M' : 'F';
                double valorUnitario = 20000 + random.nextDouble() * 30000;
                boolean estadoMora = random.nextBoolean();
                boolean embargable = random.nextBoolean();

                bw.write(id + "," + nombre + "," + fechaNacimiento + "," + sexo + "," +
                        valorUnitario + "," + estadoMora + "," + embargable);
                bw.newLine();
            }
        }
    }
}
