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
1	Launch and Login 	Launch and Login  to SalesForce Application	SalesForce application should be Launced
2	Click Contact Tab	Click ON Contact Tab	Contact Home page should be displayed
3	Select recently created 	Select 'Recently created' from the drop down list in the right hand side of the 'Recent contact' frame of contacts page	Recently created contacts should be displayed

*/
public class tc27checkRecentlyCreatedContacts extends BaseAction {
//1.	Launch and Login 
	
	@Test
	public static void checkRecentlyCreatedContacts () throws InterruptedException {
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
		
//	3	Select recently created 	Select 'Recently created' from the drop down list in the right hand side of the 'Recent contact' frame of contacts page	Recently created contacts should be displayed
		WebElement dropdown = driverSF.findElement(By.id("hotlist_mode"));
		dropdown.click();
		Select selectRecentlyCreated = new Select(dropdown);
		selectRecentlyCreated.selectByVisibleText("Recently Created");
		Thread.sleep(2000);
		String expected2 = "Recent Contacts";
		String actual2 = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[3]/div[1]/div/div[1]/form/table/tbody/tr/td[1]/h3")).getText();// Recent Contacts frame

    	Assert.assertEquals(actual2, expected2, "Recently created contacts frame is displayed");

  //  	ba.closeBrowser(driverSF);
	}		
}