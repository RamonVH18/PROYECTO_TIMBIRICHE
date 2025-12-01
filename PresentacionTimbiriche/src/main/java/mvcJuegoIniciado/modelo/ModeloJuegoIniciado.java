/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.modelo;

import adapters.CuadroAdapter;
import adapters.JugadorAdapter;
import adapters.LineaAdapter;
import adapters.PuntajeAdapter;
import enums.ObserverType;
import static enums.ObserverType.MENU_OPCIONES;
import static enums.ObserverType.PANTALLA_JUEGO;
import interfaces.IModeloJuegoIniciado;
import interfaces.ObservadorJuego;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import objetosPresentacion.JugadorVisual;
import enums.TamañosTablero;
import factorys.TableroFactory;
import gestion.ManejadorObservers;
import mvcJuegoIniciado.interfaces.IVista;
import objetosPresentacion.LineaTablero;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;
import mvcJuegoIniciado.interfaces.IModeloModificableJI;
import objetosModeloJuego.Cuadro;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Puntaje;
import objetosModeloJuego.Punto;
import objetosModeloJuego.TamañoTablero;
import objetosPresentacion.CuadroTablero;
import objetosPresentacion.PuntajeVisual;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoIniciado implements IModeloLeibleJI, IModeloModificableJI, ObservadorJuego {

    private final IModeloJuegoIniciado modeloJuego;
    private PuntoTablero[][] matriz;
    private List<LineaTablero> lineas;

    //
    private List<CuadroTablero> cuadros;
    //

    private final TamañosTablero tamañoTablero;
    private boolean mostrandoPantallaDeJuego;
    private boolean mostrandoTablaJugadores;
    private boolean mostrandoMenuDeOpciones;

    private final List<JugadorVisual> listaJugadores;
    private final List<PuntajeVisual> listaPuntajes;
    private boolean estoyJugando;
    private boolean matrizVacia;

    private final ManejadorObservers manejoObservers;

    public ModeloJuegoIniciado(TamañosTablero tamaño, IModeloJuegoIniciado model) {
        this.matrizVacia = true;
        this.modeloJuego = model;
        this.tamañoTablero = tamaño;
        this.listaJugadores = new ArrayList<>();
        this.listaPuntajes = new ArrayList<>();
        
        modeloJuego.crearMatriz(
                TamañoTablero.valueOf(tamaño.name())
        );
        this.matriz = TableroFactory.crearMatriz(modeloJuego.obtenerMatriz());
        this.lineas = TableroFactory.crearLineas(modeloJuego.obtenerLineas(), tamañoTablero);
        this.cuadros = getCuadros();
        mostrandoPantallaDeJuego = false;
        mostrandoTablaJugadores = false;
        mostrandoMenuDeOpciones = false;

        this.manejoObservers = new ManejadorObservers();
        this.estoyJugando = false;
    }

    @Override
    public boolean estoyJugando() {
        return estoyJugando;
    }

    @Override
    public void añadirObserver(IVista v, ObserverType tipo) {
        manejoObservers.agregarObserver(tipo, v);
    }

    public void eliminarObserver(IVista v, ObserverType tipo) {
        manejoObservers.agregarObserver(tipo, v);
    }

    public void notificar(ObserverType tipo) {
        manejoObservers.notificar(tipo);
    }

    //Metodos Modificables
    @Override
    public void mostrarPantalla(ObserverType tipo) {
        activarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }

    private void activarPantallas(ObserverType tipo) {
        switch (tipo) {
            case PANTALLA_JUEGO -> {
                this.mostrandoPantallaDeJuego = true;
            }
            case MENU_OPCIONES -> {
                this.mostrandoMenuDeOpciones = true;
            }  
        }
    }

    @Override
    public void ocultarPantalla(ObserverType tipo) {
        desactivarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }
    
    public void desactivarPantallas(ObserverType tipo) {
        switch (tipo) {
            case PANTALLA_JUEGO -> {
                this.mostrandoPantallaDeJuego = false;
            }
            case MENU_OPCIONES -> {
                this.mostrandoMenuDeOpciones = false;
            }  
        }
    }

    @Override
    public boolean isMostrandoPantallaDeJuego() {
        return mostrandoPantallaDeJuego;
    }

    @Override
    public boolean isMostrandoTablaJugadores() {
        return mostrandoTablaJugadores;
    }

    @Override
    public boolean isMostrandoMenuDeOpciones() {
        return mostrandoMenuDeOpciones;
    }

    @Override
    public List<JugadorVisual> obtenerJugadores() {
        return JugadorAdapter
                .mapeoJugadores(modeloJuego.obtenerJugadores(), listaJugadores);
    }

    @Override
    public TamañosTablero getTamañoTablero() {
        return this.tamañoTablero;
    }

    @Override
    public void realizarJugada(LineaTablero lineaSelecionada) {
        Linea linea = LineaAdapter.toLinea(lineaSelecionada);
        modeloJuego.realizarJugada(linea);
        lineas = TableroFactory.crearLineas(modeloJuego.obtenerLineas(), tamañoTablero);;
        cuadros = TableroFactory.crearCuadros(modeloJuego.obtenerCuadros(), tamañoTablero);
    }

    @Override
    public PuntoTablero[][] getMatriz() {
        return TableroFactory.crearMatriz(modeloJuego.obtenerMatriz());
    }

    @Override
    public List<LineaTablero> getLineas() {
        return this.lineas;
    }

    //
    @Override
    public List<CuadroTablero> getCuadros() {
        return this.cuadros;
    }

    @Override
    public void cambiarTurno(boolean turno) {
        lineas = TableroFactory.crearLineas(modeloJuego.obtenerLineas(), tamañoTablero);
        cuadros = TableroFactory.crearCuadros(modeloJuego.obtenerCuadros(), tamañoTablero);
        estoyJugando = turno;
        manejoObservers.notificar(ObserverType.PANTALLA_JUEGO);

    }

    @Override
    public List<PuntajeVisual> obtenerPuntajes() {
        return PuntajeAdapter.mapeoPuntajes(
                listaPuntajes,
                obtenerJugadores(), 
                modeloJuego.obtenerPuntajes()
        );
    }

}
