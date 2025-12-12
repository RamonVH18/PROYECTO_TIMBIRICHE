package mvcJuegoIniciado.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;

public class PantallaUnirsePartida extends JFrame implements IVista {

    private IControlJuegoInicio control;
    private IModeloLeibleJInicio modelo;

    public PantallaUnirsePartida(IControlJuegoInicio control, IModeloLeibleJInicio modelo) {
        this.control = control;
        this.modelo = modelo;

        setTitle("Pantala Unirse a Partida");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panelEncabezado(), BorderLayout.NORTH);
        add(panelBotones(), BorderLayout.SOUTH);
    }

    private JPanel panelEncabezado() {
        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));

        Font fuente = new Font("Arial", 1, 18);
        JLabel labelNombre = new JLabel("Unirse a partida");
        labelNombre.setFont(fuente);

        panelSuperior.add(labelNombre);

        setBackground(Color.LIGHT_GRAY);

        return panelSuperior;
    }

    private JPanel panelBotones() {
        JPanel panelInferior = new JPanel();

        panelInferior.setLayout(new FlowLayout(FlowLayout.CENTER));

        Font fuente = new Font("Arial", 1, 14);
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(fuente);

        panelInferior.add(btnVolver);

        return panelInferior;
    }

    public void agregarPanel(String name, String bs, int maxP) {
        JPanel panel = crearPanel(name, bs, maxP);
        this.add(panel, BorderLayout.CENTER);
        repaint();
    }

    private JPanel crearPanel(String name, String bs, int maxP) {
        JPanel panel = new JPanel();
        panel.setSize(370, 420);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));

        JLabel nombrePartida = new JLabel(name);

        JButton btnInformacion = new JButton("Info");
        btnInformacion.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    "Nombre de la partida: " + name + "\n\nJugadores: " + maxP + "\n\nTamaño del tablero: "
                            + bs,
                    "Información", JOptionPane.INFORMATION_MESSAGE);
        });

        JButton btnUnirse = new JButton("Unirse");
        btnUnirse.addActionListener(e->{
            control.seleccionarPartida();
        });

        panel.add(nombrePartida);
        panel.add(btnInformacion);
        panel.add(btnUnirse);

        return panel;
    }

    @Override
    public void actualizar() {
        repaint();
    }

    @Override
    public void mostrar() {
        setVisible(modelo.isMostrandoUnirsePartida());
        if(modelo.isPartidaCargada()){
           agregarPanel(modelo.obtenerNombrePartida(), modelo.obtenerBoardSize(), modelo.obtenerMaxJugadores());
        }
    }

}
