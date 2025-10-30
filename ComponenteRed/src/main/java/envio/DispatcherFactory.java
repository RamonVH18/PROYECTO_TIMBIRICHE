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
    
    public static Emisor createDispatcher() throws IOException {
        Emisor emisor = Emisor.getInstanceEmisor();
        ColaEnvio cola = emisor.getColaEnvio();
        ICliente cliente = new ClienteTCP();
        cola.subscribirCliente(cliente);
        return emisor;
    }
    
}
