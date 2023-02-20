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
1	Launch and Login 	Launch and Login  to SalesForce Application	SalesForce application should be Launched
2	Click Contact Tab	Click ON Contact Tab	Contact Home page should be displayed
3	Select 'My Contacts'	Select 'My Contacts'  VIEw from the drop down list in contacts page	My contacts VIEw should be displayed

*/
public class tc28checkMyContacts extends BaseAction {
//1.	Launch and Login 
	
	@Test
	public static void checkMyContacts () throws InterruptedException {
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
		
//	3	3	Select 'My Contacts'	Select 'My Contacts'  VIEw from the drop down list in contacts page	My contacts VIEw should be displayed
		WebElement dropdown = driverSF.findElement(By.id("fcf"));
		dropdown.click();
		Thread.sleep(2000);		
		Select myContacts = new Select(dropdown);
		myContacts.selectByValue("00BDn00000IFIRY"); 
		driverSF.findElement(By.name("go")).click();
		Thread.sleep(2000);

		String expected2 = "Contacts ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	Assert.assertEquals(actual2, expected2, "My contacts view should be displayed");
 
// How to assess- Screenshot?	
//	ba.closeBrowser(driverSF);
	}		
}