/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mvcJuegoInicio.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;
import mvcJuegoInicio.interfaces.IVista;
import objetosPresentacion.JugadorLobbyVisual;

/**
 *
 * @author multaslokas33
 */
public class PantallaLobby extends JFrame implements IVista {

    private final IModeloLeibleJInicio modelo;
    private final IControlJuegoInicio controlador;

    private JPanel panelJugadores;
    private JButton btnListo;
    private JLabel lblEstado;

    public PantallaLobby(IModeloLeibleJInicio modelo, IControlJuegoInicio controlador) {
        this.modelo = modelo;
        this.controlador = controlador;
        generarPantallaLobby();
    }

    private void generarPantallaLobby() {
        setTitle("Lobby - Timbiriche");
        setSize(650, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        JLabel lblTitulo = new JLabel("Sala de Espera", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 36));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));
        lblTitulo.setForeground(new Color(50, 50, 50));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setBackground(new Color(245, 245, 245));
        centro.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblSubtitulo = new JLabel("Jugadores en la sala:");
        lblSubtitulo.setFont(new Font("SansSerif", Font.PLAIN, 18));
        centro.add(lblSubtitulo, gbc);

        gbc.gridy++;
        panelJugadores = new JPanel();
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));
        panelJugadores.setBackground(Color.WHITE);
        panelJugadores.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(panelJugadores);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
        centro.add(scrollPane, gbc);

        gbc.gridy++;
        lblEstado = new JLabel("Esperando jugadores...");
        lblEstado.setFont(new Font("SansSerif", Font.ITALIC, 14));
        lblEstado.setForeground(new Color(100, 100, 100));
        centro.add(lblEstado, gbc);

        add(centro, BorderLayout.CENTER);

        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(245, 245, 245));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(20, 20, 40, 20));

        btnListo = new JButton("Listo");
        btnListo.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnListo.setPreferredSize(new Dimension(200, 50));
        btnListo.setBackground(new Color(76, 175, 80));
        btnListo.setForeground(Color.WHITE);
        btnListo.setFocusPainted(false);
        btnListo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        btnListo.addActionListener(e -> {
            if (modelo.isJugadorLocalListo()) {
                controlador.marcarNoListo();
            } else {
                controlador.marcarListo();
            }
            actualizarBotonListo();
        });

        panelBoton.add(btnListo);
        add(panelBoton, BorderLayout.SOUTH);

        actualizarListaJugadores();
    }

    private void actualizarListaJugadores() {
        panelJugadores.removeAll();

        List<JugadorLobbyVisual> jugadores = modelo.obtenerJugadoresLobby();

        if (jugadores.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay jugadores conectados");
            lblVacio.setFont(new Font("SansSerif", Font.ITALIC, 14));
            lblVacio.setForeground(Color.GRAY);
            panelJugadores.add(lblVacio);
        } else {
            for (JugadorLobbyVisual jugador : jugadores) {
                JPanel panelJugador = crearPanelJugador(jugador);
                panelJugadores.add(panelJugador);
                panelJugadores.add(Box.createVerticalStrut(10));
            }
        }

        panelJugadores.revalidate();
        panelJugadores.repaint();
        actualizarEstado(jugadores);
    }

    private JPanel crearPanelJugador(JugadorLobbyVisual jugador) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(jugador.getColor(), 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
        ));
        panel.setMaximumSize(new Dimension(450, 60));
        panel.setPreferredSize(new Dimension(450, 60));

        JLabel lblNombre = new JLabel(jugador.getNombre());
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblNombre.setForeground(jugador.getColor());
        panel.add(lblNombre, BorderLayout.WEST);

        JLabel lblEstadoJugador = new JLabel(jugador.getEstadoTexto());
        lblEstadoJugador.setFont(new Font("SansSerif", Font.PLAIN, 14));

        if (jugador.isListo()) {
            lblEstadoJugador.setForeground(new Color(76, 175, 80));
        } else {
            lblEstadoJugador.setForeground(new Color(255, 152, 0));
        }

        panel.add(lblEstadoJugador, BorderLayout.EAST);

        return panel;
    }

    private void actualizarEstado(List<JugadorLobbyVisual> jugadores) {
        int listos = 0;
        for (JugadorLobbyVisual j : jugadores) {
            if (j.isListo()) {
                listos++;
            }
        }

        int total = jugadores.size();
        if (total == 0) {
            lblEstado.setText("Esperando jugadores...");
        } else if (listos == total && total >= 2) {
            lblEstado.setText("Todos listos! Iniciando partida...");
        } else {
            lblEstado.setText(listos + " de " + total + " jugadores listos");
        }
    }

    private void actualizarBotonListo() {
        int cantidadJugadores = modelo.obtenerJugadoresLobby().size();
        boolean suficientesJugadores = cantidadJugadores >= 2;

        btnListo.setEnabled(suficientesJugadores);

        if (!suficientesJugadores) {
            btnListo.setText("Esperando jugadores...");
            btnListo.setBackground(new Color(158, 158, 158));
        } else if (modelo.isJugadorLocalListo()) {
            btnListo.setText("Cancelar");
            btnListo.setBackground(new Color(244, 67, 54));
        } else {
            btnListo.setText("Listo");
            btnListo.setBackground(new Color(76, 175, 80));
        }
    }

    @Override
    public void actualizar() {
        SwingUtilities.invokeLater(() -> {
            actualizarListaJugadores();
            actualizarBotonListo();
        });
    }

    @Override
    public void mostrar() {
        SwingUtilities.invokeLater(() -> {
            actualizar();
            setVisible(true);
        });
    }
}
