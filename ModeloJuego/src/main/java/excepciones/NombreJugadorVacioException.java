/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramon Valencia
 */
public class NombreJugadorVacioException extends Exception{

    public NombreJugadorVacioException(String message) {
        super(message);
    }

    public NombreJugadorVacioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
