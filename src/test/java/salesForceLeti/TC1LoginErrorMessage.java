package salesForceLeti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;
// Only Test Case where we have to tests for Edge, Firefox and Chrome (ALL 3 BROWSERS)
public class TC1LoginErrorMessage extends BaseAction {
@Test
	public static void loginErrorMessage() throws Exception {
//1. Firefox Browser
		WebDriver driverSF;		
		BaseAction ba = new BaseAction();
		//driverSF = ba.getWebDriver("chrome");
		//driverSF = ba.getWebDriver("edge");
		driverSF = ba.getWebDriver("firefox");
		ba.setMaxWindowBrowser(driverSF);
		
		String expected = "Please enter your password.";// Expected: Error message "Please enter your password."
		driverSF.get("https://login.salesforce.com/");
		driverSF.findElement(By.id("username")).sendKeys("User@gmail.com"); // we enter incorrect username
		driverSF.findElement(By.id("password")).clear(); //we clear password
		driverSF.findElement(By.id("Login")).click();
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/div[3]/form/div[1]")).getText();
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Login script passed"); 
		} else {
			System.out.println("Login script failed");
		}
		ba.closeBrowser(driverSF);
//2. Chrome Browser		
		driverSF = ba.getWebDriver("chrome");
		ba.setMaxWindowBrowser(driverSF);
						
		String expected2 = "Please enter your password.";// Expected: Error message "Please enter your password."
		driverSF.get("https://login.salesforce.com/");
		driverSF.findElement(By.id("username")).sendKeys("User@gmail.com"); // we enter incorrect username
		driverSF.findElement(By.id("password")).clear(); //we clear password
		driverSF.findElement(By.id("Login")).click();
		Thread.sleep(2000); // we let the page load
		String actual2 = driverSF.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/div[3]/form/div[1]")).getText();
		if (actual2.equalsIgnoreCase(expected2)) {
			System.out.println("Login script passed"); 
		} else {
			System.out.println("Login script failed");
		}
		ba.closeBrowser(driverSF);
//3. Edge Browser			
		driverSF = ba.getWebDriver("edge");
		ba.setMaxWindowBrowser(driverSF);
						
		String expected3 = "Please enter your password.";// Expected: Error message "Please enter your password."
		driverSF.get("https://login.salesforce.com/");
		driverSF.findElement(By.id("username")).sendKeys("User@gmail.com"); // we enter incorrect username
		driverSF.findElement(By.id("password")).clear(); //we clear password
		driverSF.findElement(By.id("Login")).click();
		Thread.sleep(2000); // we let the page load
		String actual3 = driverSF.findElement(By.xpath("/html/body/div[1]/div[1]/div/div/div[2]/div[3]/form/div[1]")).getText();
		if (actual2.equalsIgnoreCase(expected3)) {
			System.out.println("Login script passed"); 
		} else {
			System.out.println("Login script failed");
		}
		ba.closeBrowser(driverSF);
		
	}

}