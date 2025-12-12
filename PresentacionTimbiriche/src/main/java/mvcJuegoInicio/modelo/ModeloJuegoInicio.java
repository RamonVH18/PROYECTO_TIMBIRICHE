/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.modelo;

import enums.ObserverType;
import gestion.ManejadorObservers;
import interfaces.IModeloJuegoInicio;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio {

    private final ManejadorObservers manejoObservers;

    private boolean mostrandoPantallaUnirsePartida;
    private boolean partidaEncontrada;
    private boolean mostrandoMockPartida;
    private IModeloJuegoInicio modelo;
    
    public ModeloJuegoInicio(IModeloJuegoInicio modelo){
        this.manejoObservers = new ManejadorObservers();
        this.mostrandoPantallaUnirsePartida = false;
        this.partidaEncontrada = false;
        
    }

    @Override
    public void seleccionarPartida() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'seleccionarPartida'");
    }

    @Override
    public String obtenerNombrePartida() {
        return modelo.getNombrePartida();
    }

    @Override
    public int obtenerMaxJugadores() {
    return modelo.getCantidadJugadores();
    }

    @Override
    public String obtenerBoardSize() {
        return modelo.getBoardSize();
    }

// metodos de oberservadores


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

    public void mostrarPantalla(ObserverType tipo) {
        activarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }

    private void activarPantallas(ObserverType tipo) {
        switch (tipo) {
        
        }
    }
    public void ocultarPantalla(ObserverType tipo) {
        desactivarPantallas(tipo);
        manejoObservers.mostrarObservers(tipo);
    }

    public void desactivarPantallas(ObserverType tipo) {
        switch (tipo) {
         
    }




}
}