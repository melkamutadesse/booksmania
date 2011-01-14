package edu.aptu.sd.library;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BooksList {

    private List<BookRecord> books = new LinkedList<BookRecord>();

    /**
     * @return the data
     */
    public List<BookRecord> getData() {
        return books;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<BookRecord> data) {
        this.books = data;
    }
    
}
