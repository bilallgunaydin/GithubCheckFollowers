package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;


    public static Properties initialize_Properties() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("./Configs/Configuration.properties");
            properties.load(fileInputStream);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return getProperties();
    }

    public static Properties getProperties() {
        return properties;
    }
}
