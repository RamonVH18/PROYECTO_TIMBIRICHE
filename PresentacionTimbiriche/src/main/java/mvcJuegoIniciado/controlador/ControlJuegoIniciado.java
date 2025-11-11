/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.controlador;

import objetosPresentacion.Linea;
import objetosPresentacion.TamañosTablero;
import mvcJuegoIniciado.vistas.MensajeError;
import mvcJuegoIniciado.interfaces.IControlJuegoIniciado;
import mvcJuegoIniciado.interfaces.IModeloModificableJI;

/**
 *
 * @author Ramon Valencia
 */
public class ControlJuegoIniciado implements IControlJuegoIniciado {

    private IModeloModificableJI modelo;

    public ControlJuegoIniciado(IModeloModificableJI modelo) {
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

    @Override
    public void mostrarMenuDeOpciones() {
        modelo.mostrarMenuDeOpciones();
    }

    @Override
    public void ocultarMenuDeOpciones() {
        modelo.ocultarMenuDeOpciones();
    }

}
