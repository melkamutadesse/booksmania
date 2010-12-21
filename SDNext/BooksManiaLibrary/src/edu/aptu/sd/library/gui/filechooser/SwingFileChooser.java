/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.library.gui.filechooser;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author nikita
 */
public class SwingFileChooser implements IFileChooser {

    private JFileChooser fch = new JFileChooser();

    public void setSelectedFile(File file) {
        fch.setSelectedFile(file);
    }

    public boolean showSaveDialog() {
        return fch.showSaveDialog(null) == JFileChooser.APPROVE_OPTION;
    }

    public File getSelectedFile() {
        return fch.getSelectedFile();
    }
}
