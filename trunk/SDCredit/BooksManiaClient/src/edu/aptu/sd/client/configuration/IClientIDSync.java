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
public interface IClientIDSync {

    int generateNewClientID(String address) throws IOException, Exception;

    boolean writeClientIDtoDB(int clientID, String address);

}
