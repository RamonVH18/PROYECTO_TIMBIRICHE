/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

import java.io.IOException;

/**
 *
 * @author Ramon Valencia
 */
public class DispatcherFactory {
    
    public static Emisor createDispatcher(String ip, int port) throws IOException {
        ColaEnvio cola = ColaEnvio.getInstancia();
        Emisor emisor = Emisor.getInstanceEmisor();
        
        ICliente cliente = new ClienteTCP(ip, port);
        cola.subscribirCliente(cliente);
        return emisor;
    }
    
}
