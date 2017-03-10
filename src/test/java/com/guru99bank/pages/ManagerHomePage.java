package com.guru99bank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerHomePage extends BasePage{
	
	public WebDriver driver;
	
	@FindBy(linkText="Manager")
	private WebElement Managerlink;
	
	@FindBy(linkText="New Customer")
	private WebElement NewCustomerlink;
	
	@FindBy(linkText="Edit Customer")
	private WebElement EditCustomerlink;
	
	@FindBy(linkText="Delete Customer")
	private WebElement DeleteCustomerlink;
	
	@FindBy(linkText="New Account")
	private WebElement NewAccountlink;
	
	@FindBy(linkText="Edit Account")
	private WebElement EditAccountlink;
	
	@FindBy(linkText="Delete Account")
	private WebElement DeleteAccountlink;
	
	@FindBy(linkText="Deposit")
	private WebElement Depositlink;
	
	@FindBy(linkText="Withdrawal")
	private WebElement Withdrawallink;
	
	@FindBy(linkText="Fund Transfer")
	private WebElement FundTransferlink;
	
	@FindBy(linkText="Change Password")
	private WebElement ChangePasswordlink;
	
	@FindBy(linkText="Balance Enquiry")
	private WebElement BalanceEnquirylink;
	
	@FindBy(linkText="Mini Statement")
	private WebElement MiniStatementlink;
	
	@FindBy(linkText="Customised Statement")
	private WebElement CustomisedStatementlink;
	
	@FindBy(linkText="Home")
	private WebElement Homelink;
	
	
	public ManagerHomePage(WebDriver driver){
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickManagerLink(){
		Managerlink.click();
	}
	
	public void clickNewCustomerLink(){
		NewCustomerlink.click();
	}
	
	public void clickEditCustomerLink(){
		EditCustomerlink.click();
	}
	
	public void clickDeleteCustomerLink(){
		DeleteCustomerlink.click();
	}
	
	public void clickNewAccountLink(){
		NewAccountlink.click();
	}
	
	public void clickEditAccountLink(){
		EditAccountlink.click();
	}
	
	public void clickDeleteAccountLink(){
		DeleteAccountlink.click();
	}
	
	public void clickDepositLink(){
		Depositlink.click();
	}
	
	public void clickWithdrawalLink(){
		Withdrawallink.click();
	}
	
	public void clickFundTransferLink(){
		FundTransferlink.click();
	}
	
	public void clickChangePasswordLink(){
		ChangePasswordlink.click();
	}
	
	public void clickBalanceEnquiryLink(){
		BalanceEnquirylink.click();
	}
	
	
	public void clickMiniStatementLink(){
		MiniStatementlink.click();
	}
	
	public void clickCustomisedStatementLink(){
		CustomisedStatementlink.click();
	}
	
	public void clickHomeLink(){
		Homelink.click();
	}
	
}
