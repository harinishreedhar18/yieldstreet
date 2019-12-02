package swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


public class LoginUser {
	
	WebDriver driver;
	final String URL = "https://www.saucedemo.com";
	final int TIMEOUT = 5;
	
	public void login(String username, String password){
	  
	    // Provide credentials and login
	    driver.findElement(By.id("user-name")).sendKeys(username);
	    driver.findElement(By.id("password")).sendKeys(password);
	    driver.findElement(By.className("btn_action")).click();
	}
	
	
	public void openPage(String driverPath){
		System.out.println("Initializing browser");
		//TODO: Modify path to point to the chromedriver for selenium
		System.setProperty("webdriver.chrome.driver", driverPath);
		// Navigate to the website
		driver=new ChromeDriver();
		driver.get(URL);  
	    driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
	    System.out.println("Reading title: "+ driver.getTitle());
	}
	
	public void tearDown(){
		if(driver!=null){
			driver.quit();
		}
	}
}
