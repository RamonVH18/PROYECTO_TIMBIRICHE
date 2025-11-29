/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mvcJuegoIniciado.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mvcJuegoIniciado.interfaces.IVista;
import objetosPresentacion.LineaTablero;
import mvcJuegoIniciado.interfaces.IControlJuegoIniciado;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;
import objetosPresentacion.PuntajeVisual;

/**
 *
 * @author Ramon Valencia
 */
public class PantallaDeJuego extends JFrame implements IVista {

    private final IModeloLeibleJI modelo;
    private final IControlJuegoIniciado control;
    private final TableroJuego tablero;

    private JPanel panelJugadores;
    private JLabel lblTitulo;
    private JButton btnJugada;

    /**
     * Creates new form pantallaDeJuego
     *
     * @param modelo
     * @param control
     * @param tablero
     */
    public PantallaDeJuego(IModeloLeibleJI modelo, IControlJuegoIniciado control, TableroJuego tablero) {
        this.modelo = modelo;
        this.control = control;
        this.tablero = tablero;
        pantallaJuego();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1MouseClicked

    //TODO: posible desuso
//    private void generarPantallaDeJuego() {
//        setTitle("Timbiriche");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//        setLayout(new BorderLayout());
//
//        //esta es la tabal de los jugadores
//        TablaJugadores tabla = new TablaJugadores(modelo);
//        add(tabla, BorderLayout.WEST);
//
//        //y esta la del tablero tablero asi nomas
//        // tablero = modelo.obtenerTablero();
//        javax.swing.JScrollPane scrollTablero = new javax.swing.JScrollPane(tablero);
//        add(scrollTablero, BorderLayout.CENTER);
//
//        generarPanelBotones();
//
//        pack();
//    }
//
//    private void generarPanelBotones() {
//        // por el momento no tienen la funcion pero ya es para que lo dejemos listo 
//        JPanel panelBotones = new JPanel(new FlowLayout());
//        JButton btnJugada = new JButton("Realizar Jugada");
//        configurarBotonClick(btnJugada);
//        JButton btnMenu = new JButton("Menú de opciones");
//        configurarBotonClick(btnMenu);
//
//        panelBotones.add(btnJugada);
//        panelBotones.add(btnMenu);
//
//        add(panelBotones, BorderLayout.SOUTH);
//    }
//
//    private void configurarBotonClick(JButton boton) {
//        boton.addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                switch (boton.getText()) {
//                    case ("Realizar Jugada"):
//                        LineaTablero lineaSeleccionada = tablero.getLineaSeleccionada();
//                        control.realizarJugada(lineaSeleccionada);
//                        break;
//                    case ("Menú de opciones"):
//                        control.mostrarMenuDeOpciones();
//                        break;
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private void pantallaJuego() {
        setTitle("Timbiriche - Partida");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(40, 40, 40));
        panelSuperior.setPreferredSize(new Dimension(100, 70));

        lblTitulo = new JLabel("¡A JUGAR!");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        panelSuperior.add(lblTitulo);

        add(panelSuperior, BorderLayout.NORTH);

        panelJugadores = new JPanel();
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));
        panelJugadores.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelJugadores.setBackground(Color.WHITE);

        actualizarPanelJugadores();
        add(panelJugadores, BorderLayout.WEST);

        JPanel contenedorTablero = new JPanel(new BorderLayout());
        contenedorTablero.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(tablero);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        contenedorTablero.add(scroll, BorderLayout.CENTER);

        add(contenedorTablero, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(Color.WHITE);

        btnJugada = new JButton("Realizar Jugada");
        btnJugada.setBackground(new Color(30, 150, 80));
        btnJugada.setForeground(Color.WHITE);
        btnJugada.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnJugada.setFocusPainted(false);
        configurarBotonClick(btnJugada);

        JButton btnOpciones = new JButton("Menú de opciones");
        btnOpciones.setBackground(new Color(30, 30, 30));
        btnOpciones.setForeground(Color.WHITE);
        btnOpciones.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnOpciones.setFocusPainted(false);
        configurarBotonClick(btnOpciones);

        panelBotones.add(btnJugada);
        panelBotones.add(btnOpciones);

        add(panelBotones, BorderLayout.SOUTH);

        pack();
    }

    private void actualizarPanelJugadores() {
        panelJugadores.removeAll();
        JLabel titulo = new JLabel("Jugadores en partida");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelJugadores.add(titulo);

        panelJugadores.add(Box.createVerticalStrut(10));

        List<PuntajeVisual> puntajes = modelo.obtenerPuntajes();

        for (PuntajeVisual p : puntajes) {
            JPanel card = crearCartaJugador(p);
            panelJugadores.add(card);
            panelJugadores.add(Box.createVerticalStrut(10));
        }

        panelJugadores.repaint();
        panelJugadores.revalidate();
    }

    private JPanel crearCartaJugador(PuntajeVisual p) {
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

    private void configurarBotonClick(JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                switch (boton.getText()) {
                    case "Realizar Jugada" -> {
                        LineaTablero lineaSeleccionada = tablero.getLineaSeleccionada();
                        control.realizarJugada(lineaSeleccionada);
                    }
                    case "Menú de opciones" ->
                        control.mostrarMenuDeOpciones();
                }
            }
        });
    }

    @Override
    public void actualizar() {
        actualizarPanelJugadores();
        repaint();
        revalidate();
        btnJugada.setEnabled(
                modelo.estoyJugando()
        );
    }

    @Override
    public void mostrar() {
        setVisible(modelo.isMostrandoPantallaDeJuego());
    }

    private Color getColorPorIndice(int index) {
        Color[] colores = {
            
            new Color(255, 245, 180),
            new Color(220, 180, 255),
            new Color(255, 200, 220),
            new Color(230, 230, 230)
        };

        return colores[index % colores.length];
    }
}
