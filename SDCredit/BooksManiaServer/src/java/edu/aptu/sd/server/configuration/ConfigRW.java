package edu.aptu.sd.server.configuration;

import edu.aptu.sd.library.logger.Logger;
import edu.aptu.sd.server.db.DBConnection;
import java.io.File;
import java.util.logging.Level;
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
            Logger.log(ConfigRW.class.getName(), ex, Level.SEVERE, DBConnection.curPath);
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
            Logger.log(ConfigRW.class.getName(), ex, Level.SEVERE, DBConnection.curPath);
        }
        return false;
    }
}
