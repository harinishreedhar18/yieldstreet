package swaglabs;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.Iterator;
import java.util.List;

public class ProblemUser extends LoginUser{

	@BeforeClass
	@Parameters({ "driver-path" })
	public void openPage(String driverPath){
		super.openPage(driverPath);
	}
	
	@Test
	public void validateUser(){
		
		login("problem_user","secret_sauce");
		
		boolean verified = true;
		try {
			List <WebElement> imgElements = driver.findElements(By.tagName("img"));
			Iterator <WebElement> imgElIter = imgElements.iterator();
			while(imgElIter.hasNext()){
				WebElement imgEl = imgElIter.next();
				if(verifyImage(imgEl)){
					verified = false;
					break;
				}
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		if(verified){
			System.out.println("Test case 3 validated. Images did not load for problem_user.");
		}
	}
	
	public static boolean verifyImage(WebElement imgEl){
		
		try{
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgEl.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200){
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	@AfterClass
	public void tearDown(){
		super.tearDown();
	}
	
}
