/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package directorio;

import excepciones.ErrorEnviarMensajesException;
import excepciones.ErrorRecibirMensajesExcepction;

/**
 *
 * @author Ramon Valencia
 */
public class EnsambladorDirectorio {
    
    private static ServidorDirectorio servidor;
    /**
     * @param args the command line arguments
     * @throws excepciones.ErrorRecibirMensajesExcepction
     * @throws excepciones.ErrorEnviarMensajesException
     */
    public static void main(String[] args) throws ErrorRecibirMensajesExcepction, ErrorEnviarMensajesException {
        // TODO code application logic here
        servidor = new ServidorDirectorio(8000);
        servidor.iniciarServidor();
    }
    
}
