/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.aptu.sd.library.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

/**
 *
 * @author nikita
 */
public class Logger {

    public static void log(String className, String logText, Level level, String filePath) {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(className);
        try {
            logger.addHandler(new FileHandler(filePath + "BooksMania.log", true));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.log(level, logText);
    }

    public static void log(String className, Exception exception, Level level, String filePath) {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(className);
        try {
            logger.addHandler(new FileHandler(filePath + "BooksMania.log", true));
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.log(level, null, exception);
    }

}
