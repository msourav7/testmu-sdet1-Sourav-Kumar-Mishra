package ai;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class PromptLoader {

    public static String load(String fileName) {

        try (InputStream input = PromptLoader.class
                .getClassLoader()
                .getResourceAsStream("prompts/" + fileName)) {

            if (input == null) {
                throw new RuntimeException(fileName + " not found.");
            }

            return new String(input.readAllBytes(), StandardCharsets.UTF_8);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}