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
1	Launch and Login 	Launch and Login  to SalesForce Application	SalesForce application should be Launced
2	Click Contact Tab	Click ON Contact Tab	Contact Home page should be displayed
3	Click New Button	Clickn on New Button	New Contact home page sould be displayed
4	Enter Last name	Enter Last Name <LastName> in Last name fIEld	Last name should be enterd in Last name fIEld
5	Enter Account Name	"Enter account name in account name fIEld
<AccountName>"	Account name should be displayed in account name fIEld
6	Click On Save	Click on Save button	New contact should be created
*/
public class tc25createNewContact extends BaseAction {
//1.	Launch and Login 
	
	@Test
	public static void createNewContact () throws InterruptedException {
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
		
//	3	Click on the New button.	New Contact home page sould be displayed
		driverSF.findElement(By.name("new")).click(); //
		
	   	String expected2 = "Contact Edit: New Contact ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	Assert.assertEquals(actual2, expected2, "New Contact Creation page displayed");
 
//4	Enter Last name	Enter Last Name <LastName> in Last name fIEld	Last name should be enterd in Last name fIEld   	
 
		driverSF.findElement(By.id("name_lastcon2")).sendKeys("NewLastName2"); 
		
// how to assess ?? Screenshot?
//5	Enter Account Name	"Enter account name in account name fIEld
		
		WebElement accountName = driverSF.findElement(By.id("con4")); //AccountName -> Only works with previously created Account name!!
		accountName.sendKeys("Magical");
		accountName.click();
// how to assess ?? Screenshot?
//6	Click On Save	Click on Save button	New contact should be created
				
		driverSF.findElement(By.name("save")).click(); // click on save Button
		String expected3 = "Contact: NewLastName2 ~ Salesforce - Developer Edition";
    	String actual3 = driverSF.getTitle();
    	Assert.assertEquals(actual3, expected3, "New Contact Creation page created");
			
//	ba.closeBrowser(driverSF);
	}		
}