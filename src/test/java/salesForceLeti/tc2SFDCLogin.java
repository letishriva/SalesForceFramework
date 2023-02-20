package salesForceLeti;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
// For LOGIN, This is one way but I also created reusable methods for all the other test cases in [LoginUtility] and [BaseAction] classes
// I wanted to see if I can use this full login as one reusable method loginToSalesForce >> CF tc20leadsTab.java (it also works)
import salesForceUtility.LoginUtility;

public class tc2SFDCLogin extends BaseAction{
    @Test
    public static void loginToSalesForce() throws Exception  {
    	WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
        Thread.sleep(2000);
        String expected = "Home Page ~ Salesforce - Developer Edition";
        String actual = driver.getTitle();
        Assert.assertEquals(actual, expected, "login passed");

    }

}