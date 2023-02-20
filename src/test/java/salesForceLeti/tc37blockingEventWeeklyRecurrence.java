package salesForceLeti;
import java.awt.AWTException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;
/*
1	Click Home Tab	Click on Home Tab	1. Home page should be displayed. 2. Current date is displayed as a link below the FirstName and LastName in Day Month Date,Year format. Ex: Thursday July 18,2019
2	Click on the current date link	Click on the current date link	The 'Calendar for FirstName LastName' page should be displayed.
3	Click on '4:00PM' link	Click on '4:00PM' link	The 'Calendar: New Event' page should be displayed with the cursor at the Subject field.
4	Click on 'Subject Combo' icon	Click on 'Subject Combo' icon next to Subject field.	The 'Combobox' popup should be opened.
5	Click 'Other' from Combobox	Click 'Other' from Combobox	1. The 'Combobox' popup should be closed. 2.Other should be entered in the Subject field
6	Click on the 'End' time field	Click on the 'End' time field	Drop down should be displayed with time starting from 5:00 PM till 11:30 PM.
7	Select '7:00 PM' from the dropdown.	Select '7:00 PM' from the dropdown.	7:00 PM should be selected in the 'End' field.
8	Check 'Recurrence'	Check the'Create Recurring Series of Events' under the 'Recurrence' field	1. The box should be checked.  2. 'Frequency', 'Start Date', 'End Date' sections should be displayed.
9	Select 'Weekly' radiobutton	Select 'Weekly' radiobutton	1. 'Weekly' radio button should be selected. 2. 'Recurs Every..' section shlould be displayed with '1' entered in it. 3. Current day of the week should be checked. Ex:Thursday
10	Enter End Date	Click on the 'End Date' field and select 2 weeks from then.	The 'End Date' should be selected and the calendar should be closed.
11	Click Save button	Click Save button	The 'Calendar for FirstName LastName' page should be displayed with 'Other' event in the time slot 4:00PM to 7:00PM, as a link.
12	Click 'Month View' icon	Click 'Month View' icon	1. The 'Calendar for FirstName LastName - Month View' page should be displayed.  2. Current date and a week from current date should have the 'Other' event blocked as a link.

*/
public class tc37blockingEventWeeklyRecurrence extends BaseAction {
//1.	Launch and Login 

	@Test
	public static void blockingEventWeeklyRecurrence () throws InterruptedException, AWTException, Exception {
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
		
// how to assess that Current date is displayed as a link below the FirstName and LastName in Day Month Date,Year format. Ex: Thursday July 18,2019		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd, yyyy");
		String expectedCurrentDateLink = sdf.format(date); // if we use Sunday February 12, 2023, test will work only one time...
		WebElement currentDateLink = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[1]/div[1]/div/div[2]/span[2]/a")); // Current Date Link
		String actualCurrentDateLink = currentDateLink.getText();
		Assert.assertEquals(actualCurrentDateLink, expectedCurrentDateLink, "login passed");
		
//	2	Click on the current date link	Click on the current date link	The 'Calendar for FirstName LastName' page should be displayed.		
		currentDateLink.click();
		String expectedCalendarPage = "Calendar for Leti Casta ~ Salesforce - Developer Edition";
		String actualCalendarPage = driverSF.getTitle();//bCalDiv
		Assert.assertEquals(actualCalendarPage, expectedCalendarPage, "Calendar Page for Leti Casta is displayed");
		
//3	Click on '4:00PM' link	Click on '4:00PM' link	The 'Calendar: New Event' page should be displayed with the cursor at the Subject field.
		driverSF.findElement(By.partialLinkText("4:00 PM")).click();
		String expectedNewEventCalendarPage = "Calendar: New Event ~ Salesforce - Developer Edition";
		String actualNewEventCalendarPage = driverSF.getTitle();//bCalDiv
		Assert.assertEquals(actualNewEventCalendarPage, expectedNewEventCalendarPage, "Calendar New Event Page is displayed");
		
//	4	Click on 'Subject Combo' icon	Click on 'Subject Combo' icon next to Subject field.	The 'Combobox' popup should be opened.
		driverSF.findElement(By.className("comboboxIcon")).click();
		
//		5	Click 'Other' from Combobox	Click 'Other' from Combobox	1. The 'Combobox' popup should be closed. 2.Other should be entered in the Subject field
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

		driverSF.findElement(By.partialLinkText("Other")).click();
		
//	6	Click on the 'End' time field	Click on the 'End' time field	Drop down should be displayed with time starting from 5:00 PM till 11:30 PM.
		driverSF.switchTo().window(mainWindowHandle); //go back to main window
		Thread.sleep(2000);		
				
		driverSF.findElement(By.id("EndDateTime_time")).click();
		// how to  assert that we get time starting from 9PM till 11:30PM ??? with a screenshot at this moment?
		File screenshot = ((TakesScreenshot)driverSF).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("assessScreenshots/screenshotTC37" + System.currentTimeMillis()+ ".png"));
		// YES > Screenshot is generated  and shows the options of selection: C:\working\eclipseworkspace\SeleniumWithMaven
		
//7	Select '7:00 PM' from the dropdown.	Select '7:00 PM' from the dropdown.	7:00 PM should be selected in the 'End' field.
		driverSF.findElement(By.id("EndDateTime_time")).click();
		WebElement contentInput = driverSF.findElement(By.id("EndDateTime_time"));
		contentInput.clear();
		contentInput.sendKeys("7:00 PM");
		Thread.sleep(2000); 
		
//8 Check 'Recurrence'	Check the'Create Recurring Series of Events' under the 'Recurrence' field	1. The box should be checked.  2. 'Frequency', 'Start Date', 'End Date' sections should be displayed.
		WebElement recurrenceCheckBox = driverSF.findElement(By.id("IsRecurrence"));
		recurrenceCheckBox.click();
		// Verify that the checkbox is selected
		Assert.assertTrue(recurrenceCheckBox.isSelected(), "The 'Create Recurring Series of Events' checkbox is not selected");// Assert if checkbox is selected
		WebElement frequencySection = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div[1]/div[2]/div[8]/table/tbody/tr[2]/td/div/table/tbody/tr[1]/td[1]/label"));// Frequency section is displayed
		Assert.assertTrue(frequencySection.isDisplayed(), "The 'Frequency' section is not displayed");
		WebElement startDateSection = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div[1]/div[2]/div[8]/table/tbody/tr[2]/td/div/table/tbody/tr[2]/td[1]/label"));// Start Date section is displayed
		Assert.assertTrue(startDateSection.isDisplayed(), "The 'Start Date' section is not displayed");
		WebElement endDateSection = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div[1]/div[2]/div[8]/table/tbody/tr[2]/td/div/table/tbody/tr[3]/td[1]/label"));// End Date section is displayed
		Assert.assertTrue(endDateSection.isDisplayed(), "The 'End Date' section is not displayed");
		
// 9	Select 'Weekly' radiobutton	Select 'Weekly' radiobutton	1. 'Weekly' radio button should be selected. 2. 'Recurs Every..' section shlould be displayed with '1' entered in it. 3. Current day of the week should be checked. Ex:Thursday		
		WebElement frequencyRadioButton=driverSF.findElement(By.id("rectypeftw"));
		frequencyRadioButton.click();
	/*	WebElement numberExpected = driverSF.findElement(By.id("wi"));// id for every 1 = "wi"
		String numberEnteredExpected = numberExpected.getText().trim(); // error! Number entered is as expected expected [] but found [1] ??
		String numberEnteredActual = "1";
		Assert.assertEquals(numberEnteredActual, numberEnteredExpected, "Number entered is as expected");
	*/	
	    // Current day of the week should be checked. Ex:Thursday	
		//we have to check dynamically ! so that it passes whenever a test is executed
		
// 10	Enter End Date	Click on the 'End Date' field and select 2 weeks from then.	The 'End Date' should be selected and the calendar should be closed.		
		WebElement recurrenceEndDateBox =driverSF.findElement(By.id("RecurrenceEndDateOnly"));
		recurrenceEndDateBox.click();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 14); // we add 14 days thanks to Calendar class

		SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yyyy");//format 2/12/2023
		String date2 = sdf2.format(calendar.getTime());
		recurrenceEndDateBox.sendKeys(date2); // we select 2 weeks from today
		
//11	Click Save button	Click Save button	The 'Calendar for FirstName LastName' page should be displayed with 'Other' event in the time slot 4:00PM to 7:00PM, as a link.
		driverSF.findElement(By.name("save")).click();
	
		// How to assess ? With another screenshot?
		
// 12	Click 'Month View' icon	Click 'Month View' icon	1. The 'Calendar for FirstName LastName - Month View' page should be displayed.  2. Current date and a week from current date should have the 'Other' event blocked as a link.		
		driverSF.findElement(By.className("monthViewIcon")).click();
		String expectedViewPage = "Calendar for Leti Casta - Month View ~ Salesforce - Developer Edition";
		String actualViewPage = driverSF.getTitle();
		Assert.assertEquals(actualViewPage, expectedViewPage, "Calendar Monthly View for Leti Casta is displayed");
		
//		ba.closeBrowser(driverSF);

	}		
}