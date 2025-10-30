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
        PaqueteDTO paquete = cola.desencolar();
        salida.println(paquete);
    }
    
    
}
