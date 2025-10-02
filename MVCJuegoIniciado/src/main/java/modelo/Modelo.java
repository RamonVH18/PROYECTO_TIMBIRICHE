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
import javax.swing.JPanel;
import modeloJuego.ModeloJuego;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.TableroFactory;
import objetosPresentacion.TamañosTablero;
import vistas.TableroJuego;
import interfaces.IVista;

/**
 *
 * @author Ramon Valencia
 */
public class Modelo implements IModeloLeible, IModeloModificable {

    private static Modelo instanciaModelo;
    private IModeloJuego modeloJuego;

    private boolean mostrandoPantallaDeJuego;
    private boolean mostrandoTablaJugadores;
    private boolean mostrandoTableroJuego;

    private static List<JugadorVisual> listaJugadores;
    
    private List<IVista> pantallas;
    private List<IVista> vistas;

    private Modelo() {
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

    public static Modelo getInstaciaModelo() {
        if (instanciaModelo == null) {
            instanciaModelo = new Modelo();
        }
        return instanciaModelo;
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
    
    public void notificarObservadoresPantallas(IVista v){
      for(IVista vi : pantallas){
        v.mostrar();
      }
    }
    public void notificarObservers() {
        for (IVista v : vistas) {
            v.actualizar();
        }
    }

    @Override
    public JPanel obtenerTablero() {
        TableroJuego tablero = TableroFactory.crearTablero(TamañosTablero.GRANDE);
        return tablero;
    }

    @Override
    public List<JugadorVisual> obtenerJugadores() {

        return listaJugadores;
    }

    @Override
    public void agregarJugador() {
        listaJugadores.add(new JugadorVisual("Isaaquito", ""));
        notificarObservers();
    }

    public void mostrarPantallaDeJuego() {

    }

    public void mostrarTablaJugadores() {

    }

    public void mostrarTableroJuego() {

    }

}
