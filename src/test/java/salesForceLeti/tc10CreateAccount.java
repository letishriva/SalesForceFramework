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

//Launch and Login 
//Click on Accounts link on the home page
//Click on the New button to create new account

public class tc10CreateAccount extends BaseAction {
//1.	Launch and Login 
//	Click on the New button to create new account
	
	@Test
	public static void CreateAccount () throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);

//2.		Click on Accounts link on the home page
		driverSF.findElement(By.id("Account_Tab")).click();// click on Accounts link
		Thread.sleep(2000);

// a pop up opens!! 
		driverSF.switchTo().activeElement();
		driverSF.findElement(By.id("tryLexDialogX")).click();
		Thread.sleep(2000);

//3.Click on the New button to create new account
		driverSF.findElement(By.id("createNewButton")).click();//click on create new Button
		driverSF.findElement(By.linkText("Account")).click();// click on create new account  
		
// Enter <Account name>  select type as Technology partner from drop down, set customer priority as high 
		driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/form/div/div[2]/div[3]/table/tbody/tr[2]/td[2]/div/input")).sendKeys("Leti Other");
		
		WebElement contentDropdown = driverSF.findElement(By.id("acc6")); // Dropdown with Technology partner
		contentDropdown.click();
		Select contentSelect = new Select(contentDropdown);
		contentSelect.selectByValue("Technology Partner");
		
		WebElement contentDropdown2 = driverSF.findElement(By.id("00NDn00000RRZyf")); // Dropdown customer priority
		contentDropdown.click();
		Select contentSelect2 = new Select(contentDropdown2);
		contentSelect2.selectByValue("High");
		Thread.sleep(2000);
		
		driverSF.findElement(By.name("save"));
		System.out.println("how to evaluate : New account page is displayed with account details??? ");
		Thread.sleep(5000);
	//	ba.closeBrowser(driverSF);
	}
}
