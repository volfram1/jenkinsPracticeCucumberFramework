package steps;

import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.ArrayList;
import java.util.List;

public class DashboardSteps extends CommonMethods {
    @Then("user verifies all the dashboard tabs")
    public void user_verifies_all_the_dashboard_tabs(io.cucumber.datatable.DataTable dataTable) {
       List<String> expectedTabs = dataTable.asList();
       List<String> actualTabs = new ArrayList<>();

        for (WebElement ele:dash.dashboardTabs
             ) {
            actualTabs.add(ele.getText());

        }
        System.out.println(actualTabs);
        System.out.println(expectedTabs);

        //Assert.assertEquals(actualTabs,expectedTabs); or
        Assert.assertTrue(expectedTabs.equals(actualTabs));  // if this assertion will pass it will not give
                                                                // any information and will execute our code
                                                                // if it fails it will give the error with comparison


    }
}
