/*
 * To change this template, choose Tools | Templates
 * and open the template reader the editor.
 */
package edu.aptu.sd.client;

import edu.aptu.sd.client.configuration.IConfigRW;
import edu.aptu.sd.client.configuration.Configuration;
import edu.aptu.sd.client.ui.Main;
import edu.aptu.sd.library.logger.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;

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
    private ExecutorService exServ;

    public LoadManager(Configuration conf, IConfigRW rw, int clientID, ExecutorService exServ) {
        this.conf = conf;
        this.rw = rw;
        this.clientID = clientID;
        this.exServ = exServ;
    }   

    public void openServerSocket() throws IOException {
        servers = new ServerSocket(conf.getServerPort());

        listener = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Socket fromclient = null;
                    try {
                        fromclient = servers.accept();
                    } catch (IOException e) {
                        Logger.log(LoadManager.class.getName(), "Can't accept other client", Level.INFO, Main.curPath);
                    }
                    exServ.submit(new BookSender(fromclient, conf, rw, clientID));
                }
            }
        });
        listener.start();
    }

    public void closeServerSocket() {        
        try {
            listener.stop();
            servers.close();
        } catch (IOException ex) {
            Logger.log(LoadManager.class.getName(), ex, Level.SEVERE, Main.curPath);
            Logger.log(LoadManager.class.getName(), "Can't accept other client", Level.INFO, Main.curPath);
        }
    }
}
