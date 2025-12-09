/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.modelo;

import Enums.ColorJugador;
import Enums.ImagenJugador;
import enums.ObserverType;
import static enums.ObserverType.REGISTRAR_JUGADOR;
import static enums.ObserverType.SELECCION_IMAGEN;
import excepciones.DatosJugadorInvalidosException;
import gestion.ManejadorObservers;
import interfaces.IModeloJuegoInicio;
import interfaces.ObservadorInicio;
import javax.swing.JOptionPane;
import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio, ObservadorInicio{

    private IModeloJuegoInicio modelo;
    private ImagenJugador imagenAlmacenada;
    private ColorJugador colorAlmacenado;
    private boolean mostrandoPantallaRegistrarJugador;
    private boolean mostrandoPantallaSeleccionImagen;
    private final ManejadorObservers manejadorObservers;

    public ModeloJuegoInicio(IModeloJuegoInicio modelo) {
        this.modelo = modelo;
        this.manejadorObservers = new ManejadorObservers();
        this.mostrandoPantallaRegistrarJugador = false;
        this.mostrandoPantallaSeleccionImagen = false;
    }

    @Override
    public ImagenJugador obtenerImagenAlmacenada() {
        return imagenAlmacenada;
    }

    @Override
    public ColorJugador obtenerColorAlmacenado() {
        return colorAlmacenado;
    }

    @Override
    public void almacenarImagenYColorJugador(ImagenJugador imagen, ColorJugador color) {
        this.imagenAlmacenada = imagen;
        this.colorAlmacenado = color;
        ocultarVista(ObserverType.SELECCION_IMAGEN);
        manejadorObservers.notificar(ObserverType.REGISTRAR_JUGADOR);
    }

    @Override
    public void registrarJugador(String nombre, ImagenJugador imagen, ColorJugador color) {
        try {
            modelo.guardarInformacionJugador(nombre, imagen, color);
            ocultarVista(REGISTRAR_JUGADOR);
        } catch (DatosJugadorInvalidosException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void mostrarVista(ObserverType tipo) {
        activarPantallas(tipo);
        manejadorObservers.mostrarObservers(tipo);
    }

    private void activarPantallas(ObserverType tipo) {
        switch (tipo) {
            case SELECCION_IMAGEN -> {
                this.mostrandoPantallaSeleccionImagen = true;
            }
            case REGISTRAR_JUGADOR -> {
                this.mostrandoPantallaRegistrarJugador = true;
            }
        }
    }

    @Override
    public void ocultarVista(ObserverType tipo) {
        desactivarPantallas(tipo);
        manejadorObservers.mostrarObservers(tipo);
    }

    private void desactivarPantallas(ObserverType tipo) {
        switch (tipo) {
            case SELECCION_IMAGEN -> {
                this.mostrandoPantallaSeleccionImagen = false;
            }
            case REGISTRAR_JUGADOR -> {
                this.mostrandoPantallaRegistrarJugador = false;
            }
        }
    }

    @Override
    public boolean isMostrandoPantallaRegistrarJugador() {
        return mostrandoPantallaRegistrarJugador;
    }

    @Override
    public boolean isMostrandoPantallaSeleccionarImagen() {
        return mostrandoPantallaSeleccionImagen;
    }
    
    public void añadirObserver(IVista v, ObserverType tipo) {
        manejadorObservers.agregarObserver(tipo, v);
    }

    public void eliminarObserver(IVista v, ObserverType tipo) {
        manejadorObservers.agregarObserver(tipo, v);
    }

    public void notificar(ObserverType tipo) {
        manejadorObservers.notificar(tipo);
    }
}
