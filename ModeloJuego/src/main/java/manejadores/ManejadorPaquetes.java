/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import DTOs.DireccionDTO;
import DTOs.EnvioDTO;
import DTOs.PaqueteDTO;
import envio.DispatcherFactory;
import excepciones.ErrorAlEnviarPaqueteException;
import interfaces.Mediador;
import interfaz.IEmisor;
import interfaz.IReceptorPaquetes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ximena
 */
public class ManejadorPaquetes implements IReceptorPaquetes {
    private final IEmisor emisor;
    private final Map<String, DireccionDTO> direcciones;
    private final Mediador modeloJuego;

    public ManejadorPaquetes(Mediador modeloJuego) {
        this.modeloJuego = modeloJuego;
        emisor = DispatcherFactory.createDispatcher();
        direcciones = new HashMap<>();
    }

    public boolean cambiarClaveDireccionJugador(String antiguoNombreJugador, String nuevoNombreJugador) {
        if (direcciones.containsKey(antiguoNombreJugador)) {
            DireccionDTO direccion = direcciones.get(antiguoNombreJugador);
            direcciones.remove(antiguoNombreJugador);
            direcciones.put(nuevoNombreJugador, direccion);
            return true;
        }
        return false;
    }

    public boolean agregarNuevaDireccion(String nombreJugador, DireccionDTO nuevaDireccion) {
        if (direcciones.containsKey(nombreJugador)) {
            return false;
        }
        direcciones.put(nombreJugador, nuevaDireccion);
        return true;
    }

    public boolean enviarPaqueteDTO(PaqueteDTO paquete) throws ErrorAlEnviarPaqueteException {
        for (Map.Entry<String, DireccionDTO> entrada : direcciones.entrySet()) {
            String nombreJugador = entrada.getKey();
            DireccionDTO direccion = entrada.getValue();
            try {
                emisor.enviarPaquete(new EnvioDTO(direccion.getHost(), direccion.getPort(), paquete));
            } catch (Exception ex) {
                throw new ErrorAlEnviarPaqueteException("Hay un error de conexion con el jugador " + nombreJugador);
            }
        }
        return true;
    }

    public boolean enviarPaqueteDireccion(PaqueteDTO paquete, DireccionDTO direccion)
            throws ErrorAlEnviarPaqueteException {
        try {
            emisor.enviarPaquete(new EnvioDTO(direccion.getHost(), direccion.getPort(), paquete));
        } catch (Exception ex) {
            throw new ErrorAlEnviarPaqueteException("Hay un error de conexion con el jugador ");
        }
        return true;
    }

    @Override
    public void recibirPaquete(PaqueteDTO paquete) {
        if (paquete == null || paquete.getTipoPaquete().isBlank()) {
            return;
        }
        modeloJuego.revisarPaqueteRecibido(paquete);
    }
    
    public boolean isDireccionRegistrada(DireccionDTO direccion) {
        return direcciones.containsValue(direccion);
    }
    

}
