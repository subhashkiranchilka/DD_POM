/*
 * Declare some common parameters for scripts
 * You can change them to adapt your environment
 *
 */
package com.guru99bank.libraries;

public class Config {
	
	/* You can change the Path of FireFox based on your environment here */
//	public static final String FIREFOX_PATH = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	
	public static final String GECKODRIVER_PATH = "D:\\Selenium\\geckodriver-v0.14.0-win64\\geckodriver.exe";
	
	// Setting Base URL
	public static final String BASE_URL = "http://www.demo.guru99.com/";
    
	// Time to wait when searching for a GUI element 
	public static final int WAIT_TIME = 30; 
	
	//Broweser name
	public static final String BROSER_NAME = "firefox";

	// Valid account for login
	public static final String USER_NAME = "mngr67188";
	public static final String PASSWD = "subbuSK56@";

	//Excell file path                   D:/workspace/Guru99BankMavenProject/src/test/java/XLFiles/Guru99BankTestData.xlsx
	public static final String xlPath = System.getProperty("user.dir")+"/src/test/java/XLFiles/Guru99BankTestData.xlsx";
	
	
	// for Test report mailing configuration we can use the below parameters
	public static String server="smtp.gmail.com";
	public static String frommailid = "w2aispg@gmail.com";
	public static String mailpassword = "Ispg$123";
	public static String[] to ={"seleniumcoaching@gmail.com","trainer@way2automation.com"};
	public static String subject = "Test Report";
	
	public static String messageBody ="TestMessage";
	public static String attachmentPath="C:\\screenshot\\error.jpg";
	public static String attachmentName="Error.jpeg";
	
	
	//For Database connection configuration
	
	//SQL DATABASE DETAILS	
	public static String sqldriver="net.sourceforge.jtds.jdbc.Driver"; 
	public static String dbConnectionUrl="jdbc:jtds:sqlserver://192.101.44.22;DatabaseName=monitor_eval"; 
	public static String dbUserName="sa"; 
	public static String dbPassword="$ql$!!1"; 
	
	
	//MYSQL DATABASE DETAILS
	public static String mysqldriver="com.mysql.jdbc.Driver";
	public static String mysqluserName = "root";
	public static String mysqlpassword = "subbuSK%62";
	public static String mysqlurl = "jdbc:mysql://localhost:3306/test_database";

}
