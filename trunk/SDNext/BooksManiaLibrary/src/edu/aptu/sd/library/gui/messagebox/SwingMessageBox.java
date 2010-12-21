/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aptu.sd.library.gui.messagebox;

import javax.swing.JOptionPane;

/**
 *
 * @author nikita
 */
public class SwingMessageBox implements IMessageBox {

    public void show(String message, String title, MessageType type) {
        int jType;
        switch (type) {
            case ERROR:
                jType = JOptionPane.ERROR_MESSAGE;
                break;
            case INFO:
                jType = JOptionPane.INFORMATION_MESSAGE;
                break;
            case QUESTION:
                jType = JOptionPane.QUESTION_MESSAGE;
                break;
            case WARNING:
                jType = JOptionPane.WARNING_MESSAGE;
                break;
            default:
                jType = JOptionPane.PLAIN_MESSAGE;
        }
        JOptionPane.showMessageDialog(null, message, title, jType);
    }
}
