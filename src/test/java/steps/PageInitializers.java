package steps;

import pages.*;

public class PageInitializers {
    public static LoginPage login;
    public static EmployeeSearchPage employeeSearchPage;
    public static AddEmployeePage addEmployeePage;
    public static PersonalDetailPage personalDetailPage;
    public static DashboardPage dash;
    public static void initializePageObjects(){
        login=new LoginPage();
        employeeSearchPage =new EmployeeSearchPage();
        addEmployeePage = new AddEmployeePage();
        personalDetailPage = new PersonalDetailPage();
        dash = new DashboardPage();



    }

}
