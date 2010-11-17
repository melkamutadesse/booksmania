/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd;

import BMLib.BookRecord;
import java.util.LinkedList;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Никита
 */
@WebService()
public class BookBase {

    //private static LinkedList<BookRecord> db = new LinkedList<BookRecord>();
    private static IDBConnection conn = DBConnection.getInstance();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "showBooks")
    public java.util.LinkedList<String> showBooks() {
        List<BookRecord> db = conn.search("*");
        LinkedList<String> lst = new LinkedList<String>();
        for (int i = 0; i < db.size(); ++i)
            lst.add(db.get(i).author + " " + db.get(i).title);
        return lst;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registerBook")
    public boolean registerBook(@WebParam(name = "author")
    final String author, @WebParam(name = "title")
    final String title, @WebParam(name = "clientID")
    final int clientID) {
        System.out.println(clientID + " " + author + " " + title);
        return conn.addBook(clientID, author, title);
    }

}
