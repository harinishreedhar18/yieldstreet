package swaglabs;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LockedOutUser extends LoginUser {

	
	@BeforeClass
	@Parameters({ "driver-path" })
	public void openPage(String driverPath){
		super.openPage(driverPath);
	}
	
	@Test
	public void validateUser(){
		
		Assert.assertEquals(driver.getTitle(),"Swag Labs");

		login("locked_out_user","secret_sauce");
	    String msg = driver.findElement(By.tagName("h3")).getText();
	    Assert.assertEquals(msg,"Epic sadface: Sorry, this user has been locked out.");
	}

	@AfterClass
	public void tearDown(){
		super.tearDown();
	}

}
