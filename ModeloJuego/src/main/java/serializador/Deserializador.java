/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializador;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import eventos.NuevoJugadorEvent;
import eventos.VerificadorEventos;
import excepciones.PaqueteVacioAlDeserializarException;
import interfaces.Mediador;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ramon Valencia
 */
public class Deserializador {

    private final Mediador modeloJuego;
    private final VerificadorEventos verificadorEventos;
    private final Gson gson;

    public Deserializador(Mediador modeloJuego, VerificadorEventos verificadorEventos) {
        this.modeloJuego = modeloJuego;
        this.verificadorEventos = verificadorEventos;
        this.gson = new Gson();
    }

    public void deserializarPaquete(PaqueteDTO paquete) throws PaqueteVacioAlDeserializarException {
        validarDeserializacion(paquete.getTipoPaquete(), paquete.getMensaje());
        switch (paquete.getTipoPaquete()) {
            case ("direccionPeer") -> {
                DireccionDTO direccion = deserializarDireccionPeer(paquete.getMensaje());
                verificadorEventos.eventoNuevaDireccion(direccion);
            }
            case ("solicitudInfoJugador") -> {
                DireccionDTO direccion = deserializarDireccionPeer(paquete.getMensaje());
                verificadorEventos.eventoSolicitudNuevoJugador(direccion);
            }
            case ("nuevaInfoJugador") -> {
                NuevoJugadorEvent njEvent = deserializarNuevoJugadorEvent(paquete.getMensaje());
                
            }
        }
    }

    private DireccionDTO deserializarDireccionPeer(JsonObject json) {
        DireccionDTO direccion = gson.fromJson(json, DireccionDTO.class);
//        ValidacionesClases.validarDireccionPeerDTO(direccion);
        return direccion;
    }
    
    private NuevoJugadorEvent deserializarNuevoJugadorEvent(JsonObject json) {
        DireccionDTO d = gson.fromJson(json.get("jugador"), DireccionDTO.class);
        Jugador j = gson.fromJson(json.get("direccion"), Jugador.class);
        NuevoJugadorEvent njEvent = new NuevoJugadorEvent(j, d);
        return njEvent;
    }

    private boolean validarDeserializacion(String tipo, JsonObject jason) throws PaqueteVacioAlDeserializarException {
        if (tipo.isBlank()) {
            throw new PaqueteVacioAlDeserializarException("El paquete no tiene ningun tipo");
        }
        if (jason.isEmpty()) {
            throw new PaqueteVacioAlDeserializarException("No se puede deserializar el paquete porque no tiene contenido");
        }
        return true;
    }

}
