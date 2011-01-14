/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.server.db;

/**
 *
 * @author nikita
 */
public class DBException extends RuntimeException {
    public DBException() {}
    public DBException(String msg) {
        super(msg);
    }
    public DBException(Throwable innerException) {
        super(innerException);
    }
}
