package com.guru99bank.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.guru99bank.libraries.GenericMethods;
import com.guru99bank.libraries.Config;
import com.guru99bank.libraries.Xls_Reader;
import com.guru99bank.pages.BasePage;
import com.guru99bank.pages.LoginPage;

public class SuperTestNG{
	
	public static WebDriver driver ;
	public static Xls_Reader excel;
	
	public LoginPage loginPage;
	public GenericMethods generic;
	
//	@BeforeSuite
	@BeforeMethod
	public void preCondtion(){
		
				if(Config.BROSER_NAME.equalsIgnoreCase("firefox")){
		
				System.setProperty("webdriver.gecko.driver", Config.GECKODRIVER_PATH);
			//	generic=new GenericMethods();
				driver=new FirefoxDriver();
		
			}else if(Config.BROSER_NAME.equalsIgnoreCase("IE")){
			
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			
			}else if(Config.BROSER_NAME.equalsIgnoreCase("Chrome")){
			
				System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
				driver = new ChromeDriver();
			}
	
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(Config.BASE_URL+"V4/");
		generic=new GenericMethods();
		loginPage = new LoginPage(driver);
		
		
		
		
		
	}	
	
/*	@BeforeMethod
	public void BeforeMethodCondition(){
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(Config.BASE_URL+"V4/");
		
		loginPage = new LoginPage(driver);
		generic=new GenericMethods();
		loginPage.login(Config.USER_NAME, Config.PASSWD);
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Manager HomePage");
	
	}*/
	
	
	
	/*@AfterMethod
	public void afterTestMethodCondition(){
		
		BasePage basePage = new BasePage(driver);
		basePage.logout();
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");
	}*/
	

//	@AfterSuite
	@AfterMethod
	public void postCondtion()
	{
		//driver.close();
	    driver.quit();
	}
}
