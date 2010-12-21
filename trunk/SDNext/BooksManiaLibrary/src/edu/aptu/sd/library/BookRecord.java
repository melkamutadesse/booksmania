package edu.aptu.sd.library;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BookRecord implements Serializable {
    private String author;
    private String title;
    private int id;
    private int size;
    private List<String> addresses = new LinkedList<String>();

    public BookRecord(int id, String author, String title, int size) {
        this.author = author;
        this.title = title;
        this.id = id;
        this.size = size;
    }

    public BookRecord(){
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the addresses
     */
    public List<String> getAddresses() {
        return addresses;
    }

    /**
     * @param addresses the addresses to set
     */
    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}
