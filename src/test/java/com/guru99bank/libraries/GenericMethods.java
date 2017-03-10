package com.guru99bank.libraries;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.guru99bank.testcases.SuperTestNG;

public class GenericMethods extends SuperTestNG{
	
		
	/*//public static WebDriver driver;
	//public Logger logger;
	
	
	
	public GenericMethods(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	//	logger = Logger.getLogger(this.getClass().getName());
	}
	*/
	
	public void verifyTitle(WebDriver driver,String eTitle)
	{
		String aTitle=driver.getTitle();
		try{
		
			Assert.assertEquals(aTitle,eTitle);
			System.out.println(aTitle+" Title MATCHED with "+eTitle);
			
		}catch(Throwable t){
		
			System.out.println(aTitle+" title NOT MATCHED with "+eTitle);
			ErrorCollector.addVerificationFailure(t);
			
		}
   }	
	
	public void explicitWait(int durationInSec)
	{
		try {
			Thread.sleep(1000*durationInSec);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	public void verifyText(String aText,String eText)
	{ 
		try{
			
			Assert.assertEquals(aText,eText);
			System.out.println(aText+" is MATCHED with "+eText);
			
		}catch(Throwable t){
		
			System.out.println(aText+" is NOT MATCHED with "+eText);
			ErrorCollector.addVerificationFailure(t);
			
		}
   }
	
	public void checkAlertDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(driver, 3);
			try{
					if(wait.until(ExpectedConditions.alertIsPresent())!=null){
						System.out.println("Alert is present!");
						Alert alert = driver.switchTo().alert();
						System.out.println("Alert message is:"+alert.getText());
					}
			}catch(Throwable t){
					System.out.println("alert is not displayed");
					ErrorCollector.addVerificationFailure(t);
				}
	}
	
	public void acceptAlertPopup()
	{
		try
		{
			driver.switchTo().alert().accept();
			
		}catch(Throwable t){
			System.out.println("alert is not displayed");
			ErrorCollector.addVerificationFailure(t);
		}
	}
	
	public void dismissAlertPopup()
	{
		try{
			
			driver.switchTo().alert().dismiss();
			
			}catch(Throwable t){
				
			System.out.println("alert is not displayed");
			ErrorCollector.addVerificationFailure(t);
		}
	}
	
	
/* **********	Utility methods ****************** */
	
	//Reading data from excel file 
	public static Object[][] getData(String sheetname){
		
		excel = new Xls_Reader(Config.xlPath);
		int rowc = excel.getRowCount(sheetname);
	//	System.out.println("Excell row count is intially :"+rowc);
		int colc = excel.getColumnCount(sheetname);
		
		Object[][] data = new Object[rowc-1][colc];
		
		for(int rowNum = 2 ; rowNum <= rowc  ; rowNum++){ //2
			
	//		System.out.println("RowNum inside loop :"+rowNum);
						
			for(int colNum=0 ; colNum< colc; colNum++){
	//			System.out.println("ColumnNum inside loop:"+colNum);
				data[rowNum-2][colNum]=excel.getCellData(sheetname, colNum, rowNum); //-2
			}
		}
				
		return data;
	}
	
	
	// For Getting the current time stamp
	public static String mailscreenshotpath;
	public static String generateTimeStamp(){
		 
		Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH); //3
		  int year = cal.get(Calendar.YEAR); //2014
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
		
		String timestamp = year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec;
		return timestamp;
	}

	
	// For Taking the screen shots.
	public static void CaptureScreenshot(String SSName) throws IOException{
		
		  Calendar cal = new GregorianCalendar();
		  int month = cal.get(Calendar.MONTH); //3
		  int year = cal.get(Calendar.YEAR); //2014
		  int sec =cal.get(Calendar.SECOND);
		  int min =cal.get(Calendar.MINUTE);
		  int date = cal.get(Calendar.DATE);
		  int day =cal.get(Calendar.HOUR_OF_DAY);
		
		  mailscreenshotpath = System.getProperty("user.dir")+"\\ScreenShots\\"+SSName+"_"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpeg";
			
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
		
		
	}


	
	/*
	public boolean elementExist(WebDriver d, String element)
	{  
		try
		{
		 driver.findElement(By.xpath(xpath));
		 logger.info("Element found with xpath:"+xpath);
		 return true;
		}
		catch(NoSuchElementException ex)
		{
			return false;
		}
	}
	*/
	// all these methods are from keywrod driven testing frame work methods i am trying to modify to support data driven
	
	/*public boolean elementExist(String xpath)
	{  
		try
		{
		 driver.findElement(By.xpath(xpath));
		 logger.info("Element found with xpath:"+xpath);
		 return true;
		}
		catch(NoSuchElementException ex)
		{
			return false;
		}
	}
	
	
	
	public void verifyText(String xpath,String expectedText)
	{ 
		if(elementExist(xpath))
		{
			String actualText=driver.findElement(By.xpath(xpath)).getText();
		 if(actualText.equalsIgnoreCase(expectedText))
		  {
			 logger.info("Text is correct:"+expectedText);
		  }
		  else
		  {
			 scriptStatus="FAIL";
			 logger.error("FAIL:Text is not correct:Expected:"+expectedText+" Actual:"+actualText);
		   }
		}
		else
		{ 
			scriptStatus="FAIL";
			logger.error("FAIL:verifyText();Element Not Found with the xpath:"+xpath );
		}
	}
	
	public void sendKeys(String xpath,String input)
	{
		
		if(elementExist(xpath))
		{
			driver.findElement(By.xpath(xpath)).sendKeys(input);
			logger.info("Entering \""+input+"\"");
		}
		else
		{
			scriptStatus="FAIL";
			logger.error("FAIL:sendKeys();Element Not Found with the xpath:"+xpath);
		}
	}
	
	public void click(String xpath)
	{
		
		if(elementExist(xpath))
		{
			driver.findElement(By.xpath(xpath)).click();
			
		}
		else
		{
			scriptStatus="FAIL";
			logger.error("FAIL:click(): Element Not Found with the xpath:"+xpath);
		}
	}
	
	  public void clearText(String xpath)
	  {
		  if(elementExist(xpath))
			{
				driver.findElement(By.xpath(xpath)).clear();
			}
			else
			{
				scriptStatus="FAIL";
				logger.error("FAIL:clearText();Element Not Found with the xpath:"+xpath);
			}
	  }
	
	public void waitForSeconds(String s)
	{
		try
		{  
		    int seconds = Integer.parseInt(s);
			Thread.sleep(1000 * seconds);
			 logger.info("Wait for "+ seconds + " seconds");
		}
		catch(InterruptedException ex)
		{
			logger.error("invalid input for: waitForSeconds ");
		}
	}
	
	
	public void checkAlertDisplayed()
	{
		WebDriverWait wait = new WebDriverWait(driver, 3);
		
			if(wait.until(ExpectedConditions.alertIsPresent())!=null){
				System.out.println("Alewrt is present!");
				Alert alert = driver.switchTo().alert();
				String logoutSmsg = alert.getText();
				alert.accept();
				try{
				Assert.assertEquals(logoutSmsg, "You Have Succesfully Logged Out!!");
				}catch(Throwable t){
					System.out.println("alert message:"+logoutSmsg);
					ErrorCollector.addVerificationFailure(t);
				}
				
	        }
	}
	
	public void acceptAlertPopup()
	{
		try
		{
			driver.switchTo().alert().accept();
			logger.info("Alert popup accepted: ");
		}
		catch(Exception ex)
		{
			scriptStatus="FAIL";
			logger.error("FAIL:acceptAlertPopup();Accept Alert popup failed:");
		}
	}
	
	public void dismissAlertPopup()
	{
		try
		{
			driver.switchTo().alert().dismiss();
			logger.info("Alert popup dismissed: ");
		}
		catch(Exception ex)
		{
			scriptStatus="FAIL";
			logger.error("FAIL:dismissAlertPopup();dismiss Alert popup failed:");
		}
	}
 
	public void verifyElementPresent(String xpath)
	{
		if(elementExist(xpath))
		{
			
			 logger.info("Element found with xpath:"+xpath);
		}
		else
		{
			scriptStatus="FAIL";
			logger.error("Element not found with xpath:"+xpath);
		}
	}
	
	public void verifyElementNotPresent(String xpath)
	{
		if(elementExist(xpath))
		{
			scriptStatus="FAIL";
			logger.error("Element found with xpath:"+xpath);
		}
		else
		{
			logger.info("Element not found with xpath:"+xpath);
		}
	}
	*/
}
