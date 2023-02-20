package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1	Launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application. 	SalesForce login page is launched and application home page is logged in with correct username.
2	Click on Accounts link on the home page	Click on Accounts link on the home page	Accounts page is displayed with correct <username>
2	Select the <view name> from the view drop down list on the account page to edit	Select the <view name> from the view drop down list on the account page. Click on the Edit link the accounts page.	<View name> edit page Is displayed
3	Edit the view	Change the <view name> to <new view name>. Select the filters field <Account name>, operator  <contains>, Value <a>. In Select fields to display,  Click on save button.	View page with <new view name> in the drop down is displayed. View will have Last activity column added and the data of the list will have all account names which has <a> in the text.
 */

public class tc12EditView extends BaseAction {
	@Test
	public static void editView() throws InterruptedException {
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
		String actualAccountsUsername = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]/div[2]/div[1]/div/a")).getText();//username displayed on account page
		Assert.assertEquals(actualAccountsUsername, expectedAccountsUsername, "login passed and correct username is displayed"); //pass
		
//2	Select the <view name> from the view drop down list on the account page to edit	Select the <view name> from the view drop down list on the account page. Click on the Edit link the accounts page.	<View name> edit page Is displayed
	
		driverSF.findElement(By.partialLinkText("Edit")).click(); // click on Edit for the view name Magical created (TC11)
		
		//assess that EDIT VIEW PAGE is displayed
	
		String actualPageDescription = driverSF.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td[2]/div[1]/div[1]/div[1]/h2")).getText(); // Xpath of "Edit View Page" description
		String expectedPageDescription = "Edit View";
		Assert.assertEquals(actualPageDescription, expectedPageDescription, "login passed - correct page is displayed"); // pass
		Thread.sleep(2000);
		//---------------------------
		
		driverSF.findElement(By.id("fname")).sendKeys("New Magical"); // provide New View Name = "New Magical"
		Thread.sleep(2000);

//3 Select the filters field <Account name>, operator  <contains>, Value <a>. In Select fields to display,  Click on save button.
		//Additional fields with <Account name>
	
		WebElement contentDropdownAdditionalFields = driverSF.findElement(By.id("fcol1")); // field menu
		contentDropdownAdditionalFields.click();
		Select contentSelect = new Select(contentDropdownAdditionalFields);// click on dropdown
		contentSelect.selectByValue("ACCOUNT.NAME"); //ok
		Thread.sleep(2000); 
		
		//driver.findElement(By.name("Account Name")).click(); // click on Account Name
		// click on dropdown Operator Contains
		WebElement contentDropdownOperator = driverSF.findElement(By.id("fop1")); // field menu
		contentDropdownOperator.click();
		Select contentSelect2 = new Select(contentDropdownOperator);// click on dropdown
		contentSelect2.selectByValue("c"); // c = value for contains
		
		driverSF.findElement(By.id("fval1")).sendKeys("a"); // send key "a" in textfield
		driverSF.findElement(By.name("save")).click(); // click on save button
		
	
		Thread.sleep(5000);
		
// ok for all - ??? How to assess : View will have Last activity column added and the data of the list will have all account names which has <a> in the text.

	//	ba.closeBrowser(driverSF);
		
		
	}

}
