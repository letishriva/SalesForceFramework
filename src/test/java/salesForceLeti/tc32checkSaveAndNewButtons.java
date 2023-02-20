package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1	Login	Login to Salesforce.com	"Logged in salesforce.com
2	Home Page	Click on Contacts on the menu bar	Contacts page is opened.
3	Contacts: Home Page	Click on the New button	Contact Edit: New Contact Page is dispalyed
4	Contact Edit: New Contact Page	Enter the last name and Account Name(Last Name - Indian, Account Name-Global Media)	Last Name and the Account Name should be enetered.
5	Click Save & New button	Click the Save & New button	New contact is created. Contact Edit: New Contact Page is dispalyed
*/
public class tc32checkSaveAndNewButtons extends BaseAction {

	@Test
	public static void checkSaveAndNewButtons () throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
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
		
//3	Contacts: Home Page	Click on the New button	Contact Edit: New Contact Page is dispalyed
		driverSF.findElement(By.name("new")).click();
		String expected2 = "Contact Edit: New Contact ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	Assert.assertEquals(actual2, expected2, "new Contact page displayed");	
    	
//4	Contact Edit: New Contact Page	Enter the last name and Account Name(Last Name - Indian, Account Name-Global Media)	Last Name and the Account Name should be enetered.    	
    	driverSF.findElement(By.id("name_lastcon2")).sendKeys("Indian");
    	driverSF.findElement(By.id("con4")).sendKeys("Dickenson plc"); // replace with "Dickenson plc" Global Media doesn't exist
		Thread.sleep(4000);	
		
// 5	Click Save & New button	Click the Save & New button	New contact is created. Contact Edit: New Contact Page is dispalyed
		driverSF.findElement(By.name("save_new")).click();
		Thread.sleep(4000);	

		String expected3 = "Contact Edit: New Contact ~ Salesforce - Developer Edition";
    	String actual3 = driverSF.getTitle();
    	Assert.assertEquals(actual3, expected3, "Contact Edit : New Contact Page  displayed");
    	// if account name is correct, function works
   // FAIL : GLOBAL MEDIA DOESN'T EXIST
    
 //   	ba.closeBrowser(driverSF);
	}		
}