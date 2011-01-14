package edu.aptu.sd.client.registration;

import edu.aptu.sd.client.configuration.ClientIDRW;
import edu.aptu.sd.client.configuration.ClientIDSyncWithDB;
import edu.aptu.sd.client.configuration.IClientIDRW;
import edu.aptu.sd.client.configuration.IClientIDSync;
import edu.aptu.sd.client.ui.Main;

public class ClientIDFormer implements IClientIDFormer {

    private IClientIDRW clientIDRW = new ClientIDRW(Main.curPath + "BooksMania.id");
    private IClientIDSync clientIDSync = new ClientIDSyncWithDB();

    public int formClientID(String address) throws Exception {
        int clientID = 0;
        try {
            clientID = clientIDRW.readClientIDFromFile(address);
            clientIDSync.writeClientIDtoDB(clientID, address);
        } catch(Exception ex) {
            clientID = clientIDSync.generateNewClientID(address);
            clientIDRW.writeClientIDtoFile(clientID);
        }
        return clientID;
    }
}
