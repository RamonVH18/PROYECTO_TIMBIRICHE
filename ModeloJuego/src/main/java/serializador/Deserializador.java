/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializador;

import DTOs.DireccionDTO;
import DTOs.JugadorLobbyDTO;
import DTOs.LobbyEstadoDTO;
import DTOs.PaqueteDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import eventos.LineaPintadaEvent;
import eventos.NuevoJugadorEvent;
import eventos.VerificadorEventos;
import excepciones.PaqueteVacioAlDeserializarException;
import interfaces.Mediador;
import java.util.ArrayList;
import java.util.List;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;

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
                verificadorEventos.eventoNuevoJugador(njEvent);

            }
            case ("nuevaLineaPintada") -> {
                LineaPintadaEvent lpEvent = deserializarLineaPintadaEvent(paquete.getMensaje());
                verificadorEventos.eventoLineaPintada(lpEvent);
            }

            case "nuevoJugadorLobby" -> {
                JugadorLobbyDTO jugador = deserializarJugadorLobby(paquete.getMensaje());
                modeloJuego.revisarPaqueteRecibido(paquete);
            }

            case "jugadorListo" -> {
                modeloJuego.revisarPaqueteRecibido(paquete);
            }
            case "estadoLobby" -> {
                modeloJuego.revisarPaqueteRecibido(paquete);
            }
            case "iniciarPartida" -> {
                modeloJuego.revisarPaqueteRecibido(paquete);
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

    private JugadorLobbyDTO deserializarJugadorLobby(JsonObject json) {
        return new JugadorLobbyDTO(
                json.get("idJugador").getAsString(),
                json.get("nombre").getAsString(),
                json.get("imagen").getAsString(),
                json.get("color").getAsString(),
                json.get("listo").getAsBoolean()
        );
    }

    private LobbyEstadoDTO reconstruirEstadoLobby(JsonObject json) {
        JsonArray array = json.getAsJsonArray("jugadores");
        List<JugadorLobbyDTO> jugadores = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            JsonObject j = array.get(i).getAsJsonObject();
            JugadorLobbyDTO jugador = new JugadorLobbyDTO(
                    j.get("idJugador").getAsString(),
                    j.get("nombre").getAsString(),
                    j.get("imagen").getAsString(),
                    j.get("color").getAsString(),
                    j.get("listo").getAsBoolean()
            );
            jugadores.add(jugador);
        }

        int max = json.get("maxJugadores").getAsInt();
        boolean iniciada = json.get("partidaIniciada").getAsBoolean();
        String tam = json.get("tamanoTablero").getAsString();

        return new LobbyEstadoDTO(jugadores, max, iniciada, tam);
    }

    public LobbyEstadoDTO deserializarEstadoLobby(JsonObject json) {
        return reconstruirEstadoLobby(json);
    }
}
