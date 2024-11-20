/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.repositorio.csv;
import java.util.LinkedList;
import co.edu.uniquindio.modelo.Cotizante;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Jorge
 */

public class RepositorioCotizantes extends RepositorioBase<Cotizante> {
    private final String rutaArchivo;

    public RepositorioCotizantes(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public LinkedList<Cotizante> leerTodasLasFilas() {
        LinkedList<Cotizante> cotizantes = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            br.readLine(); // Saltar el encabezado
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                Cotizante cotizante = new Cotizante(
                    Integer.parseInt(datos[0]),
                    datos[1],
                    LocalDate.parse(datos[2]),
                    datos[3].charAt(0),
                    Double.parseDouble(datos[4]),
                    Boolean.parseBoolean(datos[5]),
                    Boolean.parseBoolean(datos[6])
                );
                cotizantes.add(cotizante);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cotizantes;
    }

    @Override
    public void escribirFila(Cotizante cotizante) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(cotizante.toString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



