/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import co.edu.uniquindio.modelo.Cotizante;
import co.edu.uniquindio.modelo.SolicitudTraslado;
import co.edu.uniquindio.servicio.ValidadorCotizantes;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jorge
 */


public class ValidadorCotizantesTest {

    @Test
    public void testEsMayorDeEdad() {
        Cotizante menor = new Cotizante(1, "Carlos", LocalDate.now().minusYears(17), 'M', 20000.0, false, false);
        Cotizante mayor = new Cotizante(2, "Ana", LocalDate.now().minusYears(30), 'F', 30000.0, false, false);
        assertFalse(ValidadorCotizantes.esMayorDeEdad(menor));
        assertTrue(ValidadorCotizantes.esMayorDeEdad(mayor));
    }

    @Test
    public void testEstaEnMora() {
        Cotizante enMora = new Cotizante(3, "Juan", LocalDate.now().minusYears(25), 'M', 10000.0, true, false);
        Cotizante alDia = new Cotizante(4, "Maria", LocalDate.now().minusYears(28), 'F', 15000.0, false, false);
        assertTrue(ValidadorCotizantes.estaEnMora(enMora));
        assertFalse(ValidadorCotizantes.estaEnMora(alDia));
    }

    @Test
    public void testEsEmbargable() {
        Cotizante embargable = new Cotizante(5, "Pedro", LocalDate.now().minusYears(40), 'M', 50000.0, false, true);
        Cotizante noEmbargable = new Cotizante(6, "Luis", LocalDate.now().minusYears(35), 'M', 60000.0, false, false);
        assertTrue(ValidadorCotizantes.esEmbargable(embargable));
        assertFalse(ValidadorCotizantes.esEmbargable(noEmbargable));
    }

    @Test
    public void testSolicitudDuplicada() {
        List<SolicitudTraslado> solicitudes = new ArrayList<>();
        solicitudes.add(new SolicitudTraslado(1, true, "Aprobada"));
        solicitudes.add(new SolicitudTraslado(2, false, "Pendiente"));

        SolicitudTraslado nuevaSolicitud = new SolicitudTraslado(1, true, "Aprobada de nuevo");
        assertTrue(ValidadorCotizantes.solicitudDuplicada(nuevaSolicitud, solicitudes));

        SolicitudTraslado solicitudNoDuplicada = new SolicitudTraslado(3, true, "Nueva solicitud");
        assertFalse(ValidadorCotizantes.solicitudDuplicada(solicitudNoDuplicada, solicitudes));
    }
}
