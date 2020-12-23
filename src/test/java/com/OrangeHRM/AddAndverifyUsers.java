package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class AddAndverifyUsers {
	
	public ChromeDriver driver;

  @BeforeTest
  public void launchBrowser() {
	  WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test(priority=0)
  public void login() throws InterruptedException {
	  
	  String pass1="admin123";
	  
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/auth/login");
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys(pass1);
		driver.findElement(By.id("btnLogin")).click();
		driver.findElement(By.linkText("Dashboard")).isDisplayed();
  }

  @Test(priority=1)
  public void logout() throws InterruptedException {
	driver.findElementByPartialLinkText("Welcome").click();
	Thread.sleep(3000);
	driver.findElementByLinkText("Logout").click();
	
	driver.findElementById("logInPanelHeading").isDisplayed();
	String actualtxt = driver.findElementById("logInPanelHeading").getText();
	String cururl= driver.getCurrentUrl();
	String title = driver.getTitle();
	
	Assert.assertEquals("LOGIN Panel", actualtxt);
	Assert.assertEquals("https://opensource-demo.orangehrmlive.com/index.php/auth/login", cururl);
	Assert.assertEquals("OrangeHRM", title);
}
  
  @AfterTest
  public void closeBrowser() {
	  driver.quit();
  }

}
