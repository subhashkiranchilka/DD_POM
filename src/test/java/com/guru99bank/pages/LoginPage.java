package com.guru99bank.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.guru99bank.libraries.ErrorCollector;
import com.guru99bank.libraries.GenericMethods;


public class LoginPage 
{
	
	public WebDriver driver;
	
	@FindBy(name ="uid")
	private WebElement username;
	String username1 = "uid";
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(name="btnLogin")
	private WebElement loginbutton;
	
	@FindBy(id="message23")
	private WebElement uiderrMsg;
	
	@FindBy(xpath="//td[contains(text(), 'mngr67188')]")
	//managerid = mngr67188
	WebElement mngrid;
	
	@FindBy(xpath="//marquee[contains(text(), 'Welcome')]")
	private WebElement welcommsg;
	
	@FindBy(id="message23")
	private WebElement usrIdBlankErrMsg;
	
	@FindBy(id="message18")
	private WebElement pwdBlankErrMsg;
	
	GenericMethods generic = new GenericMethods();
	
	public LoginPage(WebDriver driver){
	//	super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login(String un, String pwd){
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbutton.click();
		
		/*mngrid.isDisplayed();
		welcommsg.isDisplayed();
		*/
		try{
			mngrid.isDisplayed();
		}catch(NoSuchElementException nsee){
			System.out.println(nsee.toString());
			ErrorCollector.addVerificationFailure(nsee);
		}
		
		try{
		welcommsg.isDisplayed();
		}catch(NoSuchElementException nsee){
			System.out.println("Wecome message exception:"+nsee.toString());
			ErrorCollector.addVerificationFailure(nsee);
		}
	}
	
	public void loginInvalidData(String un, String pwd){
		
		username.sendKeys(un);
		password.sendKeys(pwd);
		loginbutton.click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		
		if(wait.until(ExpectedConditions.alertIsPresent())!=null){
			System.out.println("Alert is present!");
			Alert alert = driver.switchTo().alert();
			String blankDataErrMsg = alert.getText();
			alert.accept();
			Assert.assertEquals(blankDataErrMsg, "User or Password is not valid");
			System.out.println("Error message:"+blankDataErrMsg);
        }
	}	
	
	public void usrIdCurserBlank(){
		username.clear();
		username.click();
		loginbutton.click();
		generic.verifyText(usrIdBlankErrMsg.getText(), "User-ID must not be blank");
	
	}
	
	public void pwdCurserBlank(){
	
		password.clear();
		password.click();
		loginbutton.click();
		generic.verifyText(pwdBlankErrMsg.getText(), "Password must not be blank");
			
	}
	
	
	
	

}
