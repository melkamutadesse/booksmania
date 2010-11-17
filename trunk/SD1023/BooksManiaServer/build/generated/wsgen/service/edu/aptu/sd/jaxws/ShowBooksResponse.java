
package edu.aptu.sd.jaxws;

import java.util.LinkedList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "showBooksResponse", namespace = "http://sd.aptu.edu/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "showBooksResponse", namespace = "http://sd.aptu.edu/")
public class ShowBooksResponse {

    @XmlElement(name = "return", namespace = "")
    private LinkedList<String> _return;

    /**
     * 
     * @return
     *     returns LinkedList<String>
     */
    public LinkedList<String> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(LinkedList<String> _return) {
        this._return = _return;
    }

}
