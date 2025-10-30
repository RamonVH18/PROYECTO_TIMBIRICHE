package recepcion;

import envio.PaqueteDTO;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author janot
 */
public class ColaRecepcion {
    private static Queue<PaqueteDTO> cola;
    private static ColaRecepcion instancia;
    private static IReceptor receptor;
    
    private ColaRecepcion() {
        cola = new ArrayDeque<>();
    }
    
    public static ColaRecepcion getInstancia() {
        if (instancia == null) {
            instancia = new ColaRecepcion();
        }
        return instancia;
    }
    
    public void subscribirRecepcion(IReceptor receptor) {
        this.receptor = receptor;
    }
    
    public void encolar(PaqueteDTO paquete) {
        cola.add(paquete);
        notificarPaqueteNuevo();
    }
    
    public PaqueteDTO desencolar() {
        PaqueteDTO paquete = cola.remove();
        return paquete;
    }
    
    private void notificarPaqueteNuevo() {
        receptor.recibirPaquete();
    }
    
}
