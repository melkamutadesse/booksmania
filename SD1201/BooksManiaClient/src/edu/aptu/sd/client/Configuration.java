package edu.aptu.sd.client;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration {

    private int serverPort = 10666;
    private Map<Integer, String> books = new HashMap<Integer, String>();

    /**
     * @return the serverPort
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * @param serverPort the serverPort to set
     */
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    /**
     * @return the books
     */
    public Map<Integer, String> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Map<Integer, String> books) {
        this.books = books;
    }
}
