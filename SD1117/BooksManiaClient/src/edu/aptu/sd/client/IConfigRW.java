package edu.aptu.sd.client;

public interface IConfigRW {

    Configuration readConfig();

    boolean writeConfig(Configuration conf);

}
