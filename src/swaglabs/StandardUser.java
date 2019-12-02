package swaglabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StandardUser extends LoginUser{
	
	@BeforeClass
	@Parameters({ "driver-path" })
	public void openPage(String driverPath){
		super.openPage(driverPath);
	}
	
	@Test
	public void validateUser(){
		
		login("standard_user","secret_sauce");
		try{
			List <WebElement> invItems = driver.findElements(By.className(("inventory_item")));
			Iterator <WebElement> invItemsIter = invItems.iterator();
			while(invItemsIter.hasNext()){
				WebElement invItem = invItemsIter.next();
				String prodName = invItem.findElement(By.className("inventory_item_name")).getText();
	//			System.out.println("Found: "+prodName);
				if(prodName.contains("T-Shirt")){
					System.out.println("Matched: "+prodName);
					invItem.findElement(By.className("btn_inventory")).click();
				}
			}
			System.out.println("Found all T-Shirts");
			// Show cart
			driver.findElement(By.className("shopping_cart_container")).click();
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
			
			// Checkout
			driver.findElement(By.className("checkout_button")).click();
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
			
			// Your Information
			driver.findElement(By.id("first-name")).sendKeys("Standard");
			driver.findElement(By.id("last-name")).sendKeys("User");
			driver.findElement(By.id("postal-code")).sendKeys("10001");
			driver.findElement(By.className("cart_button")).click();
			driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
			
			// Finish
			driver.findElement(By.className("cart_button")).click();
			System.out.println("Test case 2 validated. standard_user placed an order.");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void tearDown(){
		super.tearDown();
	}
}
