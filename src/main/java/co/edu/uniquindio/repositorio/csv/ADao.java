package co.edu.uniquindio.repositorio.csv;



import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class ADao<Entidad> {
    private final String rutaArchivo;

    public ADao(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    /**
     * Lee todas las filas desde el archivo CSV.
     * 
     * @return Lista de entidades parseadas desde el archivo.
     */
public List<Entidad> leerTodasLasFilas() {
    File archivo = new File(rutaArchivo);
    if (!archivo.exists()) {
        System.err.println("El archivo no existe: " + rutaArchivo);
        return new ArrayList<>();
    }
    if (archivo.length() == 0) {
        System.err.println("El archivo está vacío: " + rutaArchivo);
        return new ArrayList<>();
    }
        return null;
}

    /**
     * Escribe una nueva fila en el archivo CSV.
     * 
     * @param entidad La entidad a escribir.
     */
    public void escribirFila(Entidad entidad) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(convertirAString(entidad));
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convierte una línea del archivo CSV en una entidad.
     * 
     * @param linea Línea leída del archivo.
     * @return Entidad parseada.
     */
    protected abstract Entidad parsearLinea(String linea);

    /**
     * Convierte una entidad en una línea para el archivo CSV.
     * 
     * @param entidad La entidad a convertir.
     * @return Representación en String de la entidad.
     */
 
    protected abstract String convertirAString(Entidad entidad);
    
   

}

