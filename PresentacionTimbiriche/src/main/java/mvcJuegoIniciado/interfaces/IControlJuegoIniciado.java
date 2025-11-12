/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoIniciado.interfaces;

import objetosPresentacion.LineaTablero;

/**
 *
 * @author Ramon Valencia
 */
public interface IControlJuegoIniciado {

    public void realizarJugada(LineaTablero lineaSeleccionada);
    
    public void mostrarPantallaDeJuego();
    
    public void ocultarPantallaDeJuego();
    
    public void mostrarMenuDeOpciones();
    
    public void ocultarMenuDeOpciones();
}
