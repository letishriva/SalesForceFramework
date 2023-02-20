
package salesForceLeti;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import salesForceUtility.LoginUtility;
/*
 	Click leads tab link from Home Page	Link should navigate to Leads Home page	Leads homepage should be displayed
2	Click New button	click on the new button displayed in the Recent Leads frame	New Lead creation page should open
3	enter last name	enter "ABCD" in the last name field 	ABCD is entered
4	enter company name 	enter "ABCD" in the company name field	ABCD is entered
5	Click save button	click on the save button	new lead should be saved and the newly created lead view page should be opened

*/
public class tc24newButtonLeads extends BaseAction {
	    @Test
    public static void newButtonLeads() throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);
    	
//2	Click New button	click on the new button displayed in the Recent Leads frame	New Lead creation page should open  	
		
    	driverSF.findElement(By.id("Lead_Tab")).click();
    	Thread.sleep(3000);
    	// a pop up opens!! 
    	driverSF.switchTo().activeElement();
    	driverSF.findElement(By.id("tryLexDialogX")).click();
    	Thread.sleep(3000);
		
		
		driverSF.findElement(By.name("new")).click(); // new Button
		Thread.sleep(3000);
    	String expected2 = "Lead Edit: New Lead ~ Salesforce - Developer Edition";
    	String actual2 = driverSF.getTitle();
    	
    	Assert.assertEquals(actual2, expected2, "New Lead Creation page passed");
    	
    	
//3	enter last name	enter "ABCD" in the last name field 	ABCD is entered 	
    	driverSF.findElement(By.id("name_lastlea2")).sendKeys("ABCD");
    	
// 4	enter company name 	enter "ABCD" in the company name field	ABCD is entered	
    	driverSF.findElement(By.id("lea3")).sendKeys("ABCD");
    	
    	
// 5	Click save button	click on the save button	new lead should be saved and the newly created lead view page should be opened    	
    	driverSF.findElement(By.name("save")).click();
		Thread.sleep(3000);
	   	String expected3 = "Lead: ABCD ~ Salesforce - Developer Edition";
    	String actual3 = driverSF.getTitle();
    	
    	Assert.assertEquals(actual3, expected3, "New Lead Creation page saved and opened");
//    	ba.closeBrowser(driverSF);
    	
       }
   }
// PASS