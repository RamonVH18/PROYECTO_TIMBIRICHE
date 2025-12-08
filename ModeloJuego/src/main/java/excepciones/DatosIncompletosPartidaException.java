/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ximena
 */
public class DatosIncompletosPartidaException extends Exception {
    
    public DatosIncompletosPartidaException(String message) {
        super(message);
    }

    public DatosIncompletosPartidaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
