package com.guru99bank.libraries;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.guru99bank.testcases.SuperTestNG;

public class GenericMethods extends SuperTestNG
{
	
		
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
					//	wait.until(ExpectedConditions.elementToBeSelected(element))
						
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
	
	public void waitForElementVisible(WebDriver driver, WebElement webelement){ 
		try{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOf(webelement));
						
			System.out.println(webelement+": webelement is visible");
		}catch(Throwable t){
			System.out.println(webelement+" : webelement is not visible");
			ErrorCollector.addVerificationFailure(t);			
		}
   }
	
	public void waitForElementClickable(WebDriver driver, WebElement webelement){
		
		try{
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(webelement));
			
		}catch(Throwable t){
			System.out.println(webelement+": Element is not clickable");
			ErrorCollector.addVerificationFailure(t);	
		}
   }
	
	

	
	
/* **********	Utility methods ****************** */
	
	//Reading data from excel file using dataProviders
	public  Object[][] getData(String sheetname){
		
		excel = new Xls_Reader(Config.xlPath);
		int rowc = excel.getRowCount(sheetname);
	//	System.out.println("Excell row count is intially :"+rowc);
		int colc = excel.getColumnCount(sheetname);
		
		Object[][] data = new Object[rowc-1][colc];
		System.out.println("Row Count : "+rowc);
		System.out.println("Column count:"+colc);
		
		for(int rowNum = 2 ; rowNum <= rowc  ; rowNum++){ //2
			
	//		System.out.println("RowNum inside loop :"+rowNum);
						
			for(int colNum=0 ; colNum< colc; colNum++){
	//			System.out.println("ColumnNum inside loop:"+colNum);
				data[rowNum-2][colNum]=excel.getCellData(sheetname, colNum, rowNum); //-2
			}
		}
				
		return data;
	}
	
	
	/* Setting Employee id with random number */
	
	public void setRandomNumber(String sheetname, String colName){
		
		excel = new Xls_Reader(Config.xlPath);
		int rowc = excel.getRowCount(sheetname);
		Random rand = new Random(); 
		for(int rowNum=2;rowNum<=rowc;rowNum++){
		int randomnumber = rand.nextInt(10000000);
		 String randomdata =Integer.toString(randomnumber); 
		excel.setCellData(sheetname, colName, rowNum, randomdata);
		
		}
	
	}
	
	// setting the excell sheet date to previous to previous sunday.
	
public void setDateinExcell(String sheetname, String colName){
		
		excel = new Xls_Reader(Config.xlPath);
		int rowc = excel.getRowCount(sheetname);
		String currentdate = GenericMethods.generateTimeStamp();
		SimpleDateFormat dateformate = new SimpleDateFormat("dd/MM/yyyy");
		Date date2=null;
		
		try{
			date2=dateformate.parse(currentdate);
		}catch(Exception e){
			
			System.out.println(e.getCause());
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);
		cal.add(Calendar.DAY_OF_WEEK,-(cal.get(Calendar.DAY_OF_WEEK)+6));
		SimpleDateFormat dateformate1 = new SimpleDateFormat("dd/MM/yyyy");
		String privoustopreviousSunday = dateformate1.format(cal.getTime());
		System.out.println(privoustopreviousSunday);
		
		for(int rowNum=2;rowNum<=rowc;rowNum++){
		excel.setCellData(sheetname, colName, rowNum, privoustopreviousSunday);
		
		}
	
	}

public void setCurrentDateInExcell(String sheetname, String colName){
	
	excel = new Xls_Reader(Config.xlPath);
	int rowc = excel.getRowCount(sheetname);
	
	
	String CurrentDate = GenericMethods.generateTimeStamp();
	
	for(int rowNum=2;rowNum<=rowc;rowNum++){
	excel.setCellData(sheetname, colName, rowNum, CurrentDate);
	
	}

}
	
public void copyCellValueToSheet(String fromsheetname, String colName, String toSheetName){
	
		excel = new Xls_Reader(Config.xlPath);
		int rowc = excel.getRowCount(fromsheetname);
		for(int rowNum=2;rowNum<=rowc;rowNum++){
		String cellValue =excel.getCellData(fromsheetname, colName, rowNum);
		excel.setCellData(toSheetName, colName, rowNum, cellValue);
	}
		
}


public void dynamicXpathForEmpId(String empId) throws Exception {
    try {
        
    	driver.findElement(By.xpath("//tr[td[text()='"+empId+"']]//th/a")).click();
        
    } catch (AssertionError Ae) {
        Ae.printStackTrace();
    }
}

public void dynamicXpathForDeptName(String DeptName) throws Exception {
    try {
    
        driver.findElement(By.xpath("//tr[td[2]/span/a[text()='"+DeptName+"']]//input[@type='checkbox']")).click();
     
       } catch (AssertionError Ae) {
        Ae.printStackTrace();
    }
}

//Dynamic xpath for percentage field 

public void dynamicXpathForPersentage(String DeptName,String percentage) throws Exception {
  try {
 
      driver.findElement(By.xpath("//tr[td[2]/span/a[text()='"+DeptName+"']]//input[@type='text']")).sendKeys(percentage);;
  	
     } catch (AssertionError Ae) {
      Ae.printStackTrace();
  }
}

public void switchToWindow(WebDriver driver){
	String parentWindow = driver.getWindowHandle();
	Set<String> handles =  driver.getWindowHandles();
	   for(String windowHandle  : handles)
	       {
	       if(!windowHandle.equals(parentWindow))
	          {
	          driver.switchTo().window(windowHandle);
	        // <!--Perform your operation here for new window-->
	      //   driver.close(); //closing child window
	      //   driver.switchTo().window(parentWindow); //cntrl to parent window
	          }
	       }
	}

public void waitForPageLoaded(WebDriver driver) {
    ExpectedCondition<Boolean> expectation = new
            ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                }
            };
    try {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(expectation);
    } catch (Throwable error) {
        Assert.fail("Timeout waiting for Page Load Request to complete.");
    }
}


public void waitForLoad(WebDriver driver) {
    ExpectedCondition<Boolean> pageLoadCondition = new
            ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                }
            };
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(pageLoadCondition);
}

public void checkPageIsReady() {
	  
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  
	  
	  //Initially bellow given if condition will check ready state of page.
	  if (js.executeScript("return document.readyState").toString().equals("complete")){ 
	   System.out.println("Page Is loaded.");
	   return; 
	  } 
	  
	  //This loop will rotate for 25 times to check If page Is ready after every 1 second.
	  //You can replace your value with 25 If you wants to Increase or decrease wait time.
	  for (int i=0; i<30; i++){ 
	   try {
	    Thread.sleep(1000);
	    }catch (InterruptedException e) {} 
	   //To check page ready state.
	   if (js.executeScript("return document.readyState").toString().equals("complete")){ 
	    break; 
	   }   
	  }
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
		
		//String timestamp = year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec;
		  String timestamp = date+"/"+(month+1)+"/"+year;
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
	
	
	//for downloading file into local system.
	
	public static Robot robot;
	public static void downloadFile() throws AWTException{
		
		robot = new Robot();
		
		if(Config.BROSER_NAME.equalsIgnoreCase("firefox")){
			
	//		robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_TAB);
						
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}else if(Config.BROSER_NAME.equalsIgnoreCase("chrome")){
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		}
    }
		
	// for uploading a file
	public static void uploadFile(String filepath){
		 StringSelection file=new StringSelection(filepath);
	     Toolkit.getDefaultToolkit().getSystemClipboard().setContents(file, null);
	     
	     robot.keyPress(KeyEvent.VK_CONTROL);
	     robot.keyPress(KeyEvent.VK_V);
	
	     robot.keyRelease(KeyEvent.VK_CONTROL);
	     robot.keyRelease(KeyEvent.VK_V);
	    // Thread.sleep(3000);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	}
 
	// for accepting window popup
	public static void windowPopUpAccepting(){
		 robot.keyPress(KeyEvent.VK_ENTER);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	 }
	 
	// for cancealing window popup
	 public static void widowPopUpCanceling(){
		 robot.keyPress(KeyEvent.VK_CANCEL);
	     robot.keyRelease(KeyEvent.VK_CANCEL);
	 }
	 
	 // for mouse movements on windows applications
	 public static void mouseMoveOnWindowPopUp(){
		 	robot.mouseMove(630, 420); // move mouse point to specific location	630, 420 are x and y coordinates
	        robot.delay(1500);        // delay is to make code wait for mentioned milliseconds before executing next step	
	        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click	
	        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click	
	        robot.delay(1500);	
	        robot.keyPress(KeyEvent.VK_DOWN); // press keyboard arrow key to select Save radio button	
	       robot.keyPress(KeyEvent.VK_ENTER);	
	        // press enter key of keyboard to perform above selected action	
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
