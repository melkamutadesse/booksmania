/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.library.gui.filechooser;

import java.io.File;

/**
 *
 * @author nikita
 */
public interface IFileChooser {
    public void setSelectedFile(File file);

    public boolean showSaveDialog();

    public File getSelectedFile();
}
