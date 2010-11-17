package edu.aptu.sd.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Registrator implements IRegistrator {

    public int getClientID(String address) {
        int clientID = 0;
        try {
            clientID = readClientIDFromFile();
        } catch(Exception ex) {
            try {
                clientID = generateNewClientID(address);
            } catch (IOException ex1) {
                Logger.getLogger(Registrator.class.getName()).log(Level.SEVERE, null, ex1);
                closeApp();
            }
        }
        return clientID;
    }

    private int readClientIDFromFile() throws FileNotFoundException, IOException {
        File file = new File("BooksMania.id");
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        int id = dis.readInt();
        dis.close();
        edu.aptu.sd.server.BookBaseService service = new edu.aptu.sd.server.BookBaseService();
        edu.aptu.sd.server.BookBase port = service.getBookBasePort();
        port.setUserStatus(id, true);
        return id;
    }

    private int generateNewClientID(String address) throws IOException {
        edu.aptu.sd.server.BookBaseService service = new edu.aptu.sd.server.BookBaseService();
        edu.aptu.sd.server.BookBase port = service.getBookBasePort();
        int newID = port.registerUser(address);
        if (newID == 0) {
            closeApp();
        }
        File file = new File("BooksMania.id");
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        dos.writeInt(newID);
        dos.close();
        return newID;
    }

    private void closeApp() {
        JOptionPane.showMessageDialog(null, "Cannot generate new ID!");
        Logger.getLogger(Registrator.class.getName()).log(Level.SEVERE, "Error, application is closing");
        System.exit(-1);
    } 

}
