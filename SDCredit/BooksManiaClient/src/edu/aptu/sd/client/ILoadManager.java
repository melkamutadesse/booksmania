/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client;

import java.io.IOException;

/**
 *
 * @author nikita
 */
public interface ILoadManager {

    public void openServerSocket() throws IOException;

    public void closeServerSocket();

}
