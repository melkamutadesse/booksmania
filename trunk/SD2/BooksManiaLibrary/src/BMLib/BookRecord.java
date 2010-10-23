package BMLib;

import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Никита
 */

public class BookRecord implements Serializable {

    public String author;
    public String title;
    public String address;
    public int guid;

    public BookRecord(String author, String title, String address, int guid) {
        this.author = author;
        this.title = title;
        this.address = address;
        this.guid = guid;
    }
}
