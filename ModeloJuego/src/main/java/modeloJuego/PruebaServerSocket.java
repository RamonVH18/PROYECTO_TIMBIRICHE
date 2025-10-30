package modeloJuego;

import recepcion.IReceptor;
import recepcion.ReceptorFactory;

/**
 *
 * @author janot
 */
public class PruebaServerSocket {
    private static IReceptor receptor;
    
    public static void main(String[] args) {
        receptor = ReceptorFactory.CreateReceptor(5000);
        
        
    }
}
