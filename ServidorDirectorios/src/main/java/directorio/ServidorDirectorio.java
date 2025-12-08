/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package directorio;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import DTOs.PartidaDTO;
import com.google.gson.JsonObject;
import excepciones.ErrorRecibirMensajesExcepction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramon Valencia
 * @author TRMrDucky
 */
public class ServidorDirectorio {

    List<DireccionDTO> direcciones;
    private final int port;
    private boolean finalizarProceso;
    
    private final List<PartidaDTO> partidasRegistradas;

    public ServidorDirectorio(int port) {
        this.direcciones = new ArrayList<>();
        this.port = port;
        finalizarProceso = false;
        
        this.partidasRegistradas = new ArrayList<>();
    }

    public void iniciarServidor() throws ErrorRecibirMensajesExcepction {

        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                try (Socket cliente = server.accept()) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(cliente.getInputStream()));
                    String mensaje = in.readLine();
                    if (mensaje == null || mensaje.isBlank()) {
                        continue;
                    }

                    // Verifica si el mensaje es para apagar el servidor
                    verificarApagadoServidor(mensaje);
                    if (finalizarProceso) {
                        return;
                    }
                    // Verifica si el mensaje es un registro de peer
                    if (esRegistroPeer(mensaje)) {
                        DireccionDTO direccion = obtenerDireccionDePeer(mensaje);
                        registrarDireccion(direccion);
                        if (direcciones.size() > 1) {
                            enviarDireccionesAPeerNuevo(direccion);
                        }
                    }
                    if (esRegistroPartida(mensaje)) {
                        PartidaDTO partida = obtenerPartidaDelMensaje(mensaje);
                        registrarPartida(partida);
                        System.out.println("Partida registrada: " + partida.getNombrePartida());
                    }
                }
            }
        } catch (IOException e) {
            throw new ErrorRecibirMensajesExcepction("Error en el servidor: " + e.getMessage());
        }
    }

    private void verificarApagadoServidor(String mensaje) {
        PaqueteDTO paquete = Serializador.deserializar(mensaje);
        if ("apagarServidor".equals(paquete.getTipoPaquete())) {
            finalizarProceso = true;
        }
    }

    private boolean esRegistroPeer(String mensaje) {
        PaqueteDTO paquete = Serializador.deserializar(mensaje);
        return "registroPeer".equals(paquete.getTipoPaquete());
    }

    private DireccionDTO obtenerDireccionDePeer(String mensaje) {

        PaqueteDTO paquete = Serializador.deserializar(mensaje);
        DireccionDTO direccion = Serializador.deserializarDireccion(paquete.getMensaje());

        return direccion;
    }

    private void registrarDireccion(DireccionDTO direccion) {
        // Verificar que no se encuentre la dirección ya registrada
        if(direcciones.contains(direccion)){
            return;
        }

        // Cocazo
        direcciones.add(direccion);
    }

    private void enviarDireccionesAPeerNuevo(DireccionDTO direccionNueva) {
        //Crear una lista temporal sin la direccion nueva
        List<DireccionDTO> direcciones = new ArrayList<>(this.direcciones);
        direcciones.remove(direccionNueva);

        //Crear el mensaje con la lista de direcciones para el peer nuevo
        String mensaje = Serializador.serializarDirecciones(direcciones);

        //Enviar el mensaje al peer nuevo
        try (Socket socketCliente = new Socket(direccionNueva.getHost(), direccionNueva.getPort());
                PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true)) {
            out.println(mensaje);
        } catch (IOException ex) {
            System.err.println(
                    "No se encontro el peer con el host " + direccionNueva.getHost() + " en el puerto "
                            + direccionNueva.getPort());
        }
    }
    
    private boolean esRegistroPartida(String mensaje) {
        PaqueteDTO paquete = Serializador.deserializar(mensaje);
        return "registroPartida".equals(paquete.getTipoPaquete());
    }
    
    // Deserializa la partida que llega en el servidor
    private PartidaDTO obtenerPartidaDelMensaje(String mensaje) {
        PaqueteDTO paquete = Serializador.deserializar(mensaje);
        JsonObject json = paquete.getMensaje().getAsJsonObject(mensaje);
        
        String nombre = json.get("nombrePartida").getAsString();
        int numJugadores = json.get("numJugadores").getAsInt();
        
        DireccionDTO host = Serializador.deserializarDireccion((JsonObject) json.get("host"));
        
        String tamaño = json.has("tamañoTablero") ? json.get("tamañoTablero").getAsString() : null;
        
        return new PartidaDTO(nombre, numJugadores, host, tamaño);
    }
    
    // Registra la partida en el servidor
    private void registrarPartida(PartidaDTO partida) {
        boolean yaExiste = partidasRegistradas.stream()
                .anyMatch(p -> p.getNombrePartida().equals(partida.getNombrePartida()));
        
        if (yaExiste) {
            System.out.println("Partida ya registrada.");
            return;
        }
        
        partidasRegistradas.add(partida);
        System.out.println("Partida agregada al servidor.");
    }

}
