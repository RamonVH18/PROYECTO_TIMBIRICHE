/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

/**
 *
 * @author Ramon Valencia
 */
public class DispatcherFactory {
    
    public static IEmisor createDispatcher() {
        Emisor emisor = Emisor.getInstanceEmisor();
        ColaEnvio cola = ColaEnvio.getInstancia();
        ICliente cliente = new ClienteTCP();
        cola.subscribirCliente(cliente);
        return emisor;
    }
    
}
