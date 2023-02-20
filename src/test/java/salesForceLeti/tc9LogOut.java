package salesForceLeti;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

public class tc9LogOut extends BaseAction {

	@Test
	public static void LogOut () throws InterruptedException {
//1 user menu for <username> drop down is selected >>	Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
//2 Click on Logout option from the drop down >>	Logout  of current sales force application  and https://login.salesforce.com/ page is displayed.
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);
			
//			2	Click "My Settings" option from user menu > Force.com Developer Console window is displayed
		driverSF.findElement(By.id("userNavLabel")).click();// userNavLabel id > for username dropdowm to be clicked
		driverSF.findElement(By.partialLinkText("Logout")).click(); 

	}

}
