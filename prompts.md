# AI Prompt Log

This document contains the raw prompts used for AI-powered test generation and failure analysis within the framework.

---

# Prompt 1 – Login Test Case Generation

Generate Selenium Java TestNG test cases for the login functionality of a web application.

Requirements:
- Return ONLY valid JSON.
- Include positive and negative test cases.
- Include boundary value tests.
- Include empty username and password scenarios.
- Include invalid credentials.
- Include SQL Injection test.
- Include Cross Site Scripting (XSS) test.
- Each test case must contain:
    - id
    - title
    - priority
    - category
    - preconditions
    - steps
    - expectedResult

---

# Prompt 2 – Dashboard Test Case Generation

Generate Selenium Java TestNG test cases for a dashboard page.

Requirements:
- Return ONLY valid JSON.
- Cover dashboard navigation.
- Verify dashboard widgets.
- Verify charts and statistics.
- Verify logout functionality.
- Include positive and negative scenarios.
- Each test case must contain:
    - id
    - title
    - priority
    - category
    - preconditions
    - steps
    - expectedResult

---

# Prompt 3 – Login API Test Case Generation

Generate REST Assured API test cases for a Login API.

Requirements:
- Return ONLY valid JSON.
- Include successful login.
- Invalid credentials.
- Missing username.
- Missing password.
- Invalid request body.
- Unauthorized access.
- Each test case must contain:
    - id
    - title
    - priority
    - request
    - expectedStatus
    - expectedResponse

---

# Prompt 4 – AI Failure Analysis

Analyze the failed Selenium test execution.

Provide:
- Root Cause
- Possible Reason
- Suggested Fix
- Best Practice
- Confidence Level

Return the response in a readable format.

---

# Prompt 5 – AI Execution Summary

Analyze the complete execution results.

Generate:
- Total Tests Executed
- Passed Tests
- Failed Tests
- Failure Trends
- Recommendations
- Overall Execution Summary