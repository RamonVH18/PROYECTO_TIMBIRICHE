/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mvcJuegoIniciado.vistas;

import enums.ObserverType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import mvcJuegoIniciado.interfaces.IVista;
import objetosPresentacion.LineaTablero;
import mvcJuegoIniciado.interfaces.IControlJuegoIniciado;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;

/**
 *
 * @author Ramon Valencia
 */
public class PantallaDeJuego extends JFrame implements IVista {

    private final IModeloLeibleJI modelo;
    private final IControlJuegoIniciado control;
    private final TableroJuego tablero;
    private final TablaPuntajes tablaPuntajes;
    
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
        this.tablaPuntajes = new TablaPuntajes(modelo);
        pantallaJuego();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1MouseClicked


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
        
        add(tablaPuntajes, BorderLayout.WEST);

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

    

    private void configurarBotonClick(JButton boton) {
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                switch (boton.getText()) {
                    case "Realizar Jugada" -> {
                        LineaTablero lineaSeleccionada = tablero.getLineaSeleccionada();
                        control.realizarJugada(lineaSeleccionada);
                        tablero.desocuparLineaSeleccionada();
                    }
                    case "Menú de opciones" ->
                        control.mostrarVista(ObserverType.MENU_OPCIONES);
                }
            }
        });
    }

    @Override
    public void actualizar() {
        repaint();
        revalidate();
        tablaPuntajes.actualizar();
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
