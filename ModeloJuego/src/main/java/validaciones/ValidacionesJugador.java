/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import excepciones.ImagenJugadorInexistenteException;
import excepciones.NombreJugadorVacioException;

/**
 *
 * @author Ramon Valencia
 */
public class ValidacionesJugador {
    
    public static void validarCreacionJugador(String nombreJugador, String imagenJugador, String colorJugador) throws NombreJugadorVacioException, ImagenJugadorInexistenteException {
        if (nombreJugador == null || nombreJugador.isBlank()){
            throw new NombreJugadorVacioException("El nombre que ingreso no puede estar vacio");
        }
        if (imagenJugador == null || imagenJugador.isBlank()) {
            throw new ImagenJugadorInexistenteException("Tiene que elegir una imagen para el jugador");
        }
        if (colorJugador == null || colorJugador.isBlank()) {
            
        }
    }
}
