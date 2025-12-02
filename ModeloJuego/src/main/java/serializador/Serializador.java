/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializador;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import com.google.gson.JsonObject;
import eventos.LineaPintadaEvent;
import eventos.NuevoJugadorEvent;
import excepciones.PaqueteVacioAlSerializarException;
import objetosModeloJuego.Jugador;
import objetosModeloJuego.Linea;
import objetosModeloJuego.Punto;

/**
 *
 * @author Ramon Valencia
 */
public class Serializador {



    public Serializador() {
    }

    public PaqueteDTO serializarDireccionAPaquete(String tipoPaquete, DireccionDTO direccion) throws PaqueteVacioAlSerializarException {
        if (validarSerializacion(tipoPaquete, direccion)) {
            JsonObject jsonDireccion = serializarDireccion(direccion);
            return new PaqueteDTO(tipoPaquete, jsonDireccion);
        }
        return null;
    }

    public PaqueteDTO serializarNuevoJugadorEvent(String tipoPaquete, NuevoJugadorEvent njEvent) throws PaqueteVacioAlSerializarException {
        if (validarSerializacion(tipoPaquete, njEvent)) {

            JsonObject jsonJugador = serializarJugador(njEvent.getJugador());
            JsonObject jsonDireccion = serializarDireccion(njEvent.getDireccion());

            JsonObject jsonEvent = new JsonObject();
            jsonEvent.add("jugador", jsonJugador);
            jsonEvent.add("direccion", jsonDireccion);
            return new PaqueteDTO(tipoPaquete, jsonEvent);
        }
        return null;
    }

    public PaqueteDTO serializarLineaPintadaEvent(String tipoPaquete, LineaPintadaEvent lpEvent) throws PaqueteVacioAlSerializarException {
        if (validarSerializacion(tipoPaquete, lpEvent)) {

            JsonObject jsonLinea = serializarLinea(lpEvent.getLinea());

            JsonObject jsonEvent = new JsonObject();
            jsonEvent.add("linea", jsonLinea);
            return new PaqueteDTO(tipoPaquete, jsonEvent);
        }
        return null;
    }

    private JsonObject serializarLinea(Linea l) {
        JsonObject jsonLinea = new JsonObject();
        JsonObject jsonPuntoA = serializarPunto(l.getPuntoA());
        JsonObject jsonPuntoB = serializarPunto(l.getPuntoB());
        jsonLinea.add("puntoA", jsonPuntoA);
        jsonLinea.add("puntoB", jsonPuntoB);
        jsonLinea.addProperty("estadoLinea", l.getEstadoLinea());
        return jsonLinea;
    }

    private JsonObject serializarPunto(Punto p) {
        JsonObject jsonPunto = new JsonObject();
        jsonPunto.addProperty("coordenadaX", p.getCoordenadaX());
        jsonPunto.addProperty("coordenadaY", p.getCoordenadaY());
        return jsonPunto;
    }

    private JsonObject serializarJugador(Jugador j) {
        JsonObject jsonJugador = new JsonObject();
        jsonJugador.addProperty("idJugador", j.getIdJugador());
        jsonJugador.addProperty("nombre", j.getNombre());
        jsonJugador.addProperty("imagen", j.getImagen().name());
        jsonJugador.addProperty("color", j.getColor().name());
        return jsonJugador;
    }

    private JsonObject serializarDireccion(DireccionDTO d) {
        JsonObject jsonDireccion = new JsonObject();
        jsonDireccion.addProperty("host", d.getHost());
        jsonDireccion.addProperty("port", d.getPort());
        return jsonDireccion;
    }

    private boolean validarSerializacion(String tipo, Object objetoASerializar) throws PaqueteVacioAlSerializarException {
        if (tipo.isBlank()) {
            throw new PaqueteVacioAlSerializarException("El paquete no tiene ningun tipo");
        }
        if (objetoASerializar == null) {
            throw new PaqueteVacioAlSerializarException("No se puede serializar el paquete porque no tiene contenido");
        }
        return true;
    }

}
