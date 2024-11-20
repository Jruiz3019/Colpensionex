/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.modelo;

/**
 *
 * @author Jorge
 */
public class SolicitudTraslado {
   
    private int idCotizante;
    private boolean aprobado;
    private String comentarios;

    public SolicitudTraslado(int idCotizante, boolean aprobado, String comentarios) {
        this.idCotizante = idCotizante;
        this.aprobado = aprobado;
        this.comentarios = comentarios;
    }

    // Getters y Setters
    public int getIdCotizante() { 
        return idCotizante; 
    }

    public void setIdCotizante(int idCotizante) {
        this.idCotizante = idCotizante;
    }

    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public boolean isAprobado() {
        return aprobado; 
    }
    
    public String getComentarios() {
        return comentarios; 
    }
    
    @Override
public String toString() {
    return "SolicitudTraslado{" +
            "idCotizante=" + idCotizante +
            ", aprobado=" + aprobado +
            ", comentarios='" + comentarios + '\'' +
            '}';
}

}


