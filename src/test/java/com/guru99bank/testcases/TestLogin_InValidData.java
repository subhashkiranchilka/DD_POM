package com.guru99bank.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru99bank.libraries.Config;
import com.guru99bank.libraries.GenericMethods;

public class TestLogin_InValidData extends SuperTestNG{
	
	@DataProvider
	public  Object[] getData(){
		
		Object[][] data = new Object[6][2];
		
		// invalid userid and invalid password
		data[0][0] = "invalidusername";
		data[0][1] = "invalidpassword";
		
		//blank username and password
		data[1][0] = "";
		data[1][1] = "";
		
		//valid username and invalid password
		data[2][0] = Config.USER_NAME;
		data[2][1] = "invalidpassword";
		
		//invalid username and valid password
		data[3][0] = "invalidusername ";
		data[3][1] = Config.PASSWD;
		
		//blank username and valid password
		data[4][0] = "";
		data[4][1] = Config.PASSWD;
		
		
		
			
		return data;
	}
	
	@Test(dataProvider = "getData")
	public void testLoginInvalidData(String Pusername, String Ppassword){
		
	//	LoginPage lp=new LoginPage(driver);
		GenericMethods generic=new GenericMethods();
		generic.explicitWait(3);
		generic.verifyTitle(driver,"Guru99 Bank Home Page");
		loginPage.loginInvalidData(Pusername, Ppassword);
		generic.explicitWait(3);
		generic.verifyTitle(driver, "Guru99 Bank Home Page");
	}
	
	
}
