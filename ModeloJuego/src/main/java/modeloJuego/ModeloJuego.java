/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modeloJuego;

import interfaces.IModeloJuego;
import objetosModeloJuego.Linea;

/**
 *
 * @author Ramon Valencia
 */
public class ModeloJuego implements IModeloJuego {

    private static ModeloJuego instanciaModelo;

    private ModeloJuego() {
    }

    public static ModeloJuego getInstance() {
        if (instanciaModelo == null) {
            instanciaModelo = new ModeloJuego();
        }

        return instanciaModelo;
    }

    @Override
    public void realizarJugada(Linea linea) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void notificarCambioLinea() {
        
    }
    
    public void verificarCuadrosCompletados() {
        
    }
    
    public void revisarPaqueteRecibido() {
        
    }

}
