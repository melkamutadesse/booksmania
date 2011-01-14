/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client.configuration;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author nikita
 */
public class ClientIDRW implements IClientIDRW {

    private String filePath;

    public ClientIDRW(String filePath) {
        this.filePath = filePath;
    }

    public int readClientIDFromFile(String address) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
        int id = dis.readInt();
        dis.close();
        return id;
    }

    public void writeClientIDtoFile(int clientID) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
        dos.writeInt(clientID);
        dos.close();
    }

}
