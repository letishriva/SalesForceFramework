package salesForceLeti;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

public class tc5UserDropDownMenu extends BaseAction {
//1.	Launch and Login 
	@Test
		public static void UserDropDownMenu () throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);

		driverSF.findElement(By.id("userNavLabel")).click();// userNavLabel id > for username dropdowm to be clicked
		Thread.sleep(3000); // we let the page load
		String actualusername = driverSF.findElement(By.id("userNavLabel")).getText();//we get the text on menu dropdown to compare 
		String expectedusername = "leti Iss";// username should be displayed after logout - same as the one entered
		System.out.println("the expected name on top of menu dropdown is: "+expectedusername);
		System.out.println("the actual name appearing on top of menu dropdown is: "+actualusername);
		if (actualusername.equalsIgnoreCase(expectedusername)) {
			System.out.println("Script passed for username"); 
		} else {
			System.out.println("Script failed for username");
		}
		System.out.println("--------------"); //
		System.out.println("expected menu displayed: 'My profile','My Settings','Developer Console','Logout','Switching to lightning Experience'"); //
		List<WebElement> dropDownList = driverSF.findElements(By.id("userNav-menuItems"));// we get all the items of the dropdown menu to in a list compare with expected
		System.out.print("actual menu displayed: "); // we print our list of dropdown menu
		for (WebElement item : dropDownList) {
			System.out.print((item.getText())+", ");
			}
	//	ba.closeBrowser(driverSF);
	}
	}

		

