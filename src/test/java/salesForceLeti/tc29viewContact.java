package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1	Launch and Login 	Launch and Login  to SalesForce Application	SalesForce application should be Launched
2	Click Contact Tab	Click ON Contact Tab	Contact Home page should be displayed
3	Click on a contact name	Click on a <contact name> under the Name fIEld in the Recent Contact Frame	Contact Page related to <contact name>, which contains entire information about that <contact name> should be displayed 
*/
public class tc29viewContact extends BaseAction {

	
	@Test
//	@Parameters({"URL", "UID", "PASS"})
//	public static void viewContact (String broURL, String broUID, String broPass) throws InterruptedException {
	public static void viewContact () throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		
//		loginSF.loginToSalesForce(driverSF, broURL, broUID, broPass);// This is Login Method, it takes URL and UID, PASS
		
		Thread.sleep(2000); // we let the page load

		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);

//2.	Click on Contact page
		driverSF.findElement(By.id("Contact_Tab")).click();
		Thread.sleep(2000);
		
// a pop up opens!! 
		driverSF.findElement(By.id("tryLexDialog"));
		driverSF.switchTo().activeElement();
		driverSF.findElement(By.id("tryLexDialogX")).click();
		Thread.sleep(2000);		
		
//3	Click on a contact name	Click on a <contact name> under the Name fIEld in the Recent Contact Frame	Contact Page related to <contact name>, which contains entire information about that <contact name> should be displayed 
		driverSF.findElement(By.partialLinkText("NewLastName2")).click(); // Click on a <contact name> under the Name fIEld in the Recent Contact Frame
		Thread.sleep(2000);		
		String expected2 = "Contact: NewLastName2 ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	Assert.assertEquals(actual2, expected2, "My contacts view should be displayed");
 
  //  	ba.closeBrowser(driverSF);
	}		
}