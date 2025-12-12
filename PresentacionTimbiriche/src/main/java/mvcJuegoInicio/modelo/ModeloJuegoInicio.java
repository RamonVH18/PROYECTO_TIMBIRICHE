/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.modelo;

import adapters.JugadorLobbyAdapter;
import interfaces.IModeloJuegoInicio;
import interfaces.ObservadorLobby;
import java.util.ArrayList;
import java.util.List;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;
import mvcJuegoInicio.interfaces.IVista;
import objetosPresentacion.JugadorLobbyVisual;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio, ObservadorLobby {

    private final IModeloJuegoInicio modeloJuego;
    private final List<IVista> observers;
    private boolean mostrandoLobby;
    private Runnable accionIniciarPartida;

    public ModeloJuegoInicio(IModeloJuegoInicio modeloJuego) {
        this.modeloJuego = modeloJuego;
        this.observers = new ArrayList<>();
        this.mostrandoLobby = false;
    }

    public void setAccionIniciarPartida(Runnable accion) {
        this.accionIniciarPartida = accion;
    }

    @Override
    public void añadirObserver(IVista v) {
        if (!observers.contains(v)) {
            observers.add(v);
        }
    }

    public void eliminarObserver(IVista v) {
        observers.remove(v);
    }

    private void notificarObservers() {
        for (IVista v : observers) {
            v.actualizar();
        }
    }

    @Override
    public List<JugadorLobbyVisual> obtenerJugadoresLobby() {
        return JugadorLobbyAdapter.mapeoJugadoresLobby(modeloJuego.obtenerJugadores());
    }

    @Override
    public boolean isMostrandoLobby() {
        return mostrandoLobby;
    }

    @Override
    public boolean isJugadorLocalListo() {
        return modeloJuego.getJugadorLocal().isListo();
    }

    @Override
    public void marcarListo() {
        modeloJuego.marcarListo();
        notificarObservers();
    }

    @Override
    public void marcarNoListo() {
        modeloJuego.marcarNoListo();
        notificarObservers();
    }

    @Override
    public void mostrarLobby() {
        this.mostrandoLobby = true;
        for (IVista v : observers) {
            v.mostrar();
        }
    }

    @Override
    public void ocultarLobby() {
        this.mostrandoLobby = false;
        notificarObservers();
    }

    @Override
    public void actualizarLobby() {
        notificarObservers();
    }

    @Override
    public void iniciarPartida() {
        if (accionIniciarPartida != null) {
            accionIniciarPartida.run();
        }
    }

}
