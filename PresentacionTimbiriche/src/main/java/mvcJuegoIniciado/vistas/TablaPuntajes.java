/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoIniciado.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;
import mvcJuegoIniciado.interfaces.IVista;
import objetosPresentacion.PuntajeVisual;

/**
 *
 * @author Ramon Valencia
 */
public class TablaPuntajes extends JPanel implements IVista {

    private final IModeloLeibleJI modelo;

    public TablaPuntajes(IModeloLeibleJI modelo) {
        this.modelo = modelo;
        generarTablaPuntajes();
    }

    private void generarTablaPuntajes() {

        JLabel titulo = new JLabel("Jugadores en partida");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(titulo);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        generarCards();
    }

    private void generarCards() {
        removeAll();
        add(Box.createVerticalStrut(10));

        List<PuntajeVisual> puntajes = modelo.obtenerPuntajes();

        for (PuntajeVisual p : puntajes) {
            add(crearCardsJugador(p));
            add(Box.createVerticalStrut(10));
        }

        repaint();
        revalidate();
    }

    private JPanel crearCardsJugador(PuntajeVisual p) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setPreferredSize(new Dimension(220, 80));

        card.setBackground(p.getColorJugador());

        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel lblNombre = new JLabel(p.getNombreJugador());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombre.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 5));

        JLabel lblScore = new JLabel(String.valueOf(p.getPuntuacion()));
        lblScore.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblScore.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        card.add(lblNombre, BorderLayout.WEST);
        card.add(lblScore, BorderLayout.EAST);

        return card;
    }

    @Override
    public void actualizar() {
        generarTablaPuntajes();
    }

    @Override
    public void mostrar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
