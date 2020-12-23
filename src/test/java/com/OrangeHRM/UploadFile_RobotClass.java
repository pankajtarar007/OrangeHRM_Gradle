package com.OrangeHRM;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFile_RobotClass {
	String URL = "https://gofile.io/?t=uploadFiles";
	WebDriver  driver;
	//WebDriverWait wait = new WebDriverWait(driver,10);
	@BeforeTest
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
		// create Edge instance and maximize it
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
	}

	@AfterTest
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testUpload() throws InterruptedException{		
		
		String absolutePath=System.getProperty("user.dir");
		String filepath=absolutePath+"\\Images\\Image.jpg";
		System.out.println(filepath);
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.id("dropZoneBtnSelect")).click();
		uploadFile(filepath);
		Thread.sleep(2000);
	}

	public static void uploadFile(String fileLocation) {
		try {
			//Upload file through Robot API
			StringSelection ss = new StringSelection(fileLocation);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			//native key strokes for CTRL, V and ENTER kxeys
			Robot robot = new Robot();
			robot.delay(1000);     
			// Press CTRL+V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);    
			// Release CTRL+V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			//Press Enter
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);	
			robot.delay(1000);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

}

