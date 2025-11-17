/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.modelo;

import adapters.JugadorAdapter;
import adapters.LineaAdapter;
import interfaces.IModeloJuegoIniciado;
import interfaces.ObservadorJuego;
import java.util.ArrayList;
import java.util.List;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.TamañosTablero;
import mvcJuegoIniciado.vistas.TableroJuego;
import mvcJuegoIniciado.interfaces.IVista;
import objetosPresentacion.LineaTablero;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;
import mvcJuegoIniciado.interfaces.IModeloModificableJI;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;
import objetosModeloJuego.TamañoTablero;
import objetosPresentacion.PuntoTablero;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoIniciado implements IModeloLeibleJI, IModeloModificableJI, ObservadorJuego {

    private final IModeloJuegoIniciado modeloJuego;
    private PuntoTablero[][] matriz;
    private List<LineaTablero> lineas;
    private final TamañosTablero tamaño;
    private boolean mostrandoPantallaDeJuego;
    private boolean mostrandoTablaJugadores;
    private final boolean mostrandoTableroDeJuego;
    private boolean mostrandoMenuDeOpciones;

    private final List<JugadorVisual> listaJugadores;
    private JugadorVisual jugadorEnTurno;

    private final List<IVista> pantallas;
    private final List<IVista> vistas;
    private IVista observadorTablero;
    private IVista observadorPantallaJuego;
    private boolean matrizVacia;

    public ModeloJuegoIniciado(TamañosTablero tamaño, IModeloJuegoIniciado model) {
        this.matrizVacia = true;
        this.modeloJuego = model;
        this.tamaño = tamaño;
        this.matriz = generarMatriz();
        this.lineas = generarLineas();
        generarLineas();
        this.listaJugadores = new ArrayList<>();

        mostrandoPantallaDeJuego = false;
        mostrandoTablaJugadores = false;
        mostrandoTableroDeJuego = false;
        mostrandoMenuDeOpciones = false;
        
        this.vistas = new ArrayList<>();
        this.pantallas = new ArrayList<>();
    }

    //Metodos Observers
    public void añadirObserverPantallaDeJuego(IVista tablero) {
        this.observadorPantallaJuego = tablero;
    }

    @Override
    public void añadirObserver(IVista v) {
        vistas.add(v);
    }

    public void eliminarObserver(IVista v) {
        vistas.remove(v);
    }

    public void añadirObservadorPantallas(IVista v) {
        pantallas.add(v);
    }

    public void eliminarObservadorPantallas(IVista v) {
        pantallas.remove(v);
    }

    public void notificarObservadoresPantallas() {
        for (IVista v : pantallas) {
            v.mostrar();
        }
    }

    public void notificarObservers() {
        for (IVista v : vistas) {
            v.actualizar();
        }
    }

    //Metodos Leibles
    @Override
    public void setObserverTablero(TableroJuego tablero) {

        observadorTablero = tablero;
    }

    //Metodos Modificables
    @Override
    public void mostrarPantallaDeJuego() {
        mostrandoPantallaDeJuego = true;
        notificarObservadoresPantallas();
    }

    @Override
    public void ocultarPantallaDeJuego() {
        mostrandoPantallaDeJuego = false;
        notificarObservadoresPantallas();
    }

    @Override
    public void mostrarMenuDeOpciones() {
        mostrandoMenuDeOpciones = true;
        notificarObservadoresPantallas();
    }

    @Override
    public void ocultarMenuDeOpciones() {
        mostrandoMenuDeOpciones = false;
        notificarObservadoresPantallas();
    }

    public void mostrarTablaJugadores() {

    }

    public void mostrarTableroJuego() {

    }

    @Override
    public boolean isMostrandoPantallaDeJuego() {
        return mostrandoPantallaDeJuego;
    }

    public void setMostrandoPantallaDeJuego(boolean mostrandoPantallaDeJuego) {
        this.mostrandoPantallaDeJuego = mostrandoPantallaDeJuego;
    }

    @Override
    public boolean isMostrandoTablaJugadores() {
        return mostrandoTablaJugadores;
    }

    public void setMostrandoTablaJugadores(boolean mostrandoTablaJugadores) {
        this.mostrandoTablaJugadores = mostrandoTablaJugadores;
    }

    @Override
    public boolean isMostrandoMenuDeOpciones() {
        return mostrandoMenuDeOpciones;
    }

    @Override
    public List<JugadorVisual> obtenerJugadores() {
        List<Jugador> jugadores = modeloJuego.obtenerJugadores();
        for (Jugador j : jugadores) {
            listaJugadores.add(
                    JugadorAdapter.toJVisual(j)
            );
        }
        return listaJugadores;
    }

    @Override
    public TamañosTablero getTamañoTablero() {
        return this.tamaño;
    }

    @Override
    public void realizarJugada(LineaTablero lineaSelecionada) {
        Linea linea = LineaAdapter.toLinea(lineaSelecionada);
        modeloJuego.realizarJugada(linea);
        lineas = generarLineas();
    }

    @Override
    public PuntoTablero[][] getMatriz() {
        return generarMatriz();
    }

    @Override
    public List getLineas() {
        return this.lineas;
    }

    private List<LineaTablero> generarLineas() {
        List<Linea> lineasOriginales = modeloJuego.obtenerLineas();
        List<LineaTablero> lineasTablero = new ArrayList<>();
        for (Linea l : lineasOriginales) {
            LineaTablero linea = LineaAdapter.toLineaTablero(l);
            linea.setGrosorLinea(tamaño.getGrosorLinea());
            lineasTablero.add(linea);
        }
        return lineasTablero;
    }
    
    private PuntoTablero[][] generarMatriz() {
        if (matrizVacia) {
            modeloJuego.crearMatriz(TamañoTablero.PEQUEÑO);
            matrizVacia = false;
        }

        Punto[][] matrizPuntos = modeloJuego.obtenerMatriz();
        int filas = matrizPuntos.length;
        int columnas = matrizPuntos[0].length;
        
        PuntoTablero[][] matrizTablero = new PuntoTablero[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                Punto p = matrizPuntos[i][j];
                matrizTablero[i][j] = new PuntoTablero(p.getCoordenadaX(), p.getCoordenadaY());
            }
        }

        return matrizTablero;
    }

    

    @Override
    public void cambiarTurno() {
        lineas = generarLineas();
        jugadorEnTurno = JugadorAdapter.toJVisual(
                modeloJuego.obtenerJugadorEnTurno()
        );
        observadorPantallaJuego.actualizar();
        
    }

}
