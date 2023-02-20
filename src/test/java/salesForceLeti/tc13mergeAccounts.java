package salesForceLeti;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1	Launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application. 	SalesForce login page is launched and application home page is logged in with correct username.
2	Click on Accounts link on the home page	Click on Accounts link on the home page	Accounts page is displayed with correct <username>
3	Select accounts to merge	On the accounts page in Tools area, click on Merge Accounts link. Enter the <Account name> in the text box of merge accounts page and click Find accounts button. Entered <Account name> should result in atleast 2 or more account links. Select first two account links displayed in the list and click on Next button	Merge by Accounts step 2 page is displayed with the selected accounts details to merge
4	Merge accounts	Click on Merge button on Merge by accounts step 2 page. Click on OK button on the pop up.	New pop up for account merge confirmation is displayed and once the accounts are merged, account page is displayed. In recently viewed view, new merged account is listed.
*/
public class tc13mergeAccounts extends BaseAction {
	@Test
	
	public static void mergeAccounts() throws InterruptedException {
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

		String expectedAccountsUsername = "Leti Casta";
		String actualAccountsUsername = driverSF.findElement(By.id("mru005Dn000004aZBX")).getText();//username displayed on account page
		Assert.assertEquals(actualAccountsUsername, expectedAccountsUsername, "login passed and correct username is displayed"); 
		
//3	Select accounts to merge	On the accounts page in Tools area, click on Merge Accounts link. Enter the <Account name> in the text box of merge accounts page and click Find accounts button. Entered <Account name> should result in atleast 2 or more account links. Select first two account links displayed in the list and click on Next button	Merge by Accounts step 2 page is displayed with the selected accounts details to merge
		driverSF.findElement(By.partialLinkText("Merge Accounts")).click(); // click on merge accounts link
		
		
		driverSF.findElement(By.id("srch")).sendKeys("new");// enter "new" in text box area to reach dummy accounts NewAccount1 and NewAccount1
		driverSF.findElement(By.name("srchbutton")).click(); // click on Find accounts button
// select first 2 links and click on Next 
		driverSF.findElement(By.id("cid0")).click();
		driverSF.findElement(By.id("cid1")).click();
// sometimes 2 first links are selected by default!
		driverSF.findElement(By.name("goNext")).click();

//4	Merge accounts	Click on Merge button on Merge by accounts step 2 page. Click on OK button on the pop up.	New pop up for account merge confirmation is displayed and once the accounts are merged, account page is displayed. In recently viewed view, new merged account is listed.
		driverSF.findElement(By.name("save")).click();//Merge button has name "save"

		// it opens a popup window alert / we accept  the alert for the merge
		Alert alert=driverSF.switchTo().alert();
		String actualAlert = alert.getText();
		System.out.println(actualAlert);
		String expectedAlert = "These records will be merged into one record using the selected values. Merging can't be undone. Proceed with the record merge?";
		alert.accept();
		if(expectedAlert.equals(actualAlert)) {
			System.out.println("confirmation alert displayed");
		}
		else {
			System.out.println("confirmation alert not displayed");
		}
		
		String expectedNewPageName = "Accounts: Home ~ Salesforce - Developer Edition";
		String actualNewPageName = driverSF.getTitle();
		Assert.assertEquals(actualNewPageName, expectedNewPageName, "account page is displayed"); 
		
//		ba.closeBrowser(driverSF);
	}
}