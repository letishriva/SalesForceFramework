package salesForceLeti;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import salesForceUtility.LoginUtility;
/*
1	Click leads tab link from Home Page	Click leads tab link from Home Page	Link should navigate to Leads Home page
2	click drop down list	click on the drop down list handle	
    List should drop down and should show the following contents: All Open Leads / My Unread Leads / Recently Viewed Leads / Today's Leads.  
*/
public class tc21leadsSelectView extends BaseAction {
    @Test
    public static void leadsSelectView() throws InterruptedException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
    	Thread.sleep(2000); // we let the page load
    	String actual = driverSF.getTitle();
    	String expected = "Home Page ~ Salesforce - Developer Edition";
    	Assert.assertEquals(actual, expected, "login passed");
    	Thread.sleep(2000);
 //1	Click leads tab link from Home Page	Click leads tab link from Home Page	Link should navigate to Leads Home page
    	driverSF.findElement(By.id("Lead_Tab")).click();
    	Thread.sleep(2000);	
		
    	// a pop up opens!! 
    			driverSF.switchTo().activeElement();
    			driverSF.findElement(By.id("tryLexDialogX")).click();
    			Thread.sleep(2000);		
    			

  // 2 click drop down list (id="fcf")- List should drop down and should show the following contents: All Open Leads / My Unread Leads / Recently Viewed Leads / Today's Leads.  
    	List<String> expectedLeadsList = Arrays.asList("All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads");
    	List<WebElement> actualLeadsList = driverSF.findElements(By.id("fcf"));// we get all the items of the dropdown menu to in a list compare with expected
    	System.out.print("actual menu displayed: "); // we print our list of dropdown menu
    	for (WebElement item : actualLeadsList) {
    		System.out.print(item.getText() + ", ");
    	}
 //   	assert actualLeadsList.size() == expectedLeadsList.size() : "The menu content is not the same size as expected";
 //   	for (int i = 0; i < actualLeadsList.size(); i++) {
 //   		assert actualLeadsList.get(i).getText().equals(expectedLeadsList.get(i)) : "The menus have different values at index " + i;
 //   		System.out.println(actualLeadsList);
 //   	}
    	Thread.sleep(3000);
  //  	ba.closeBrowser(driverSF);
    }
}