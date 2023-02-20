package salesForceLeti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;


public class tc4aForgotPassword extends BaseAction{
	@Test

	public static void aForgotPassword() throws Throwable {
		BaseAction ba = new BaseAction();
		logger.info("inside TC4A Forgot Password");
		WebDriver driverSF = driver;	
		driverSF = ba.getWebDriver("chrome");
		ba.setMaxWindowBrowser(driverSF);		
		String expectedForgotPage = "Forgot Your Password | Salesforce";
		String expectedResetPage = "Check Your Email | Salesforce";
		driverSF.get("https://login.salesforce.com/");
		driverSF.findElement(By.id("forgot_password_link")).click(); // click on forgot password
		
		// Salesforce forgot password page is displayed
		String actualPage = driverSF.getTitle();
		if (actualPage.equalsIgnoreCase(expectedForgotPage)) {
			System.out.println("SalesForce forgot password page is displayed - PASS"); 
		} else {
			System.out.println("SalesForce forgot password page is NOT displayed - FAIL");
		}
		
		// provide username in forgot your password page
		driverSF.findElement(By.id("un")).sendKeys("leti@isreal.com"); // we enter correct username
		// click continue button
		driverSF.findElement(By.id("continue")).click();
		Thread.sleep(2000); // we let the page load
		String actualResetPage = driverSF.getTitle();
		if (actualResetPage.equalsIgnoreCase(expectedResetPage)) {
			System.out.println("Password reset message page is displayed - PASS"); 
		} else {
			System.out.println("Password reset message page is NOT displayed - FAIL");
		}
		ba.closeBrowser(driverSF);
	}

}