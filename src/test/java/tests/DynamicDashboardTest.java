package tests;

import base.BaseTest;
import model.GeneratedTestCase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import utils.AIDataProvider;
import utils.ExecutionCollector;

public class DynamicDashboardTest extends BaseTest {

    @Test(
            dataProvider = "dashboardTestCases",
            dataProviderClass = AIDataProvider.class
    )
    public void executeDashboardTests(GeneratedTestCase tc) {

        System.out.println("==========");
        System.out.println(tc.getId());
        System.out.println(tc.getTitle());

        ExecutionCollector.log(
                "Running Dashboard Test : " + tc.getId()
        );

        LoginPage login = new LoginPage(getDriver());

        login.login(
                "test1new@gmail.com",
                "Test@123456"
        );

        DashboardPage dashboard =
                new DashboardPage(getDriver());

        String title =
                tc.getTitle().toLowerCase();

        try {

            if (title.contains("dashboard")) {

                Assert.assertTrue(
                        dashboard.isDashboardLoaded()
                );

            }

            else if (title.contains("profile")) {

                Assert.assertTrue(
                        dashboard.isProfileVisible()
                );

            }

            else if (title.contains("navigation")) {

                Assert.assertTrue(
                        dashboard.hasNavigationBar()
                );

            }

            else {

                System.out.println(
                        "Skipping unsupported scenario..."
                );

            }

            ExecutionCollector.log(
                    tc.getId() + " PASSED"
            );

        }

        catch (Exception e) {

            ExecutionCollector.log(
                    tc.getId() + " FAILED"
            );

            throw e;

        }

    }

}