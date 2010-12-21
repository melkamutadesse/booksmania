package edu.aptu.sd.client.configuration;

import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Configuration {

    public static int HTTPServerPort() {
        ConfigRW rw = new ConfigRW();
        Configuration conf = rw.readConfig();
        return conf.getHttpServerPort();
    }

    private int serverPort = 10666;
    private int httpServerPort = 8080;
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

    /**
     * @return the httpServerPort
     */
    public int getHttpServerPort() {
        return httpServerPort;
    }

    /**
     * @param httpServerPort the httpServerPort to set
     */
    public void setHttpServerPort(int httpServerPort) {
        this.httpServerPort = httpServerPort;
    }
}
