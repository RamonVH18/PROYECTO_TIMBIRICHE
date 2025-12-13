package mvcJuegoIniciado.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mvcJuegoIniciado.interfaces.IVista;
import mvcJuegoInicio.interfaces.IControlJuegoInicio;
import mvcJuegoInicio.interfaces.IModeloLeibleJInicio;

public class FrameMock extends JFrame implements IVista {

    private IControlJuegoInicio control;
    private IModeloLeibleJInicio modelo;

    public FrameMock(IControlJuegoInicio control, IModeloLeibleJInicio modelo) {
        this.control = control;
        this.modelo = modelo;
        initComponents();
    }

    private JPanel panelEncabezado() {
        JPanel panelSuperior = new JPanel();

        panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER));

        Font fuente = new Font("Arial", 1, 18);
        JLabel labelNombre = new JLabel("Esperando Jugadores...");
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

    private JPanel crearPanel(String name) {
        JPanel panel = new JPanel();
        panel.setSize(370, 420);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));

        JLabel nombrePartida = new JLabel(name);
        JButton btnUnirse = new JButton("Listo");

        panel.add(nombrePartida);
        panel.add(btnUnirse);

        setVisible(true);

        return panel;
    }

    public JPanel agregarPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 15));

        String tempName = "Jugador ";
        JButton btnListo = new JButton("Listo");

        for (int i = 0; i < 3; i++) {
            JPanel panel = crearPanel(tempName + (i + 1));
            mainPanel.add(panel);
        }

        mainPanel.setVisible(true);
        return mainPanel;
    }

    @Override
    public void actualizar() {
        repaint();
    }

    @Override
    public void mostrar() {
        setVisible(modelo.isMostrandoPantallaMock());
    }

    private void initComponents() {
        setTitle("Nombre");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(panelEncabezado(), BorderLayout.NORTH);
        add(panelBotones(), BorderLayout.SOUTH);

        add(agregarPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

}
