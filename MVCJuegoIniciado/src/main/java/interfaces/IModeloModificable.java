/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import objetosPresentacion.Linea;
import objetosPresentacion.TamañosTablero;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloModificable {

    public void añadirObserver(IVista v);
    
    public void realizarJugada(Linea lineaSelecionada);
    
    public void mostrarPantallaDeJuego(TamañosTablero tamaño);
    
    public void ocultarPantallaDeJuego();
}
