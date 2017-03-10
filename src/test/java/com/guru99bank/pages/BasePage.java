package com.guru99bank.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.guru99bank.libraries.ErrorCollector;
import com.guru99bank.libraries.GenericMethods;

public class BasePage
{
	public WebDriver driver;
	public GenericMethods generic;
	
	@FindBy(linkText="Log out")
	private WebElement logoutLink;
	
	@FindBy(className="successmsg")
	private WebElement successMsg;
	
	public BasePage(WebDriver driver){
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void logout(){
		
		logoutLink.click();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		try{
				if(wait.until(ExpectedConditions.alertIsPresent())!=null){
					System.out.println("Alert is present!");
					Alert alert = driver.switchTo().alert();
					System.out.println("Alert message is:"+alert.getText());
					alert.accept();
				}
		}catch(Throwable t){
				System.out.println("alert is not displayed");
				ErrorCollector.addVerificationFailure(t);
				
			}
		/*generic.checkAlertDisplayed();
		generic.acceptAlertPopup();*/
		
		}
}
