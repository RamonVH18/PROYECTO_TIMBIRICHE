/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Ramon Valencia
 */
public class ClienteTCP implements ICliente {
    private final Socket socket;
    private final PrintWriter salida;
    private final ColaEnvio cola;
    
    public ClienteTCP(String ip, int port) throws IOException {
        this.cola = ColaEnvio.getInstancia();
        this.socket = new Socket(ip, port);
        this.salida = new PrintWriter(socket.getOutputStream(), true);
    }
    
    @Override
    public void enviarPaquete() {
        Gson gson = new Gson();
        PaqueteDTO paquete = cola.desencolar();
        String json = gson.toJson(paquete);
        salida.println(json);
    }
    
    
}
