/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.modelo;

import static enums.ObserverType.PANTALLA_UNIRSE_PARTIDA;
import static enums.ObserverType.PARTIDA_CARGADA;

import enums.ObserverType;
import gestion.ManejadorObservers;
import interfaces.IModeloJuegoInicio;
import interfaces.ObservadorInicio;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio, ObservadorInicio {

    private IModeloJuegoInicio modelo;

    private final ManejadorObservers manejoObservers;

    private boolean mostrandoPantallaUnirsePartida;
    private boolean mostrandoMockPartida;
    private boolean mostrandoMainMenu;
    private boolean partidaCargada;

    public ModeloJuegoInicio(IModeloJuegoInicio modelo) {
        this.modelo = modelo;
        this.manejoObservers = new ManejadorObservers();
        this.mostrandoPantallaUnirsePartida = false;
        this.mostrandoMockPartida = false;
        this.mostrandoMainMenu = false;
        this.partidaCargada = false;
    }

    @Override
    public void seleccionarPartida() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seleccionarPartida'");
    }

    @Override
    public String obtenerNombrePartida() {
        return modelo.getNombrePartida();
    }

    @Override
    public int obtenerMaxJugadores() {
        return modelo.getCantidadJugadores();
    }

    @Override
    public String obtenerBoardSize() {
        return modelo.getBoardSize();
    }

    public void activarPantallas(ObserverType tipo) {
        switch (tipo) {
            case PANTALLA_MAIN_MENU -> {
                this.mostrandoMainMenu = true;
                break;
            }
            case PANTALLA_UNIRSE_PARTIDA -> {
                this.mostrandoPantallaUnirsePartida = true;
                break;
            }
            case PANTALLA_CARGA_MOCK -> {
                this.mostrandoMockPartida = true;
            }
            case PARTIDA_CARGADA -> {
                this.partidaCargada = true;
            }
        }
    }

    public void mostrarPantallas(ObserverType tipo) {
        activarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }

    public void desactivarPantallas(ObserverType tipo) {
        switch (tipo) {
            case PANTALLA_MAIN_MENU -> {
                this.mostrandoMainMenu = false;
                break;
            }
            case PANTALLA_UNIRSE_PARTIDA -> {
                this.mostrandoPantallaUnirsePartida = false;
                break;
            }
            case PANTALLA_CARGA_MOCK -> {
                this.mostrandoMockPartida = false;
            }
        }
    }

    public void añadirObserver(IVista v, ObserverType tipo) {
        manejoObservers.agregarObserver(tipo, v);
    }

    public void eliminarObserver(IVista v, ObserverType tipo) {
        manejoObservers.agregarObserver(tipo, v);
    }

    public void notificar(ObserverType tipo) {
        manejoObservers.notificar(tipo);
    }

    @Override
    public void ocultarPantallas(ObserverType tipo) {
        desactivarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }

    @Override
    public boolean isMostrandoMainMenu() {
        return mostrandoMainMenu;
    }

    @Override
    public boolean isMostrandoUnirsePartida() {
        return mostrandoPantallaUnirsePartida;
    }

    @Override
    public boolean isMostrandoPantallaMock() {
        return mostrandoMockPartida;
    }

    @Override
    public boolean isPartidaCargada() {
        return partidaCargada;
    }

    @Override
    public void obtenerPartida() {
        String bs = modelo.getBoardSize();
        if (bs != null && !bs.isBlank()) {
            String nombre = modelo.getNombrePartida();
            int maxJugadores = modelo.getCantidadJugadores();
            this.partidaCargada = true;
            notificar(PARTIDA_CARGADA);
        }

    }

    @Override
    public void mostrarPantallaUnirsePartida() {
        mostrarPantallas(PANTALLA_UNIRSE_PARTIDA);
    }
}