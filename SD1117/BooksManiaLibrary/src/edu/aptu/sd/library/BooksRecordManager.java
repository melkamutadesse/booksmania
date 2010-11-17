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

public class BooksRecordManager {

    public static BooksResult deserialize(String xml) {
        try {
            JAXBContext context = JAXBContext.newInstance(BooksResult.class);
            Unmarshaller u = context.createUnmarshaller();
            return  (BooksResult) u.unmarshal(new ByteArrayInputStream(xml.getBytes()));
        } catch (JAXBException ex) {
            Logger.getLogger(BooksRecordManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String serialize(BooksResult br) {
        try {
            OutputStream os = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(BooksResult.class);
            Marshaller m = context.createMarshaller();
            m.marshal(br, os);
            return os.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(BooksRecordManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
