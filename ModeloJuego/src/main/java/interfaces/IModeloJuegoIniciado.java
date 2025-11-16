package interfaces;

import objetosModeloJuego.Linea;
import estructurasDatos.MatrizPuntos;
import java.util.List;
import objetosModeloJuego.Jugador;
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
    
    public MatrizPuntos obtenerMatriz();
    
    public List<Jugador> obtenerJugadores();
    
    public void realizarJugada(Linea linea);
}
