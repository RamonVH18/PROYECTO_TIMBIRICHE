package interfaces;

import objetosModeloJuego.Linea;
import java.util.List;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Puntaje;
import objetosModeloJuego.Punto;
import objetosModeloJuego.TamañoTablero;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
/**
 *
 * @author Ramon Valencia
 */
public interface IModeloJuegoIniciado {
    
    public void crearMatriz(TamañoTablero tamaño);
    
    public Punto[][] obtenerMatriz();
    
    public List<Linea> obtenerLineas();
    
    public List<Cuadro> obtenerCuadros();
    
    public List<Jugador> obtenerJugadores();
    
    public List<Puntaje> obtenerPuntajes();
    
    public Jugador obtenerJugadorEnTurno();
    
    public void realizarJugada(Linea linea);
}
