/*
 * To change this template, choose Tools | Templates
 * and open the template reader the editor.
 */
package edu.aptu.sd.client;

import edu.aptu.sd.client.ui.ProgressDialog;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author nikita
 */
public class LoadManager implements ILoadManager {

    private Configuration conf;
    private IConfigRW rw;
    private ServerSocket servers;
    private Thread listener;
    int clientID;

    public LoadManager(Configuration conf, IConfigRW rw, int clientID) {
        this.conf = conf;
        this.rw = rw;
        this.clientID = clientID;
    }

    private void removeBook(int bookID) {
        edu.aptu.sd.server.BookBaseService service = new edu.aptu.sd.server.BookBaseService();
        edu.aptu.sd.server.BookBase port = service.getBookBasePort();
        port.removeBook(clientID, bookID);
    }

    public void openServerSocket() {
        int port = conf.getServerPort();
        try {
            servers = new ServerSocket(port);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Can't open socket!", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(LoadManager.class.getName()).log(Level.INFO, "Can't open socket " + port);
            System.exit(-1);
        }

        listener = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Socket fromclient = null;
                    try {
                        fromclient = servers.accept();
                    } catch (IOException e) {
                        System.out.println("Can't accept");
                        System.exit(-1);
                    }

                    try {
                        DataInputStream is = new DataInputStream(fromclient.getInputStream());
                        DataOutputStream dos = new DataOutputStream(fromclient.getOutputStream());
                        int bookID = is.readInt();

                        String bookPath = conf.getBooks().get(bookID);
                        if (bookPath == null) {
                            removeBook(bookID);
                            dos.writeInt(-1);
                        } else {
                            File file = new File(bookPath);
                            if (!file.exists()) {
                                conf.getBooks().remove(bookID);
                                rw.writeConfig(conf);
                                removeBook(bookID);
                                dos.writeInt(-1);
                            } else {
                                int size = (int) file.length();
                                FileInputStream fis = new FileInputStream(file);                        
                                dos.writeInt(size);
                                for (int i = 0; i < size; ++i) {
                                    dos.write(fis.read());
                                }
                                fis.close();
                            }
                        }
                        dos.close();
                        is.close();
                        fromclient.close();
                    } catch (Exception ex) {
                        Logger.getLogger(LoadManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        listener.start();
    }

    public void closeServerSocket() {
        listener.stop();
        try {
            servers.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getBook(String address, int port, int bookID) {
        try {
            Socket fromserver = null;
            try {
                fromserver = new Socket(address, port);
            } catch (Exception ex) {
                Logger.getLogger(LoadManager.class.getName()).log(Level.SEVERE, "JFileChooser returned errror");
                JOptionPane.showMessageDialog(null, "Can't conect to external server!\nRefresh search result and try downloading again, please.", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DataOutputStream dos = new DataOutputStream(fromserver.getOutputStream());
            dos.writeInt(bookID);
            DataInputStream in = new DataInputStream(fromserver.getInputStream());
            int size = in.readInt();
            if (size == -1) {
                JOptionPane.showMessageDialog(null, "Book was removed!\nRefresh search result and try downloading again, please.", "Error!", JOptionPane.ERROR_MESSAGE);
            } else {
                JFileChooser fch = new JFileChooser();
                switch (fch.showSaveDialog(null)) {
                    case JFileChooser.ERROR_OPTION:
                        Logger.getLogger(LoadManager.class.getName()).log(Level.SEVERE, "JFileChooser returned errror");
                        break;
                    case JFileChooser.APPROVE_OPTION:
                        File f = fch.getSelectedFile();
                        f.createNewFile();
                        try {
                            OutputStream toFile = new FileOutputStream(f);
                            ProgressDialog pd = new ProgressDialog(null, false);
                            pd.setVisible(true);
                            for (int i = 0; i < size; ++i) {
                                toFile.write(in.read());
                                pd.setPercents(100 * i / size);
                            }
                            pd.setVisible(false);
                            toFile.close();

                            conf.getBooks().put(bookID, f.getAbsolutePath());
                            rw.writeConfig(conf);

                            edu.aptu.sd.server.BookBaseService service = new edu.aptu.sd.server.BookBaseService();
                            edu.aptu.sd.server.BookBase port2 = service.getBookBasePort();
                            port2.registerBookCopy(clientID, bookID); 

                        } catch(Exception ex) {
                            Logger.getLogger(LoadManager.class.getName()).log(Level.SEVERE, "JFileChooser returned errror");
                            JOptionPane.showMessageDialog(null, "Downloading error!\nRefresh search result and try downloading again, please.", "Error!", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        break;
                }
            }
            in.close();
            dos.close();
            fromserver.close();
        } catch (Exception ex) {
        }
    }
}
