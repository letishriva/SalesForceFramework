package salesForceLeti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class tc4bWrongUsernameAndPassword extends BaseAction {
@Test
	public static void bWrongUsernameAndPassword() throws InterruptedException {
		String expected = "Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. Please contact the administrator at your company for help";
		WebDriver driverSF;		
		BaseAction ba = new BaseAction();
		driverSF = ba.getWebDriver("chrome");
		ba.setMaxWindowBrowser(driverSF);	
		WebDriverManager.chromedriver().setup();
		driverSF.get("https://login.salesforce.com/");
		driverSF.findElement(By.id("username")).sendKeys("123"); // we enter incorrect username 123
		driverSF.findElement(By.id("password")).sendKeys("22131"); //we provide incorrect password 22131
		driverSF.findElement(By.id("Login")).click(); // click log in button
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.findElement(By.id("error")).getText();
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("Login script passed"); 
		} else {
			System.out.println("expected message: " + expected);
			System.out.println("actual message: " + actual);
			System.out.println("Login script failed");
		}
		ba.closeBrowser(driverSF);
	}

}