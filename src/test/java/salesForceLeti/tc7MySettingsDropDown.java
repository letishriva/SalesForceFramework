package salesForceLeti;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

public class tc7MySettingsDropDown extends BaseAction {

	//	1	Select user menu for <username> drop down[TC01]
	//	2	Click "My Settings" option from user menu
	//	2	Click on personal link and select Login History link. 
	//	3	Click on Display & Layout link
	//	4	Click on Email link and click on my email settings link under that
	//	5	Click on Calendar & Remainders	
	
	@Test
	public static void verifyMySettingsMenu() throws InterruptedException, AWTException {	
			WebDriver driverSF = driver;		
			LoginUtility loginSF =  new LoginUtility();
			loginSF.loginToSalesForce(driverSF);
			Thread.sleep(2000); // we let the page load
			String actual = driverSF.getTitle();
			String expected = "Home Page ~ Salesforce - Developer Edition";
			Assert.assertEquals(actual, expected, "login passed");
			Thread.sleep(2000);
			
//			2	Click "My Settings" option from user menu
			driverSF.findElement(By.id("userNavLabel")).click();// userNavLabel id > for username dropdowm to be clicked
			driverSF.findElement(By.partialLinkText("My Settings")).click(); 
			Thread.sleep(2000); // we let the page load

			
//			2	Click on personal link and select Login History link. 
// Login history is displayed and the data is downloaded in .csv format.

			driverSF.findElement(By.linkText("Personal")).click();
			driverSF.findElement(By.linkText("Login History")).click();
			System.out.println("what to assert for IP + cvs page?");
			Thread.sleep(2000); 
			
//			3	Click on Display & Layout link
//Click on Display & Layout link and select Customize My Tabs link. Select "Salesforce Chatter" option from custom App: drop down. Select Reports tab from Available Tabs list. Click on >(Add) button. 
			driverSF.findElement(By.linkText("Display & Layout")).click();
			driverSF.findElement(By.linkText("Customize My Tabs")).click();
				
			// click on content - Select "Salesforce Chatter" option from custom App: drop down. Select Reports tab from Available Tabs list. Click on >(Add) button. 
			WebElement contentDropdown = driverSF.findElement(By.id("p4"));
			contentDropdown.click();
			Select contentSelect = new Select(contentDropdown);
			contentSelect.selectByValue("02uDn000002FI2x");  // SalesForce Chatter
			Thread.sleep(2000); 
			
		// from Salesforce Chatter, now we have to select "Reports"	
		    WebElement availableTabs = driverSF.findElement(By.id("duel_select_0"));
			Select selectReports = new Select(availableTabs);
			Thread.sleep(1000); 
			selectReports.selectByValue("report");
			Thread.sleep(1000); 
			driverSF.findElement(By.id("duel_select_0_right")).click();// click on add button
			Thread.sleep(1000); 
			driverSF.findElement(By.name("save")).click();
			Thread.sleep(3000); 
			System.out.println("what to assert Reports page was added???"); // it doesn't show as mentionned
			
//---------> in order to perform the case again I need to revert this action for future tests
			
			// click on content - Select "Salesforce Chatter" option from custom App: drop down. Select Reports tab from Available Tabs list. Click on >(Add) button. 
			driverSF.findElement(By.linkText("Display & Layout")).click();
			driverSF.findElement(By.linkText("Customize My Tabs")).click();
			Thread.sleep(1000); 
			WebElement contentDropdown2 = driverSF.findElement(By.id("p4"));
			contentDropdown2.click();
			Select SalesForceChatter = new Select(contentDropdown2);
			SalesForceChatter.selectByValue("02uDn000002FI2x"); // SalesForce Chatter
			Thread.sleep(3000); 
			WebElement selectedTabs = driverSF.findElement(By.id("duel_select_1"));
			Select selectReports2 = new Select(selectedTabs);
			selectReports2.selectByValue("report"); 
			Thread.sleep(1000); 
			driverSF.findElement(By.id("duel_select_0_left")).click();// click on remove button
			Thread.sleep(1000); 
			driverSF.findElement(By.name("save")).click();
			Thread.sleep(3000); 
			
// 4	Click on Email link and click on my email settings link under that
			driverSF.findElement(By.linkText("Email")).click();
			driverSF.findElement(By.linkText("My Email Settings")).click();
			
// 4 bis Provide <EmailName> in Email Name field, <EmailAddress> in Email Address field, Select automatic BCC radio button and click on save button
			String emailNameProvided = "Lia Casta";
			String emailAddressProvided = "lb.cgnola@gmail.com";
			WebElement boxEmailName = driverSF.findElement(By.id("sender_name"));
			boxEmailName.clear();
			boxEmailName.sendKeys(emailNameProvided);
			WebElement boxEmailAddress = driverSF.findElement(By.id("sender_email"));
			boxEmailAddress.clear();
			boxEmailAddress.sendKeys(emailAddressProvided);
			driverSF.findElement(By.id("auto_bcc1")).click();//BCC radio button
			driverSF.findElement(By.name("save")).click();// click on save 
			WebElement actualMessageForSavedSettings = driverSF.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td[2]/form/div[1]/table/tbody/tr/td[2]/div")); //
			String stringOfactualMessageForSavedSettings = actualMessageForSavedSettings.getText(); 
			String expectedMessageForSavedSettings = "Your settings have been successfully saved.";
			Assert.assertEquals(stringOfactualMessageForSavedSettings, expectedMessageForSavedSettings, "Email Name and Address Change - test passed");
			System.out.println("assertion is working only in case of failure." );
			
// 5	Click on Calendar & Reminders
// Click on Calendar & Reminders link and click on Activity Remainders link. On Remainders page click on Open a test Remainder button.
			
			driverSF.findElement(By.linkText("Calendar & Reminders")).click();
			driverSF.findElement(By.linkText("Activity Reminders")).click();	
			driverSF.findElement(By.id("testbtn")).click(); // Open a test Reminder 
			System.out.println(" How to assert that the correct Pop up got open ? ");
			
//			ba.closeBrowser(driverSF);
			
	}

}