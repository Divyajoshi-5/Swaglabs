package techflare.swag;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeMethod;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Login_pageTest {
	WebDriver driver;
	Login_page lp;
	Productpage qa;
	//ExtentReports extent;
	//ExtentTest test;
	
	
  @Test(priority=1)
  public void valid_login1() {
	  lp.login("standard_user", "secret_sauce");
	  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "");
	  System.out.println("Login successfully");
	
	  qa=new Productpage(driver);
	  qa.Logout();
	  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/index.html"), "user does not logout");
	  System.out.println("user successfully loggedin");
  }
  
	 
	  
	  
	  
	 // Test.log(Status.PASS, "User logged in successfully");
	  
	/*  String expected="https://www.saucedemo.com/v1/inventory.html";
	  String actual=driver.getCurrentUrl();
	  Assert.assertEquals(actual, expected, "Login failed");
	  System.out.println("Login successfull");*/
  
  
  @Test(priority=2)
  public void invalid_login2() {
	  lp.login("div", "123");
	  
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "user logedin");
	  System.out.println("User  does not logged in successfully");
	    
	 // test.log(Status.PASS, "User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
	  System.out.println("Correct error message");
  }  
  @Test(priority = 3)
  public void invalid_login3() {
	  lp.login("divya", "secret_sauce");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	// test.log(Status.PASS, "User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
	  System.out.println("Correct error message");
	    
  }
  @Test(priority=4)
  public void invalid_login4() {
	  lp.login("standard_user", "divya");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	//  test.log(Status.PASS, "User  does not logged in successfully");
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
	  System.out.println("Correct error message");
  }
	  @Test(priority = 5)
	  public void invalid_login5() {
	  lp.login("", "secret_sauce");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	 // test.log(Status.PASS, "User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username is required"), "Wrong error message");
	  System.out.println("Correct error message");
	  
	  }
	  
	  @Test(priority = 6)
	  public void invalid_login6() {
	  lp.login("standard_user", "");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	// test.log(Status.PASS, "User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: Password is required"), "Wrong error message");
	  System.out.println("Correct error message");
	  
	  }
	  
	  @Test(priority = 7)
	  public void invalid_login7() {
	  lp.login("", "");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: username and Password is required"), "Wrong error message for empty field");
	  System.out.println("error message is not correct");
	  
	 // extent = Extentreport_manager.getReportInstance();
	  }
	  
	 
	  
	 
	 
	  
	  
  @BeforeMethod(alwaysRun = true)
  public void beforeMethod() {
	  driver=new ChromeDriver();
	  lp=new Login_page(driver);
	  
	  lp.getUrl("https://www.saucedemo.com/v1/index.html");
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  
	
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}
