
package edu.aptu.sd.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RegisterBook", namespace = "http://sd.aptu.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegisterBook", namespace = "http://sd.aptu.edu/", propOrder = {
    "author",
    "title"
})
public class RegisterBook {

    @XmlElement(name = "author", namespace = "")
    private String author;
    @XmlElement(name = "title", namespace = "")
    private String title;

    /**
     * 
     * @return
     *     returns String
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * 
     * @param author
     *     the value for the author property
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 
     * @param title
     *     the value for the title property
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
