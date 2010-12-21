package edu.aptu.sd.server.configuration;

public interface IConfigRW {

    Configuration readConfig();

    boolean writeConfig(Configuration conf);

}
