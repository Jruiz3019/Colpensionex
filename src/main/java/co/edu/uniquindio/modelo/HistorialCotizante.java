/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.modelo;
import java.time.LocalDate;
/**
 *
 * @author Jorge
 */

public class HistorialCotizante {
    private int idCotizante;
    private String evento;
    private LocalDate fecha;

    public HistorialCotizante(int idCotizante, String evento, LocalDate fecha) {
        this.idCotizante = idCotizante;
        this.evento = evento;
        this.fecha = fecha;
    }

    
    public int getIdCotizante() {
        return idCotizante; 
    }
    
    public String getEvento() {
        return evento; 
    }
    
    public LocalDate getFecha() { 
        return fecha; 
    }

    public void setIdCotizante(int idCotizante) {
        this.idCotizante = idCotizante;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


}
