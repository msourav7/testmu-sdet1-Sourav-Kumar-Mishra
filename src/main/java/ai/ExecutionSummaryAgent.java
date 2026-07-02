package ai;

import utils.ExecutionCollector;

public class ExecutionSummaryAgent implements AIAgent {

    @Override
    public String execute(String input) {

        String prompt = """
You are an AI QA Lead.

Below is the execution log.

Generate:

1. Overall Summary

2. Passed Tests

3. Failed Tests

4. Risks

5. Recommendation

Execution Log:

""" + input;

        return GeminiClient.generate(prompt);

    }

    public String generateSummary() {

        String log =
                ExecutionCollector.getLogs();

        return execute(log);

    }

}