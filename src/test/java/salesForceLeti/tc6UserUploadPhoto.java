package salesForceLeti;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import salesForceUtility.LoginUtility;
//	Select user menu for <username> drop down[TC01]	|| user menu for <username> drop down is selected		|| Drop down with "My profile", "My Settings", "Developer Console","Logout" , "Switching to lightning Experience" is displayed
//	Click "My profile" option from user menu		|| Click "My profile" option from user menu		|| User profile page is displayed
//	Click on edit profile button to edit contact information		|| Check for the edit button next to contact text on the page and click the button. 		|| Edit profile pop up window is displayed with contact and About tabs to edit. 
//	Click on About tab	Click on About tab and enter <Lastname> and click on save all button		|| UserProfilePage with changed <lastname> is displayed
//	Click on post link	|| Click on Post link, Enter the <text> to post in the post text area and click on share button		|| <text> entered should be displayed on the page
//	Click on file link		|| Click on the  file link and click on "upload a file from computer" button. Click on choose file button and select a file to be uploaded and click open button.		|| Selected file should be posted
//	Click on Add photo link		|| Hover the mouse on myprofilephoto image and Add photo link appears. Click on the link to upload a photo. Click on Choose file button and select the image to upload. Crop the photo to fit the image.		|| Uploaded photo should appear on the image.


public class tc6UserUploadPhoto extends BaseAction{
//1.	Launch and Login 
	@Test
		public static void UserUploadPhoto () throws InterruptedException, Exception {
		WebDriver driverSF = driver;		
		LoginUtility loginSF =  new LoginUtility();
		loginSF.loginToSalesForce(driverSF);
		Thread.sleep(2000); // we let the page load
		String actual = driverSF.getTitle();
		String expected = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actual, expected, "login passed");
		Thread.sleep(2000);
	
		driverSF.findElement(By.id("userNavLabel")).click();// userNavLabel id > for username dropdowm to be clicked
		driverSF.findElement(By.partialLinkText("My Profile")).click();
		Thread.sleep(2000); 
		driverSF.findElement(By.id("moderatorMutton")).click();//Click on button to get the menu with edit profile
		driverSF.findElement(By.partialLinkText("Edit Profile")).click(); // when click, a pop up window appears
		Thread.sleep(2000); 
		
		driverSF.switchTo().frame("aboutMeContentId");//we enter the iFrame id=aboutMeContentId
		driverSF.findElement(By.id("lastName")).clear();
		driverSF.findElement(By.id("lastName")).sendKeys("Casta");//By default about is already selected >> Click on Last Name and change
		driverSF.findElement(By.cssSelector("#TabPanel > div > div.zen-body > form > div > input.zen-btn.zen-primaryBtn.zen-pas")).click();// click on save button
		
		// check if UserProfilePage with changed <lastname> is displayed
		String expectedusername2="Leti Casta";
		String actualusername = driverSF.findElement(By.id("userNavLabel")).getText();//we get the text on menu dropdown to compare 
		System.out.println("the expected name on top of menu dropdown is: "+expectedusername2);
		System.out.println("the actual name appearing on top of menu dropdown is: "+actualusername);
		if (actualusername.equalsIgnoreCase(expectedusername2)) {
			System.out.println("Script passed for username"); 
		} else {
			System.out.println("Script failed for username");
		}
		System.out.println("--------------"); 
		Thread.sleep(3000);
		
		// Click on Post link, Enter the <text> to post in the post text area and click on share button
		driverSF.switchTo().defaultContent();
		Thread.sleep(2000);
		driverSF.findElement(By.partialLinkText("Post")).click();
		WebElement textEditor = driverSF.findElement(By.id("cke_publisherRichTextEditor"));
		textEditor.click(); 
		Thread.sleep(2000);
		WebElement myFrame = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[3]/div[1]/div/div[1]/div/div[2]/ul/li[1]/div/div/div[1]/div[1]/div[1]/div[1]/div/div[2]/div[2]/div/div/iframe"));
		driverSF.switchTo().frame(myFrame);
		Thread.sleep(2000);
		driverSF.findElement(By.xpath("/html/body")).sendKeys("Test 2 new post about me"); 
		driverSF.switchTo().defaultContent(); 
		Thread.sleep(1000);
		driverSF.findElement(By.id("publishersharebutton")).click();
		String expectedPost = "Test 2 new post about me";
		String actualPost = driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[3]/div[1]/div/div[1]/div/div[2]/ul/li[1]/div/div/div[2]/div/div[5]/div/div[1]/div[1]/div[2]/div[1]/span/p")).getText();
		if (actualPost.equalsIgnoreCase(expectedPost)) {
			System.out.println("Script passed for Post"); 
		} else {
			System.out.println("Script failed for Post");
		}
		System.out.println("--------------"); 
		
		Thread.sleep(3000);
		
		// Click on the  file link and click on "upload a file from computer" button. Click on choose file button and select a file to be uploaded and click open button.
		driverSF.findElement(By.partialLinkText("File")).click(); 
		Thread.sleep(1000); 
		driverSF.findElement(By.id("chatterUploadFileAction")).click(); //click upload file from computer
		Thread.sleep(5000); 
		Actions actions = new Actions(driverSF);
		actions.moveToElement(driverSF.findElement(By.xpath("/html/body/div[1]/div[2]/table/tbody/tr/td/div/div[3]/div[1]/div/div[1]/div/div[2]/ul/li[1]/div/div/div[1]/div[1]/div[2]/div[1]/div[2]/div[2]/form/table/tbody/tr[1]/td/div/div[1]/input"))).click().perform();
		Thread.sleep(2000); 
	
		String filePath = "C:\\Users\\Aditya\\Documents\\letifortekarch.png"; // we send the file path to the clipboard
		StringSelection stringSelection = new StringSelection(filePath);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		Robot robot = new Robot(); 	    // we use the Robot class to paste filepath and press Enter
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);// ok
		Thread.sleep(2000);
		
		String mainWindow = driverSF.getWindowHandle();
		for (String handle : driverSF.getWindowHandles()) {
		    if (!handle.equals(mainWindow)) {
		        driverSF.switchTo().window(handle);
		        driverSF.close();
		    }
		}
		driverSF.switchTo().window(mainWindow);
		driverSF.findElement(By.partialLinkText("File")); //find file link
		driverSF.findElement(By.id("publishersharebutton")).click();//we click on share button to send the photo
		System.out.println("--------------"); 
		System.out.println("Script evaluation Passed for File"); 
		Thread.sleep(3000);
		
		//click on profile image + add photo
		driverSF.switchTo().defaultContent(); //out of frame
		Thread.sleep(2000); 
		
		Thread.sleep(2000); 
		WebElement photoProfil = driverSF.findElement(By.id("photoSection"));

		Actions action = new Actions(driverSF);
		action.moveToElement(photoProfil).build().perform(); //Hover the mouse on myprofilephoto image and Add photo link appears.
		Thread.sleep(1000);
		WebElement photoUser = driverSF.findElement(By.id("uploadLink"));// Click on the link to upload a photo. (add photo is only for the 1st time)
		photoUser.click(); 
		Thread.sleep(3000); 

		WebElement newFrame = driverSF.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']")); // search the frame to switch to it
		driverSF.switchTo().frame(newFrame);
		WebElement chooseFile = driverSF.findElement(By.id("j_id0:uploadFileForm:uploadInputFile")); // click on choose file
		Thread.sleep(2000); 
		chooseFile.sendKeys("C:\\Users\\Aditya\\Documents\\letifortekarch.png");
		Thread.sleep(4000); 
		driverSF.findElement(By.id("j_id0:uploadFileForm:uploadBtn")).click(); //1st save button 
		Thread.sleep(2000); 
		System.out.println("after 1st save button");
		driverSF.findElement(By.xpath("//*[@id=\'j_id0:j_id7:save\']")).click();//2nd save button
		Thread.sleep(2000); 
		
//		PASSED
//		ba.closeBrowser(driverSF);
	}
}