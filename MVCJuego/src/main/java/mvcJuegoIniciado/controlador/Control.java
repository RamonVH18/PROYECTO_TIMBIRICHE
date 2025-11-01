/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.controlador;

import mvcJuegoIniciado.interfaces.IControl;
import mvcJuegoIniciado.interfaces.IModeloModificable;
import objetosPresentacion.Linea;
import objetosPresentacion.TamañosTablero;
import mvcJuegoIniciado.vistas.MensajeError;

/**
 *
 * @author Ramon Valencia
 */
public class Control implements IControl {

    private IModeloModificable modelo;

    public Control(IModeloModificable modelo) {
        this.modelo = modelo;
    }

    @Override
    public void realizarJugada(Linea lineaSeleccionada) {
        if (lineaSeleccionada == null) {
            MensajeError.mostrarError("Debe de seleccionar una linea antes de continuar");
        }
        
        modelo.realizarJugada(lineaSeleccionada);
    }

    @Override
    public void mostrarPantallaDeJuego() {
        modelo.mostrarPantallaDeJuego();
    }

    @Override
    public void ocultarPantallaDeJuego() {
        modelo.ocultarPantallaDeJuego();
    }

    

}
