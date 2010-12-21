package edu.aptu.sd.library;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BooksRecordTransformer {

    public static BooksList deserialize(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(BooksList.class);
            Unmarshaller u = context.createUnmarshaller();
            return  (BooksList) u.unmarshal(new ByteArrayInputStream(xml.getBytes()));
        } catch (JAXBException ex) {
            Logger.getLogger(BooksRecordTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serialize(BooksList br) {
        try {
            OutputStream os = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(BooksList.class);
            Marshaller m = context.createMarshaller();
            m.marshal(br, os);
            return os.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(BooksRecordTransformer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
