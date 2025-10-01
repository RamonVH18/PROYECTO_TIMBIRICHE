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
    //Constantes
    private Integer MAX_JUGADORES = 4;
    private Dimension DIM_FLD_JUGADOR = new Dimension();
    
    //Atributos de la clase
    private IModeloLeible modelo;
    //TODO: Hacer esto responsivo
    private Dimension dimension_tabla = new Dimension(350, 200);
    
    private static List<JugadorVisual> jugadoresActuales  = new ArrayList<>();;
    private String estadoJugadores = "Jugadores: " + jugadoresActuales.size() + "/" + MAX_JUGADORES;
    private JLabel titulo = new JLabel(estadoJugadores);
    private JPanel panelJugadores = new JPanel();
    
    public TablaJugadores() {
        //Inicializar el modelo
        modelo = Modelo.getInstaciaModelo();
        modelo.añadirObserver(this);
        
        //Definir disposicion
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        crearTabla();
    }

    private void inicializar(){
        
    }
    
    private void crearTabla() {
        //Inicializar lista de jugadores
        jugadoresActuales  = new ArrayList<>();
        
        //Diseño de la tabla de jugadores
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        setSize(dimension_tabla);
        setPreferredSize(dimension_tabla);
        generarMarcosJugadores();
    }

    private void generarMarcosJugadores() {
        //Título
        titulo.setAlignmentX(CENTER_ALIGNMENT);
        titulo.setOpaque(true);
        add(Box.createVerticalGlue());
        add(titulo);
        add(Box.createVerticalGlue());
        
        //Generar la vista de los jugadores
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
