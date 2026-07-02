package ai;

import utils.JsonUtil;

public class TestGenerationAgent implements AIAgent {

    @Override
    public String execute(String prompt) {
        return GeminiClient.generate(prompt);
    }

    public String generateLoginTestCases() {

        String response = execute(
                PromptLoader.load("loginPrompt.txt")
        );

        response = cleanJson(response);

        JsonUtil.saveJson(
                response,
                "login-testcases.json"
        );

        return response;
    }

    public String generateDashboardTestCases() {

        String response = execute(
                PromptLoader.load("dashboardPrompt.txt")
        );

        response = cleanJson(response);

        JsonUtil.saveJson(
                response,
                "dashboard-testcases.json"
        );

        return response;
    }

    public String generateApiTestCases() {

        String response = execute(
                PromptLoader.load("apiPrompt.txt")
        );

        response = cleanJson(response);

        JsonUtil.saveJson(
                response,
                "api-testcases.json"
        );

        return response;
    }

    /**
     * Removes markdown code blocks from Gemini response
     */
    private String cleanJson(String response) {

        if (response == null || response.isBlank()) {
            return "";
        }

        response = response.trim();

        // Remove Markdown code block markers
        response = response.replace("```json", "");
        response = response.replace("```JSON", "");
        response = response.replace("```", "");

        return response.trim();
    }
}