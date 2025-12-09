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
import java.util.logging.Level;
import java.util.logging.Logger;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IModeloModificableJInicio;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuegoInicio implements IModeloLeibleJInicio, IModeloModificableJInicio {

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
        desactivarPantallas(ObserverType.SELECCION_IMAGEN);
        this.imagenAlmacenada = imagen;
        this.colorAlmacenado = color;
        mostrarVista(ObserverType.SELECCION_IMAGEN);
    }

    @Override
    public boolean registrarJugador(String nombre, ImagenJugador imagen, ColorJugador color) {
        try {
            modelo.guardarInformacionJugador(nombre, imagen, color);
            return true;
        } catch (DatosJugadorInvalidosException ex) {
            Logger.getLogger(ModeloJuegoInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
}
