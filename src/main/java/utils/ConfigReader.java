package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            FileInputStream input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void setProperty(String Key, String Value) {
        properties.setProperty(Key, Value); // Store in properties for later retrieval.  Better approach would be to return it from method
        try {
            properties.store(new java.io.FileOutputStream("src/test/resources/config.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}