
package edu.aptu.sd.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.aptu.sd.server package. 
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

    private final static QName _RegisterBook_QNAME = new QName("http://server.sd.aptu.edu/", "registerBook");
    private final static QName _SetUserStatus_QNAME = new QName("http://server.sd.aptu.edu/", "setUserStatus");
    private final static QName _ShowBooks_QNAME = new QName("http://server.sd.aptu.edu/", "showBooks");
    private final static QName _ShowBooksResponse_QNAME = new QName("http://server.sd.aptu.edu/", "showBooksResponse");
    private final static QName _RegisterUserResponse_QNAME = new QName("http://server.sd.aptu.edu/", "registerUserResponse");
    private final static QName _SetUserStatusResponse_QNAME = new QName("http://server.sd.aptu.edu/", "setUserStatusResponse");
    private final static QName _RegisterBookResponse_QNAME = new QName("http://server.sd.aptu.edu/", "registerBookResponse");
    private final static QName _RegisterUser_QNAME = new QName("http://server.sd.aptu.edu/", "registerUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.aptu.sd.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetUserStatusResponse }
     * 
     */
    public SetUserStatusResponse createSetUserStatusResponse() {
        return new SetUserStatusResponse();
    }

    /**
     * Create an instance of {@link ShowBooks }
     * 
     */
    public ShowBooks createShowBooks() {
        return new ShowBooks();
    }

    /**
     * Create an instance of {@link RegisterBook }
     * 
     */
    public RegisterBook createRegisterBook() {
        return new RegisterBook();
    }

    /**
     * Create an instance of {@link SetUserStatus }
     * 
     */
    public SetUserStatus createSetUserStatus() {
        return new SetUserStatus();
    }

    /**
     * Create an instance of {@link ShowBooksResponse }
     * 
     */
    public ShowBooksResponse createShowBooksResponse() {
        return new ShowBooksResponse();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link RegisterUser }
     * 
     */
    public RegisterUser createRegisterUser() {
        return new RegisterUser();
    }

    /**
     * Create an instance of {@link RegisterBookResponse }
     * 
     */
    public RegisterBookResponse createRegisterBookResponse() {
        return new RegisterBookResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterBook }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "registerBook")
    public JAXBElement<RegisterBook> createRegisterBook(RegisterBook value) {
        return new JAXBElement<RegisterBook>(_RegisterBook_QNAME, RegisterBook.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUserStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "setUserStatus")
    public JAXBElement<SetUserStatus> createSetUserStatus(SetUserStatus value) {
        return new JAXBElement<SetUserStatus>(_SetUserStatus_QNAME, SetUserStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowBooks }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "showBooks")
    public JAXBElement<ShowBooks> createShowBooks(ShowBooks value) {
        return new JAXBElement<ShowBooks>(_ShowBooks_QNAME, ShowBooks.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShowBooksResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "showBooksResponse")
    public JAXBElement<ShowBooksResponse> createShowBooksResponse(ShowBooksResponse value) {
        return new JAXBElement<ShowBooksResponse>(_ShowBooksResponse_QNAME, ShowBooksResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "registerUserResponse")
    public JAXBElement<RegisterUserResponse> createRegisterUserResponse(RegisterUserResponse value) {
        return new JAXBElement<RegisterUserResponse>(_RegisterUserResponse_QNAME, RegisterUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUserStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "setUserStatusResponse")
    public JAXBElement<SetUserStatusResponse> createSetUserStatusResponse(SetUserStatusResponse value) {
        return new JAXBElement<SetUserStatusResponse>(_SetUserStatusResponse_QNAME, SetUserStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterBookResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "registerBookResponse")
    public JAXBElement<RegisterBookResponse> createRegisterBookResponse(RegisterBookResponse value) {
        return new JAXBElement<RegisterBookResponse>(_RegisterBookResponse_QNAME, RegisterBookResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.sd.aptu.edu/", name = "registerUser")
    public JAXBElement<RegisterUser> createRegisterUser(RegisterUser value) {
        return new JAXBElement<RegisterUser>(_RegisterUser_QNAME, RegisterUser.class, null, value);
    }

}
