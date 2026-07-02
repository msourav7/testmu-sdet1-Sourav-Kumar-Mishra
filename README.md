# 🤖 AI-Powered Selenium Automation Framework

An AI-enhanced Selenium Automation Framework built using **Java**, **Selenium WebDriver**, **TestNG**, **Maven**, and **Google Gemini**.

This project demonstrates how **Large Language Models (LLMs)** can be integrated into traditional Selenium automation frameworks to make testing smarter, more dynamic, and more maintainable.

---

# 🚀 Key Highlights

- AI-generated Selenium test cases using Google Gemini
- Dynamic execution of AI-generated JSON test cases
- AI-powered failure analysis for failed tests
- AI-generated execution summary after test execution
- Self-Healing Locator mechanism using fallback locators
- Page Object Model (POM) architecture
- Data-driven testing using TestNG DataProviders
- Extent Reports with screenshots
- Screenshot capture on test failure

---

# 🛠 Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Google Gemini API
- Jackson
- Extent Reports

---

# 🔑 API Key Setup

This repository **does NOT include** a Google Gemini API key.

After cloning the project:

### 1. Copy

```text
config.properties.example
```

to

```text
config.properties
```

### 2. Open `config.properties`

Replace

```properties
gemini.api.key=YOUR_GEMINI_API_KEY_HERE
```

with your own Gemini API key.

Example:

```properties
gemini.api.key=AIzaSyXXXXXXXXXXXXXXXXXXXXXXXX
```

> **Note**
>
> `config.properties` is intentionally excluded from Git using `.gitignore` to keep API keys secure.
>
> Every user who clones this repository must create their own `config.properties` file and provide their own Gemini API key.

Without a valid API key, the following AI features will not work:

- AI Test Generation
- AI Failure Analysis
- AI Execution Summary

---

# 🤖 AI Features

## 1. AI Test Case Generator

Google Gemini generates test cases dynamically in JSON format.

```
Prompt
      ↓
Google Gemini
      ↓
JSON Test Cases
      ↓
Framework
      ↓
Dynamic Test Execution
```

Generated files:

```
generated/login-testcases.json

generated/dashboard-testcases.json

generated/api-testcases.json
```

---

## 2. Dynamic Test Execution

Instead of writing hardcoded Selenium test methods, the framework:

- Reads AI-generated JSON
- Converts JSON into Java objects
- Executes each generated test automatically
- Produces Extent Reports

---

## 3. AI Failure Analysis

Whenever a Selenium test fails:

- Screenshot is captured
- Exception details are collected
- Failure context is sent to Google Gemini
- Gemini analyzes the failure
- An AI explanation is generated
- Report is saved automatically

Reports are stored in:

```
reports/ai-analysis/
```

---

## 4. Self-Healing Locators

If the primary locator fails, the framework automatically tries multiple fallback locators before failing the test.

Example flow:

```
Primary Locator
        ↓
Failed
        ↓
Fallback Locator 1
        ↓
Fallback Locator 2
        ↓
Fallback Locator 3
        ↓
Element Found
```

This improves test stability when UI elements change.

---

## 5. AI Execution Summary

After execution completes, Google Gemini analyzes the execution logs and generates:

- Overall Execution Summary
- Passed Tests
- Failed Tests
- Risks
- Recommendations

This provides a human-readable AI summary instead of just raw logs.

---

# 🧠 AI Agent Architecture

The framework uses multiple specialized AI agents.

```
                    AIAgent
                       │
        ┌──────────────┼──────────────┐
        │              │              │
        ▼              ▼              ▼
TestGeneration   FailureAnalysis   ExecutionSummary
     Agent             Agent             Agent
```

Each agent implements a common interface:

```java
public interface AIAgent {

    String execute(String input);

}
```

This makes the framework modular and easy to extend.

---

# 📂 Project Structure

```
src
│
├── main
│   ├── ai
│   ├── model
│   ├── pages
│   ├── utils
│   └── resources
│
└── test
    ├── tests
    ├── listeners
    └── utils

generated/

reports/

test-output/

prompts/
```

---

# 🏗 Design Patterns Used

- Page Object Model (POM)
- Singleton Pattern (ExtentManager)
- Strategy Pattern (AI Agents)
- Data-Driven Testing
- Listener Pattern (TestNG Listeners)

---

# ▶ Running the Project

## Step 1

Clone the repository.

```bash
git clone <repository-url>
```

---

## Step 2

Create your configuration file.

Copy

```
config.properties.example
```

to

```
config.properties
```

---

## Step 3

Add your Gemini API key.

```properties
gemini.api.key=YOUR_GEMINI_API_KEY
```

---

## Step 4

Install project dependencies.

```bash
mvn clean install
```

---

## Step 5

Generate AI Test Cases

Run:

```
GenerateLoginTests

GenerateDashboardTests

GenerateApiTests
```

These classes generate JSON test cases inside the `generated` folder.

---

## Step 6

Execute Dynamic Tests

Run:

```
DynamicLoginTest

DynamicDashboardTest
```

The framework automatically executes the AI-generated JSON test cases.

---

# 📊 Reports

### Extent Report

```
test-output/ExtentReport.html
```

---

### AI Failure Reports

```
reports/ai-analysis/
```

---

### AI Generated JSON

```
generated/
```

---

# 🔮 Future Improvements

- AI-generated Selenium Java code
- AI-based bug severity prediction
- AI-powered test prioritization
- Embedding-based intelligent locator healing
- Parallel AI execution
- AI-generated API assertions
- AI-generated regression suites

---

# 👨‍💻 Author

**Sourav Kumar Mishra**

AI-Powered Selenium Automation Framework

Built as part of an AI SDET Assignment demonstrating the integration of **Google Gemini** with **Selenium WebDriver** to create intelligent, AI-assisted test automation.

---

# 📄 License

This project is intended for educational, learning, and demonstration purposes.