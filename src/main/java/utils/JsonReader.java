package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GeneratedTestCase;

import java.io.File;
import java.util.List;

public class JsonReader {

    public static List<GeneratedTestCase> read(String fileName) {

        try {

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(
                    new File("src/main/resources/generated/" + fileName),
                    new TypeReference<List<GeneratedTestCase>>() {}
            );

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}