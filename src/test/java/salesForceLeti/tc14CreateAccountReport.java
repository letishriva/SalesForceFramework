package salesForceLeti;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1	Launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application. 	SalesForce login page is launched and application home page is logged in with correct username.
2	Click on Accounts link on the home page	Click on Accounts link on the home page	Accounts page is displayed with correct <username>
3	Create account report	Click on Accounts with last activity > 30 days link in reports area on accounts page. 	Unsaved Repoet page is diaplayed
4	Select report options	Select create date in the date field drop down, select <todays date> in From and To fileds on the unsaved report filed	List of accounts qualified is displayed
5	Save report	Click on the save button on unsaved report page. Provide <report name>, <report unique name> in the pop window and click on save and run report button.	Report page with details and <report name>is displayed.

 */


public class tc14CreateAccountReport extends BaseAction {
	@Test
	
	public static void createAccountReport() throws InterruptedException, AWTException {
//1.launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application.
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);


//2. Click on Accounts link on the home page	Click on Accounts link on the home page	Accounts page is displayed with correct <username>
		
		driverSF.findElement(By.id("Account_Tab")).click();// click on Accounts link
		Thread.sleep(2000);
		
// a pop up opens!! 
		driverSF.switchTo().activeElement();
		driverSF.findElement(By.id("tryLexDialogX")).click();
		Thread.sleep(2000);
//--------------------------------------------------------------------------------------------------
//		String expectedAccountsUsername = "Leti Casta";
//		String actualAccountsUsername = driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]/div[2]/div[1]/div/a")).getText();//username displayed on account page
//		Assert.assertEquals(actualAccountsUsername, expectedAccountsUsername, "login passed and correct username is displayed");
		
// 3. 3	Create account report	Click on Accounts with last activity > 30 days link in reports area on accounts page. 	Unsaved Repoet page is diaplayed
		driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/table/tbody/tr/td[1]/div/div/div[1]/ul/li[2]/a")).click();
		String expectedTitle = "Unsaved Report ~ Salesforce - Developer Edition";
		String actualTitle = driverSF.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "login passed : Unsaved Report page is diaplayed");// pass
	//	driver.switchTo().activeElement();
		
// 4	Select report options	Select create date in the date field drop down, select <todays date> in From and To fields on the unsaved report filed	List of accounts qualified is displayed
		
		
		// Select create date / become "CREATED"!!! in the date field drop down, select <todays date> in From and To fields on the unsaved report filed
				
		Thread.sleep(2000);
		
		// By default we are on calendar 
		Actions action = new Actions (driverSF);
		WebElement dateFieldDropdown = driverSF.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[1]/div/div/div/div/div/div/div/div[1]/div[2]/table/tbody/tr[2]/td[2]/div/div/form/div/div[1]/input")); // field menu
		do { 
			action.sendKeys(Keys.ARROW_DOWN).perform(); //  we need to go down by 1 in menu
		} while (!dateFieldDropdown.isDisplayed());
		dateFieldDropdown.click();//click on dropdown ok
		System.out.println("dropdown is clicked");
		driverSF.findElement(By.xpath("/html/body/div[16]/div/div[3]")).click(); // Click on Created Date
		Thread.sleep(2000);
		driverSF.findElement(By.id("ext-gen152")).click();
		driverSF.findElement(By.id("ext-gen276")).click(); // today button from 1st table
		Thread.sleep(2000);
		driverSF.findElement(By.id("ext-gen154")).click();
		driverSF.findElement(By.id("ext-gen296")).click(); // today button from 2 nd table
		Thread.sleep(2000);
		
// 5	Save report	Click on the save button on unsaved report page. Provide <report name>, <report unique name> in the pop window and click on save and run report button.	Report page with details and <report name>is displayed.
		// save button doesn't work

	//	driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/table/tbody/tr/td[1]/table/tbody/tr/td[1]")); // click on save page button

		driverSF.findElement(By.xpath("/html/body/div[2]/div[1]/div/div/div/table/tbody/tr/td[1]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/em/button")).click();
		Thread.sleep(2000);
		driverSF.findElement(By.id("saveReportDlg_reportNameField")).sendKeys("ReportOneTest"); // provide report Name ReportOneTest
		driverSF.switchTo().activeElement();
		driverSF.findElement(By.id("saveReportDlg_DeveloperName")).sendKeys("Unique"); // provide report unique Name = UniqueNameReportOneTest
		System.out.println("after writing unique name");
		driverSF.findElement(By.xpath("/html/body/div[20]/div[2]/div[2]/div/div/div/div[1]/table/tbody/tr/td[1]/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/em/button")).click();// click on save and run
		System.out.println("after clicking save and run");
		Thread.sleep(5000);
		
// how to assess that Newly added View should be displayed in the account view list ??
		
//		ba.closeBrowser(driverSF);
		
		
	}

}
