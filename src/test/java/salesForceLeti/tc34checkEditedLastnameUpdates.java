package salesForceLeti;
import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;
/*
1	Click Home Tab	Click on Home Tab	Home page should be displayed.                                    
2	Click on the 'FirstName LastName' link in the home page	Click on the FirstName LastName link at the left had side of the home page	The 'User:FirstName LastName' page should be displayed.                                                                                                
3	Click on the 'Edit Profile' icon near Contact	Click on the 'Edit Profile' icon near Contact	The 'Edit Profile' popup should be displayed with the 'Contact' tab selected.
4	Click on the 'About' tab	Click on the 'About' tab in the popup.	Focus shoud be in the First Name field.
5	Edit 'Last Name' field.	Edit the Last Name to 'Abcd' and click Saveall button	1. The 'Edit Profile' popup should be closed.            2. Verify that the updated LastName of the account holder is displayed at the top left hand side of the page.                                                                     3. Verify that the 'User menu for FirstName LastName' menu button shows the updated Last Name, at the top right hand side of the page.          4. Verify that the 'User:FirstName LastName' page has the updated LastName.
                                                                                    2. This page should be same as the 'My Profile' page.
*/
public class tc34checkEditedLastnameUpdates extends BaseAction{
//1.	Launch and Login 

	@Test
	public static void checkEditedLastnameUpdates () throws InterruptedException, AWTException {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);

		driverSF.findElement(By.id("home_Tab")).click();
		Thread.sleep(2000);
		String expectedPage = "Salesforce - Developer Edition";
		String actualPage = driverSF.getTitle();
		Assert.assertEquals(actualPage, expectedPage, "Home page passed");
		Thread.sleep(2000);

		// a pop up opens!! 
		driverSF.findElement(By.id("tryLexDialog"));
		driverSF.switchTo().activeElement();
		driverSF.findElement(By.id("tryLexDialogX")).click();
		Thread.sleep(3000);		

//2	Click on the 'FirstName LastName' link in the home page	Click on the FirstName LastName link at the left had side of the home page	The 'User:FirstName LastName' page should be displayed. 	
		WebElement link = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td[1]/div/div[2]/div[2]/div[2]/div[1]/div/a")); 
		String actualLinkName = link.getText();
		String expectedLinkName = "Leti Casta";
		Assert.assertEquals(actualLinkName, expectedLinkName, "User First and Last Name displayed in link");
		link.click();
		Thread.sleep(4000);
		String expectedPage2 = "User: Leti Casta ~ Salesforce - Developer Edition";
		String actualPage2 = driverSF.getTitle();
		Assert.assertEquals(actualPage2, expectedPage2, "User Contact page passed");
		Thread.sleep(2000);
		
		
//3	Click on the 'Edit Profile' icon near Contact	Click on the 'Edit Profile' icon near Contact	The 'Edit Profile' popup should be displayed with the 'Contact' tab selected.
		WebElement iconEditProfile = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/div[2]/div[1]/h3/div/div/a/img")); 
		iconEditProfile.click();
		Thread.sleep(4000);
//		WebElement contactTab = driver.findElement(By.partialLinkText("Contact")); //contact tab displayed 
//		Assert.assertSame(contactTab, "contactTab");
		
// 4	Click on the 'About' tab	Click on the 'About' tab in the popup.	Focus shoud be in the First Name field.
		
		WebElement newFrame = driverSF.findElement(By.xpath("//iframe[@id='contactInfoContentId']")); // search the frame to switch to it
		driverSF.switchTo().frame(newFrame);
		Thread.sleep(2000);
		System.out.println("we are about to click on AboutTab");
		WebElement aboutTab = driverSF.findElement(By.id("aboutTab")); 
		aboutTab.click();
		Thread.sleep(2000);
		
// 5	Edit 'Last Name' field.	Edit the Last Name to 'Abcd' and click Saveall button	1. The 'Edit Profile' popup should be closed.            2. Verify that the updated LastName of the account holder is displayed at the top left hand side of the page.                                                                     3. Verify that the 'User menu for FirstName LastName' menu button shows the updated Last Name, at the top right hand side of the page.          4. Verify that the 'User:FirstName LastName' page has the updated LastName.
// 2. This page should be same as the 'My Profile' page.
		WebElement updatedLastNameBox = driverSF.findElement(By.id("lastName"));
		updatedLastNameBox.click();
		updatedLastNameBox.clear();
		
/*		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
		robot.keyPress(KeyEvent.VK_BACK_SPACE);
		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
*/
		updatedLastNameBox.sendKeys("Abcd");
		driverSF.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div/input[1]")).click();
//  Verify that the last name has been updated with last name Abcd
		driverSF.switchTo().parentFrame();
		WebElement nameUpdated = driverSF.findElement(By.id("tailBreadcrumbNode")); 
		String actualNameUpdated = nameUpdated.getText();
		String expectedNameUpdated = "Leti Abcd ";
		Assert.assertEquals(actualNameUpdated, expectedNameUpdated, "User First and Last Name displayed in link has been updated");
		
// FOR FUTURE TESTS, WE NEED TO REVERT BACK THE NAME TO CASTA!
		WebElement iconEditProfile2 = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[2]/div[2]/div[1]/h3/div/div/a/img")); 
		iconEditProfile2.click();
		Thread.sleep(4000);
		WebElement newFrame2 = driverSF.findElement(By.xpath("//iframe[@id='contactInfoContentId']")); // search the frame to switch to it
		driverSF.switchTo().frame(newFrame2);
		Thread.sleep(2000);
		System.out.println("we are about to click on AboutTab");
		WebElement aboutTab2 = driverSF.findElement(By.id("aboutTab")); 
		aboutTab2.click();
		Thread.sleep(2000);
		WebElement updatedLastNameBox2 = driverSF.findElement(By.id("lastName"));
		updatedLastNameBox2.click();
		updatedLastNameBox2.clear();
		updatedLastNameBox2.sendKeys("Casta");
		driverSF.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div/input[1]")).click();
		driverSF.switchTo().parentFrame(); 
//		ba.closeBrowser(driverSF);
	}		
}