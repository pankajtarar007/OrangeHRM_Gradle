package com.OrangeHRM;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


import org.testng.annotations.BeforeTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LoginTest {
	
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
  public void addUser() throws InterruptedException {
	  
	  Random rand = new Random(); 
	 int randName_int1 = rand.nextInt(10000);
	  
	 String name = "Pankaj1_"+randName_int1;
	  driver.findElement(By.linkText("Admin")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.id("btnAdd")).click();
	  driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Fiona Grace");
	  driver.findElement(By.id("systemUser_userName")).sendKeys(name);
	  driver.findElement(By.id("systemUser_password")).sendKeys("admin123");
	  driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("admin123");
	  Thread.sleep(1000);
	  driver.findElement(By.id("btnSave")).click();
	  
	  Thread.sleep(5000);
	  driver.findElement(By.id("searchSystemUser_userName")).click();
	  driver.findElement(By.id("searchSystemUser_userName")).sendKeys(name);
	  driver.findElement(By.id("searchBtn")).click();
	  	  
	  Thread.sleep(2000);
	  String actname = driver.findElement(By.xpath("//table//td[2]/a")).getText();
	  Assert.assertEquals(name, actname);
	
  }
  
  @Test(priority=2)
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

 /* String pass1 = "admin123";
  byte[] encodedBytes = Base64.encodeBase64(pass1.getBytes());
  byte[] password = Base64.decodeBase64(encodedBytes);*/
  
  //driver.findElementByName("txtPassword").sendKeys(new String(password));
  
  //driver.findElementByXPath("//td/a[text()='"+EnteredValue+"']//parent::td/../td/input").click();
  //driver.findElementByXPath("//a[text()='"+EnteredValue+"']//parent::td/../td[contains(text(), 'Admin')]//parent::td/preceding-sibling::td/input").click();
}
