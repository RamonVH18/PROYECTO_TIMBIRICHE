/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.modelo;

import Enums.TamañoTablero;
import enums.ObserverType;
import enums.TamañosTablero;
import excepciones.DatosIncompletosPartidaException;
import gestion.ManejadorObservers;
import interfaces.IModeloJuegoInicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio {
    private final IModeloJuegoInicio modeloJuego;
    private final ManejadorObservers manejoObservers;
    
    private boolean mostrandoCrearPartida;
    private boolean mostrandoPantallaJuego;
    private boolean mostrandoPantallaRegistroJugador;

    public ModeloJuegoInicio(IModeloJuegoInicio modelo) {
        this.modeloJuego = modelo;
        this.manejoObservers = new ManejadorObservers();
    }

    @Override
    public void crearPartida(String nombrePartida, int numJugadores, TamañosTablero tamaño) {
        TamañoTablero tamañoModelo = TamañoTablero.valueOf(tamaño.name());
        try {
            modeloJuego.crearPartida(nombrePartida, numJugadores, tamañoModelo);
        } catch (DatosIncompletosPartidaException ex) {
            Logger.getLogger(ModeloJuegoInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        manejoObservers.notificar(ObserverType.PANTALLA_JUEGO);
    }

    @Override
    public void mostrarPantalla(ObserverType tipo) {
        activarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }
    
    private void activarPantallas(ObserverType tipo) {
        switch (tipo) {
            case CREAR_PARTIDA -> {
                this.mostrandoCrearPartida = true;
            }
            case PANTALLA_JUEGO -> {
                this.mostrandoPantallaJuego = true;
            }
            case REGISTRAR_JUGADOR -> {
                this.mostrandoPantallaRegistroJugador = true;
            }
        }
    }

    @Override
    public void ocultarPantalla(ObserverType tipo) {
        desactivarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }
    
    private void desactivarPantallas(ObserverType tipo) {
        switch (tipo) {
            case CREAR_PARTIDA -> {
                this.mostrandoCrearPartida = false;
            }
            case PANTALLA_JUEGO -> {
                this.mostrandoPantallaJuego = false;
            }
            case REGISTRAR_JUGADOR -> {
                this.mostrandoPantallaRegistroJugador = false;
            }
        }
    }

    @Override
    public void añadirObserver(IVista v, ObserverType tipo) {
        manejoObservers.agregarObserver(tipo, v);
    }
    
}
