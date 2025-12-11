/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manejadores;

import DTOs.DireccionDTO;
import DTOs.EnvioDTO;
import DTOs.PaqueteDTO;
import DTOs.PartidaDTO;
import envio.DispatcherFactory;
import eventos.CambioJugadorEvent;
import eventos.LineaPintadaEvent;
import eventos.NuevoJugadorEvent;
import excepciones.ErrorAlEnviarPaqueteException;
import excepciones.PaqueteVacioAlSerializarException;
import interfaz.IEmisor;

import java.util.HashMap;
import java.util.Map;
import serializador.Serializador;

/**
 *
 * @author Ximena
 */
public class ManejoEnvioPaquetes {

    private final IEmisor emisor;
    private final Map<String, DireccionDTO> direcciones;
    private final Serializador serializador;

    public ManejoEnvioPaquetes() {
        emisor = DispatcherFactory.createDispatcher();
        direcciones = new HashMap<>();
        serializador = new Serializador();
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

    private boolean enviarPaqueteDTO(PaqueteDTO paquete) throws ErrorAlEnviarPaqueteException {
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

    private boolean enviarPaqueteDireccion(PaqueteDTO paquete, DireccionDTO direccion)
            throws ErrorAlEnviarPaqueteException {
        try {
            emisor.enviarPaquete(new EnvioDTO(direccion.getHost(), direccion.getPort(), paquete));
        } catch (Exception ex) {
            throw new ErrorAlEnviarPaqueteException("Hay un error de conexion con el jugador ");
        }
        return true;
    }

    public boolean isDireccionRegistrada(DireccionDTO direccion) {
        return direcciones.containsValue(direccion);
    }

    public void conectarseAServidor(DireccionDTO direccionLocal, DireccionDTO direccionServidor)
            throws PaqueteVacioAlSerializarException, ErrorAlEnviarPaqueteException {
        PaqueteDTO paquete = serializador.serializarDireccionAPaquete("registroPeer", direccionLocal);
        enviarPaqueteDireccion(paquete, direccionServidor);
    }

    public void transmitirNuevaJugada(LineaPintadaEvent lpEvent)
            throws PaqueteVacioAlSerializarException, ErrorAlEnviarPaqueteException {
        PaqueteDTO paquete = serializador.serializarLineaPintadaEvent("nuevaLineaPintada", lpEvent);
        enviarPaqueteDTO(paquete);
    }

    public void solicitarInfoNuevoJugador(DireccionDTO direccionLocal, DireccionDTO direccionJugador)
            throws PaqueteVacioAlSerializarException, ErrorAlEnviarPaqueteException {
        PaqueteDTO paquete = serializador.serializarDireccionAPaquete("solicitudInfoJugador", direccionLocal);
        enviarPaqueteDireccion(paquete, direccionJugador);
    }

    public void transmitirInfoANuevoJugador(DireccionDTO direccionJugador, NuevoJugadorEvent njEvent)
            throws PaqueteVacioAlSerializarException, ErrorAlEnviarPaqueteException {
        PaqueteDTO paquete = serializador.serializarNuevoJugadorEvent("nuevaInfoJugador", njEvent);
        enviarPaqueteDireccion(paquete, direccionJugador);
    }

    public void transmitirCambioDatosJugador(CambioJugadorEvent cjEvent)
            throws PaqueteVacioAlSerializarException, ErrorAlEnviarPaqueteException {
        PaqueteDTO paquete = serializador.serializarCambioJugadorEvent("CambioDatosJugador", cjEvent);
        enviarPaqueteDTO(paquete);
    }

    public void solicitarInfoPartida() throws PaqueteVacioAlSerializarException, ErrorAlEnviarPaqueteException {
        try {
            PaqueteDTO paquete = serializador.serializarSolicitudInfoPartida("solicitudInfoPartida");
            enviarPaqueteDTO(paquete);
        } catch (PaqueteVacioAlSerializarException ex) {
            throw new PaqueteVacioAlSerializarException("Error al serializar la solicitud de info de partida");
        } catch (ErrorAlEnviarPaqueteException ex) {
            throw new ErrorAlEnviarPaqueteException("Error al enviar la solicitud de info de partida");

        }
    }

    public void enviarInfoPartida(PartidaDTO partida, DireccionDTO direccion)
            throws PaqueteVacioAlSerializarException, ErrorAlEnviarPaqueteException {
       PaqueteDTO paquete;
        try {
            paquete = serializador.serializarPartida("infoPartida", partida);
            enviarPaqueteDireccion(paquete, direccion);
        } catch (PaqueteVacioAlSerializarException ex) {
            throw new PaqueteVacioAlSerializarException("Error al serializar la info de partida");
        } catch (ErrorAlEnviarPaqueteException ex) {
            throw new ErrorAlEnviarPaqueteException("Error al enviar la info de partida");
        }
    }
}
