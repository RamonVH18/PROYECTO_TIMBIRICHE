/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Ramon Valencia
 */
public class ClienteTCP implements Cliente {
    private final Socket socket;
    private final PrintWriter salida;
    private String ip;
    private int port;
    private static ColaEnvio cola;
    
    public ClienteTCP() throws IOException {
        this.socket = new Socket(ip, port);
        this.salida = new PrintWriter(socket.getOutputStream(), true);
    }
    
    @Override
    public void enviarPaquete() {
        PaqueteDTO paquete = cola.desencolar();
        salida.println(paquete);
    }
    
    
}
