/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author nikita
 */
class MyServer implements Runnable {

    public void run() {
        try {
            ServerSocket servers = null;
            try {
                servers = new ServerSocket(4444);
            } catch (IOException e) {
                System.out.println("Couldn't listen to port 4444");
                System.exit(-1);
            }

            while (true) {
                Socket fromclient = null;

                try {
                    fromclient = servers.accept();
                } catch (IOException e) {
                    System.out.println("Can't accept");
                    e.printStackTrace();
                    System.exit(-1);
                }

                DataInputStream is = new DataInputStream(fromclient.getInputStream());
                int numFromClient = is.readInt();

                File f1 = new File("J2EE_plan.pdf");
                File f2 = new File("GlassFish.pdf");
                File f = numFromClient == 0 ? f1 : f2;
                int size = (int) f.length();
                FileInputStream fis = new FileInputStream(f);
                DataOutputStream dos = new DataOutputStream(fromclient.getOutputStream());
                dos.writeInt(size);
                for (int i = 0; i < size; ++i) {
                    dos.write(fis.read());
                }
                dos.close();
                fis.close();
                fromclient.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MyServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

class MyClient implements Runnable {

    public void run() {
        try {
            int request = 1;
            while (true) {
                JFileChooser fch = new JFileChooser();
                switch (fch.showSaveDialog(null)) {
                    case JFileChooser.ERROR_OPTION:
                        Logger.getLogger(LoadManager.class.getName()).log(Level.SEVERE, "JFileChooser returned errror");
                        break;
                    case JFileChooser.APPROVE_OPTION:
                        File f = fch.getSelectedFile();
                        f.createNewFile();

                        Socket fromserver = new Socket(InetAddress.getLocalHost(), 4444);
                        DataOutputStream dos = new DataOutputStream(fromserver.getOutputStream());
                        dos.writeInt(request);
                        DataInputStream in = new DataInputStream(fromserver.getInputStream());                        
                        OutputStream toFile = new FileOutputStream(f);
                        int size = in.readInt();
                        ProgressDialog pd = new ProgressDialog(null, false);
                        pd.setVisible(true);
                        for (int i = 0; i < size; ++i) {
                            toFile.write(in.read());
                            pd.setPercents(100 * i / size);
                        }
                        pd.setVisible(false);
                        dos.close();
                        toFile.close();
                        in.close();
                        fromserver.close();

                        break;
                }        
                request = 1 - request;
            }
        } catch (Exception ex) {
        }
    }
}

public class SocketTest {

    public static void main(String[] args) {
        new Thread(new MyServer()).start();
        new Thread(new MyClient()).start();
    }
}
