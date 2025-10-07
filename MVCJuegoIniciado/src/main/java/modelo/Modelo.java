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
import objetosPresentacion.Linea;

/**
 *
 * @author Ramon Valencia
 */
public class Modelo implements IModeloLeible, IModeloModificable {

    private IModeloJuego modeloJuego;

    private boolean mostrandoPantallaDeJuego;
    private boolean mostrandoTablaJugadores;
    private boolean mostrandoTableroJuego;

    private static List<JugadorVisual> listaJugadores;
    
    private List<IVista> pantallas;
    private List<IVista> vistas;

    public Modelo() {
        this.listaJugadores = new ArrayList<>();
        this.modeloJuego = ModeloJuego.getInstance();
        mostrandoPantallaDeJuego = false;
        mostrandoTablaJugadores = false;
        mostrandoTableroJuego = false;
        this.vistas = new ArrayList<>();
        this.pantallas = new ArrayList<>();
        listaJugadores.add(new JugadorVisual("Rodrigo", ""));
        listaJugadores.add(new JugadorVisual("Daniel Miramontes", ""));
    }

    
    //Metodos Observers
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
            //EL TABLERO DEBE DE TENER SU PROPIO OBSERVER
            v.actualizar();
        }
    }
 //Metodos Leibles
    @Override
    public TableroJuego obtenerTablero() {
        TableroJuego tablero = TableroFactory.crearTablero(TamañosTablero.PEQUEÑO);
        //EL TABLERO DEBE DE TENER SU PROPIO OBSERVER
        añadirObserver(tablero);
        return tablero;
    }

    @Override
    public List<JugadorVisual> obtenerJugadores() {

        return listaJugadores;
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
        //EL TABLERO DEBE DE TENER SU PROPIO OBSERVER
        notificarObservers();
    }

}
