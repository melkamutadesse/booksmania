package edu.aptu.sd.client.configuration;

import edu.aptu.sd.client.ui.Main;
import edu.aptu.sd.library.logger.Logger;
import java.io.File;
import java.util.logging.Level;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConfigRW implements IConfigRW {

    public static int HTTPServerPort() {
        ConfigRW rw = new ConfigRW();
        Configuration conf = rw.readConfig();
        return conf.getHttpServerPort();
    }

    private String fileName = Main.curPath + "conf.config";

    public Configuration readConfig() {
        try {
            File xmlFile = new File(fileName);
            JAXBContext context = JAXBContext.newInstance(Configuration.class);
            Unmarshaller u = context.createUnmarshaller();
            return  (Configuration) u.unmarshal(xmlFile);
        } catch (Exception ex) {//JAXBException
            Logger.log(ConfigRW.class.getName(), ex, Level.SEVERE, Main.curPath);
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
            Logger.log(ConfigRW.class.getName(), ex, Level.SEVERE, Main.curPath);
        }
        return false;
    }
}
