package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1.launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application. 	SalesForce login page is launched and application home page is logged in with correct username.
2. Click on Accounts link on the home page	Click on Accounts link on the home page	Accounts page is displayed with correct <username>
 */

public class tc11CreateNewView extends BaseAction {
	@Test
	
	public static void createNewView() throws InterruptedException {
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

		// Assertion workimFIND "Leti Casta" link by xpath
		String expectedAccountsUsername = "Leti Casta";
//		String actualAccountsUsername = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]/div[2]/div[1]/div/a")).getText();//username displayed on account page
//		Assert.assertEquals(actualAccountsUsername, expectedAccountsUsername, "login passed and correct username is displayed");

// > WRONG, USERNAME IS NOT DISPLAYED IN ACCOUNT // FAIL		
		
// 3. Click on create new view link on accounts page	Click on create new view link and provide <View name>, <View unique name> and click on save button	Newely added View should be displayed in the account view list
		driverSF.findElement(By.partialLinkText("Create New View")).click(); // click on "create new view Link"
		driverSF.findElement(By.id("fname")).sendKeys("Magical"); // provide View Name = "Magical"
		driverSF.findElement(By.name("save")).click();
		Thread.sleep(5000);
		
// how to assess that Newely added View should be displayed in the account view list ??
		
//		ba.closeBrowser(driverSF);
		
		
	}

}
