package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;

import java.util.concurrent.TimeUnit;

public class EmployeeSearchSteps extends CommonMethods {

    @Given("user is navigated to HRMS application")
    public void user_is_navigated_to_hrms_application() {
        openBrowserAndLaunchApplication();
    }


    @When("user enters valid admin credentials")
    public void user_enters_valid_admin_credentials() {
        //LoginPage login=new LoginPage();
        //WebElement usernameField = driver.findElement(By.id("txtUsername"));
        //usernameField.sendKeys(ConfigReader.getPropertyValue("username"));
        sendText(login.usernameBox, ConfigReader.getPropertyValue("username"));


        //WebElement passwordField = driver.findElement(By.name("txtPassword"));
        sendText(login.passwordBox, ConfigReader.getPropertyValue("password"));
        //passwordField.sendKeys(ConfigReader.getPropertyValue("password"));

    }
    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        //LoginPage login=new LoginPage();
        //WebElement loginButton =driver.findElement(By.id("btnLogin"));
        click(login.loginBtn);
        //loginButton.click();
        //driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }
    @When("user navigateed to employee list page")
    public void user_navigateed_to_employee_list_page() {
       // WebElement pimOption =driver.findElement(By.id("menu_pim_viewPimModule"));
        //pimOption.click();
        click(employeeSearchPage.pimOption);
        //driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);

        //WebElement empListOptions=driver.findElement(By.id("menu_pim_viewEmployeeList"));
        //empListOptions.click();
        click(employeeSearchPage.empListOption);

    }
    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        //WebElement searchId=driver.findElement(By.id("empsearch_id"));
        //searchId.sendKeys("8510142");
        sendText(employeeSearchPage.idField,"8510142");
    }
    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        //WebElement searchButton=driver.findElement(By.id("searchBtn"));
        //searchButton.click();
        click(employeeSearchPage.searchButton);
    }
    @Then("user is able to see eployee information")
    public void user_is_able_to_see_eployee_information() {
        System.out.println("result displayed");
        //tearDown();
    }


    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        //WebElement searchName = driver.findElement(By.xpath("(//*[@type='text'])[1]"));
        //searchName.sendKeys("Zubair");
        sendText(employeeSearchPage.nameField,"Zubair");
    }
}
