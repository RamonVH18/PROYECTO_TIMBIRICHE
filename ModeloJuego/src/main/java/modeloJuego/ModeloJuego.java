/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package modeloJuego;

import interfaces.IModeloJuego;

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

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
