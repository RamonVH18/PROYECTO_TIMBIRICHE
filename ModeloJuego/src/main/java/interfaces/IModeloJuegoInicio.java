/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import Enums.TamañoTablero;
import excepciones.DatosIncompletosPartidaException;
import excepciones.DatosJugadorInvalidosException;
import java.util.List;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloJuegoInicio {
    
    public void guardarInformacionJugador(String idJugador, String nombreJugador, ImagenJugador imagenJugador, ColorJugador colorJugador) throws DatosJugadorInvalidosException;
    
    public void editarInformacionJugador(String nombreJugador, ImagenJugador imagenJugador, ColorJugador colorJugador) throws DatosJugadorInvalidosException;

    public void crearPartida(String nombrePartida, int numJugadores, TamañoTablero tamaño) throws DatosIncompletosPartidaException;
    
    public void crearMatriz(TamañoTablero tamaño);
    
    public Punto[][] obtenerMatriz();
    
    public List<Linea> obtenerLineas();
    
    public List<Cuadro> obtenerCuadros();
    
}
