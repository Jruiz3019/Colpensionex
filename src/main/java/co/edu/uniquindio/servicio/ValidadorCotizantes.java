/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.uniquindio.servicio;
import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.modelo.SolicitudTraslado;
import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author Jorge
 */





public class ValidadorCotizantes {

    public static boolean esMayorDeEdad(Cotizante cotizante) {
        int edad = Period.between(cotizante.getFechaNacimiento(), LocalDate.now()).getYears();
        return edad >= 18;
    }

    public static boolean estaEnMora(Cotizante cotizante) {
        return cotizante.isEstadoMora();
    }

    public static boolean esEmbargable(Cotizante cotizante) {
        return cotizante.isEmbargable();
    }

    public static boolean solicitudDuplicada(SolicitudTraslado nuevaSolicitud, Iterable<SolicitudTraslado> solicitudesExistentes) {
        for (SolicitudTraslado solicitud : solicitudesExistentes) {
            if (solicitud.getIdCotizante() == nuevaSolicitud.getIdCotizante()) {
                return true;
            }
        }
        return false;
    }
}


