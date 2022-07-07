package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.Constants;
import utils.DBUtils;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    String empId;
    String firstName;
    String dbFirstName;
    String dbEmpId;


    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        click(employeeSearchPage.pimOption);
    }
    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
       click(employeeSearchPage.addEmployeeOption);
    }
    @When("user enters firstname, middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        sendText(addEmployeePage.firtsNameField, "dasir");
       sendText(addEmployeePage.middleNameField, "hamilia");
       sendText(addEmployeePage.lastNameField, "tolom");
    }
    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
       click(addEmployeePage.saveButton);
    }
    @Then("employee added successfully")
    public void employee_added_successfully() {
        System.out.println("Employee added");
    }

    @When("user enters {string} {string} and {string}")
    public void user_enters_and(String firstNameValue, String middleNameValue, String lastNameValue) {
        sendText(addEmployeePage.firtsNameField, firstNameValue);
        sendText(addEmployeePage.middleNameField, middleNameValue);
        sendText(addEmployeePage.lastNameField, lastNameValue);
    }

    @When("user provides {string} {string} and {string}")
    public void user_provides_and(String firstName, String middleName, String lastName) {
        sendText(addEmployeePage.lastNameField, firstName);
        sendText(addEmployeePage.middleNameField, middleName);
        sendText(addEmployeePage.lastNameField, lastName);


    }
    @When("user provides multiple employee data and verify they are added")
    public void user_provides_multiple_employee_data_and_verify_they_are_added(DataTable dataTable) throws InterruptedException {
       List<Map<String,String>> employeeNames = dataTable.asMaps();
        for (Map<String, String> employee:employeeNames
             ) {
            //System.out.println(employee);
            String firstNamevalue= employee.get("firstName");
            String middleNamevalue= employee.get("middleName");
            String lastNamevalue= employee.get("lastName");

            System.out.println(firstNamevalue+" "+middleNamevalue+" "+lastNamevalue);

            sendText(addEmployeePage.firtsNameField, firstNamevalue);
            sendText(addEmployeePage.middleNameField, middleNamevalue);
            sendText(addEmployeePage.lastNameField, lastNamevalue);

            click(addEmployeePage.saveButton);
            String employeeFullName = employee.get("firstName")+" "+employee.get("middleName")+" "+employee.get("lastName");
            if(TextVerification(personalDetailPage.EmployeeNameVerText).equals(employeeFullName)){
                System.out.println("Employee added successfully");
            }else{
                System.out.println("employee not added");
            }
            click(employeeSearchPage.addEmployeeOption);
        }
    }

    @When("user adds multiple employees from excel file using employee {string} sheet and verify the user added")
    public void user_adds_multiple_employees_from_excel_file_using_employee_sheet_and_verify_the_user_added(String sheetName) throws InterruptedException {
        List<Map<String,String>> newEmployees=ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH,sheetName);
        Iterator<Map<String,String>> itr=newEmployees.iterator();

        while (itr.hasNext()){
            Map<String,String> mapNewEmp=itr.next();
            System.out.println(mapNewEmp.get("FirstName"));
            System.out.println(mapNewEmp.get("MiddleName"));
            System.out.println(mapNewEmp.get("LastName"));

            sendText(addEmployeePage.firtsNameField,mapNewEmp.get("FirstName") );
            sendText(addEmployeePage.middleNameField,mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastNameField, mapNewEmp.get("LastName"));

            String empIdValue=addEmployeePage.empIdLocator.getAttribute("value");
            sendText(addEmployeePage.photograph, mapNewEmp.get("Photograph"));

            if(!addEmployeePage.checkBox.isSelected()){
                click(addEmployeePage.checkBox);
            }

            sendText(addEmployeePage.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployeePage.confirmPassword, mapNewEmp.get("Password"));
            click(addEmployeePage.saveButton);
            Thread.sleep(3000);
            click(employeeSearchPage.empListOption);
            Thread.sleep(3000);


            sendText(employeeSearchPage.idField,empIdValue);
            click(employeeSearchPage.searchButton);


            List<WebElement> rowData =driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                String rowText=rowData.get(i).getText();
                System.out.println(rowText);

                String expectedData=empIdValue+" "+mapNewEmp.get("FirstName")+" " +mapNewEmp.get("MiddleName")+" "+
                mapNewEmp.get("LastName");
                Assert.assertEquals(expectedData,rowText);
            }
            click(employeeSearchPage.addEmployeeOption);
            Thread.sleep(3000);

        }
    }


    @And("user grabs the employee Id")
    public void userGrabsTheEmployeeId() {
        empId=addEmployeePage.empIdLocator.getAttribute("value");
        firstName=addEmployeePage.firtsNameField.getAttribute("value");



    }

    @And("user query the database for the same employee Id")
    public void userQueryTheDatabaseForTheSameEmployeeId() {
        String query="select * from hs_hr_employees where employee_id='"+empId+"'";
        List<Map<String, String>> tableData=DBUtils.getDataFromDB(query);
        dbFirstName=tableData.get(0).get("emp_firstname");
        dbEmpId=tableData.get(0).get("employee_id");


    }

    @Then("user verifies the results")
    public void userVerifiesTheResults() {
        System.out.println("First name from front end "+firstName);
        System.out.println("from back end "+dbFirstName);
        Assert.assertEquals(firstName,dbFirstName);
        Assert.assertEquals(empId,dbEmpId);
    }
}
