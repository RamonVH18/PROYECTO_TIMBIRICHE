package modeloJuego;

import DTOs.JugadorLobbyDTO;
import DTOs.LobbyEstadoDTO;
import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import interfaces.Mediador;
import interfaces.ObservadorLobby;
import manejadores.ManejadorPaquetes;
import serializador.Serializador;
import serializador.Deserializador;

import java.util.ArrayList;
import java.util.List;

public class ModeloLobby implements Mediador {

    private List<JugadorLobbyDTO> jugadores;
    private DireccionDTO direccionLocal;
    private boolean soyHost;
    private boolean partidaIniciada;

    private int maxJugadores;
    private String tamanoTablero;

    private Serializador serializador;
    private Deserializador deserializador;
    private ManejadorPaquetes manejadorPaquetes;

    private ObservadorLobby observadorLobby;

    public ModeloLobby(DireccionDTO direccionLocal, boolean soyHost) {
        this.jugadores = new ArrayList<>();
        this.direccionLocal = direccionLocal;
        this.soyHost = soyHost;

        this.maxJugadores = 4;
        this.tamanoTablero = "PEQUENO";
    }

    public void inicializar(ManejadorPaquetes manejador, Serializador serial, Deserializador deserial) {
        this.manejadorPaquetes = manejador;
        this.serializador = serial;
        this.deserializador = deserial;
    }

    public void suscribirObservador(ObservadorLobby observador) {
        this.observadorLobby = observador;
    }

    public void configurarLobby(int maxJugadores, String tamano) {
        this.maxJugadores = maxJugadores;
        this.tamanoTablero = tamano;
        notificarUI();
    }

    public void agregarJugador(JugadorLobbyDTO jugador) {
        if (partidaIniciada) return;
        if (jugadores.size() >= maxJugadores) return;

        jugadores.add(jugador);
        notificarUI();

        if (soyHost) {
            enviarEstadoLobbyATodos();
        }
    }

    public void marcarJugadorListo(String idJugador) {
        for (JugadorLobbyDTO j : jugadores) {
            if (j.getIdJugador().equals(idJugador)) {
                j.setListo(true);
            }
        }

        notificarUI();
        verificarInicioPartida();
    }

    private boolean todosListos() {
        return jugadores.size() >= 2 &&
               jugadores.stream().allMatch(JugadorLobbyDTO::isListo);
    }

    private void verificarInicioPartida() {
        if (!soyHost) return;

        if (todosListos()) {
            try {
                partidaIniciada = true;

                PaqueteDTO paquete = serializador.serializarTipoSimple("iniciarPartida");
                manejadorPaquetes.enviarPaqueteDTO(paquete);

                enviarEstadoLobbyATodos();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void enviarEstadoLobbyATodos() {
        try {
            LobbyEstadoDTO estado = new LobbyEstadoDTO(
                    jugadores,
                    maxJugadores,
                    partidaIniciada,
                    tamanoTablero
            );

            PaqueteDTO paquete = serializador.serializarEstadoLobby(estado);
            manejadorPaquetes.enviarPaqueteDTO(paquete);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notificarUI() {
        if (observadorLobby != null) {
            observadorLobby.actualizarLobby(jugadores, maxJugadores, partidaIniciada, tamanoTablero);
        }
    }

    @Override
    public void revisarPaqueteRecibido(PaqueteDTO paquete) {

        switch (paquete.getTipoPaquete()) {

            case "nuevoJugadorLobby" -> {
                JugadorLobbyDTO jugador = new JugadorLobbyDTO(
                        paquete.getMensaje().get("idJugador").getAsString(),
                        paquete.getMensaje().get("nombre").getAsString(),
                        paquete.getMensaje().get("imagen").getAsString(),
                        paquete.getMensaje().get("color").getAsString(),
                        false
                );
                agregarJugador(jugador);
            }

            case "jugadorListo" -> {
                String id = paquete.getMensaje().get("idJugador").getAsString();
                marcarJugadorListo(id);
            }

            case "estadoLobby" -> {
                LobbyEstadoDTO estado = deserializador.deserializarEstadoLobby(paquete.getMensaje());

                this.jugadores = estado.getJugadores();
                this.maxJugadores = estado.getMaxJugadores();
                this.partidaIniciada = estado.isPartidaIniciada();
                this.tamanoTablero = estado.getTamanoTablero();

                notificarUI();
            }

            case "iniciarPartida" -> {
                this.partidaIniciada = true;

                if (observadorLobby != null) {
                    observadorLobby.iniciarPartida();
                }
            }
        }
    }
}
