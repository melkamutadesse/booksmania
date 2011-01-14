/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author nikita
 */
public interface IClientIDRW {

    int readClientIDFromFile(String address) throws FileNotFoundException, IOException;

    void writeClientIDtoFile(int clientID) throws FileNotFoundException, IOException;

}
