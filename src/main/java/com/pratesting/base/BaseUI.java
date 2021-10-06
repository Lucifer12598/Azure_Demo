package com.pratesting.base;



import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pratesting.Utils.DateUtils;



public class BaseUI {
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
	static Properties prop=new Properties();
	public static String browser_choice;
	static String driverPath=System.getProperty("user.dir");
	public static String timestamp = DateUtils.getTimeStamp();
	public static Logger log;
	String parent;
	private static final Logger logBase = LogManager
			.getLogger(com.pratesting.base.BaseUI.class);
	/*************properties file************/
	public void ReadPropertiesFile()
	{
		InputStream readFile=null;
		
		try{
			readFile=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\objectRepository\\projectConfig.properties");
			prop.load(readFile);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/************** Invoke Browser ****************/
	public static WebDriver invokeBrowser1(){
		logBase.debug("Opening browser");
		browser_choice = prop.getProperty("browserName");
		try{
		if(browser_choice.equalsIgnoreCase("chrome"))
		{
			driver=DriverSetUp.getChromeD();
		}
		else if(browser_choice.equalsIgnoreCase("edge"))
		{
			driver=DriverSetUp.getEdgeD();
		}
		else if(browser_choice.equalsIgnoreCase("firefox"))
		{
			driver=DriverSetUp.getFireFoxD();
		}
		else{
			throw new Exception("Invalid browser name provided in property file");
		}
		logBase.info("Opened browser");
	} 
		catch (Exception e) {
		logBase.error("Failed to open browser: "+e.getMessage());
	}

		return driver;
	}
	/********************Open URL***********/
	public void openURL(String websiteURL)
	{
		try {
			logBase.debug("Opening URL");
		driver.get(prop.getProperty(websiteURL));
		 logger.log(Status.INFO,"Opened URL");
		logBase.info("Opened URL");
		} catch (Exception e) {
			e.printStackTrace();
			logBase.error("Failed to open URL");
		}
	}
	/*******************click*******************/
	public void clickOn(By locator, int timeout)
	{
		try{
		new WebDriverWait(driver, timeout).until(ExpectedConditions
				.elementToBeClickable(locator));
		driver.findElement(locator).click();
	} catch (Exception e) {
		e.printStackTrace();
		log.error("Failed to click on element");
	}
}
	/************** Check if an element is present ****************/
	public static boolean isElementPresent(By locator, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/************** Click on element with Actions ****************/
	public static void clickAction(By locator, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions
					.elementToBeClickable(locator));
			Actions action = new Actions(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);",
					driver.findElement(locator));
			jse.executeScript("window.scrollBy(0,300)");
			action.moveToElement(driver.findElement(locator)).build().perform();
			action.click(driver.findElement(locator)).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Failed to click on element");
		}
	}
	
	/**************** Get By locator using locator key ****************/
	public static By getLocator(String locatorKey) {
		if (locatorKey.endsWith("_id")) {
			return By.id(prop.getProperty(locatorKey));
		}
		if (locatorKey.endsWith("_name")) {
			return (By.name(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_className")) {
			return (By.className(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_xpath")) {
			return (By.xpath(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_css")) {
			return (By.cssSelector(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_linkText")) {
			return (By.linkText(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_partialLinkText")) {
			return (By.partialLinkText(prop.getProperty(locatorKey)));
		}
		if (locatorKey.endsWith("_tagName")) {
			return (By.tagName(prop.getProperty(locatorKey)));
		}
		return null;
	}
	/************** Send text to an element ****************/
	public static void sendText(By locator, String text) {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(locator));
			driver.findElement(locator).sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/************drop down***************/
	public void dropDown(By locator, String text)
	{
		Select age=new Select(driver.findElement(locator));
		age.selectByVisibleText(text);
	}
	/*******************close**********/
	public void close()
	{
		driver.close();
	}
	/*******************clear**********/
	public void clearText(By locator) {
        driver.findElement(locator).clear();
    }
   
	/************** Take screenshot  ****************/
	public static void takeScreenShot(String filepath) throws HeadlessException, AWTException {
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File srcFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filepath);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*******************Switch Window**********/
    public void switchWindow() {
        parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
      
        Iterator<String> I1 = s.iterator();
        while (I1.hasNext()) {
            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
                System.out.println(driver.switchTo().window(child_window).getTitle());
            }
        }
    }
   
    /*******************Switch to Main Window**********/
    public void switchToMainWindow() {
        System.out.println(parent);
        driver.switchTo().window(parent);
    }
   
    /**************Get WebPage Title**************/
    public String getWebPageTitle() {
        String title= driver.getTitle();
        return title;
    }
   
    /**************Get Data from Property File**************/
    public String getData(String text) {
        String data= prop.getProperty(text);
        return data;
    }
    /**************Wait *************/
    public void delay(int time) throws AWTException {
    Robot robot = new Robot();   
    robot.delay(time);
    }                  
        
	
}
