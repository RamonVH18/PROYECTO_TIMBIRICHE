/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.controlador;

import enums.ObserverType;
import enums.TamañosTablero;
import mvcJuegoIniciado.vistas.MensajeError;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;
import mvcJuegoInicio.vistas.MensajeExitoso;

/**
 *
 * @author Ramon Valencia
 */
public class ControlJuegoInicio implements IControlJuegoInicio {
    private IModeloModificableJInicio modelo;

    public ControlJuegoInicio(IModeloModificableJInicio modelo) {
        this.modelo = modelo;
    }

    @Override
    public void crearPartida(String nombrePartida, int numJugadores, TamañosTablero tamaño) {
        if (nombrePartida == null || nombrePartida.trim().isEmpty()) {
            MensajeError.mostrarError("No se le dio nombre a la partida.");
            return;
        }
        
        if (numJugadores == 0) {
            MensajeError.mostrarError("No se seleccionó el número de jugadores.");
            return;
        }
        
        if (tamaño == null) {
            MensajeError.mostrarError("No se seleccionó el tamaño del tablero.");
            return;
        }
        
        // Crear partida en el modelo
        modelo.crearPartida(nombrePartida, numJugadores, tamaño);
        MensajeExitoso.mostrarMensaje("La partida se ha creado exitosamente\n\n"
                + "Nombre: " + nombrePartida + "\n"
                + "Número de jugadores: " + numJugadores + "\n"
                + "Tamaño de tablero: " + tamaño + "\n");
                
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
