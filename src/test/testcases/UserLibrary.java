package test.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import test.resources.generic.WebLibrary;

public class UserLibrary extends WebLibrary {

	public static boolean Login(String URL,String Username, String password)
	{	
	boolean loginstatus = true;
	boolean retval;

	// Launch Application
	retval = OpenUrl(URL);
	if(retval)
		LogEventWithScreeshot("info", "Application is Up and Running");
	else
		LogEventWithScreeshot("fail", "Unable to Launch HRM Application");

	// Step1: Enter User id
 retval = SetText("//input[@id='txtUsername']",Username);
	if(retval)
		LogEventWithScreeshot("info", "Username entered");
	else
		LogEventWithScreeshot("info", "Username is not entered");
	
	// Step2: Enter password
	retval = SetText("//input[@id='txtPassword']",password);
	if(retval)
		LogEventWithScreeshot("info", "Password is enetered");
	else
		LogEventWithScreeshot("fail", "Password is not enetered");
	
	// Step3: Click on login button
	retval = ClickElement("//input[@id='btnLogin']");
	if(retval)
		LogEventWithScreeshot("info", "Log in button is clicked");
	else
		LogEventWithScreeshot("info", "Log in button is not clicked");
	
	wait(2);
	retval = Exist("//a(Contains[text(),'Welcome'])");
	if(retval)
		LogEventWithScreeshot("info","Log in successful");
	else
	{
		LogEventWithScreeshot("fail","Log in not successful");
	loginstatus=false;
	
	}
	return loginstatus;

	}
//====================================================== 
	public static void NavigateMyLeave()
	{
		boolean stepstatus=SelectMenuOption("//*[@id='menu_leave_viewLeaveModule']","//a[@id='menu_leave_viewMyLeaveList']");
		if(stepstatus)
		{
			LogEventWithScreeshot("pass", "My Leave page dispalyed");
		}
		else
		{
			LogEventWithScreeshot("fail", "My Leave page not displayed");
		}
	}
	
	//========================================================
	
	public static void CancelLeave(String inp_leaveDate) 
	{
	
	boolean rval;
	int rownumber = VerifyLeaveStatus(inp_leaveDate,"Pending Approval");
	if(rownumber!=0)
	{
		rval = SelectOPtionByText("//table[@id='resultTable']/tbody/tr["+rownumber+"]/td[8]/select","Cancel");
	if(rval)
		LogEventWithScreeshot("info", "Cancel selcted in dropdown");
		else
		LogEventWithScreeshot("fail", "Cancel is not selected in dropdown");
	}
	else
	 LogEventWithScreeshot("fail", "Leave date with pending approval status was not found");
}
	 
	 /*try
	 {
		int rowCount = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
		
		for(int iRow=43; iRow<rowCount; iRow++)
		{
		String appDate = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+iRow+"]/td[1]")).getText();
		String appStatus = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+iRow+"]/td[6]")).getText();
		//boolean ad1 = (appDate.contentEquals(inp_leaveDate));
				
	   // boolean ad = (appDate.equals(inp_leaveDate));
		//boolean as = (appStatus.contains("Pending Approval"));
		if(  (appDate.equals(inp_leaveDate)) && (appStatus.contains("Pending Approval"))  )
		{
		 WebElement dropdown = driver.findElement(By.xpath("//table[@id ='resultTable']/tbody/tr["+iRow+"]/td[8]/select"));
		 Select obj = new Select(dropdown);
		 obj.selectByVisibleText("Cancel");
		 bTag = true;
		 break;
		}
	}
	 }
	 catch(Exception e)
		{
			bTag = false;
			}
		if(bTag)
		 LogEventWithScreeshot("info", "Cancel selected in dropdown");
		else
		LogEventWithScreeshot("fail", "Cancel not selected in dropdown");
	 
	}

*/

//=============================================================================
	
public static int VerifyLeaveStatus(String Leavedate, String Leavestatus)
{
	int rownum = 0;

	try
	{
		int rowCount = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
		for(int iRow=34; iRow<rowCount; iRow++)
		{
		String appDate = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+iRow+"]/td[1]")).getText();
		String appStatus = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+iRow+"]/td[6]")).getText();
		
		boolean a = (appDate.equals(Leavedate));
		boolean b = (appStatus.contains(Leavestatus));
		
		if ( a && b)
		//if(  (appDate.equals(Leavedate)) && (appStatus.contains(Leavestatus))  )
		{
			rownum = iRow;
			break;
		}
		}
    }
	catch(Exception e)
	{
		rownum = 0;
	}
		return rownum;
}
 //==============================================================================

public static void NavApplyLeave()
{
	Actions obj = new Actions(driver);
   WebElement ele=driver.findElement(By.id("menu_leave_viewLeaveModule"));
   obj.moveToElement(ele).build().perform();
   
   clickLink("//a[@id='menu_leave_applyLeave']","Apply");
  
	boolean retvalue = verifyText("h1","Apply Leave");
		
   if(retvalue)
	   LogEventWithScreeshot("info","Apply Leave page displayed");
   else
	   LogEventWithScreeshot("fail","Apply Leave page is not displayed");
	}

// ================ Apply Leave
public static void ApplyLeave(String LeaveType, String stDate, String endDate, String Comments)
{
	  // select leave type
	  boolean retval;
	  retval = selectItemwithText("applyleave_txtLeaveType", LeaveType);
	  if(retval)
		  LogEventWithScreeshot("info", "Leave type selected");
	  else
		  LogEventWithScreeshot("fail", "Leave type is not selected");
	  
	  // Enter from date
	  retval = SetTextAndEscape("//input[@id='applyleave_txtFromDate']",stDate );
	  if(retval)
		  LogEventWithScreeshot("info","From Date selected");
	  else
		  LogEventWithScreeshot("fail","From Date is not selected");
	  
	  // Enter To date
	  retval = SetTextAndEscape("//input[@id='applyleave_txtToDate']",endDate);
	  if(retval)
		  LogEventWithScreeshot("info","To Date selected");
	  else
		  LogEventWithScreeshot("fail","To Date is not selected");
	  
	  // Enter comment
	  retval = setText("//textarea[@id='applyleave_txtComment']", Comments);
	  if(retval)
		  System.out.println("Comment is entered");
	  else
		  System.out.println("Comment is not entered");
	  
	  // Click on Apply
	  retval = ClickElement("//input[@id='applyBtn']");
	  if(retval)
		  System.out.println("Apply is clicked");
	  else
		  System.out.println("Apply is not clicked");
	  	  
	    }
//============================ verify applied leave details
public static int VerifyLeaveStatusN(String stDate, String endDate, String LeaveStatus)
{
	int rownum = 0;

	try
	{
		int rowCount = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
		for(int iRow=1; iRow<rowCount; iRow++)
		{
		 String ActDateRange;
		 ActDateRange = stDate+" "+"to"+" "+endDate;
			
		 String appDate = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+iRow+"]/td[1]")).getText();
		 String appStatus = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+iRow+"]/td[6]")).getText();
		
		
		if(  (appDate.equals(ActDateRange)) && (appStatus.contains(LeaveStatus)) )
		{
			String[] strarr = appDate.split(" to ");
			System.out.println(strarr[0]);
			System.out.println(strarr[1]);
			rownum= iRow;
			break;
		}
    }
	}
	catch(Exception e)
	{
		rownum = 0;
	}
		return rownum;
}

// =================== Verify all check box unchecked

 public static void VerifyAll()
 
 { 
	/* boolean check;
	 check = Exist("//*(Contains[text(),'Show Leave '])");
		if(check)
			LogEventWithScreeshot("info","My Leave info page is dispalyed ");
		else
		{
			LogEventWithScreeshot("fail","My Leave info page is not displayed");
			check=false;
		
		}*/
	   boolean retval;
	   retval =ClickElement("//input[@id='leaveList_chkSearchFilter_checkboxgroup_allcheck']");
	   if(retval)
			LogEventWithScreeshot("info", "All is unchecked");
		else
			LogEventWithScreeshot("fail", "All is checked still");
 }
 
 public static void VerifyStatusinWebTable()
 {
	boolean bval;
	 bval = VerifyElementSelected("//input[@id='leaveList_chkSearchFilter_checkboxgroup_allcheck']", false);
	 bval = ClickElement("//*[text()='Cancelled']");
	 ClickElement("//input[@id='btnSearch']");
	 if(bval)
		 LogEventWithScreeshot("info", "Cancelled was selected");
	 else
		 LogEventWithScreeshot("fail", "Cancelled was not selected");
	 
	  }
 
//============================================================================
public static void NavigateTime()
{
	boolean stepstatus=SelectMenuOption("//*[@id='menu_time_viewTimeModule']","//*[@id='menu_time_Timesheets']");
	if(stepstatus)
	{
		LogEventWithScreeshot("pass", "Time sheet page displayed");
	}
	else
	{
		LogEventWithScreeshot("fail", "Time sheet page not displayed");
	}
}

//====================================================================

public static void Timesheet()
{
	boolean retval;
	retval = ClickElement(".//a[@id='menu_time_Timesheets']");
	  if(retval)
		  LogEventWithScreeshot("info", "Timesheets is clicked");
	  else
		  LogEventWithScreeshot("fail", "Timesheets is not clicked");
	
	  retval = ClickElement("//a[@id='menu_time_viewMyTimesheet']");
	  if(retval)
		  LogEventWithScreeshot("info", "My Timesheets is selected");
	  else
		  LogEventWithScreeshot("fail", "My Timesheets is not selected");
	
	//selectItemwithText
}
}
