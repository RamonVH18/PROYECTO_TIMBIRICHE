/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.modelo;

import Enums.TamañoTablero;
import enums.ObserverType;
import enums.TamañosTablero;
import excepciones.DatosIncompletosPartidaException;
import factorys.TableroFactory;
import gestion.ManejadorObservers;
import interfaces.IModeloJuegoInicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;
import objetosPresentacion.CuadroTablero;
import objetosPresentacion.LineaTablero;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio {
    private final IModeloJuegoInicio modeloJuego;
    private final ManejadorObservers manejoObservers;
    
    private PuntoTablero[][] matriz;
    private List<LineaTablero> lineas;
    private List<CuadroTablero> cuadros;

    private TamañosTablero tamañoTablero;
    private String nombrePartida;
    private int numJugadores;
    
    private boolean mostrandoCrearPartida;
    private boolean mostrandoPantallaMock;

    public ModeloJuegoInicio(IModeloJuegoInicio modelo) {
        this.modeloJuego = modelo;
        this.manejoObservers = new ManejadorObservers();
        
        this.mostrandoCrearPartida = false;
        this.mostrandoPantallaMock = false;
    }

    @Override
    public void crearPartida(String nombrePartida, int numJugadores, TamañosTablero tamaño) {
        this.tamañoTablero = tamaño;
        this.nombrePartida = nombrePartida;
        this.numJugadores = numJugadores;
        
        TamañoTablero tamañoModelo = TamañoTablero.valueOf(tamaño.name());
        try {
            modeloJuego.crearPartida(nombrePartida, numJugadores, tamañoModelo);
        } catch (DatosIncompletosPartidaException ex) {
            Logger.getLogger(ModeloJuegoInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Cargar tablero actualizado desde el modelo real
        this.matriz  = TableroFactory.crearMatriz(modeloJuego.obtenerMatriz());
        this.lineas  = TableroFactory.crearLineas(modeloJuego.obtenerLineas(), tamaño);
        this.cuadros = TableroFactory.crearCuadros(modeloJuego.obtenerCuadros(), tamaño);
        
        manejoObservers.notificar(ObserverType.PANTALLA_MOCK);
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
            case PANTALLA_MOCK -> {
                this.mostrandoPantallaMock = true;
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
            case PANTALLA_MOCK-> {
                this.mostrandoPantallaMock = false;
            }
        }
    }

    @Override
    public void añadirObserver(IVista v, ObserverType tipo) {
        manejoObservers.agregarObserver(tipo, v);
    }

    @Override
    public boolean isMostrandoCrearPartida() {
        return mostrandoCrearPartida;
    }

    @Override
    public boolean isMostrandoPantallaMock() {
        return mostrandoPantallaMock;
    }

    @Override
    public TamañosTablero getTamañoTablero() {
        return tamañoTablero;
    }

    @Override
    public String getNombrePartida() {
        return nombrePartida;
    }

    @Override
    public int getNumJugadores() {
        return numJugadores;
    }

    @Override
    public PuntoTablero[][] getMatriz() {
        return matriz;
    }

    @Override
    public List getLineas() {
        return lineas;
    }

    @Override
    public List getCuadros() {
        return cuadros;
    }
    
}
