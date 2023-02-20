package salesForceLeti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

public class tc3CheckRememberMe extends BaseAction{
@Test
	public static void checkRememberMe () throws Exception {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);
		String actualpage = driverSF.getTitle();
		String expectedpage = "Home Page ~ Salesforce - Developer Edition";// we have to enter and then log out
		if (actualpage.equalsIgnoreCase(expectedpage)) {
			System.out.println("We are in homepage"); 
		} else {
			System.out.println("We are not in homepage");
		}
		driverSF.findElement(By.id("userNavLabel")).click();// userNavLabel id > for username dropdowm to be clicked
		driverSF.findElement(By.xpath("//div[3]/div/div/div[2]/div[3]/a[5]")).click(); // we force logout
		Thread.sleep(3000); // we let the page load
		String expectedusername = "leti@isreal.com";// username should be displayed after logout - same as the one entered
		String actualusername = driverSF.findElement(By.id("idcard-identity")).getText();// we check again for the login username field that should match expected one / Id changed 
		System.out.println(actualusername);
		if (actualusername.equalsIgnoreCase(expectedusername)) {
			System.out.println("Remember Me script passed"); 
		} else {
			System.out.println("Remember Me script failed");
		}
	
	}

}
		
		
		
		
	
