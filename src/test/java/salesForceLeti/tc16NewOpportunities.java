package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;

/*
1	Launch and Login 	Launch https://www.login.salesforce.com and provide positive <username> and <password> data to SalesForce Application. 	SalesForce login page is launched and application home page is logged in with correct username.
2	Click on opportunities link	Click on opportunities link from the main menu	Opportunities home page is displayed
3	Click on the New button to create new Opty	New Opportunity Edit page is displayed. Enter Opportunity Name,Account Name,Close Date,Stage,Probability,Lead Source , Primary Campaign Source and click on save button.	New Opportunity page is displayed with Opportunity details.
*/

public class tc16NewOpportunities extends BaseAction{
//1.	Launch and Login 
	
	@Test
	
	public static void newOpportunites () throws InterruptedException {
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
		

//	3	Click on the New button to create new Opty	New Opportunity Edit page is displayed. Enter Opportunity Name,Account Name,Close Date,Stage,Probability,Lead Source , Primary Campaign Source and click on save button.	New Opportunity page is displayed with Opportunity details.
		driverSF.findElement(By.name("new")).click(); // we enter correct username
		driverSF.findElement(By.id("opp3")).sendKeys("OpportunityNameTest"); //enter Opportunity Name
		driverSF.findElement(By.id("opp4")).sendKeys("AccountNameTest"); //Account Name 
		driverSF.findElement(By.id("opp9")).sendKeys("1/3/2023"); //Close Date
		driverSF.findElement(By.id("head_1_ep")).click();// click outside calendar
		//driver.findElement(By.id("opp9")).click(); 
		//driver.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[2]/div[2]/div[2]/table/tbody/tr[5]/td[5]")); // click on date
		WebElement dropdownStage = driverSF.findElement(By.id("opp11")); //Stage dropdown menu
		dropdownStage.sendKeys("Qualification");
		dropdownStage.click();
		driverSF.findElement(By.id("opp11")).click(); //Stage dropdown menu
		Thread.sleep(2000);		
		WebElement probability = driverSF.findElement(By.id("opp12"));
		probability.clear();//Probability clear first then sendkeys
		probability.sendKeys("40");
		WebElement dropdownLeadSource = driverSF.findElement(By.id("opp6")); //Lead Source dropdown
		dropdownLeadSource.sendKeys("Web");
		dropdownLeadSource.click();
		driverSF.findElement(By.id("opp17")).sendKeys("PrimaryCampaignSourceTest"); //Primary Campaign Source
		driverSF.findElement(By.name("save")).click(); // click on save Button
		WebElement errorMessage = driverSF.findElement(By.id("errorDiv_ep"));
			System.out.println("New Opportunity page is not displayed with Opportunity details." + errorMessage.getText()); 
		
		// HOW TO ASSESS WHEN IT IS FAILING ?
	
			
	//	ba.closeBrowser(driverSF);
	}
		
}