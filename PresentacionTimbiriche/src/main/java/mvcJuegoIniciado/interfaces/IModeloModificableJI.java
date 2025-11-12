/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mvcJuegoIniciado.interfaces;

import objetosPresentacion.LineaTablero;
import mvcJuegoIniciado.vistas.TableroJuego;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloModificableJI {

    public void añadirObserver(IVista v);
    
    public void setObserverTablero(TableroJuego tablero);
    
    public void realizarJugada(LineaTablero lineaSelecionada);
    
    public void mostrarPantallaDeJuego();
    
    public void ocultarPantallaDeJuego();
    
    public void mostrarMenuDeOpciones();
    
    public void ocultarMenuDeOpciones();
}
