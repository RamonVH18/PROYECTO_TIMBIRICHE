/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramon Valencia
 */
public class ImagenJugadorInexistenteException extends Exception{

    public ImagenJugadorInexistenteException(String message) {
        super(message);
    }

    public ImagenJugadorInexistenteException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
