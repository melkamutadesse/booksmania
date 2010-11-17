package edu.aptu.sd.library;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BooksResult {

    private List<BookRecord> data = new LinkedList<BookRecord>();

    /**
     * @return the data
     */
    public List<BookRecord> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<BookRecord> data) {
        this.data = data;
    }
    
}
