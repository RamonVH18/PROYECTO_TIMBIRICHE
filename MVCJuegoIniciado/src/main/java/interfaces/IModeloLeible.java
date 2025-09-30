/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.util.List;
import javax.swing.JPanel;
import objetosPresentacion.JugadorVisual;

/**
 *
 * @author Ramon Valencia
 */
public interface IModeloLeible {
    
    public JPanel obtenerTablero();
    
    public List<JugadorVisual> obtenerJugadores();
}
