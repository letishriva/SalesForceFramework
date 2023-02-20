package salesForceLeti;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

	public class tc8developersConsole extends BaseAction {
//1	Select user menu for <username> drop down[TC01]	
//user menu for <username> drop down is selected	
//Drop down with "My profile", "My Settings", "Developer Console","Logout" is displayed
//2	Click on Developer Console link from the drop down > Force.com Developer Console window is displayed
//String expectedusername = "Leti Casta";// username should be displayed after change

	@Test
	public static void developersConsole () throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);

//2	Click "My Settings" option from user menu > Force.com Developer Console window is displayed
		driverSF.findElement(By.id("userNavLabel")).click();// userNavLabel id > for username dropdowm to be clicked
		driverSF.findElement(By.partialLinkText("Developer Console")).click(); 

//we switch to the open window
		String currentWindowHandle = driverSF.getWindowHandle();
		Set<String> windowHandles = driverSF.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(currentWindowHandle)) {
				driverSF.switchTo().window(handle);
				break;
			}
		}
		String titlePageExpected = "Developer Console";
		String titlePageActual = driverSF.getTitle();
		Assert.assertEquals(titlePageActual, titlePageExpected, "login passed");
		System.out.println("How to assert for newly opened window ----------------------?");
		Thread.sleep(2000);


// 3Click on close button 
//Click on close button the developer console browser	Force.com Developer Console window is closed >> NO CLOSE BUTTON ??
	
//		ba.closeBrowser(driverSF);
	}

}
