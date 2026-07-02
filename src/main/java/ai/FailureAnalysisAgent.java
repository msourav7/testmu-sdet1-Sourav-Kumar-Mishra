package ai;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FailureAnalysisAgent implements AIAgent {

    @Override
    public String execute(String failure) {

        String prompt = """
                You are a Senior QA Automation Architect.
                
                Analyze the following Selenium Test Failure.
                
                Generate a detailed report using the following sections:
                
                1. Root Cause
                2. Possible Reasons
                3. Severity (Low / Medium / High)
                4. Confidence Score (0-100%)
                5. Recommended Fix
                6. Code Improvement Suggestion
                7. Preventive Best Practice
                
                Failure:

                """ + failure;

        return GeminiClient.generate(prompt);
    }

    public String analyze(String testName, Throwable throwable) {

        String analysis =
                execute(throwable.toString());

        return """
========================================

        AI FAILURE ANALYSIS REPORT

========================================

Test Name
---------
%s

Execution Time
--------------
%s

Exception
---------
%s

Message
-------
%s

========================================

AI ANALYSIS

========================================

%s

========================================
""".formatted(
                testName,
                LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                throwable.getClass().getSimpleName(),
                throwable.getMessage(),
                analysis
        );
    }
}