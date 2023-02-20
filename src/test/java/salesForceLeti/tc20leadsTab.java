package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import salesForceUtility.LoginUtility;

/*
1	Click leads tab link from Home Page	Click leads tab link from Home Page	Link should navigate to Leads Home page
*/
public class tc20leadsTab extends BaseAction {

    @Test
    public static void leadsTab() throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
        Thread.sleep(4000);
        
		
    	driverSF.findElement(By.id("Lead_Tab")).click();
    	Thread.sleep(3000);
    	// a pop up opens!! 
    	driverSF.switchTo().activeElement();
    	driverSF.findElement(By.id("tryLexDialogX")).click();
    	Thread.sleep(3000);
        
        String expected = "Leads: Home ~ Salesforce - Developer Edition";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected, "login and Leads Home page passed");
        

    }
}
		  
// PASS and ASSESSED
