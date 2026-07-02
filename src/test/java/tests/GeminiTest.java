package tests;

import ai.GeminiClient;
import org.testng.annotations.Test;

public class GeminiTest {

    @Test
    public void testGeminiConnection() {

        String response = GeminiClient.generate(
                "Say Hello from Gemini."
        );

        System.out.println(response);

    }

}