package edu.aptu.sd.server.db;

import edu.aptu.sd.library.BooksList;

public interface ILibrary {

    //books treating
    /**
     * Adds new book to the Data Base
     * @param clientID is a unique ID of client (from file or DB)
     * @param author is book author
     * @param title is book title
     * @param size is book size
     * @return unique ID of added book, if it returns 0, it means that book wasn't added
     */
    int addBook(int clientID, String author, String title, int size);
    /**
     * Searches books with title or author like searchString
     * @param searchString is a world for search in DB
     * @return list of books which were found in special format
     */
    BooksList search(String searchString);
    /**
     * Removes book from DB
     * @param clientID is a unique ID of client (from file or DB)
     * @param bookID is a unique ID of book from DB
     * @return true if book was successfully deleted, false - otherwise
     */
    boolean removeBook(int clientID, int bookID);
    /**
     * Adds a copy of book to DB
     * @param clientID is a unique ID of client (from file or DB)
     * @param bookID is a unique ID of book from DB
     * @return true if book copy was successfully added, false - otherwise
     */
    boolean addBookCopy(int clientID, int bookID);

    //users treating
    /**
     * Registers new user
     * @param address is an address of new user
     * @return unique user ID, if an error occured, 0 will be returned
     */
    int registerUser(String address);
    /**
     * Changes user status (online-offline)
     * @param clientID is a unique ID of client (from file or DB)
     * @param online is status: true means online, false - offline
     * @param address is an address of new user
     * @return true if user status was successfully changed, false - otherwise
     */
    boolean setUserStatus(int clientID, boolean online, String address);

}
