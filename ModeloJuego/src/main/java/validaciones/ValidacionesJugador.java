/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import excepciones.DatosJugadorInvalidosException;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ramon Valencia
 */
public class ValidacionesJugador {

    public static void validarCreacionJugador(String nombreJugador, ImagenJugador imagenJugador, ColorJugador colorJugador)
            throws DatosJugadorInvalidosException {
        if (nombreJugador == null || nombreJugador.isBlank()) {
            throw new DatosJugadorInvalidosException("El nombre que ingreso no puede estar vacio");
        }
        if (imagenJugador == null) {
            throw new DatosJugadorInvalidosException("Tiene que elegir una imagen para el jugador");
        }
        if (colorJugador == null) {
            throw new DatosJugadorInvalidosException("Tiene que elegir un color para el jugador");
        }
    }

    public static void validarCambiosJugador(Jugador jugadorLocal, String nuevoNombre, ImagenJugador nuevaImagen, ColorJugador nuevoColor)
            throws DatosJugadorInvalidosException {

        if (jugadorLocal == null) {
            throw new DatosJugadorInvalidosException("No se encontró al jugador a modificar.");
        }

        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new DatosJugadorInvalidosException("El nombre del jugador no puede estar vacío.");
        }

        if (nuevaImagen == null) {
            throw new DatosJugadorInvalidosException("La imagen del jugador no puede ser nula.");
        }

        if (nuevoColor == null) {
            throw new DatosJugadorInvalidosException("El color del jugador no puede ser nulo.");
        }


        boolean mismoNombre = jugadorLocal.getNombre().equals(nuevoNombre);
        boolean mismaImagen = jugadorLocal.getImagen().equals(nuevaImagen);
        boolean mismoColor = jugadorLocal.getColor().equals(nuevoColor);

        if (mismoNombre && mismaImagen && mismoColor) {
            throw new DatosJugadorInvalidosException("No se realizaron cambios debido a que los valores son los mismos.");
        }
    }
}
