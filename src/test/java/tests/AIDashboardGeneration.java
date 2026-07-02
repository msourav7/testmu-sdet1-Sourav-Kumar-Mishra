package tests;

import ai.TestGenerationAgent;
import org.testng.annotations.Test;

public class AIDashboardGeneration {

    @Test
    public void generateDashboardTests() {

        TestGenerationAgent agent =
                new TestGenerationAgent();

        System.out.println(
                agent.generateDashboardTestCases()
        );

    }

}