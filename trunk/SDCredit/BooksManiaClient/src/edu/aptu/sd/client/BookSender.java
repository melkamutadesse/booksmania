/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aptu.sd.client;

import edu.aptu.sd.client.configuration.IConfigRW;
import edu.aptu.sd.client.configuration.Configuration;
import edu.aptu.sd.client.ui.Main;
import edu.aptu.sd.library.logger.Logger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.logging.Level;

/**
 *
 * @author nikita
 */
public class BookSender implements Runnable {

    private Socket fromclient;
    private Configuration conf;
    private IConfigRW rw;
    private int clientID;

    public BookSender(Socket socket, Configuration conf, IConfigRW rw, int clientID) {
        fromclient = socket;
        this.conf = conf;
        this.rw = rw;
        this.clientID = clientID;
    }

    public void run() {
        try {
            DataInputStream is = new DataInputStream(fromclient.getInputStream());
            DataOutputStream dos = new DataOutputStream(fromclient.getOutputStream());
            int bookID = is.readInt();

            String bookPath = conf.getBooks().get(bookID);
            if (bookPath == null) {                
                dos.writeInt(-1);
                removeBook(bookID);
            } else {
                File file = new File(bookPath);
                if (!file.exists()) {
                    dos.writeInt(-1);
                    conf.getBooks().remove(bookID);
                    rw.writeConfig(conf);
                    removeBook(bookID);                    
                } else {
                    int size = (int) file.length();
                    FileInputStream fis = new FileInputStream(file);
                    dos.writeInt(size);
                    dos.writeBytes(file.getName());
                    dos.writeByte((byte)'\n');
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
            Logger.log(LoadManager.class.getName(), ex, Level.SEVERE, Main.curPath);
        }
    }

    private void removeBook(int bookID) {
        edu.aptu.sd.server.BooksManiaServiceService service = new edu.aptu.sd.server.BooksManiaServiceService();
        edu.aptu.sd.server.BooksManiaService port = service.getBooksManiaServicePort();
        port.removeBook(clientID, bookID);
    }
}
