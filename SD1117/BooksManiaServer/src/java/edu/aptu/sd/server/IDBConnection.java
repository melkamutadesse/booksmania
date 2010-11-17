package edu.aptu.sd.server;

import edu.aptu.sd.library.BooksResult;

public interface IDBConnection {

    //books treating
    int addBook(int clientID, String author, String title, int size);
    BooksResult search(String searchString);
    boolean addBookCopy(int clientID, int bookID);

    //users treating
    int registerUser(String address);
    boolean setUserStatus(int clientID, boolean online);

}
