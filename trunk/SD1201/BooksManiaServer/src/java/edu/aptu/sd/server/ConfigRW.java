package edu.aptu.sd.server;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConfigRW implements IConfigRW {

    private String fileName = "conf.config";

    public Configuration readConfig() {
        try {
            File xmlFile = new File(fileName);
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller u = context.createUnmarshaller();
            return  (Configuration) u.unmarshal(xmlFile);

        } catch (Exception ex) {//JAXBException
            Logger.getLogger(ConfigRW.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean writeConfig(Configuration conf) {
        try {
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File xmlFile = new File(fileName);
            m.marshal(conf, xmlFile);
            return true;
        } catch (Exception ex) {//JAXBException
            Logger.getLogger(ConfigRW.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
