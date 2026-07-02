package tests;

import ai.TestGenerationAgent;
import org.testng.annotations.Test;

public class AITestGeneration {

    @Test
    public void generateLoginTests() {

        TestGenerationAgent agent =
                new TestGenerationAgent();

        String response =
                agent.generateLoginTestCases();

        System.out.println(response);

    }

}