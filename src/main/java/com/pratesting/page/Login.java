package com.pratesting.page;

import java.awt.AWTException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pratesting.base.BaseUI;
public class Login extends BaseUI {
	public static Logger log;
	public ExtentTest logger;
	By nameLo=getLocator("emailLogin_xpath");
	By pass=getLocator("password_xpath");
	By nextLocator = getLocator("nextLogin_xpath");
	By signInLocator = getLocator("signIn_xpath");

	 public void wait(By locator)
	    {
	        
	    }

	public Login(WebDriver driver,ExtentTest logger) {
		BaseUI.driver = driver;
		this.logger = logger;
		log = LogManager.getLogger(com.pratesting.page.Login.class);
	}

	public void provideEmail() throws  AWTException {
		log.debug("Enter User Email,Phone or Skype and click Next");
		delay(20000);
		clickAction(nextLocator, 100);
		logger.log(Status.INFO,"User Email is Entered");
		log.info("User Email is Entered");
	}



	public void providePassword()throws AWTException {
		log.debug("Enter User Password and click Sign-in");
		delay(15000);
		clickAction(signInLocator, 100);
		logger.log(Status.INFO,"User Password is Entered");
		log.info("User Password is Entered");
	}




	public void staySignedIn() {
		log.debug("Click donot stay Signed-in");
		By noButton = null;
		clickAction(noButton,30);
		logger.log(Status.INFO,"Donot stay Signed-in button is clicked");
		log.info("Donot stay Signed-in button is clicked");
	}
	}