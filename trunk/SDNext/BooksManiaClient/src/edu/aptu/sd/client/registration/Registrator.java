package edu.aptu.sd.client.registration;

import edu.aptu.sd.client.configuration.ConfigRW;
import edu.aptu.sd.client.configuration.Configuration;
import edu.aptu.sd.client.ui.Main;
import edu.aptu.sd.library.gui.GUIFactory;
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
import javax.xml.ws.WebServiceException;

public class Registrator implements IClientInfoFormer {

    private String servlet = "http://localhost:8084/BooksManiaServer/GetIP";
    private String filePath = Main.curPath + "BooksMania.id";

    public Registrator(int serverPort) {
        this.serverPort = serverPort;
    }
    private int serverPort;

    public String getClientIP() {
        try {
//            URL url = new URL(servlet);
//            URLConnection con = url.openConnection();
//            InputStream input = con.getInputStream();
//            StringBuilder sb = new StringBuilder();
//            int c;
//            while ((c = input.read()) != -1) {
//                sb.append((char) c);
//            }
//            return sb.toString();
            return "127.0.0.1 " + serverPort;
        } catch (Exception ex) {
            Logger.getLogger(Registrator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getClientID(String address) throws Exception {
        int clientID = 0;
        try {
            clientID = readClientIDFromFile(address);
            writeClientIDtoDB(clientID, address);
        } catch(Exception ex) {
            clientID = generateNewClientID(address);
            writeClientIDtoFile(clientID);
        }
        return clientID;
    }

    private int readClientIDFromFile(String address) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        int id = dis.readInt();
        dis.close();
        return id;
    }

    private boolean writeClientIDtoDB(int clientID, String address) {
        edu.aptu.sd.server.BooksManiaServiceService service = new edu.aptu.sd.server.BooksManiaServiceService();
        edu.aptu.sd.server.BooksManiaService port = service.getBooksManiaServicePort();
        return port.setUserStatus(clientID, true, address);
    }

    private int generateNewClientID(String address) throws IOException, Exception {
        edu.aptu.sd.server.BooksManiaServiceService service = new edu.aptu.sd.server.BooksManiaServiceService();
        edu.aptu.sd.server.BooksManiaService port = service.getBooksManiaServicePort();
        int newID = port.registerUser(address);
        if (newID == 0) {
            throw new Exception("Cannot generate new ID!");
        }
        return newID;
    }

    private void writeClientIDtoFile(int clientID) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        dos.writeInt(clientID);
        dos.close();
    }

}
