/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package directorio;

import DTOs.DireccionDTO;
import DTOs.PaqueteDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import excepciones.ErrorEnviarMensajesException;
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
 */
public class ServidorDirectorio {

    List<DireccionDTO> direcciones;
    private final int port;
    private final Gson gson;
    private boolean finalizarProceso;
    

    public ServidorDirectorio(int port) {
        this.direcciones = new ArrayList<>();
        this.port = port;
        this.gson = new Gson();
        finalizarProceso = false;
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

                    //Verifica si el mensaje es para apagar el servidor
                    verificarApagadoServidor(mensaje);
                    if (finalizarProceso) {
                        return;
                    }
                    //Verifica si el mensaje es un registro de peer
                    if (!esRegistroPeer(mensaje)) {
                        continue;
                    }else{
                        DireccionDTO direccion = obtenerDireccionDePeer(mensaje);
                        registrarDireccion(direccion);
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            throw new ErrorRecibirMensajesExcepction("Error en el servidor: " + e.getMessage());
        }
    }

    private void verificarApagadoServidor(String mensaje) {
        PaqueteDTO paquete = gson.fromJson(mensaje, PaqueteDTO.class);
        if ("apagarServidor".equals(paquete.getTipoPaquete())) {
            finalizarProceso = true;
        }
    }

    private boolean esRegistroPeer(String mensaje) {
        PaqueteDTO paquete = gson.fromJson(mensaje, PaqueteDTO.class);
        return "registroPeer".equals(paquete.getTipoPaquete());
    }

    private DireccionDTO obtenerDireccionDePeer(String mensaje) {

        PaqueteDTO paquete = gson.fromJson(mensaje, PaqueteDTO.class);
        DireccionDTO direccion = gson.fromJson(paquete.getMensaje(), DireccionDTO.class);
        
        return direccion;
    }

    private void registrarDireccion(DireccionDTO direccion) {
        // for para comprobar que una no se repitan las direcciones
        for (DireccionDTO d : direcciones) {
            if (d.equals(direccion)) {
                return;
            }
        }
        direcciones.add(direccion);
        if (direcciones.size() > 1) {
            enviarNuevaDireccionASockets(direccion);
        }

    }

    private void enviarNuevaDireccionASockets(DireccionDTO direccion) {

        for (DireccionDTO d : direcciones) {
            if (!d.equals(direccion)) {
                try {
                    // Se envia la direccion del nuevo peer a un peer ya existente
                    enviarDireccion(d, direccion);
                    // Se envia la direccion de un peer existente a un nuevo peer
                    enviarDireccion(direccion, d);
                } catch (ErrorEnviarMensajesException ex) {
                    System.err.println(
                            "No se encontro el peer con el host " + d.getHost() + " en el puerto " + d.getPort());
                }
            }
        }
    }

    private void enviarDireccion(DireccionDTO direccionDestino, DireccionDTO direccionEnviada)
            throws ErrorEnviarMensajesException {

        JsonObject json = new JsonObject();
        json.addProperty("host", direccionEnviada.getHost());
        json.addProperty("port", direccionEnviada.getPort());

        String direccion = gson.toJson(new PaqueteDTO("direccionPeer", json));
        try (Socket socketCliente = new Socket(direccionDestino.getHost(), direccionDestino.getPort());
                PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true)) {

            out.println(direccion);
        } catch (IOException ex) {
            throw new ErrorEnviarMensajesException("Error al enviar mensajes: " + ex.getMessage());
        }
    }

}
