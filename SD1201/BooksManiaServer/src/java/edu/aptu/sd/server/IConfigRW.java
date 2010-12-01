package edu.aptu.sd.server;

public interface IConfigRW {

    Configuration readConfig();

    boolean writeConfig(Configuration conf);

}
