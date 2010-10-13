/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd;

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

    private static List<BookRecord> db = new LinkedList<BookRecord>();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "RegisterBook")
    @Oneway
    public void RegisterBook(@WebParam(name = "author")
    final String author, @WebParam(name = "title")
    final String title) {
        System.out.println("The book was added " + author + " " + title);
        db.add(new BookRecord(author, title));
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "showBooks")
    public java.util.LinkedList<java.lang.String> showBooks() {
        //TODO write your implementation code here:
        LinkedList<String> listAuthBook = new LinkedList<String>();
        for(int i=0; i<db.size(); ++i)
        {
            listAuthBook.add(db.get(i).author + " " + db.get(i).title);
        }
        return listAuthBook;
    }

}
