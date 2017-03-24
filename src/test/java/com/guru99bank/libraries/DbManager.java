package com.guru99bank.libraries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



public class DbManager
{
	private static Connection sqlconnection = null;
	private static Connection mysqlconnection = null;

	public static void setDbConnection() throws SQLException, ClassNotFoundException, AddressException, MessagingException
	{
		try{
		Class.forName(Config.sqldriver);
		sqlconnection =	DriverManager.getConnection(Config.dbConnectionUrl, Config.dbUserName, Config.dbPassword);
		
		if(!sqlconnection.isClosed())
			System.out.println("Successfully connected to SQL server");
			
	}catch(Exception e){
		System.err.println("Exception: " + e.getMessage());

	//	monitoringMail.sendMail(Config.server, Config.frommailid, Config.to, Config.subject+" - (Script failed with Error, Datamart database used for reports, connection not established)", Config.messageBody, Config.attachmentPath, Config.attachmentName);			
		}
		
		
	}
	
	public static void setMysqlDbConnection() throws SQLException, ClassNotFoundException, AddressException, MessagingException
    {
    try
    {
        
        Class.forName (Config.mysqldriver).newInstance ();
        mysqlconnection = DriverManager.getConnection (Config.mysqlurl, Config.mysqluserName, Config.mysqlpassword);
        if(!mysqlconnection.isClosed())
			System.out.println("Successfully connected to MySQL server");
			
	
    }
    catch (Exception e)
    {
        System.err.println ("Cannot connect to database server");
       
       // monitoringMail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject+" - (Script failed with Error, Datamart database used for reports, connection not established)", TestConfig.messageBody, TestConfig.attachmentPath, TestConfig.attachmentName);
    }
   

}
	
		
	public static List<String> getQuery(String query) throws SQLException{
		
		//String Query="select top 10* from ev_call";
		Statement St = sqlconnection.createStatement();
		ResultSet rs = St.executeQuery(query);
		List<String> sqlQueryvalues = new ArrayList<String>();
		while(rs.next()){
		
			sqlQueryvalues.add(rs.getString(1));
			
		}
		return sqlQueryvalues;
	}
	
	public static List<String> getMysqlQuery(String query) throws SQLException{
		
		
		Statement St = mysqlconnection.createStatement();
		ResultSet rs = St.executeQuery(query);
		List<String> MysqlQueryvalues = new ArrayList<String>();
		while(rs.next()){
			
			MysqlQueryvalues.add(rs.getString(1));
			
			
		}
		return MysqlQueryvalues;
	}
	
	
	
	public static Connection getConnection()
	{
		return sqlconnection;
			}
}
