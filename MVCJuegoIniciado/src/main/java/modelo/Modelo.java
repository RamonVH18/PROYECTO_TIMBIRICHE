/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import interfaces.IModeloJuego;
import interfaces.IModeloLeible;
import interfaces.IModeloModificable;
import java.util.ArrayList;
import java.util.List;
import modeloJuego.ModeloJuego;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.TableroFactory;
import objetosPresentacion.TamañosTablero;
import vistas.TableroJuego;
import interfaces.IVista;
import java.awt.Point;
import objetosPresentacion.Linea;
import objetosPresentacion.OrientacionLinea;

/**
 *
 * @author Ramon Valencia
 */
public class Modelo implements IModeloLeible, IModeloModificable {

    private Point[][] matriz;
    private final List<Linea> lineas; 
    private IModeloJuego modeloJuego;
    private TamañosTablero tamaño;
    private boolean mostrandoPantallaDeJuego;
    private boolean mostrandoTablaJugadores;
    private boolean mostrandoTableroDeJuego;
  
    private List<JugadorVisual> listaJugadores;
    
    private List<IVista> pantallas;
    private List<IVista> vistas;
    private IVista observadorTablero;
    private IVista observarPantallaJuego;
    
    public Modelo() {
        this.matriz = generarLineas();
        this.lineas = new ArrayList<>();
        this.listaJugadores = new ArrayList<>();
        this.modeloJuego = ModeloJuego.getInstance();
        mostrandoPantallaDeJuego = false;
        mostrandoTablaJugadores = false;
        mostrandoTableroDeJuego = false;
        this.vistas = new ArrayList<>();
        this.pantallas = new ArrayList<>();
        listaJugadores.add(new JugadorVisual("Rodrigo", ""));
        listaJugadores.add(new JugadorVisual("Daniel Miramontes", ""));
    }

    
    //Metodos Observers
    
    public void añadirObserverPantallaDeJuego(IVista tablero){
        this.observarPantallaJuego = tablero;
    }
    public void añadirObserver(IVista v) {
        vistas.add(v);
    }

    public void eliminarObserver(IVista v) {
        vistas.remove(v);
    }
    
    public void añadirObservadorPantallas(IVista v){
      pantallas.add(v);
    }

    public void eliminarObservadorPantallas(IVista v){
      pantallas.remove(v);
    }
    
    public void notificarObservadoresPantallas(){
      for(IVista v : pantallas){
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
    public TableroJuego obtenerTablero() {
        TableroJuego tablero = TableroFactory.crearTablero(tamaño);
        observadorTablero=tablero;
        return tablero;
    }

    @Override
    public List<JugadorVisual> obtenerJugadores() {

        return listaJugadores;
    }

    @Override
    public TamañosTablero getTamañoTablero(){
        return this.tamaño;
    }
    
    //Metodos Modificables

    @Override
    public void mostrarPantallaDeJuego(TamañosTablero tamaño) {
        this.tamaño = tamaño;
        mostrandoPantallaDeJuego = true;
        notificarObservadoresPantallas();
    }

    @Override
    public void ocultarPantallaDeJuego() {
        mostrandoPantallaDeJuego = false;
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
    public void realizarJugada(Linea lineaSelecionada) {
        observadorTablero.actualizar();
    }
    
    public void mostrarPantallaDeJuego(){
        observarPantallaJuego.mostrar();
    }

    public Point[][] getMatriz(){
        return this.matriz;
    }

    public List getLineas(){
        return this.lineas;
    }

    private void generarLineas() {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        // Líneas horizontales
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas - 1; j++) {
                Point a = matriz[i][j];
                Point b = matriz[i][j + 1];
                lineas.add(new Linea(a, b, OrientacionLinea.HORIZONTAL, tamaño.getGrosorLinea()));
            }
        }

        // Líneas verticales
        for (int i = 0; i < filas - 1; i++) {
            for (int j = 0; j < columnas; j++) {
                Point a = matriz[i][j];
                Point b = matriz[i + 1][j];
                lineas.add(new Linea(a, b, OrientacionLinea.VERTICAL, tamaño.getGrosorLinea()));
            }
        }
    }

}
