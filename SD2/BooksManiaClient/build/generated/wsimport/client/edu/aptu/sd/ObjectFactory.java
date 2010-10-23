
package edu.aptu.sd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.aptu.sd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ShowBooks_QNAME = new QName("http://sd.aptu.edu/", "showBooks");
    private final static QName _ShowBooksResponse_QNAME = new QName("http://sd.aptu.edu/", "showBooksResponse");
    private final static QName _RegisterBook_QNAME = new QName("http://sd.aptu.edu/", "RegisterBook");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.aptu.sd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterBook }
     * 
     */
    public RegisterBook createRegisterBook() {
        return new RegisterBook();
    }

    /**
     * Create an instance of {@link ShowBooks }
     * 
     */
    public ShowBooks createShowBooks() {
        return new ShowBooks();
    }

    /**
     * Create an instance of {@link ShowBooksResponse }
     * 
     */
    public ShowBooksResponse createShowBooksResponse() {
        return new ShowBooksResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sd.aptu.edu/", name = "showBooks")
    public JAXBElement<ShowBooks> createShowBooks(ShowBooks value) {
        return new JAXBElement<ShowBooks>(_ShowBooks_QNAME, ShowBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sd.aptu.edu/", name = "showBooksResponse")
    public JAXBElement<ShowBooksResponse> createShowBooksResponse(ShowBooksResponse value) {
        return new JAXBElement<ShowBooksResponse>(_ShowBooksResponse_QNAME, ShowBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sd.aptu.edu/", name = "RegisterBook")
    public JAXBElement<RegisterBook> createRegisterBook(RegisterBook value) {
        return new JAXBElement<RegisterBook>(_RegisterBook_QNAME, RegisterBook.class, null, value);
    }

}
