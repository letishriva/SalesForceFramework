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
1	Login	Login to Salesforce.com with the login credentails.	Logged in salesforce.com
2	Home page	Click on Contacts on the Menu bar	Contacts page is opened.
3	Contacts: Home Page	Click on Create New VIEw hyperlink 	Create New VIEw page is opened.
4	Contacts: Create New VIEw page	"Enter the required fIElds like VIEw Name, VIEw Unique Name(VIEw Name : ABCD
Unique Name : EFGH)"	The vIEw name and Unique name should be enetered
5	Click Cancel button	Click the Cancel Button	Contacts Home page is displayed and the VIEw ABCD should not be created.

*/
public class tc31checkCancelButtonOfCreateView extends BaseAction {
//1.	Launch and Login 
	
	@Test
	public static void checkCancelButtonOfCreateView () throws InterruptedException {
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
		
//3	Click on Create New VIEw hyperlink 

		driverSF.findElement(By.partialLinkText("Create New View")).click(); // Click on a new View link
		Thread.sleep(2000);		
		String expected2 = "Contacts: Create New View ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	Assert.assertEquals(actual2, expected2, "Create New view should be displayed");
    	
    	
  //4	Contacts: Create New VIEw page	"Enter the required fIElds like VIEw Name, VIEw Unique Name(VIEw Name : ABCD   	Unique Name : EFGH)"	The vIEw name and Unique name should be enetered
    	driverSF.findElement(By.id("fname")).sendKeys("ABCD");
    	WebElement uniqueViewBox = driverSF.findElement(By.id("devname"));
    			uniqueViewBox.clear();
    			uniqueViewBox.sendKeys("EFGH");
    	System.out.println("View Name and Unique Name are entered");
    	
 //	5	Click Cancel button	Click the Cancel Button	Contacts Home page is displayed and the VIEw ABCD should not be created.
    	driverSF.findElement(By.name("cancel")).click();
    	Thread.sleep(5000);		
    	String expectedPage = "Contacts: Home ~ Salesforce - Developer Edition";
    	String actualPage = driverSF.getTitle();
    	Assert.assertEquals(actualPage, expectedPage, " Return to home page with Standard Title ");
    
 //   	ba.closeBrowser(driverSF);
	}		
}