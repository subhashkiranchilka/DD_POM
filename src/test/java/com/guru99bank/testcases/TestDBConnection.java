package com.guru99bank.testcases;


import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.guru99bank.libraries.DbManager;

public class TestDBConnection {

	public static void main(String[] args) throws AddressException, ClassNotFoundException, SQLException, MessagingException {
		
		DbManager.setMysqlDbConnection();
		
		List<String> query = DbManager.getMysqlQuery("select emp_name from employees where emp_id='101'; ");
		
		for(int i=0;i<query.size();i++){
		 
			System.out.println(query.get(i));
		}

	}

}
