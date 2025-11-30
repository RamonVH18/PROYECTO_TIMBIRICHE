/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.controlador;



import enums.ObserverType;
import objetosPresentacion.LineaTablero;
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
    public void realizarJugada(LineaTablero lineaSeleccionada) {
        if (lineaSeleccionada == null) {
            MensajeError.mostrarError("Debe de seleccionar una linea antes de continuar");
            return;
        }
        
        modelo.realizarJugada(lineaSeleccionada);
    }

    @Override
    public void mostrarVista(ObserverType tipo) {
        modelo.mostrarPantalla(tipo);
    }

    @Override
    public void ocultarVista(ObserverType tipo) {
        modelo.ocultarPantalla(tipo);
    }

}
