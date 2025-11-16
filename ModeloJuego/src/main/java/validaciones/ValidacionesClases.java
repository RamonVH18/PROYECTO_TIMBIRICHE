/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validaciones;

import DTOs.DireccionDTO;
import excepciones.MalCasteoClaseException;

/**
 *
 * @author Ramon Valencia
 */
public class ValidacionesClases {
    
    public static boolean validarDireccionPeerDTO(DireccionDTO d) throws MalCasteoClaseException {
        if (d.getHost().isBlank()) {
            throw new MalCasteoClaseException("El Host se encuentra vacio");
        }
        if (d.getPort() < 1 || d.getPort() > 65535) {
            throw new MalCasteoClaseException("El Puerto no esta en un rango valido");
        }
        return true;
    }
    
    
}
