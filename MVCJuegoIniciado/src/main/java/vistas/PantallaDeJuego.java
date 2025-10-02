/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controlador.Control;
import interfaces.IControl;
import interfaces.IModeloLeible;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import modelo.Modelo;
import interfaces.IVista;

/**
 *
 * @author Ramon Valencia
 */
public class PantallaDeJuego extends JFrame implements IVista {

    private IModeloLeible modelo = Modelo.getInstaciaModelo();
    private IControl control = Control.getInstanciaControl();

    /**
     * Creates new form pantallaDeJuego
     */
    public PantallaDeJuego() {
        setTitle("Timbiriche");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setLayout(new BorderLayout());

        //esta es la tabal de los jugadores
        TablaJugadores tabla = new TablaJugadores();
        add(tabla, BorderLayout.WEST);

        //y esta la del tablero tablero asi nomas
        JPanel tablero = modelo.obtenerTablero();
        javax.swing.JScrollPane scrollTablero = new javax.swing.JScrollPane(tablero);
        add(scrollTablero, BorderLayout.CENTER);

        // por el momento no tienen la funcion pero ya es para que lo dejemos listo 
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnJugada = new JButton("Realizar Jugada");
        JButton btnMenu = new JButton("Menú de opciones");

        btnJugada.addActionListener(e -> control.agregarJugador()); // mock
        panelBotones.add(btnJugada);
        panelBotones.add(btnMenu);

        add(panelBotones, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        control.agregarJugador();
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaDeJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaDeJuego().setVisible(true);
            }
        });
    }

    //TODO: posible desuso
    private void generarTablaJugadores() {
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
      
    }

}
