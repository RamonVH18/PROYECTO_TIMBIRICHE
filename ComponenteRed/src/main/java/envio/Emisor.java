/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package envio;

/**
 *
 * @author Ramon Valencia
 */
public class Emisor implements IEmisor {
    private static Emisor instancia;
    private static ColaEnvio colaEnvio;
    
    private Emisor() {
        
    }
    
    @Override
    public Emisor getInstanceEmisor() {
        if (instancia == null) {
            instancia = new Emisor();
        }
        if (colaEnvio == null) {
            colaEnvio = new ColaEnvio();
        }
        return instancia;
    }

    @Override
    public void agregarAColaEnvio(PaqueteDTO paquete) {
        colaEnvio.encolar(paquete);
    }

    
    
}
