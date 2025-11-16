/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 *
 * @author Ramon Valencia
 */
public class MalCasteoClaseException extends Exception{

    public MalCasteoClaseException(String message) {
        super(message);
    }

    public MalCasteoClaseException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
