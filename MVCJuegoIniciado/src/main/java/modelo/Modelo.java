/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import interfaces.IModeloJuego;
import interfaces.IModeloLeible;
import interfaces.IModeloModificable;
import interfaces.Vista;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import modeloJuego.ModeloJuego;
import objetosPresentacion.JugadorVisual;
import objetosPresentacion.TableroFactory;
import objetosPresentacion.TamañosTablero;
import vistas.TableroJuego;

/**
 *
 * @author Ramon Valencia
 */
public class Modelo implements IModeloLeible, IModeloModificable{
    IModeloJuego modeloJuego = ModeloJuego.getInstance();
    List<Vista> vistas = new ArrayList<>();
    
    public Modelo() {
        
    }
    
    public void añadirObserver(Vista v) {
        vistas.add(v);
    }
    
    public void eliminarObserver(Vista v) {
        vistas.remove(v);
    }
    
    public void notificarObservers() {
        for(Vista v : vistas) {
            v.actualizar();
        }
    }
    
    @Override
    public JPanel obtenerTablero() {
        TableroJuego tablero = TableroFactory.crearTablero(TamañosTablero.GRANDE);
        return tablero;
    }

    @Override
    public List<JugadorVisual> obtenerJugadores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
