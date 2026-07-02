package utils;

import model.GeneratedTestCase;
import org.testng.annotations.DataProvider;

import java.util.List;

public class AIDataProvider {

    @DataProvider(name = "loginTestCases")
    public static Object[][] loginTestCases() {

        List<GeneratedTestCase> tests =
                JsonReader.read("login-testcases.json");

        Object[][] data =
                new Object[tests.size()][1];

        for (int i = 0; i < tests.size(); i++) {

            data[i][0] = tests.get(i);

        }

        return data;

    }

    @DataProvider(name = "dashboardTestCases")
    public static Object[][] dashboardTestCases() {

        List<GeneratedTestCase> tests =
                JsonReader.read("dashboard-testcases.json");

        Object[][] data =
                new Object[tests.size()][1];

        for (int i = 0; i < tests.size(); i++) {

            data[i][0] = tests.get(i);

        }

        return data;
    }

}