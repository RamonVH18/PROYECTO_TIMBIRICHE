/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import interfaces.IModeloJuego;
import interfaces.IModeloLeible;
import interfaces.IModeloModificable;
import interfaces.Vista;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import modeloJuego.ModeloJuego;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.TableroFactory;
import objetosPresentacion.TamañosTablero;
import vistas.TableroJuego;

/**
 *
 * @author Ramon Valencia
 */
public class Modelo implements IModeloLeible, IModeloModificable{
    private static Modelo instanciaModelo;
    IModeloJuego modeloJuego = ModeloJuego.getInstance();
    
    private List<JugadorVisual> listaJugadores = new ArrayList<>();
    
    List<Vista> vistas = new ArrayList<>();
    
    private Modelo(){
    }
    
    public static Modelo getInstaciaModelo() {
        if (instanciaModelo == null) {
            instanciaModelo = new Modelo();
        }
        return instanciaModelo;
    }
    
    public void añadirObserver(Vista v) {
        vistas.add(v);
    }
    
    public void eliminarObserver(Vista v) {
        vistas.remove(v);
    }
    
    public void notificarObservers() {
        for(Vista v : vistas) {
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
        listaJugadores.add(new JugadorVisual("Rodrigo", ""));
        listaJugadores.add(new JugadorVisual("Daniel Miramontes", ""));
        return listaJugadores;
    }

    @Override
    public void agregarJugador() {
        listaJugadores.add(new JugadorVisual("Isaaquito", ""));
        notificarObservers();
    }

}
