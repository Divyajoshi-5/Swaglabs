package techflare.swag;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
@Listeners(ExtentTestNGListener.class)
public class Swag_test {
	WebDriver driver;
	Login_page lp;
	Productpage qa;
	Cartpage cp;
	Product_detailpage pd;
	ExtentTest test;

	@Test(priority=0)
	public void valid_login1() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "secret_sauce");
		Thread.sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "");
		System.out.println("Login successfully");
		test.log(Status.INFO,"Checking login success");
		qa.Menu_button();
		qa.Logout();
		Thread.sleep(2000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/index.html"), "user does not logout");
		System.out.println("user successfully loggedin");
		test.log(Status.INFO,"Checking logout success");
	}
	@Test(priority=1)
	public void invalid_login2() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("div", "123");
		Thread.sleep(1000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"), "user logedin");
		System.out.println("User  does not logged in successfully");
		test.log(Status.INFO,"Checking login faild");

		// test.log(Status.PASS, "User  does not logged in successfully");

		Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
		System.out.println("Correct error message");
		test.log(Status.INFO,"Checking correct error msg");
	}  
	@Test(priority = 2)
	public void invalid_login3() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("divya", "secret_sauce");
		Thread.sleep(1000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
		System.out.println("User  does not logged in successfully");
		test.log(Status.INFO,"Checking login faild");

		// test.log(Status.PASS, "User  does not logged in successfully");

		Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
		System.out.println("Correct error message");
		test.log(Status.INFO,"Checking  Correct error message");

	}
	@Test(priority=3)
	public void invalid_login4() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "divya");
		Thread.sleep(1000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
		System.out.println("User  does not logged in successfully");
		test.log(Status.INFO,"Checking login faild");

		//  test.log(Status.PASS, "User  does not logged in successfully");
		Assert.assertTrue(lp.error().equals("Epic sadface: Username and password do not match any user in this service"), "Wrong error message");
		System.out.println("Correct error message");
		test.log(Status.INFO,"Correct error message");
	}
	@Test(priority = 4)
	public void invalid_login5() throws InterruptedException {
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("", "secret_sauce");
		Thread.sleep(1000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
		System.out.println("User  does not logged in successfully");
		test.log(Status.INFO,"Checking login faild");


		Assert.assertTrue(lp.error().equals("Epic sadface: Username is required"), "Wrong error message");
		System.out.println("Correct error message");
		test.log(Status.INFO,"Correct error message");

	}

	@Test(priority = 5)
	public void invalid_login6() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "");
		Thread.sleep(1000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
		System.out.println("User  does not logged in successfully");
		test.log(Status.INFO,"Checking login faild");



		Assert.assertTrue(lp.error().equals("Epic sadface: Password is required"), "Wrong error message");
		System.out.println("Correct error message");
		test.log(Status.INFO,"Correct error message");

	}

	@Test(priority = 6)
	public void invalid_login7() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("", "");
		Thread.sleep(1000);
		Assert.assertFalse(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"user logedin");
		System.out.println("User  does not logged in successfully");
		test.log(Status.INFO,"Checking login faild");

		Assert.assertTrue(lp.error().equals("Epic sadface: username and Password is required"), "Wrong error message for empty field");
		System.out.println("error message is not correct");
		test.log(Status.INFO,"Checking error msg is not correct");

	}
	@Test(priority=7)
	public void menu() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		SoftAssert  a =new SoftAssert();
		lp.login("standard_user", "secret_sauce");
		Thread.sleep(5000);
		qa.Menu_button();
		Assert.assertTrue(qa.navigationpannel());
		test.log(Status.INFO,"Checking menu button");
		qa.Allitems();
		
		a.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"all items button not working");
		test.log(Status.INFO,"Checking allitems is clickable");
		qa.Menu_button();
		System.out.println("All items button is working");
		/*qa.resetapp_page();
				a.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));*/

		qa.About();

		a.assertTrue(driver.getCurrentUrl().equals("https://saucelabs.com/"),"about button is not clickable");
		System.out.println("about button is clickable");
		test.log(Status.INFO,"Checking about button is clickable");
		a.assertAll();

	}
	@Test(priority=8)
	public void verifyproduct() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "secret_sauce");
		Thread.sleep(5000);
		SoftAssert sa=new SoftAssert();
		List<String> beforedSort=qa.ProductsNameList();
		System.out.println("the list before decending sort:"+beforedSort);
		Thread.sleep(2000);
		qa.decendingoredr(); 
		List<String> afterdSort=qa.ProductsNameList();
		System.out.println("the list after decending sortis:"+afterdSort);
		sa.assertNotEquals(afterdSort, beforedSort ,"deccending sort is not completed");
		test.log(Status.INFO,"Checking sorting is completed");
		

		List<String> beforeaSort=qa.ProductsNameList();
		System.out.println("the list before accending sort is:"+beforeaSort);
		Thread.sleep(2000);
		qa.asendingsort(); 
		List<String> afteraSort=qa.ProductsNameList();
		System.out.println("the list after asecnding sort:"+afteraSort);
		sa.assertNotEquals(afteraSort, beforeaSort,"accending sort is not completed");
		test.log(Status.INFO,"Checking sorting is completed");
		sa.assertAll();
	}
	@Test(priority=9)
	public void verifyprice() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "secret_sauce");
		Thread.sleep(5000);
		SoftAssert sa=new SoftAssert();
		List<Double> beforelhSort=qa.productPriceList();
		System.out.println("the list before lowtohigh sort:"+beforelhSort);
		Thread.sleep(2000);
		qa.lowtohighsorth();
		List<Double> afterlhSort=qa.productPriceList();
		System.out.println("the list after lowtohigh sortis:"+afterlhSort);
		sa.assertNotEquals(afterlhSort, beforelhSort ,"lowtohigh sort is not completed");
		test.log(Status.INFO,"Checking sorting is completed");

		List<Double> beforehlSort=qa.productPriceList();
		System.out.println("the list before hightolow sort is:"+beforehlSort);
		Thread.sleep(2000);
		qa.hightolowsorth();
		List<Double> afterhlSort=qa.productPriceList();
		System.out.println("the list after hightolow sort:"+afterhlSort);
		sa.assertNotEquals(afterhlSort, beforehlSort,"hightolow sort is not completed");
		test.log(Status.INFO,"Checking sorting is completed");
		sa.assertAll();

	}

	 /*@Test(priority=10)
	public void add() throws InterruptedException {
		lp.login("standard_user", "secret_sauce");
         System.out.println("Test 10 : LoggedIn");
		List<String> productsinpp=qa.ProductsNameList();
		qa.addToCart();
		System.out.println("Test 10 : Added to cart");
		qa.adddTocartIcon();
		System.out.println("Test 10 : Navigated to cart page");
		List<String> productsincp=cp.productlist();
		Assert.assertEquals(productsincp, productsinpp, "Added products are not found in cart");
		System.out.println("product found in cart page");
		cp.remove();
		System.out.println("Test 10 : Products removed");
		List<String> productsincpapage=cp.productlist();
		Assert.assertTrue(productsincpapage.isEmpty(),"Remove button is not working");
		System.out.println("Remove button is working");

	}*/
	
	@Test(priority = 10)
	public void add() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "secret_sauce");
		Thread.sleep(5000);
		System.out.println("Logged in");

		List<String> productsInPP = qa.ProductsNameList();
		System.out.println("Products on product page: " + productsInPP);

		qa.addToCart(); 
		System.out.println("Products added to cart");

		qa.adddTocartIcon();

		System.out.println("Navigated to cart: " + driver.getCurrentUrl());

		List<String> productsInCP = cp.productList();
		System.out.println("Products in cart page: " + productsInCP);

		Assert.assertEquals(productsInCP, productsInPP, "Added products are not found in cart");
		test.log(Status.INFO,"Checking products are added in cart");
     
        
		cp.remove();
		System.out.println("Products removed");

		List<String> productsAfterRemove = cp.productList();
		Assert.assertTrue(productsAfterRemove.isEmpty(), "Remove button is not working");
		test.log(Status.INFO,"Checking remove button is working");
		System.out.println("Cart is empty after removal");
	}
	
	@Test(priority=11)
	public void premove() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "secret_sauce");
		Thread.sleep(5000);
		System.out.println("Test 11 : LoggedIn");
		qa.addToCart();  
		System.out.println("Test 11 : Add to cart");
		qa.removebuttonproductpage();
		System.out.println("Test 11 : Products remove");
		Assert.assertTrue(qa.checkbutton(),"Remove button is not working");
		test.log(Status.INFO,"Checking remove button is working");
		System.out.println("Remove button is working");
	}
	@Test(priority=12)//productdetailpage
	public void productnameclick() throws InterruptedException {
		
		test=ExtentTestNGListener.testThread.get();
		
		lp.login("standard_user", "secret_sauce");
		Thread.sleep(5000);
		String expectedproduct="Sauce Labs Backpack";
		String productprice=qa.clickproductname(expectedproduct);
		System.out.println("Test 12 : Product name is clicked");
		Assert.assertTrue(pd.verifyproductname().equals(expectedproduct),"Both text are not same");
		test.log(Status.INFO,"Checking remove both text are same");
		System.out.println("Both text are same");

		Thread.sleep(3);
		String detailpageprices=pd.verifyproductprice();
		Assert.assertEquals(detailpageprices, productprice,"Both prices are not same");
		test.log(Status.INFO,"Checking remove both text are same");
		System.out.println("Both  prices are same");
		pd.addtocart();
		System.out.println("Test 12 : Added to cart");
		qa.adddTocartIcon();
		Assert.assertTrue(cp.productList().contains(expectedproduct),"The product is not found in the cart" );
		test.log(Status.INFO,"Checking product are found in cart");
		System.out.println("Product are found in the cart");

		driver.navigate().back();

		pd.remove();
		System.out.println("Test 12 : Product removed");
		qa.adddTocartIcon();
		Assert.assertTrue(cp.productList().isEmpty(),"The product is not removed from the cart");
		test.log(Status.INFO,"Checking product are found in cart");
		System.out.println("The product is  removed from the cart");

		Thread.sleep(3000);
		driver.navigate().back();
		pd.backbutton();
		System.out.println("Test 12 : Gone back");
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"Do not navigate to the productpage");
		test.log(Status.INFO,"Checking navigate to productpage");
		System.out.println("Navigate to product page");


		qa.adddTocartIcon();
		System.out.println("Test 12 : Added to cart");
		cp.continuesshopping();

		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"Do not navigate to the productpage");
		test.log(Status.INFO,"Checking navigate to productpage");
		System.out.println("Navigate to product page");



	}





	@BeforeMethod(alwaysRun=true)
	public void beforeMethod() {
		driver=new ChromeDriver();
		lp=new Login_page(driver); 
		lp.getUrl("https://www.saucedemo.com/v1/index.html");
		qa=new Productpage(driver);
		cp=new Cartpage(driver);
		pd=new Product_detailpage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterMethod(alwaysRun=true)
	public void afterMethod() {
		if(driver!=null)
		{
			driver.quit();
		}
	}

}
