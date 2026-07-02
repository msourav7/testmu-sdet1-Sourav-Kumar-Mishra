package ai;

public class AIExecutionSummaryAgent implements AIAgent {

    @Override
    public String execute(String prompt) {

        return GeminiClient.generate(prompt);

    }

    public String summarize(String executionReport) {

        String prompt = """
You are a Senior QA Architect.

Analyze the following Selenium execution report.

Generate a professional report with the following sections:

1. Overall Test Health
2. Passed Tests
3. Failed Tests
4. Failure Reasons
5. Risk Assessment
6. Recommendations
7. Automation Framework Quality Score (out of 10)

Execution Report:

""" + executionReport;

        return execute(prompt);

    }

}