/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.client;

/**
 *
 * @author nikita
 */
public interface ILoadManager {

    public void openServerSocket();

    public void closeServerSocket();

    public void getBook(String address, int port, int bookID);

}
