package salesForceLeti;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1. Launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application. 	SalesForce login page is launched and application home page is logged in with correct username.
2. click on opportunities link	Click on opportunities link from the main menu	Opportunities home page is displayed
3. Click on Opportunity Pipeline link  	Click on Opportunity Pipeline link  under Reports.	Report Page with the Opportunities that are pipelined will be displayed.
*/

//@Listeners 


public class tc17OpportunityReport extends BaseAction {
//1.	Launch and Login 
		@Test
		public static void OpportunityReport () throws Exception {
		//BaseAction ba = new BaseAction();
		logger.info("inside TC17 Opportunity Report");
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
		

// 3. Click on Opportunity Pipeline link  	Click on Opportunity Pipeline link  under Reports.	Report Page with the Opportunities that are pipelined will be displayed.
		driverSF.findElement(By.partialLinkText("Opportunity Pipeline")).click(); // Click on Opportunity Pipeline link 
		System.out.println("Report page with Opportunities that are pipelined is displayed");
		Thread.sleep(2000);		
		String actualPage = driverSF.getTitle();
		String expectedPage="Opportunity Pipeline ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualPage, expectedPage, "Report Page with Opportunities that are pipelined displayed");
		
		//ba.closeBrowser(driverSF);
		//BaseAction.driver.close();
		  
		  // done and assessed
		}
				
	}