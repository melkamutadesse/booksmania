/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.library.gui;

import edu.aptu.sd.library.gui.messagebox.SwingMessageBox;
import edu.aptu.sd.library.gui.messagebox.IMessageBox;
import edu.aptu.sd.library.gui.filechooser.SwingFileChooser;
import edu.aptu.sd.library.gui.filechooser.IFileChooser;

/**
 *
 * @author nikita
 */
public class GUIFactory {
    public static IMessageBox getMessageBox() {
        return new SwingMessageBox();
    }
    public static IFileChooser getFileChooser() {
        return new SwingFileChooser();
    }
}
