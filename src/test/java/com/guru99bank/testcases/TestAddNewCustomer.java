package com.guru99bank.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru99bank.libraries.GenericMethods;
import com.guru99bank.libraries.Config;

import com.guru99bank.pages.AddCustomerPage;
import com.guru99bank.pages.BasePage;

public class TestAddNewCustomer extends SuperTestNG{
	
	

	@DataProvider
	public Object[][] getData(){
		
		return GenericMethods.getData("AddNewCustomer");
	}
	
	@Test(dataProvider = "getData")
	public void testAddNewCustomer(String custname, String gender, String dob, String addrs, String city, String state, String pin, String phone, String email, String pwd) throws IOException{
	//	GenericMethods generic=new GenericMethods();
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Home Page");
		
		loginPage.login(Config.USER_NAME, Config.PASSWD);
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Manager HomePage");
		AddCustomerPage addcust=new AddCustomerPage(driver);
		addcust.AddNewCutomer(custname, gender, dob, addrs, city, state, pin, phone, email, pwd);
		generic.explicitWait(3);
		
		BasePage basePage = new BasePage(driver);
		basePage.logout();
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");
		
	}

}
