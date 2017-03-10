package com.guru99bank.testcases;

import org.testng.annotations.Test;

import com.guru99bank.libraries.Config;
import com.guru99bank.libraries.GenericMethods;
import com.guru99bank.pages.BasePage;
import com.guru99bank.pages.LoginPage;



public class TestLogin_Valid extends SuperTestNG
{
	
	@Test
	public void testLogin_valid(){
		
	//	LoginPage loginPage=new LoginPage(driver);
	//	GenericMethods generic=new GenericMethods(driver);
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Home Page");
		
		loginPage.login(Config.USER_NAME, Config.PASSWD);
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Manager HomePage");
		BasePage basePage = new BasePage(driver);
		basePage.logout();
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");

	}

}
