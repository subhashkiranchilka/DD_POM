package com.guru99bank.pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guru99bank.libraries.Config;
import com.guru99bank.libraries.GenericMethods;

import com.guru99bank.libraries.Xls_Reader;

public class AddCustomerPage extends BasePage{
	
	public WebDriver driver;
		
	@FindBy(linkText="New Customer")
	WebElement newCustmrLink;
	
	@FindBy(name="name")
	WebElement custmrName;
	
	@FindBy(xpath="//input[@value='m']")
	WebElement maleGender;
	
	@FindBy(xpath="//input[@value='f']")
	WebElement femaleGender;
	
	@FindBy(id="dob")
	WebElement custmrDob;
	
	@FindBy(name="addr")
	WebElement custmrAddress;
	
	@FindBy(name="city")
	WebElement custmrCity;
	
	@FindBy(name="state")
	WebElement custmrState;
	
	@FindBy(name="pinno")
	WebElement custmrPinNo;
	
	@FindBy(name="telephoneno")
	WebElement custmrPhone;
	
	@FindBy(name="emailid")
	WebElement custmrEmailId;
	
	@FindBy(name="password")
	WebElement custmrPwd;
	
	@FindBy(name="sub")
	WebElement custmrSubmitBtn;
	
	//td[p[contains(text(), 'Customer Registered Successfully!!!')]]
	
	@FindBy(xpath="//td[p[contains(text(), 'Customer Registered Successfully!!!')]]")
	WebElement RegstdCustSucssMsg;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Customer ID')]]/td[2]")
	WebElement RegstdCustmrId;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Customer Name')]]/td[2]")
	WebElement RegstdCustmrName;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Gender')]]/td[2]")
	WebElement RegstdCustmrGender;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Birthdate')]]/td[2]")
	WebElement RegstdCustmrDob;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Address')]]/td[2]")
	WebElement RegstdCustmrAddr;
	
	@FindBy(xpath="//tr[td[contains(text(), 'City')]]/td[2]")
	WebElement RegstdCustmrCity;
	
	@FindBy(xpath="//tr[td[contains(text(), 'State')]]/td[2]")
	WebElement RegstdCustmrState;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Pin')]]/td[2]")
	WebElement RegstdCustmrPin;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Mobile No.')]]/td[2]")
	WebElement RegstdCustmrPhone;
	
	@FindBy(xpath="//tr[td[contains(text(), 'Email')]]/td[2]")
	WebElement RegstdCustmrEmail;
	
	
	public AddCustomerPage(WebDriver driver){
		
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	public void AddNewCutomer(String custname, String gender, String dob, String addrs, String city, String state, String pin, String phone, String email, String pwd) throws IOException{
		
		newCustmrLink.click();
		custmrName.sendKeys(custname);
		if(gender.equalsIgnoreCase("male")){
			if(maleGender.isSelected()){
				femaleGender.click();
				maleGender.click();
			}else{
				maleGender.click();
			}
		}else{
			femaleGender.click();
		}
		custmrDob.sendKeys(dob);
		custmrAddress.sendKeys(addrs);
		custmrCity.sendKeys(city);
		custmrState.sendKeys(state);
		custmrPinNo.sendKeys(pin);
		custmrPhone.sendKeys(phone);
		custmrEmailId.sendKeys(email);
		custmrPwd.sendKeys(pwd);
		custmrSubmitBtn.click();
		
		if(RegstdCustSucssMsg.isDisplayed()){
		
		Xls_Reader excel = new Xls_Reader(Config.xlPath);
		excel.setCellData("RegisteredCustomer", "Customer ID", 2, RegstdCustmrId.getText());
		excel.setCellData("RegisteredCustomer", "Customer Name", 2, RegstdCustmrName.getText());
		excel.setCellData("RegisteredCustomer", "Gender", 2, RegstdCustmrGender.getText());
		excel.setCellData("RegisteredCustomer", "Name", 2, RegstdCustmrName.getText());
		excel.setCellData("RegisteredCustomer", "BirthDate", 2, RegstdCustmrDob.getText());
		excel.setCellData("RegisteredCustomer", "Address", 2, RegstdCustmrAddr.getText());
		excel.setCellData("RegisteredCustomer", "City", 2, RegstdCustmrCity.getText());
		excel.setCellData("RegisteredCustomer", "State", 2, RegstdCustmrState.getText());
		excel.setCellData("RegisteredCustomer", "Pin", 2, RegstdCustmrPin.getText());
		excel.setCellData("RegisteredCustomer", "Mobile Number", 2, RegstdCustmrPhone.getText());
		excel.setCellData("RegisteredCustomer", "E-mail", 2, RegstdCustmrEmail.getText());
		
		GenericMethods.CaptureScreenshot("RegisteredCustomer");
		
		System.out.println("Successfully written in excell sheet");
		
		}
		
			
	}
	
	
}
