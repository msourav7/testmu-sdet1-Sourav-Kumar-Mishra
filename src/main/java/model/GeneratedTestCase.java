package model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class GeneratedTestCase {

    private String id;

    @JsonAlias({"title", "name"})
    private String title;

    private String priority;

    private List<String> steps;

    @JsonAlias({"expectedResult", "expected_result"})
    private String expectedResult;

    private String category;

    private String preconditions;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPriority() {
        return priority;
    }

    public List<String> getSteps() {
        return steps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getCategory() {
        return category;
    }

    public String getPreconditions() {
        return preconditions;
    }
}