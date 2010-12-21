/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client.ui;

/**
 *
 * @author nikita
 */
public class Main {
    
    public static String curPath;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                String jarDir = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                curPath = jarDir.substring(0, jarDir.lastIndexOf('/') + 1);
                //System.setProperty("user.dir", jarDir);
                //JOptionPane.showMessageDialog(null, jarDir);

                new MainForm().setVisible(true);
            }
        });
    }

}
