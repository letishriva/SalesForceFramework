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
3	Click Create new VIEw	Click on Create New VIEw link	New VIEw Page should be displayed	
4	Enter VIEw Name	Enter the <VIEw Name> fIEld in the VIEw Name fIEld	VIEw Name should be entered in the vIEw name fIEld	
5	Enter VIEw Unique Name	Enter the <VIEw Unique Name> fIEld in the VIEw Unique Name fIEld.It will be automatically given by the salesforce application, one can modify if they wish	VIEw Unique Name should be entered in the vIEw Unique Name fIEld.	
6	Click on Save	Click on save button	Contacts Home page is opened. Created VIEw name is displayed in drop down in that page by defalut. 	
*/
public class tc26newViewContactPage extends BaseAction {
//1.	Launch and Login 
	
	@Test
	public static void newViewContactPage () throws InterruptedException {
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
		
//	3	Click Create new VIEw	Click on Create New VIEw link	New VIEw Page should be displayed	
		driverSF.findElement(By.partialLinkText("Create New View")).click(); //
		String expected2 = "Contacts: Create New View ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	Assert.assertEquals(actual2, expected2, "Contact Create New View Page is displayed");
 
//4	Enter VIEw Name	Enter the <VIEw Name> fIEld in the VIEw Name fIEld	VIEw Name should be entered in the vIEw name fIEld	
 
		driverSF.findElement(By.id("fname")).sendKeys("NewViewName"); 


//5	5	Enter VIEw Unique Name	Enter the <VIEw Unique Name> fIEld in the VIEw Unique Name fIEld.It will be automatically given by the salesforce application, one can modify if they wish	VIEw Unique Name should be entered in the vIEw Unique Name fIEld.
		WebElement newViewBox = driverSF.findElement(By.id("devname")); // NewViewName should automatically be entered
		newViewBox.click();
//		String expected3 = "NewViewName";	
//		WebElement actualBox = driver.findElement(By.id("devname"));
 //   	Assert.assertEquals(actualBox.getText(), expected3, "New View Name is automatically created");
		
//6	Click On Save	Click on Save button	Contacts Home page is opened. Created VIEw name is displayed in drop down in that page by default.
				
		driverSF.findElement(By.name("save")).click(); // click on save Button
		Thread.sleep(2000);		
// How to assess that created View name is displayed in drop down in that page by default. Screenshot?	
//	ba.closeBrowser(driverSF);
	}		
}