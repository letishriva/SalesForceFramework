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
1. Launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application. 	SalesForce login page is launched and application home page is logged in with correct username.
2. click on opportunities link	Click on opportunities link from the main menu	Opportunities home page is displayed
3	Click on Quarterly Summary link  	Click on Quarterly Summary link and choose list of values for Interval such as Current FQ,Next FQ and Include such as All,Open or closed Opportunities	Report Page with the Opportunities that satisfies the search criteria will be displayed.
*/

public class tc19SQuarterlySummaryReport extends BaseAction {

//1.	Launch and Login 
	
	@Test
		public static void QuarterlySummaryReport () throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);

//2.	Click on Opportunities link
		driverSF.findElement(By.id("Opportunity_Tab")).click();
		Thread.sleep(2000);

		
// a pop up opens!! 
		driverSF.switchTo().activeElement();
		driverSF.findElement(By.id("tryLexDialogX")).click();
		Thread.sleep(2000);		
		

//		3	Click on Quarterly Summary link  	Click on Quarterly Summary link and choose list of values for Interval such as Current FQ,Next FQ and Include such as All,Open or closed Opportunities	Report Page with the Opportunities that satisfies the search criteria will be displayed.
// 1st TEST WITH CURRENT FQ and ALL opportunities
		WebElement dropdownInterval = driverSF.findElement(By.id("quarter_q"));// Click on Interval dropdown
		Select selectAll = new Select(dropdownInterval);
		selectAll.selectByValue("current"); //select current FQ
		driverSF.findElement(By.id("open")).click();// click on All 
		Thread.sleep(2000);
		driverSF.findElement(By.name("go")).click();// click on run 
	
//		Click on Opportunities link to return to test another combo
		driverSF.findElement(By.id("Opportunity_Tab")).click();
		Thread.sleep(2000);
		
// 2nd TEST WITH NEXT FQ and Open opportunities
		WebElement dropdownInterval2 = driverSF.findElement(By.id("quarter_q"));
		Select selectAll2 = new Select(dropdownInterval2);
		selectAll2.selectByValue("next1"); //select Next FQ
		WebElement dropdownInclude = driverSF.findElement(By.id("open")); // open is the id of dropdown include
		Select selectOpen = new Select(dropdownInclude);
		selectOpen.selectByValue("open"); //select Open opportunities
		Thread.sleep(2000);
		driverSF.findElement(By.name("go")).click();// click on run 
		 
//		Click on Opportunities link to return to test another combo
		
//3rd TEST WITH NEXT FQ and Closed opportunities
		driverSF.findElement(By.id("Opportunity_Tab")).click();
		Thread.sleep(2000);
		
		WebElement dropdownInterval3 = driverSF.findElement(By.id("quarter_q"));
		Select selectAll3 = new Select(dropdownInterval3);
		selectAll3.selectByValue("next1"); //select Next FQ
		WebElement dropdownInclude2 = driverSF.findElement(By.id("open")); // open is the id of dropdown include
		Select selectClosed = new Select(dropdownInclude2);
		selectClosed.selectByValue("closed"); //select  Closed Opportunities
		
		
		Thread.sleep(2000);
		driverSF.findElement(By.name("go")).click();// click on run 
	 
				
		Thread.sleep(2000);	

//		ba.closeBrowser(driverSF);
		  
		  // PASSES - How to assess ??? for each test ? with a screenshot?
		}

	}
		
