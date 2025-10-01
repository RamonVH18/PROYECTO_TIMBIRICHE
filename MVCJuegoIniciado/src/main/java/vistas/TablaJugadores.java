/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import interfaces.IModeloLeible;
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
import interfaces.IVista;

/**
 *
 * @author Ramon Valencia
 */
public class TablaJugadores extends JPanel implements IVista {

    IModeloLeible modelo = Modelo.getInstaciaModelo();

    private Dimension DIM_TABLA = new Dimension(350, 200);
    private Dimension DIM_FLD_JUGADOR = new Dimension();
    private List<JugadorVisual> jugadoresActuales = new ArrayList<>();
    private Integer MAX_JUGADORES = 4;
    private String estadoJugadores = "Jugadores: " + jugadoresActuales.size() + "/" + MAX_JUGADORES;
    private JLabel titulo = new JLabel(estadoJugadores);
    private JPanel panelJugadores = new JPanel();

    public TablaJugadores() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        modelo.añadirObserver(this);
        crearTabla();
    }

    public void crearTabla() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setSize(DIM_TABLA);
        setPreferredSize(DIM_TABLA);
        generarMarcosJugadores();

    }

    public void generarMarcosJugadores() {
        
        
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setOpaque(true);
        add(Box.createVerticalGlue());
        add(titulo);
        add(Box.createVerticalGlue());
        
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
        removeAll();
        generarMarcosJugadores();
        repaint();
        revalidate();
    }

}
