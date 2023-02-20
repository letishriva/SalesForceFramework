package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
4	Contacts: Create New VIEw page	Enter the VIEw Unique Name(Unique Name : EFGH)	"
Unique Name : EFGH"
5	Click Save button	Click the Save Button	Error message is appeared under the VIEw Name fIEld. The Error message appears as "Error: You must enter a value".

*/
public class tc30checkErrorMessageInContact extends BaseAction {
//1.	Launch and Login 
	
	@Test
	public static void checkErrorMessageInContact () throws InterruptedException {
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
    	
    	
 //4  	Enter the VIEw Unique Name(Unique Name : EFGH)
    	driverSF.findElement(By.id("devname")).sendKeys("EFGH");
    	
//5	Click Save button	Click the Save Button	Error message is appeared under the VIEw Name fIEld. The Error message appears as "Error: You must enter a value".
    	driverSF.findElement(By.name("save")).click();
    	Thread.sleep(5000);		
    	String expectedErrorMessage = "Error: You must enter a value";
    	WebElement errorMessage = driverSF.findElement(By.xpath("//*[@id=\"editPage\"]/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"));
    	JavascriptExecutor js = (JavascriptExecutor) driverSF;
    	String errorMessageText = (String) js.executeScript("return arguments[0].textContent;", errorMessage);
    	Assert.assertEquals(errorMessageText, expectedErrorMessage, "Error Message for Contacts displayed"); // the element is in a div and can't be accessed via getText()
 
    	// pass and assessed
 //   	ba.closeBrowser(driverSF);
	}		
}