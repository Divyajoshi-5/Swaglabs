package techflare.swag;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class Swag_test {
	WebDriver driver;
	Login_page lp;
	Productpage qa;
	Cartpage cp;
	Product_detailpage pd;
  
  @Test(priority=0)
  public void valid_login1() {
	  lp.login("standard_user", "secret_sauce");
	  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "");
	  System.out.println("Login successfully");
	  qa.Menu_button();
	  qa.Logout();
	  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/index.html"), "user does not logout");
	  System.out.println("user successfully loggedin");
  }
  @Test(priority=1)
  public void invalid_login2() {
	  lp.login("div", "123");
	  
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "user logedin");
	  System.out.println("User  does not logged in successfully");
	    
	 // test.log(Status.PASS, "User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
	  System.out.println("Correct error message");
  }  
  @Test(priority = 2)
  public void invalid_login3() {
	  lp.login("divya", "secret_sauce");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	// test.log(Status.PASS, "User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
	  System.out.println("Correct error message");
	    
  }
  @Test(priority=3)
  public void invalid_login4() {
	  lp.login("standard_user", "divya");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	//  test.log(Status.PASS, "User  does not logged in successfully");
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
	  System.out.println("Correct error message");
  }
	  @Test(priority = 4)
	  public void invalid_login5() {
	  lp.login("", "secret_sauce");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	 
	  Assert.assertTrue(lp.error().equals("Epic sadface: Username is required"), "Wrong error message");
	  System.out.println("Correct error message");
	  
	  }
	  
	  @Test(priority = 5)
	  public void invalid_login6() {
	  lp.login("standard_user", "");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: Password is required"), "Wrong error message");
	  System.out.println("Correct error message");
	  
	  }
	  
	  @Test(priority = 6)
	  public void invalid_login7() {
	  lp.login("", "");
	  Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
	  System.out.println("User  does not logged in successfully");
	  
	  Assert.assertTrue(lp.error().equals("Epic sadface: username and Password is required"), "Wrong error message for empty field");
	  System.out.println("error message is not correct");
	  
	  }
	  @Test(priority=7)
	  public void menu() throws InterruptedException {
		  SoftAssert  a =new SoftAssert();
		  lp.login("standard_user", "secret_sauce");

		  qa.Menu_button();
		 Assert.assertTrue(qa.navigationpannel());
				qa.Allitems();
	
				a.assertTrue(driver.getPageSource().contains("Products"),"all items button not working");
				qa.Menu_button();

				/*qa.resetapp_page();
				a.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));*/
			
			    qa.About();
		
			    a.assertTrue(driver.getCurrentUrl().equals("https://saucelabs.com/"),"about button is not displayed");
			    a.assertAll();
			
			}
	  @Test(priority=8)
	  public void verifyproduct() throws InterruptedException {
		  lp.login("standard_user", "secret_sauce");
		  SoftAssert sa=new SoftAssert();
		 List<String> beforedSort=qa.ProductsNameList();
		 System.out.println("the list before decending sort:"+beforedSort);
		 Thread.sleep(2000);
		 qa.decendingoredr(); 
		 List<String> afterdSort=qa.ProductsNameList();
		 System.out.println("the list after decending sortis:"+afterdSort);
		 sa.assertNotEquals(afterdSort, beforedSort ,"deccending sort is not completed");
		 
		 List<String> beforeaSort=qa.ProductsNameList();
		 System.out.println("the list before accending sort is:"+beforeaSort);
		 Thread.sleep(2000);
		 qa.asendingsort(); 
		 List<String> afteraSort=qa.ProductsNameList();
		 System.out.println("the list after asecnding sort:"+afteraSort);
		 sa.assertNotEquals(afteraSort, beforeaSort,"accending sort is not completed");
		 sa.assertAll();
	  }
		 @Test(priority=9)
		 public void verifyprice() throws InterruptedException {
			 lp.login("standard_user", "secret_sauce");
			  SoftAssert sa=new SoftAssert();
				 List<Double> beforelhSort=qa.productPriceList();
				 System.out.println("the list before lowtohigh sort:"+beforelhSort);
				 Thread.sleep(2000);
				 qa.lowtohighsorth();
				 List<Double> afterlhSort=qa.productPriceList();
				 System.out.println("the list after lowtohigh sortis:"+afterlhSort);
				 sa.assertNotEquals(afterlhSort, beforelhSort ,"lowtohigh sort is not completed");
				 
				 List<Double> beforehlSort=qa.productPriceList();
				 System.out.println("the list before hightolow sort is:"+beforehlSort);
				 Thread.sleep(2000);
				 qa.hightolowsorth();
				 List<Double> afterhlSort=qa.productPriceList();
				 System.out.println("the list after hightolow sort:"+afterhlSort);
				 sa.assertNotEquals(afterhlSort, beforehlSort,"hightolow sort is not completed");
				 sa.assertAll();
				  
		 }
			 
		 @Test(priority=10)
		 public void add() throws InterruptedException {
			 lp.login("standard_user", "secret_sauce");

			 List<String> productsinpp=qa.ProductsNameList();
			 qa.addToCart();
		
			 qa.adddTocartIcon();
		
			 List<String> productsincp=cp.productlist();
			 Assert.assertEquals(productsincp, productsinpp, "Added products are not found in cart");
			 System.out.println("product found in cart page");
			 cp.remove();
			 List<String> productsincpapage=cp.productlist();
			 Assert.assertTrue(productsincpapage.isEmpty(),"Remove button is not working");
			 System.out.println("Remove button is working");
			 
		 }
	  @Test(priority=11)
	  public void premove() throws InterruptedException {
		  lp.login("standard_user", "secret_sauce");
		  
		  qa.addToCart();
		  
		  qa.removebuttonproductpage();
		  Assert.assertTrue(qa.checkbutton(),"Remove button is not working");
		  System.out.println("Remove button is working");
	  }
	  @Test(priority=12)//productdetailpage
	  public void productnameclick() throws InterruptedException {
		  lp.login("standard_user", "secret_sauce");
		  
		  String expectedproduct="Sauce Labs Backpack";
		  String productprice=qa.clickproductname(expectedproduct);
		  
		  Assert.assertTrue(pd.verifyproductname().equals(expectedproduct),"Both text are not same");
		  System.out.println("Both text are same");
		  
		  String detailpageprices=pd.verifyproductprice();
		  Assert.assertEquals(detailpageprices, productprice,"Both prices are not same");
		  System.out.println("Both  prices are same");
		  pd.addtocart();
		  qa.adddTocartIcon();
		  Assert.assertTrue(cp.productlist().contains(expectedproduct),"The product is not found in the cart" );
		  System.out.println("Product are found in the cart");
		  
		  driver.navigate().back();
		  
		  pd.remove();
		  qa.adddTocartIcon();
		  Assert.assertTrue(cp.productlist().isEmpty(),"The product is not removed from the cart");
		  System.out.println("The product is  removed from the cart");
		  
		  Thread.sleep(3000);
		  driver.navigate().back();
		  pd.backbutton();
		  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"Do not navigate to the productpage");
		  System.out.println("Navigate to product page");
		  
		 
		  qa.adddTocartIcon();
		  
		  cp.continuesshopping();
		  
		  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"Do not navigate to the productpage");
		  System.out.println("Navigate to product page");
		  
		  
		  
	  }
	  
			
		  
	  
  
  @BeforeMethod
  public void beforeMethod() {
	  driver=new ChromeDriver();
	  lp=new Login_page(driver); 
	  lp.getUrl("https://www.saucedemo.com/v1/index.html");
	  qa=new Productpage(driver);
	  cp=new Cartpage(driver);
	  pd=new Product_detailpage(driver);
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	 
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}
