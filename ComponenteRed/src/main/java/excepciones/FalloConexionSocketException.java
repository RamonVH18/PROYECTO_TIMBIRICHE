/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramon Valencia
 */
public class FalloConexionSocketException extends Exception {

    public FalloConexionSocketException(String message) {
        super(message);
    }

    public FalloConexionSocketException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
