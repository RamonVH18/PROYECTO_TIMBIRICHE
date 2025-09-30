/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import interfaces.Vista;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import objetosPresentacion.JugadorVisual;

/**
 *
 * @author Ramon Valencia
 */
public class TablaJugadores extends JPanel implements Vista {
    Dimension DIM_TABLA = new Dimension(100, 50); 
    Dimension DIM_FLD_JUGADOR = new Dimension();
    List<JugadorVisual> jugadoresActuales = new ArrayList<>();
    Integer MAX_JUGADORES = 4;
    String estadoJugadores = "Jugadores: " + jugadoresActuales.size() + "/" + MAX_JUGADORES;
    JLabel titulo = new JLabel(estadoJugadores);
    
    public TablaJugadores() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalGlue());
        add(titulo);
        add(Box.createVerticalGlue());
        
        crearTabla();
    }
    
    public void crearTabla(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setSize(DIM_TABLA);
        setPreferredSize(DIM_TABLA);
        
    }
    
    @Override
    public void actualizar() {
        
    }
    
}
