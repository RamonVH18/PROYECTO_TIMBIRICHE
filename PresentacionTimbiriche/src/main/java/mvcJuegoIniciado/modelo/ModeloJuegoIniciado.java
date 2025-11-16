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
import java.awt.Point;
import modeloJuego.ModeloJuego;
import objetosPresentacion.LineaTablero;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;
import mvcJuegoIniciado.interfaces.IModeloModificableJI;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoIniciado implements IModeloLeibleJI, IModeloModificableJI, ObservadorJuego {
    
    private final IModeloJuegoIniciado modeloJuego;
    private final Point[][] matriz;
    private final List<LineaTablero> lineas;
    private final TamañosTablero tamaño;
    private boolean mostrandoPantallaDeJuego;
    private boolean mostrandoTablaJugadores;
    private final boolean mostrandoTableroDeJuego;
    private boolean mostrandoMenuDeOpciones;

    private final List<JugadorVisual> listaJugadores;

    private final List<IVista> pantallas;
    private final List<IVista> vistas;
    private IVista observadorTablero;
    private IVista observarPantallaJuego;

    public ModeloJuegoIniciado(TamañosTablero tamaño, IModeloJuegoIniciado model) {
        this.modeloJuego = model;
        this.tamaño = tamaño;
        this.matriz = generarMatriz();
        this.lineas = new ArrayList<>();
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
        this.observarPantallaJuego = tablero;
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
        List <Jugador> jugadores = modeloJuego.obtenerJugadores();
        for(Jugador j : jugadores) {
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
    }

    @Override
    public Point[][] getMatriz() {
        return this.matriz;
    }

    @Override
    public List getLineas() {
        return this.lineas;
    }

    private void generarLineas() {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Líneas horizontales
//        for (int i = 0; i < filas; i++) {
//            for (int j = 0; j < columnas - 1; j++) {
//                Point a = matriz[i][j];
//                Point b = matriz[i][j + 1];
//                lineas.add(new LineaTablero(a, b, OrientacionLinea.HORIZONTAL, tamaño.getGrosorLinea()));
//            }
//        }
//
//        // Líneas verticales
//        for (int i = 0; i < filas - 1; i++) {
//            for (int j = 0; j < columnas; j++) {
//                Point a = matriz[i][j];
//                Point b = matriz[i + 1][j];
//                lineas.add(new LineaTablero(a, b, OrientacionLinea.VERTICAL, tamaño.getGrosorLinea()));
//            }
//        }
    }

    private Point[][] generarMatriz() {
        Integer filas = tamaño.getFilas();
        Integer columnas = tamaño.getColumnas();
        Integer distancia = tamaño.getDistanciaPuntos();
        Integer tamañoPunto = tamaño.getTamañoPunto();
        Integer grosorLinea = tamaño.getGrosorLinea();
        Point[][] puntos = new Point[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                puntos[i][j] = new Point((j + 1) * distancia, (i + 1) * distancia);
            }
        }
        return puntos;
    }

    @Override
    public void cambiarTurno() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
