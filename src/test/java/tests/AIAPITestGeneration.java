package tests;

import ai.TestGenerationAgent;
import org.testng.annotations.Test;

public class AIAPITestGeneration {

    @Test
    public void generateApiTests() {

        TestGenerationAgent agent =
                new TestGenerationAgent();

        System.out.println(
                agent.generateApiTestCases()
        );

    }

}