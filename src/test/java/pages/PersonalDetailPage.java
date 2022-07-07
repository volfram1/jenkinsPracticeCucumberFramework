package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class PersonalDetailPage extends CommonMethods {

    @FindBy(id = "profile-pic")
    public WebElement EmployeeNameVerText;

    public PersonalDetailPage (){
        PageFactory.initElements(driver,this);
    }

}
