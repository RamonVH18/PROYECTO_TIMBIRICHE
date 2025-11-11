/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mvcJuegoIniciado.vistas;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import mvcJuegoIniciado.interfaces.IVista;
import objetosPresentacion.Linea;
import mvcJuegoIniciado.interfaces.IControlJuegoIniciado;
import mvcJuegoIniciado.interfaces.IModeloLeibleJI;

/**
 *
 * @author Ramon Valencia
 */
public class PantallaDeJuego extends JFrame implements IVista {

    private final IModeloLeibleJI modelo;
    private final IControlJuegoIniciado control;
    private TableroJuego tablero;

    /**
     * Creates new form pantallaDeJuego
     * @param modelo
     * @param control
     * @param tablero
     */
    public PantallaDeJuego(IModeloLeibleJI modelo, IControlJuegoIniciado control, TableroJuego tablero) {
        this.modelo = modelo;
        this.control = control;
        this.tablero = tablero;
        generarPantallaDeJuego();
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton1MouseClicked


    //TODO: posible desuso
    private void generarPantallaDeJuego() {
        setTitle("Timbiriche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        //esta es la tabal de los jugadores
        TablaJugadores tabla = new TablaJugadores(modelo);
        add(tabla, BorderLayout.WEST);

        //y esta la del tablero tablero asi nomas
       // tablero = modelo.obtenerTablero();
        javax.swing.JScrollPane scrollTablero = new javax.swing.JScrollPane(tablero);
        add(scrollTablero, BorderLayout.CENTER);
        
        generarPanelBotones();

        pack();
    }
    
    private void generarPanelBotones() {
        // por el momento no tienen la funcion pero ya es para que lo dejemos listo 
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnJugada = new JButton("Realizar Jugada");
        configurarBotonClick(btnJugada);
        JButton btnMenu = new JButton("Menú de opciones");
        configurarBotonClick(btnMenu);

        panelBotones.add(btnJugada);
        panelBotones.add(btnMenu);

        add(panelBotones, BorderLayout.SOUTH);
    }
    
    private void configurarBotonClick(JButton boton) {
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                switch(boton.getText()) {
                    case ("Realizar Jugada"):
                        Linea lineaSeleccionada = tablero.getLineaSeleccionada();
                        control.realizarJugada(lineaSeleccionada);
                    case ("Menú de opciones"):
                        control.mostrarMenuDeOpciones();
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        repaint();
        revalidate();
    }
    
    @Override 
    public void mostrar(){
        setVisible(modelo.isMostrandoPantallaDeJuego());
    }
    
}
