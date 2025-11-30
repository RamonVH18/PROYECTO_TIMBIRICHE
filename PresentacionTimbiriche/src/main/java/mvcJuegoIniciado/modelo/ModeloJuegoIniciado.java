/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.modelo;

import adapters.CuadroAdapter;
import adapters.JugadorAdapter;
import adapters.LineaAdapter;
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
        this.matriz = generarMatriz();
        this.lineas = generarLineas();
        this.cuadros = generarCuadros();
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

        List<Jugador> jugadores = modeloJuego.obtenerJugadores();
        listaJugadores.clear();
        for (Jugador j : jugadores) {
            listaJugadores.add(
                    JugadorAdapter.toJVisual(j)
            );
        }
        return listaJugadores;
    }

    @Override
    public TamañosTablero getTamañoTablero() {
        return this.tamañoTablero;
    }

    @Override
    public void realizarJugada(LineaTablero lineaSelecionada) {
        Linea linea = LineaAdapter.toLinea(lineaSelecionada);
        modeloJuego.realizarJugada(linea);
        lineas = generarLineas();
        cuadros = generarCuadros();
    }

    @Override
    public PuntoTablero[][] getMatriz() {
        return generarMatriz();
    }

    @Override
    public List getLineas() {
        return this.lineas;
    }

    //
    @Override
    public List<CuadroTablero> getCuadros() {
        return this.cuadros;
    }
    //

    private List<LineaTablero> generarLineas() {
        List<Linea> lineasOriginales = modeloJuego.obtenerLineas();
        List<LineaTablero> lineasTablero = new ArrayList<>();
        for (Linea l : lineasOriginales) {
            LineaTablero linea = LineaAdapter.toLineaTablero(l);
            linea.setGrosorLinea(tamañoTablero.getGrosorLinea());
            lineasTablero.add(linea);
        }
        return lineasTablero;
    }

    //
    private List<CuadroTablero> generarCuadros() {
        List<Cuadro> cuadrosOriginales = modeloJuego.obtenerCuadros();
        List<CuadroTablero> cuadrosTableros = new ArrayList<>();

        int distancia = tamañoTablero.getDistanciaPuntos();

        for (Cuadro c : cuadrosOriginales) {
            int x = c.getLineaIzquierda().getPuntoA().getCoordenadaX() * distancia;
            int y = c.getLineaSuperior().getPuntoA().getCoordenadaY() * distancia;

            CuadroTablero cuadro = CuadroAdapter.toCuadroTablero(c, new Point(x, y), distancia);

            cuadrosTableros.add(cuadro);
        }
        return cuadrosTableros;

    }
    //

    private PuntoTablero[][] generarMatriz() {
        if (matrizVacia) {
            TamañoTablero tamaño = TamañoTablero.valueOf(tamañoTablero.name());
            modeloJuego.crearMatriz(tamaño);
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
    public void cambiarTurno(boolean turno) {
        lineas = generarLineas();
        cuadros = generarCuadros();
        estoyJugando = turno;
        manejoObservers.notificar(ObserverType.PANTALLA_JUEGO);

    }

    @Override
    public List<PuntajeVisual> obtenerPuntajes() {
        obtenerJugadores();
        listaPuntajes.clear();
        List<Puntaje> puntajes = modeloJuego.obtenerPuntajes();
        for (Puntaje p : puntajes) {
            for (JugadorVisual j : listaJugadores) {
                if (p.getIdJugador().equals(j.getIdentificador())) {
                    listaPuntajes.add(
                            new PuntajeVisual(j.getNombre(), p.getPuntuacion(), j.getColor())
                    );
                }
            }
        }
        return listaPuntajes;
    }

}
