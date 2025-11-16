/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramon Valencia
 */
public class ErrorAlEnviarPaqueteException extends Exception {

    public ErrorAlEnviarPaqueteException(String message) {
        super(message);
    }

    public ErrorAlEnviarPaqueteException(String message, Throwable cause) {
        super(message, cause);
    }
}
