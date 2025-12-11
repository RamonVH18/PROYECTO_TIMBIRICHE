/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializador;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import DTOs.PartidaDTO;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import eventos.LineaPintadaEvent;
import eventos.NuevoJugadorEvent;
import eventos.VerificadorEventos;
import excepciones.PaqueteVacioAlDeserializarException;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;

/**
 *
 * @author Ramon Valencia
 */
public class Deserializador {

    private final VerificadorEventos verificadorEventos;
    private final Gson gson;

    public Deserializador(VerificadorEventos verificadorEventos) {
        this.verificadorEventos = verificadorEventos;
        this.gson = new Gson();
    }

    public void deserializarPaquete(PaqueteDTO paquete) throws PaqueteVacioAlDeserializarException {
        validarDeserializacion(paquete.getTipoPaquete(), paquete.getMensaje());
        switch (paquete.getTipoPaquete()) {
            case ("direccionPeer") -> {
                DireccionDTO direccion = deserializarDireccionPeer(paquete.getMensaje());
                verificadorEventos.eventoNuevaDireccion(direccion);
                break;
            }
            case ("solicitudInfoJugador") -> {
                DireccionDTO direccion = deserializarDireccionPeer(paquete.getMensaje());
                verificadorEventos.eventoSolicitudNuevoJugador(direccion);
                break;
            }
            case ("nuevaInfoJugador") -> {
                NuevoJugadorEvent njEvent = deserializarNuevoJugadorEvent(paquete.getMensaje());
                verificadorEventos.eventoNuevoJugador(njEvent);
                break;

            }
            case ("nuevaLineaPintada") -> {
                LineaPintadaEvent lpEvent = deserializarLineaPintadaEvent(paquete.getMensaje());
                verificadorEventos.eventoLineaPintada(lpEvent);
                break;
            }
            case ("listaDirecciones") -> {
                List<DireccionDTO> direcciones = deserializarListaDirecciones(paquete.getMensaje());

                verificadorEventos.eventoListaDirecciones(direcciones);
                break;
            }
            case ("CambioDatosJugador") -> {
//                CambioJugadorEvent cjEvent = 
            }
        }
    }

    private DireccionDTO deserializarDireccionPeer(JsonObject json) {
        DireccionDTO direccion = gson.fromJson(json, DireccionDTO.class);
//        ValidacionesClases.validarDireccionPeerDTO(direccion);
        return direccion;
    }

    private NuevoJugadorEvent deserializarNuevoJugadorEvent(JsonObject json) {
        DireccionDTO d = gson.fromJson(json.get("direccion"), DireccionDTO.class);
        Jugador j = gson.fromJson(json.get("jugador"), Jugador.class);
        NuevoJugadorEvent njEvent = new NuevoJugadorEvent(j, d);
        return njEvent;
    }

    private LineaPintadaEvent deserializarLineaPintadaEvent(JsonObject json) {
        Linea l = gson.fromJson(json.get("linea"), Linea.class);
        LineaPintadaEvent lpEvent = new LineaPintadaEvent(l);
        return lpEvent;
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

    private List<DireccionDTO> deserializarListaDirecciones(JsonObject json) {
        Type listType = new TypeToken<List<DireccionDTO>>() {
        }.getType();
        List<DireccionDTO> direcciones = gson.fromJson(json.get("direcciones"), listType);
        return direcciones;
    }
}
