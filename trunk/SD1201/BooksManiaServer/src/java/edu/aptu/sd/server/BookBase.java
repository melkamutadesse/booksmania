package edu.aptu.sd.server;

import edu.aptu.sd.library.BooksRecordManager;
import edu.aptu.sd.library.BooksResult;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class BookBase {

    private static IDBConnection conn = DBConnection.getInstance();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registerBook")
    public int registerBook(@WebParam(name = "clientID")
    final int clientID, @WebParam(name = "author")
    final String author, @WebParam(name = "title")
    final String title, @WebParam(name = "size")
    final int size) {
        return conn.addBook(clientID, author, title, size);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registerUser")
    public int registerUser(@WebParam(name = "address")
    final String address) {
        return conn.registerUser(address);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "showBooks")
    public String showBooks(@WebParam(name = "searchString")
    final String searchString) {
        BooksResult db = conn.search(searchString);
        System.out.println("DB:" + db);
        return BooksRecordManager.serialize(db);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setUserStatus")
    public boolean setUserStatus(@WebParam(name = "clientID")
    final int clientID, @WebParam(name = "online")
    final boolean online, @WebParam(name = "address")
    final String address) {
        return conn.setUserStatus(clientID, online, address);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "removeBook")
    public boolean removeBook(@WebParam(name = "clientID")
    final int clientID, @WebParam(name = "bookID")
    final int bookID) {
        return conn.removeBook(clientID, bookID);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "registerBookCopy")
    public boolean registerBookCopy(@WebParam(name = "clientID")
    final int clientID, @WebParam(name = "bookID")
    final int bookID) {
        return conn.addBookCopy(clientID, bookID);
    }

}
