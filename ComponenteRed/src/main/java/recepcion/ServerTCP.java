/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recepcion;

import DTOs.PaqueteDTO;
import com.google.gson.Gson;
import excepciones.FalloCreacionServerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ramon Valencia
 */
public class ServerTCP {
    private BufferedReader entrada;
    private final int puerto;
    
    public ServerTCP(int puerto) {
        this.puerto = puerto;
    }
    
    private void recibirPaquete(PaqueteDTO paquete) {
        
    }
    
    public void iniciarServidor() throws FalloCreacionServerException {
        Gson gson = new Gson();
        try (ServerSocket server = new ServerSocket(puerto)) {
            while (true) {
                Socket cliente = server.accept(); // espera conexión
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream())
                );

                String jsonRecibido = in.readLine(); // recibe el JSON como String

                // Deserializar el JSON a JsonObject
                PaqueteDTO paquete = gson.fromJson(jsonRecibido, PaqueteDTO.class);
                
                recibirPaquete(paquete);

            }
        } catch (IOException e) {
            throw new FalloCreacionServerException("ERROR EN LA CREACION DEL SERVER SOCKET: " + e.getMessage());
        }
    }
}
