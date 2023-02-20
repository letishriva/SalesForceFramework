package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;
/*
1	Launch and Login 	Launch and Login  to SalesForce Application	SalesForce application should be Launched.
2	Click Home Tab	Click on Home Tab	1. Home page should be displayed. 2. Verify that the FirstName LastName of the account holder is displayed at the top left hand side of the page, as a link.                                                                                        
3	Click on the FirstName LastName link in the home page	Click on the FirstName LastName link in the home page	1. 'User:FirstName LastName' page should be displayed.                                                                                                2. This page should be same as the 'My Profile' page.
*/
public class tc33checkFirstAndLastNameDisplay extends BaseAction {
//1.	Launch and Login 
	
	@Test
	public static void checkFirstAndLastNameDisplay () throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);

//2.	Click on Home Tab // Homepage Should be displayed
		driverSF.findElement(By.id("home_Tab")).click();
		Thread.sleep(2000);
		String expectedPage = "Salesforce - Developer Edition";
		String actualPage = driverSF.getTitle();
		Assert.assertEquals(actualPage, expectedPage, "Home page passed");
		Thread.sleep(2000);
		
// a pop up opens!! 
    	driverSF.switchTo().activeElement();
    	driverSF.findElement(By.id("tryLexDialogX")).click();
    	Thread.sleep(3000);
		
// Verify that the FirstName LastName of the account holder is displayed at the top left hand side of the page, as a link.		
    	WebElement link = driverSF.findElement(By.xpath("//tbody/tr[1]/td[2]/div[1]/div[1]/div[1]/div[2]/span[1]/h1[1]/a[1]")); 
    	// I can't use partialLinkTest for Leti Casta because there are many Leti Casta links in the page
    	String actualLinkName = link.getText();
    	String expectedLinkName = "Leti Casta";
    	Assert.assertEquals(actualLinkName, expectedLinkName, "User First and Last Name displayed");
    	Thread.sleep(2000);
    	
//    	ba.closeBrowser(driverSF);
	}		
}