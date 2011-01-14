/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client.configuration;

import java.io.IOException;

/**
 *
 * @author nikita
 */
public class ClientIDSyncWithDB implements IClientIDSync {

    public boolean writeClientIDtoDB(int clientID, String address) {
        edu.aptu.sd.server.BooksManiaServiceService service = new edu.aptu.sd.server.BooksManiaServiceService();
        edu.aptu.sd.server.BooksManiaService port = service.getBooksManiaServicePort();
        return port.setUserStatus(clientID, true, address);
    }

    public int generateNewClientID(String address) throws IOException, Exception {
        edu.aptu.sd.server.BooksManiaServiceService service = new edu.aptu.sd.server.BooksManiaServiceService();
        edu.aptu.sd.server.BooksManiaService port = service.getBooksManiaServicePort();
        int newID = port.registerUser(address);
        if (newID == 0) {
            throw new Exception("Cannot generate new ID!");
        }
        return newID;
    }

}
