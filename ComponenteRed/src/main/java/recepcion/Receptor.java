package recepcion;

/**
 *
 * @author janot
 */
public class Receptor implements IReceptor{
private static Receptor instancia;
    public static ColaRecepcion colaRecepcion;
    
    private Receptor() {
        colaRecepcion = ColaRecepcion.getInstancia();
    }
    
    public static Receptor getInstanceReceptor() {
        if (instancia == null) {
            instancia = new Receptor();
            colaRecepcion = ColaRecepcion.getInstancia();
        }
        
        return instancia;
    }
    
   
    @Override
    public void recibirPaquete() {
        System.out.println(colaRecepcion.desencolar());
    }
    
}
