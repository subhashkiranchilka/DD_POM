package com.guru99bank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guru99bank.libraries.Config;
import com.guru99bank.libraries.Xls_Reader;

public class EditCustomerPage extends BasePage{
	
	@FindBy(linkText="Edit Customer")
	WebElement editCustmrLink;
	
	@FindBy(name="cusid")
	WebElement custmrId;
	
	@FindBy(name="AccSubmit")
	WebElement custmrIdSubmitBtn;
	
	@FindBy(name="res")
	WebElement custmrIdResetBtn;
		
	
	@FindBy(name="name")
	WebElement editCustmrName;
	
	@FindBy(name="gender")
	WebElement editCustmrGender;
	
	@FindBy(name="dob")
	WebElement editCustmrDob;
	
	@FindBy(name="addr")
	WebElement editCustmrAddress;
	
	@FindBy(name="city")
	WebElement editCustmrCity;
	
	@FindBy(name="state")
	WebElement editCustmrState;
	
	@FindBy(name="pinno")
	WebElement editCustmrPinNo;
	
	@FindBy(name="telephoneno")
	WebElement editCustmrPhone;
	
	@FindBy(name="emailid")
	WebElement editCustmrEmailId;
	
	public EditCustomerPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void editCustomer(String addrs, String city, String state, String pin, String phone, String email){
		Xls_Reader excel = new Xls_Reader(Config.xlPath);
		editCustmrLink.click();
		custmrId.sendKeys(excel.getCellData("RegisteredCustomer", "Customer ID", 2));
		custmrIdSubmitBtn.click();
		
		if((editCustmrName.isEnabled() && editCustmrGender.isEnabled() && editCustmrDob.isEnabled()) ==false){
			System.out.println("Name, Gender and Date of birth are Read only fields");
		}
		
		
		
	}
	
	
	
	
	

}
