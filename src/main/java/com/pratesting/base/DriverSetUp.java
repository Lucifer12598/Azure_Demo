package com.pratesting.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSetUp {
	
	private static WebDriver driver;
	static String driverPath=System.getProperty("user.dir");

	public static WebDriver getChromeD(){
		System.setProperty("webdriver.chrome.driver", driverPath+"\\src\\test\\resources\\Drivers\\chromedriver.exe");
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--disable-notifications");
		co.addArguments("--disable-infobars");
		co.addArguments("--disable-notifications");
		co.addArguments("--disable-gpu");
		co.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		driver=new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	public static WebDriver getFireFoxD()
	{
		System.setProperty("Webdriver.gecko.driver", driverPath+"\\src\\test\\resources\\Drivers\\geckodriver.exe");
		FirefoxOptions fo=new FirefoxOptions();
		fo.addArguments("--disable-notifications");
		fo.addArguments("--disable-infobars");
		fo.addArguments("--disable-notifications");
		fo.addArguments("--disable-gpu");
		fo.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
		driver=new FirefoxDriver(fo);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	public static WebDriver getEdgeD()
	{
		System.setProperty("Webdriver.edge.driver", driverPath+"\\src\\test\\resources\\Drivers\\msedgedriver.exe");
		driver=new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
}
