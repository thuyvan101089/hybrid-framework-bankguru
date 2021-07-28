package com.bankguru.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Level_01_Register_Login_Repeat_Yourself {
	
	WebDriver driver;
	
	String email,username, password, loginPageURL;
	
	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void initBrowser() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe" );	
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		loginPageURL = driver.getCurrentUrl();
			
	
	}
	
	@Test
	public void Login_01_Register_To_System() {
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(getRandomEmail());
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		username = driver.findElement(By.xpath(" //td[text()='User ID :']/following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
	}
	
	@Test
	public void Login_02_Login_To_System() {
		
		driver.get(loginPageURL);
		
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		String welcomeMessage = driver.findElement(By.xpath("//marquee[@class='heading3']")).getText();
		Assert.assertEquals(welcomeMessage, "Welcome To Manager's Page of Guru99 Bank");
	}
	
	@AfterClass
	
	public void cleanBrowser() {
		driver.quit();
	}
	public String getRandomEmail() {
		Random rand = new Random();
		
		return "testing" + rand.nextInt(99999) + "@live.com";
		
	}

}
