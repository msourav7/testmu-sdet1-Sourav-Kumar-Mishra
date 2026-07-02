//package config;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    private static final Properties properties = new Properties();
//
//    static {
//
//        try (InputStream input =
//                     ConfigReader.class
//                             .getClassLoader()
//                             .getResourceAsStream("config.properties")) {
//
//            if (input == null) {
//                throw new RuntimeException("config.properties not found.");
//            }
//
//            properties.load(input);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    public static String get(String key) {
//        return properties.getProperty(key);
//    }
//
//}

package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {

        try (InputStream input =
                     ConfigReader.class
                             .getClassLoader()
                             .getResourceAsStream("config.properties")) {

            if (input != null) {
                properties.load(input);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String get(String key) {

        // -----------------------------
        // First check GitHub Environment Variables
        // -----------------------------
        if ("gemini.api.key".equals(key)) {

            String envKey = System.getenv("GEMINI_API_KEY");

            if (envKey != null && !envKey.isBlank()) {
                return envKey;
            }
        }

        if ("gemini.model".equals(key)) {

            String envModel = System.getenv("GEMINI_MODEL");

            if (envModel != null && !envModel.isBlank()) {
                return envModel;
            }
        }

        // -----------------------------
        // Otherwise use config.properties
        // -----------------------------
        return properties.getProperty(key);
    }

}