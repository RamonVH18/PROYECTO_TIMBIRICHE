/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializador;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import com.google.gson.JsonObject;
import eventos.NuevoJugadorEvent;
import eventos.VerificadorEventos;
import excepciones.PaqueteVacioAlSerializarException;
import interfaces.Mediador;
import objetosModeloJuego.Jugador;

/**
 *
 * @author Ramon Valencia
 */
public class Serializador {

    private final Mediador modeloJuego;

    public Serializador(Mediador modeloJuego, VerificadorEventos verificadorEventos) {
        this.modeloJuego = modeloJuego;
    }

    public PaqueteDTO serializarDireccionAPaquete(String tipoPaquete, DireccionDTO direccion) throws PaqueteVacioAlSerializarException {
        if (validarSerializacion(tipoPaquete, direccion)) {
            JsonObject jsonDireccion = new JsonObject();
            return new PaqueteDTO(tipoPaquete, jsonDireccion);
        }
        return null;
    }

    public PaqueteDTO serializarNuevoJugadorEvent(String tipoPaquete, NuevoJugadorEvent njEvent) throws PaqueteVacioAlSerializarException {
        if (validarSerializacion(tipoPaquete, njEvent)) {

            JsonObject jsonJugador = serializarJugador(njEvent.getJugador());
            JsonObject jsonDireccion = serializarDireccion(njEvent.getDireccion());
            
            JsonObject jason = new JsonObject();
            jason.add("jugador", jsonJugador);
            jason.add("direccion", jsonDireccion);
            return new PaqueteDTO(tipoPaquete, jason);
        }
        return null;
    }

    private JsonObject serializarJugador(Jugador j) {
        JsonObject jsonJugador = new JsonObject();
        jsonJugador.addProperty("idJugador", j.getIdJugador());
        jsonJugador.addProperty("nombre", j.getNombre());
        jsonJugador.addProperty("imagen", j.getImagen());
        jsonJugador.addProperty("color", j.getColor());
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
