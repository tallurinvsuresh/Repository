package test.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Leave extends UserLibrary {

	public static void TC01_Leave_CancelLeave() 
	{
		// Step 1: Login as Team Member
		
		Login( URL,Username,Password);
		
		// Step 2: Navigate to Leave Page
		
		NavigateMyLeave();
		
		// Step 3: Search for leave and cancel the leave for the selected date
		
		CancelLeave(LeaveDate);
				
		// Step 4: After cancel, the cancelled leave should be displayed as cancelled
		
		boolean retval = ClickElement("//input[@id='btnSave']");
		if(retval)
			LogEvent("info", "Save button clicked");
		else
			LogEvent("fail", "Save button is not clicked");
		
		int rownumber = VerifyLeaveStatus(LeaveDate,"Cancelled");
		if(rownumber !=0)
			LogEventWithScreeshot("pass", "Leave is cancelled successfully");
		else
			LogEventWithScreeshot("fail", "Leave is not cancelled");
			}

public static void TC02_ApplyLeave_VerifyLeave()
{

	// Step 1: Login as Team Member
	
			Login( URL,Username,Password);
  // Step 2 : Navigate to My Leave page
			NavApplyLeave();
			
 // Step 3 : Apply Leave
			ApplyLeave(LeaveType,stDate,endDate,Comments);
			
// Step 4: Verify Leave in My Leave page
			NavigateMyLeave();
// Step 5 : Verify the applied leave in My Leave List
			int rownum = VerifyLeaveStatusN(stDate, endDate,LeaveStatus );	
			if(rownum !=0)
			
				LogEventWithScreeshot("pass", "Leave is verified successfully");
			else
				LogEventWithScreeshot("fail", "Leave is not verified");
			}

public static void TC03_MyLeave_UncheckAll_SelectanyCheckbox()
{
	// Step 1: Login as Team Member
	
				Login( URL,Username,Password);
				
    // Step 2: Navigate to Leave Page
				
				NavigateMyLeave();
				
	// Step 3: Unchek the detials in my info page
				
				VerifyAll();
	
	// Step 4: Check for status either Cancel, Rejected etc
				
				VerifyStatusinWebTable();
				int rownum = driver.findElements(By.xpath("//table[@id='resultTable']/tbody/tr")).size();
				System.out.println("No of Rows :" + rownum);
		 		{	
		 			boolean status = true;
		 			for (int i=1; i<rownum;i++)
		 			{
		 			String apptext = driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr["+i+"]/td[6]")).getText();
		 			if (!(apptext.contains("Cancelled")))
		 			{
		 				status = false;
		 			}
		 			else
		 			{
		 				status=true;
		 			}
		 				System.out.println("Wrong select");
		 			}
		 		}
}

public static void TC04_ApplyHours()
{
	// Step 1: Login as Team Member
	
	Login( URL,Username,Password);
	
	// Step 2: Navigate to Time Menu and select 
	
	NavigateTime();
	
	// Step 3 : Navigate to Time sheet and Edit button
	
	Timesheet();
	
	//driver.findElement(By.xpath("//*[@id='menu_time_viewMyTimesheet']")).click();
	
	//driver.findElement(By.xpath("//*[@id='btnEdit']")).click();
}
 public static void TC05_MyLeavelist ()
 
 {
	// Step 1: Login as Team Member
		
		Login( URL,Username,Password);
		
	// Step 2: Navigate to Leave Page
		
		NavigateMyLeave();
	// Step 3: Verify the xpath details
		
		////table/tbody/tr/td[3][text()='Personal Leave']/preceding-sibling::td[1]
		
	List<WebElement> ele = driver.findElements(By.xpath("//table/tbody/tr/td[3][text()='Personal Leave']/preceding-sibling::td[1]"));
	int e = ele.size();
	System.out.println(e);
	
	for ( WebElement itr : ele)
	{
		System.out.println(itr.getText());
	}
 }
 

}

