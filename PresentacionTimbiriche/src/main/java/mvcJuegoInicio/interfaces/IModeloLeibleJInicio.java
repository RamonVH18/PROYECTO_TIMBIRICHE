/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoInicio.interfaces;

import enums.TamañosTablero;
import java.util.List;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloLeibleJInicio {
    
    public boolean isMostrandoCrearPartida();
    
    public boolean isMostrandoPantallaMock();
    
    public TamañosTablero getTamañoTablero();
    
    public String getNombrePartida();
    
    public int getNumJugadores();
    
    public PuntoTablero[][] getMatriz();
    
    public List getLineas();
    
    public List getCuadros();
    
}
