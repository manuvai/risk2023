package res.utils;

import res.utils.exceptions.ConfigFileNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class Config {

    private static Properties properties;

    public static String get(String key) {
        return get(key, "");
    }

    public static void main(String[] args) {
        System.out.println(Config.get("aaa"));
        System.out.println(Config.get("db.username"));
    }

    public static String get(String key, String defaultValue) {
        Properties properties;
        try {
            properties = getProperties();
        } catch (IOException e) {
            throw new ConfigFileNotFoundException(e);
        }

        return properties.getProperty(key, defaultValue);
    }

    private static Properties getProperties() throws IOException {
        if (Objects.isNull(properties)) {
            Properties appProps = new Properties();
            InputStream fileInputStream;
            fileInputStream = new FileInputStream("app.properties");
            appProps.load(fileInputStream);

            properties = appProps;

        }

        return properties;
    }
}
