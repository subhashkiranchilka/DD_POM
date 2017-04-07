package com.guru99bank.libraries;

import java.util.Calendar;

import com.guru99bank.testcases.SuperTestNG;

public class CalendarHandling extends SuperTestNG{
	
	static int targetDay = 0,
			   targetMonth = 0,
			   targetYear = 0;
	
	static int currentDay = 0,
			   currentMonth = 0,
			   currentYear = 0;
	
	static int jumpMonthsBy = 0;
	static boolean increment = true;
	
	
	public static void getCurrentDateMonthAndYear(){
		
		Calendar cal = Calendar.getInstance();
		
		currentDay = cal.get(Calendar.DAY_OF_MONTH);
		currentMonth = cal.get(Calendar.MONTH)+1;
		currentYear = cal.get(Calendar.YEAR);
	}
	
	public static void getTargetDateMonthAndYear(String dateString){
		
		int firstIndex = dateString.indexOf("/");
		int lastIndex = dateString.lastIndexOf("/");
		
		String day = dateString.substring(0, firstIndex);
		targetDay = Integer.parseInt(day);
		
		String month = dateString.substring(firstIndex+1, lastIndex);
		targetMonth = Integer.parseInt(month);
		
		String year = dateString.substring(lastIndex+1, dateString.length());
		targetYear = Integer.parseInt(year);
	}
	
	public static void CalculateHowManyMonthsToJump(){
		
		if((targetMonth-currentMonth)>0){
			jumpMonthsBy = targetMonth-currentMonth;
		}else{
			jumpMonthsBy = currentMonth-targetMonth;
			increment = false;
		}
	}
	
	
	/*public static void main(String[] arg){
		
		String datetoset = "07/05/2017";
		//curernt date
		getCurrentDateMonthAndYear();
		System.out.println(currentDay+"  "+currentMonth+"   "+currentYear);
		
		//get targetdated
		getTargetDateMonthAndYear(datetoset);
		System.out.println(targetDay+" "+targetMonth+" "+targetYear);
		
		WebDriver driver = new FirefoxDriver();
		
		for(int i=0;i<jumpMonthsBy;i++){
			if(increment){
				driver.findElement(By.id("")).click();
			}else{
				driver.findElement(By.id("")).click();
			}
			
			
		}
		
		 driver.findElement(By.linkText(Integer.toString(targetDay))).click();
	}*/
	
	
	

}
