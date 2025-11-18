/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.interfaces;


import java.util.List;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.PuntoTablero;
import objetosPresentacion.TamañosTablero;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloLeibleJI {
    
    public List<JugadorVisual> obtenerJugadores();
    
    
    public boolean isMostrandoPantallaDeJuego();
    
   // public void isMostrandoTableroJuego();
    
    public boolean isMostrandoTablaJugadores();
    
    public boolean isMostrandoMenuDeOpciones();

    public PuntoTablero[][] getMatriz();
    
    public List getLineas();
    
    public List getCuadros();

    public TamañosTablero getTamañoTablero();
}
