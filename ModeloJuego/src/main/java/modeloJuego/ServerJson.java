/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modeloJuego;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import DTOs.PaqueteDTO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Ramon Valencia
 */
public class ServerJson {

    public static void main(String[] args) {
        int puerto = 5000; // puerto del servidor
        Gson gson = new Gson();

        try (ServerSocket server = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en puerto " + puerto);
            while (true) {
                Socket cliente = server.accept(); // espera conexión
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream())
                );

                String jsonRecibido = in.readLine(); // recibe el JSON como String
                System.out.println("JSON recibido: " + jsonRecibido);

                // Deserializar el JSON a JsonObject
                PaqueteDTO paquete = gson.fromJson(jsonRecibido, PaqueteDTO.class);
                System.out.println("Tipo: " + paquete.getTipoPaquete());
                System.out.println("Mensaje: " + paquete.getMensaje());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
