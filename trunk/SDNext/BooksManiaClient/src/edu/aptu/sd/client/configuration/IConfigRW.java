package edu.aptu.sd.client.configuration;

public interface IConfigRW {

    Configuration readConfig();

    boolean writeConfig(Configuration conf);

}
