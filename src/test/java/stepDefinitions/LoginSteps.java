package stepDefinitions;

 

import java.awt.AWTException;

 

import com.pratesting.base.BaseUI;
import com.pratesting.page.Login;

 

public class LoginSteps extends BaseUI {
    
    
    @io.cucumber.java.en.Given("^User Enters Application Login Page$")
    public void User_Enters_Application_Login_Page() {
        ReadPropertiesFile();
        driver = invokeBrowser1();
        openURL("URL");
    }
    
 

 

@io.cucumber.java.en.Then("^User enters Email ")
    public void EnterEmail() throws AWTException {

 

      Login logIn = new Login(driver,logger);
        logIn.provideEmail();
        
    }
    
    @io.cucumber.java.en.And("^User enters Passsword and clicks Login Button$")
    public void EnterPassword() throws AWTException {

 

        Login logIn = new Login(driver,logger);
        logIn.providePassword();
        
    }

    
    @io.cucumber.java.en.Then("^User clicks Donot stay Signed-In$")
    public void Signin() {

 

        Login logIn = new Login(driver,logger);
        logIn.staySignedIn();
        
    }
}