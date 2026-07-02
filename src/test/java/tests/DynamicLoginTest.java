package tests;

import base.BaseTest;
import model.GeneratedTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.AIDataProvider;
import utils.ExecutionCollector;

public class DynamicLoginTest extends BaseTest {

    @Test(dataProvider = "loginTestCases",
            dataProviderClass = AIDataProvider.class)
    public void executeGeneratedTests(GeneratedTestCase tc) {

        System.out.println("==========");
        System.out.println(tc.getId());
        System.out.println(tc.getTitle());

        // Log test execution start
        ExecutionCollector.log("Running : " + tc.getId());

        LoginPage login = new LoginPage(getDriver());

        String title = tc.getTitle().toLowerCase();

        try {

            // ----------------------------
            // VALID LOGIN
            // ----------------------------
            if (title.contains("successful")) {

                login.login(
                        "test1new@gmail.com",
                        "Test@123456"
                );

                Assert.assertTrue(
                        login.isLoggedIn(),
                        "Login should succeed"
                );

                System.out.println("PASS");

                ExecutionCollector.log(
                        tc.getId() + " PASSED"
                );
            }

            // ----------------------------
            // INVALID LOGIN
            // ----------------------------
            else if (title.contains("invalid")) {

                login.login(
                        "wrong@gmail.com",
                        "Wrong@123"
                );

                String error = login.getErrorMessage();

                System.out.println("ERROR : " + error);

                Assert.assertTrue(
                        error.toLowerCase().contains("invalid"),
                        "Expected invalid login message."
                );

                System.out.println("PASS");

                ExecutionCollector.log(
                        tc.getId() + " PASSED"
                );
            }

            // ----------------------------
            // EMPTY LOGIN
            // ----------------------------
            else if (title.contains("empty")) {

                login.login("", "");

                String error = login.getErrorMessage();

                System.out.println("ERROR : " + error);

                Assert.assertTrue(
                        error.toLowerCase().contains("invalid")
                                || error.toLowerCase().contains("required"),
                        "Validation message missing."
                );

                System.out.println("PASS");

                ExecutionCollector.log(
                        tc.getId() + " PASSED"
                );
            }

        } catch (Exception | AssertionError e) {

            // Log failure
            ExecutionCollector.log(
                    tc.getId() + " FAILED"
            );

            // Re-throw so TestNG still marks the test as failed
            throw e;
        }
    }
}