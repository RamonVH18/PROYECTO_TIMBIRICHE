/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcJuegoInicio.vistas;

import javax.swing.JOptionPane;

/**
 *
 * @author Ximena
 */
public class MensajeExitoso extends JOptionPane {
    
    public static void mostrarMensaje(String message) {
        showMessageDialog(null, message, "Partida creada", INFORMATION_MESSAGE);
    } 
}
