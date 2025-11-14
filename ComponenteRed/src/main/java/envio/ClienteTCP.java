/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

import interfaces.ICliente;
import DTOs.EnvioDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ramon Valencia
 */
public class ClienteTCP implements ICliente {
    private Socket socket;
    private PrintWriter salida;
    private final ColaEnvio cola;
    
    public ClienteTCP() {
        this.cola = ColaEnvio.getInstancia();
        
    }
    
    @Override
    public void enviarPaquete() {
        Gson gson = new Gson();
        EnvioDTO envio = cola.desencolar();
        
        String json = gson.toJson(envio.getPaquete());
        try {
            socket = new Socket(envio.getHost(), envio.getPort());
            salida = new PrintWriter(socket.getOutputStream(), true);
            salida.println(json);
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
}
