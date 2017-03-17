package com.guru99bank.testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.guru99bank.libraries.Config;
import com.guru99bank.libraries.GenericMethods;
import com.guru99bank.libraries.Xls_Reader;
import com.guru99bank.pages.LoginPage;

public class SauceLabsSuperTestNG {
	
	public static WebDriver driver ;
	public static Xls_Reader excel;
	public static DesiredCapabilities cap = null;
	
	public LoginPage loginPage;
	public GenericMethods generic;
	public static String SauceUsername = "subhashkiranchilka";
	public static String SauceAccessKey = "ad23f2f2-6ac1-4853-aaab-a55d2f9fa463";
	
	@BeforeMethod
	public void preCondtion() throws MalformedURLException {
	//	System.out.println(b);
		// RemoteWebdriver
		
				
		/*if(b.equalsIgnoreCase("firefox")){
			cap = DesiredCapabilities.firefox();
			cap.setBrowserName("firefox"); // chrome,iexplore
			cap.setPlatform(Platform.ANY);
		}else if (b.equalsIgnoreCase("chrome")){
			cap = DesiredCapabilities.chrome(); // no need path of chrome exe
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.ANY);
		}else if (b.equalsIgnoreCase("iexplore")){
			cap = DesiredCapabilities.internetExplorer(); // no need path of chrome exe
			cap.setBrowserName("iexplore");
			cap.setPlatform(Platform.WINDOWS);
		}*/
		
		cap = DesiredCapabilities.firefox();
		cap.setBrowserName("firefox"); // chrome,iexplore
		cap.setCapability("version", 48);
		cap.setCapability("platform", Platform.WINDOWS);
		
		
		
		// give URL of Hub
		RemoteWebDriver driver = new RemoteWebDriver(new URL("https://" + SauceUsername + ":" + SauceAccessKey + "@ondemand.saucelabs.com:443/wd/hub"),cap);
		driver.get(Config.BASE_URL+"V4/");
		generic=new GenericMethods();
		loginPage = new LoginPage(driver);
		
		 
	}
	
	
	
	
	
	@AfterMethod
	public void postCondtion()
	{
		//driver.close();
	    driver.quit();
	}
	
	


}
