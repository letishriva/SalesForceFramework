package salesForceLeti;
import java.awt.AWTException;
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
1	Click + tab	Click on the all tab '+'	The 'All Tabs' page should be displayed.
2	Click 'Customize My Tabs' button	Clcik on the 'Customize My Tabs' button on the right hand side of the page.	The 'Customize My Tabs' page should be displayed.
3	Remove any tab	Select any tab from the 'Selected Tabs' section and click Remove button.	The selected tab should be removed from the 'Selected Tabs' section and should be moved to the 'Available Tabs' section.
4	Click on Save	Click on Save button	1. The 'All Tabs' page should be displayed.            2. The tab removed at step 3 should not be displayed in the tab bar.
5	Click on Logout.	Click on User menu 'FirstName LastName' menu button and click Logout.	Salesfore login page should be displayed.       
6	Launch and Login 	Launch and Login  to SalesForce Application	1. SalesForce application should be Launced.                                                                     2. The tab removed at step 3 should not be displayed in the tab bar.

*/
public class tc35checkTabCustomization extends BaseAction {

	@Test
	public static void checkTabCustomization () throws InterruptedException, AWTException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);


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

//	1	Click + tab	Click on the all tab '+'	The 'All Tabs' page should be displayed.	
		driverSF.findElement(By.id("AllTab_Tab")).click();
		String expectedAllTabPage = "All Tabs ~ Salesforce - Developer Edition";
		String actualAllTabPage = driverSF.getTitle();
		Assert.assertEquals(actualAllTabPage, expectedAllTabPage, "Salesforce All Tabs page is displayed");
		
		
//2	Click 'Customize My Tabs' button	Click on the 'Customize My Tabs' button on the right hand side of the page.	The 'Customize My Tabs' page should be displayed.		
		driverSF.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/table/tbody/tr/td[2]/input")).click();
		String expectedCustomizeMyTabs = "Customize My Tabs ~ Salesforce - Developer Edition";
		String actualCustomizeMyTabs = driverSF.getTitle();
		Assert.assertEquals(actualCustomizeMyTabs, expectedCustomizeMyTabs, "All Tabs - Salesforce page is displayed");
		
		
//3	Remove any tab	Select any tab from the 'Selected Tabs' section and click Remove button.	The selected tab should be removed from the 'Selected Tabs' section and should be moved to the 'Available Tabs' section.		
		WebElement selectedTabs = driverSF.findElement(By.id("duel_select_1"));
		Select selectLeads = new Select(selectedTabs); // we select Leads fron the Selected Tabs
		selectLeads.selectByVisibleText("Leads");  // I remove a Tab but we will have to put it back!!!
		driverSF.findElement(By.id("duel_select_0_left")).click();// click on left button
				
// 4	Click on Save	Click on Save button	1. The 'All Tabs' page should be displayed.            2. The tab removed at step 3 should not be displayed in the tab bar.
		driverSF.findElement(By.name("save")).click();
		Thread.sleep(2000); 
		String expectedPage2 = "All Tabs ~ Salesforce - Developer Edition";
		String actualPage2 = driverSF.getTitle();
		Assert.assertEquals(actualPage2, expectedPage2, "All Tabs pages is displayed");
		// Assess ok for all tabs page - how to prove that a Tab was removed?
		
//	5	Click on Logout.	Click on User menu 'FirstName LastName' menu button and click Logout.	Salesfore login page should be displayed.    
		
		driverSF.findElement(By.id("userNavLabel")).click();// userNavLabel id > for username dropdowm to be clicked
		driverSF.findElement(By.partialLinkText("Logout")).click(); // logout
		String expectedLoginPage = "";
		String actualLoginPage = driverSF.getTitle();
		Assert.assertEquals(actualLoginPage, expectedLoginPage, "Back to Salesforce login page");
		Thread.sleep(2000);
		
//6	Launch and Login 	Launch and Login  to SalesForce Application	1. SalesForce application should be Launced.   
		driverSF.findElement(By.id("username")).sendKeys("leti@isreal.com"); 
		driverSF.findElement(By.id("password")).sendKeys("testtest123"); 
		driverSF.findElement(By.id("Login")).click();
		Thread.sleep(2000);
		String expectedHomePage = "Home Page ~ Salesforce - Developer Edition";
		String actualHomePage = driverSF.getTitle();
		Assert.assertEquals(actualHomePage, expectedHomePage, "Application is launched");
		
// For testing >> WE NEED TO ADD BACK THE LEADS TAB FOR FUTURE TESTS
		driverSF.findElement(By.id("AllTab_Tab")).click();
		Thread.sleep(2000); 
		driverSF.findElement(By.xpath("/html/body/div/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/table/tbody/tr/td[2]/input")).click();//customize my tabs
		Thread.sleep(2000); 
	    WebElement availableTabs = driverSF.findElement(By.id("duel_select_0"));
		Select selectTab = new Select(availableTabs);
		Thread.sleep(1000); 
		selectTab.selectByValue("Lead");
		Thread.sleep(1000); 
		driverSF.findElement(By.id("duel_select_0_right")).click();// click on add button
		Thread.sleep(1000); 
		driverSF.findElement(By.name("save")).click();
		Thread.sleep(3000); 
		
//		ba.closeBrowser(driverSF);
	}		
}