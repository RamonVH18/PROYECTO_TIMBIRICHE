package recepcion;

import com.google.gson.Gson;
import envio.PaqueteDTO;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author janot
 */
public class ServidorTCP{
    private Integer puerto;
    private ColaRecepcion cola;
    
    public ServidorTCP(int puerto) {
        this.puerto = puerto;
        cola = ColaRecepcion.getInstancia();
    }
    
    public void iniciarServidor(){
        Gson gson = new Gson();
        
        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en puerto " + puerto);
            
            while(true){
                Socket cliente = serverSocket.accept(); // espera conexión

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream())
                );

                String jsonRecibido = in.readLine(); // recibe el JSON como String
                PaqueteDTO paquete = gson.fromJson(jsonRecibido, PaqueteDTO.class);                
                cola.encolar(paquete);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
