/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import objetosPresentacion.Linea;
import objetosPresentacion.TamañosTablero;
import vistas.TableroJuego;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloModificable {

    public void añadirObserver(IVista v);
    
    public void setObserverTablero(TableroJuego tablero);
    
    public void realizarJugada(Linea lineaSelecionada);
    
    public void mostrarPantallaDeJuego();
    
    public void ocultarPantallaDeJuego();
}
