package edu.aptu.sd.library;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BooksRecordTransformer {

    public static BooksList deserialize(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(BooksList.class);
        Unmarshaller u = context.createUnmarshaller();
        return  (BooksList) u.unmarshal(new ByteArrayInputStream(xml.getBytes()));
    }

    public static String serialize(BooksList br) throws JAXBException {
        OutputStream os = new ByteArrayOutputStream();
        JAXBContext context = JAXBContext.newInstance(BooksList.class);
        Marshaller m = context.createMarshaller();
        m.marshal(br, os);
        return os.toString();
    }

}
