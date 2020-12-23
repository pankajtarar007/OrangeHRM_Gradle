package com.OrangeHRM.UI;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;

public class Example2_MobileBrowser {
	ChromeDriver driver;
  @Test
  public void SignOn() {
		driver.get("https://www.borrowlenses.com/");
		

  }
  @BeforeTest
  public void LaunchBrowser() {
		String absolutePath=System.getProperty("user.dir");
		String filepath=absolutePath+"\\chromedriver.exe";
		
		System.setProperty("webdriver.chrome.driver",filepath);
		
        ChromeOptions iPhoneOption = new ChromeOptions();

        driver = new ChromeDriver(iPhoneOption);
        Dimension d = new Dimension(640, 960);
        driver.manage().window().maximize();
        driver.manage().window().setSize(d);

  }

  @AfterTest
  public void CloseBrowser() {
	//	driver.quit();
  }

}
