package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JsonUtil {

    public static void saveJson(String json, String fileName) {

        try {

            File folder = new File("src/main/resources/generated");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            File file = new File(folder, fileName);

            FileWriter writer = new FileWriter(file);

            writer.write(json);

            writer.flush();
            writer.close();

            System.out.println("==================================");
            System.out.println("JSON Saved Successfully");
            System.out.println(file.getAbsolutePath());
            System.out.println("==================================");

        } catch (IOException e) {
            throw new RuntimeException("Unable to save JSON file", e);
        }

    }

}