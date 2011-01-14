/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aptu.sd.client;

import edu.aptu.sd.client.configuration.IConfigRW;
import edu.aptu.sd.client.configuration.Configuration;
import edu.aptu.sd.client.ui.Main;
import edu.aptu.sd.client.ui.ProgressDialog;
import edu.aptu.sd.library.gui.GUIFactory;
import edu.aptu.sd.library.gui.filechooser.IFileChooser;
import edu.aptu.sd.library.gui.messagebox.MessageType;
import edu.aptu.sd.library.logger.Logger;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 *
 * @author nikita
 */
public class BookReceiver implements Runnable {

    private String address;
    private int port;
    private int bookID;
    private Configuration conf;
    private IConfigRW rw;
    private int clientID;

    public BookReceiver(String address, int port, int bookID, Configuration conf, IConfigRW rw, int clientID) {
        this.address = address;
        this.port = port;
        this.bookID = bookID;
        this.conf = conf;
        this.rw = rw;
        this.clientID = clientID;
    }

    public void run() {
        try {
            Socket fromserver = null;
            try {
                fromserver = new Socket(address, port);
            } catch (Exception ex) {
                Logger.log(LoadManager.class.getName(), "JFileChooser returned errror", Level.SEVERE, Main.curPath);
                GUIFactory.getMessageBox().show("Can't conect to external server!\nRefresh search result and try downloading again, please.", "Error!", MessageType.ERROR);
                return;
            }
            DataOutputStream dos = new DataOutputStream(fromserver.getOutputStream());
            dos.writeInt(bookID);
            DataInputStream in = new DataInputStream(fromserver.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int size = in.readInt();
            if (size == -1) {
                GUIFactory.getMessageBox().show("Book was removed!\nRefresh search result and try downloading again, please.", "Error!", MessageType.ERROR);
            } else {
                String name = br.readLine();
                IFileChooser fch = GUIFactory.getFileChooser();
                //JFileChooser fch = new JFileChooser();
                fch.setSelectedFile(new File(name));
                if (fch.showSaveDialog()) {
                    File f = fch.getSelectedFile();
                    f.createNewFile();
                    ProgressDialog pd = new ProgressDialog(null, false);
                    try {
                        OutputStream toFile = new FileOutputStream(f);
                        pd.setVisible(true);
                        for (int i = 0; i < size; ++i) {
                            toFile.write(in.read());
                            pd.setPercents(100 * i / size);
                        }
                        pd.setVisible(false);
                        toFile.close();

                        conf.getBooks().put(bookID, f.getAbsolutePath());
                        rw.writeConfig(conf);

                        edu.aptu.sd.server.BooksManiaServiceService service = new edu.aptu.sd.server.BooksManiaServiceService();
                        edu.aptu.sd.server.BooksManiaService port2 = service.getBooksManiaServicePort();
                        port2.registerBookCopy(clientID, bookID);
                        GUIFactory.getMessageBox().show("The book was downloaded!", ":-)", MessageType.INFO);

                    } catch (Exception ex) {
                        pd.setVisible(false);
                        Logger.log(LoadManager.class.getName(), "JFileChooser returned errror", Level.SEVERE, Main.curPath);
                        GUIFactory.getMessageBox().show("Downloading error!\nRefresh search result and try downloading again, please.", "Error!", MessageType.ERROR);
                        return;
                    }
                } else {
                    Logger.log(LoadManager.class.getName(), "User pressed cancel or JFileChooser returned error", Level.SEVERE, Main.curPath);
                }
            }
            br.close();
            in.close();
            dos.close();
            fromserver.close();
        } catch (Exception ex) {
        }
    }
}
