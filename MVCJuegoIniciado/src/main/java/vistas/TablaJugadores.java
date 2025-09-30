/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import interfaces.IModeloLeible;
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
import modelo.Modelo;
import objetosPresentacion.JugadorVisual;

/**
 *
 * @author Ramon Valencia
 */
public class TablaJugadores extends JPanel implements Vista {
    IModeloLeible modelo = Modelo.getInstaciaModelo();
    
    Dimension DIM_TABLA = new Dimension(350, 50); 
    Dimension DIM_FLD_JUGADOR = new Dimension();
    List<JugadorVisual> jugadoresActuales = new ArrayList<>();
    Integer MAX_JUGADORES = 4;
    String estadoJugadores = "Jugadores: " + jugadoresActuales.size() + "/" + MAX_JUGADORES;
    JLabel titulo = new JLabel(estadoJugadores);
    JPanel panelJugadores = new JPanel();
    
    
    public TablaJugadores() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setOpaque(true);
        add(Box.createVerticalGlue());
        add(titulo);
        add(Box.createVerticalGlue());
        modelo.añadirObserver(this);
        crearTabla();
    }
    
    public void crearTabla(){
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setSize(DIM_TABLA);
        setPreferredSize(DIM_TABLA);
        generarMarcosJugadores();
    
    }
    
    public void generarMarcosJugadores(){
        jugadoresActuales = modelo.obtenerJugadores();
        for (JugadorVisual j : jugadoresActuales) {
            JLabel labelNombre = new JLabel(j.getNombre());
            labelNombre.setAlignmentX(CENTER_ALIGNMENT);
            add(labelNombre);
        }
        titulo.setText("Jugadores: " + jugadoresActuales.size() + "/" + MAX_JUGADORES);
    }
    
    @Override
    public void actualizar() {
        generarMarcosJugadores();
        repaint();
        revalidate();
    }
    
}
