
package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import salesForceUtility.LoginUtility;

/*
1	Click leads tab link from Home Page	Link should navigate to Leads Home page	Leads homepage should be displayed
2	select Todays Leads from the view drop down	click on Today's Leads from the view drop down click on go button	Todays's Lead page should be displayed.
*/
public class tc23todaysLeads extends BaseAction {
    @Test
    public static void todaysLeads() throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);
		
    	driverSF.findElement(By.id("Lead_Tab")).click();
    	Thread.sleep(3000);
    	// a pop up opens!!
    	driverSF.switchTo().activeElement();
    	driverSF.findElement(By.id("tryLexDialogX")).click();
    	Thread.sleep(3000);

    	String expected2 = "Leads: Home ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	Assert.assertEquals(actual2, expected2, "login and Leads Home page passed");
    	
//    	2	select Todays Leads from the view drop down	click on Today's Leads from the view drop down click on go button	Todays's Lead page should be displayed.
 	
		WebElement leadsMenu = driverSF.findElement(By.id("fcf"));
		Select todaysLeads = new Select(leadsMenu);
		todaysLeads.selectByVisibleText("Today's Leads");
		
    	driverSF.findElement(By.name("go")).click();

		Thread.sleep(3000);
	//    ba.closeBrowser(driverSF);
		
		// How To assess ? Should I take a screenshot ???
    	
    }
}
