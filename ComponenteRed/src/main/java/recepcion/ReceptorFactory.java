package recepcion;


/**
 *
 * @author janot
 */
public class ReceptorFactory {
    public static Receptor CreateReceptor(int puerto){
        ColaRecepcion cola = ColaRecepcion.getInstancia();
        Receptor receptor = Receptor.getInstanceReceptor();
        cola.subscribirRecepcion(receptor);
                
        ServidorTCP servidorTCP = new ServidorTCP(puerto);
        servidorTCP.iniciarServidor();
        
        return receptor;
    }
}
