package com.guru99bank.testcases;

import org.testng.annotations.Test;

import com.guru99bank.pages.LoginPage;

public class TestLogin_BlankData extends SuperTestNG{
	
	@Test
	public void testLoginInvalidData(){
		
	//	LoginPage lp=new LoginPage(driver);
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Home Page");
		
		//Blank data
		loginPage.loginInvalidData("", "");
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");
		
		/*valid user name and blank password    
		lp.loginInvalidData("mngr67188 ", "");
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");
		
		valid user name and invalid password    
		lp.loginInvalidData("mngr67188 ", "");
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");*/
		
	}
	
	@Test
	public void testUsrIdCruserBlank(){
		
	//	LoginPage lp=new LoginPage(driver);
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Home Page");
		loginPage.usrIdCurserBlank();
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");
	}
	
	@Test
	public void testPwdCruserBlank(){
		
	//	LoginPage lp=new LoginPage(driver);
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Home Page");
		loginPage.pwdCurserBlank();
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");
	}

}
