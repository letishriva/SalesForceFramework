package salesForceLeti;
import java.awt.AWTException;
import java.io.File;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;
/*
2	Click on the current date link	Click on the current date link	The 'Calendar for FirstName LastName' page should be displayed.
3	Click on '8:00PM' link	Click on '8:00PM' link	The 'Calendar: New Event' page should be displayed with the cursor at the Subject field.
4	Click on 'Subject Combo' icon	Click on 'Subject Combo' icon next to Subject field.	The 'Combobox' popup should be opened.
5	Click 'Other' from Combobox	Click 'Other' from Combobox	1. The 'Combobox' popup should be closed.                                                                     2.Other should be entered in the Subject field
6	Click on the 'End' time field	Click on the 'End' time field	Drop down should be displayed with time starting from 9:00 PM till 11:30 PM.
7	Select '9:00 PM' from the dropdown.	Select '9:00 PM' from the dropdown.	9:00 PM should be selected in the 'End' field.
8	Click Save button	Click Save button	The 'Calendar for FirstName LastName' page should be displayed with 'Other' event in the time slot 8:00PM to 9:00PM, as a link.
*/
public class tc36blockingEventInCalendar extends BaseAction {
//1.	Launch and Login 

	@Test
	public static void blockingEventInCalendar () throws InterruptedException, AWTException, Exception {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);
		
//1	Click Home Tab	Click on Home Tab	1. Home page should be displayed.                                                                                                                                                                                 2. Current date is displayed as a link below the FirstName and LastName in Day Month Date,Year format. Ex: Thursday July 18,2019
		
		driverSF.findElement(By.id("home_Tab")).click();
		Thread.sleep(2000);
		String expectedPage = "Salesforce - Developer Edition";
		String actualPage = driverSF.getTitle();
		Assert.assertEquals(actualPage, expectedPage, "Home page passed");
		Thread.sleep(2000);

		// a pop up opens!! 
		driverSF.findElement(By.id("tryLexDialog"));
		driverSF.switchTo().activeElement();
		driverSF.findElement(By.id("tryLexDialogX")).click();
		Thread.sleep(2000);		

//	2	Click on the current date link	Click on the current date link	The 'Calendar for FirstName LastName' page should be displayed.		
			
		driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[1]/div[1]/div/div[2]/span[2]/a")).click(); // Current Date Link
		String expectedCalendarPage = "Calendar for Leti Casta ~ Salesforce - Developer Edition";
		String actualCalendarPage = driverSF.getTitle();//bCalDiv
		Assert.assertEquals(actualCalendarPage, expectedCalendarPage, "Calendar Page for Leti Casta is displayed");
		
//	3	Click on '8:00PM' link	Click on '8:00PM' link	The 'Calendar: New Event' page should be displayed with the cursor at the Subject field.
		driverSF.findElement(By.partialLinkText("8:00 PM")).click();
		String expectedNewEventCalendarPage = "Calendar: New Event ~ Salesforce - Developer Edition";
		String actualNewEventCalendarPage = driverSF.getTitle();//bCalDiv
		Assert.assertEquals(actualNewEventCalendarPage, expectedNewEventCalendarPage, "Calendar New Event Page is displayed");
		
//	4	Click on 'Subject Combo' icon	Click on 'Subject Combo' icon next to Subject field.	The 'Combobox' popup should be opened.
		driverSF.findElement(By.className("comboboxIcon")).click();
		
//5	Click 'Other' from Combobox	Click 'Other' from Combobox	1. The 'Combobox' popup should be closed. 
		// We have to switch to the new window opened
		String mainWindowHandle = driverSF.getWindowHandle();
		Set<String> allWindowHandles = driverSF.getWindowHandles();
		String newWindowHandle = null;
		for (String handle : allWindowHandles) {
		  if (!handle.equals(mainWindowHandle)) {
		    newWindowHandle = handle;
		    break;
		  }
		}

		if (newWindowHandle == null) {
		  throw new RuntimeException("There is no new window");
		}

		driverSF.switchTo().window(newWindowHandle);
		Thread.sleep(2000);		
	//	driver.close(); >> works so I have access to my new window
		driverSF.findElement(By.partialLinkText("Other")).click();
		

// 6	Click on the 'End' time field	Click on the 'End' time field	Drop down should be displayed with time starting from 9:00 PM till 11:30 PM.
		driverSF.switchTo().window(mainWindowHandle); //go back to main window
		Thread.sleep(2000);		
		
		
		driverSF.findElement(By.id("EndDateTime_time")).click();
		// how to  assert that we get time starting from 9PM till 11:30PM ??? with a screenshot at this moment?
		File screenshot = ((TakesScreenshot)driverSF).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("assessScreenshots/screenshotTC36" + System.currentTimeMillis()+ ".png"));
		// YES > Screenshot is generated  and shows the options of selection * Screenshots in file "assessScreenshots"
		
//	7	Select '9:00 PM' from the dropdown.	Select '9:00 PM' from the dropdown.	9:00 PM should be selected in the 'End' field.
		driverSF.findElement(By.id("EndDateTime_time")).click();
		WebElement contentInput = driverSF.findElement(By.id("EndDateTime_time"));
		contentInput.clear();
		contentInput.sendKeys("9:00 PM");
		Thread.sleep(2000); 
		
//8	Click Save button	Click Save button	The 'Calendar for FirstName LastName' page should be displayed with 'Other' event in the time slot 8:00PM to 9:00PM, as a link.
		driverSF.findElement(By.name("save")).click();
	
		// How to assess ? With another screenshot?
//		ba.closeBrowser(driverSF);
	}		
}