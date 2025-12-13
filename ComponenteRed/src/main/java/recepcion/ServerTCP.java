package recepcion;

import DTOs.PaqueteDTO;
import com.google.gson.Gson;
import excepciones.FalloCreacionServerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTCP {

    private final int puerto;
    private static ColaRecepcion colaRecepcion;

    private volatile boolean activo;
    private ServerSocket serverSocket;

    public ServerTCP(int puerto) {
        this.puerto = puerto;
        this.colaRecepcion = ColaRecepcion.getInstancia();
        this.activo = true;
    }

    private void recibirPaquete(PaqueteDTO paquete) {
        colaRecepcion.encolar(paquete);
    }

    public void iniciarServidor() throws FalloCreacionServerException {
        Gson gson = new Gson();
        try {
            serverSocket = new ServerSocket(puerto);

            while (activo) {
                Socket cliente = serverSocket.accept(); // BLOQUEANTE

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream())
                );

                String jsonRecibido = in.readLine();
                PaqueteDTO paquete = gson.fromJson(jsonRecibido, PaqueteDTO.class);
                recibirPaquete(paquete);
            }

        } catch (IOException e) {
            if (activo) {
                throw new FalloCreacionServerException(
                        "ERROR EN LA CREACION DEL SERVER SOCKET: " + e.getMessage()
                );
            }
            // si no está activo, es cierre normal
        } finally {
            cerrarServidor();
        }
    }

    public void detenerServidor() {
        activo = false;
        cerrarServidor();
    }

    private void cerrarServidor() {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); 
            }
        } catch (IOException ignored) {
        }
    }
}

