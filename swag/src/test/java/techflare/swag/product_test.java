package techflare.swag;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class product_test {
	WebDriver driver;
	Login_page lp;
	Productpage qa;
	Cartpage cp;
	Product_detailpage pd;
	WebDriverWait wait;
	
	
  @Test(priority=0)
  public void menu() throws InterruptedException {
	  Thread.sleep(2000);
	  qa.Menu_button();
	  Thread.sleep(2000);
	 // Assert.assertTrue( qa.menu_button(),"");
	   boolean menu=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bm-burger-button']"))).isDisplayed();
		//boolean menu=driver.findElement(By.xpath("//div[@class='bm-burger-button']")).isDisplayed();
		Assert.assertTrue(menu,"menu bar is not working");
  Thread.sleep(2000);
			
			SoftAssert  a =new SoftAssert();
			qa.Allitems();
			Thread.sleep(2000);
			a.assertTrue(driver.getPageSource().contains("Products"),"all items button not working");
			qa.Menu_button();
			Thread.sleep(2000);
			/*qa.resetapp_page();
			a.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"));*/
		
		    qa.About();
		    Thread.sleep(2000);
		    a.assertTrue(driver.getCurrentUrl().equals("https://saucelabs.com/"),"about button is not displayed");
		    a.assertAll();
		
		}
  @Test(enabled=false)
  public void verifyproduct() throws InterruptedException {
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
	 @Test(enabled=false)
	 public void verifyprice() throws InterruptedException {
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
		 
	 @Test(enabled = false)
	 public void add() throws InterruptedException {
		 Thread.sleep(6000);
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
  @Test(enabled=false)
  public void premove() throws InterruptedException {
	  Thread.sleep(3000);
	  qa.addToCart();
	  Thread.sleep(3000);
	  qa.removebuttonproductpage();
	  Assert.assertTrue(qa.checkbutton(),"Remove button is not working");
	  System.out.println("Remove button is working");
  }
  @Test//productdetailpage
  public void productnameclick() throws InterruptedException {
	  Thread.sleep(3000);
	  String expectedproduct="Sauce Labs Backpack";
	  String productprice=qa.clickproductname(expectedproduct);
	  Thread.sleep(3000);
	  Assert.assertTrue(pd.verifyproductname().equals(expectedproduct),"Both text are not same");
	  System.out.println("Both text are same");
	  
	  String detailpageprices=pd.verifyproductprice();
	  Assert.assertEquals(detailpageprices, productprice,"Both prices are not same");
	  System.out.println("Both  prices are same");
	  pd.addtocart();
	  qa.adddTocartIcon();
	  Assert.assertTrue(cp.productlist().contains(expectedproduct),"The product is not found in the cart" );
	  System.out.println("Product are found in the cart");
	  Thread.sleep(3000);
	  driver.navigate().back();
	  Thread.sleep(3000);
	  pd.remove();
	  qa.adddTocartIcon();
	  Assert.assertTrue(cp.productlist().isEmpty(),"The product is not removed from the cart");
	  System.out.println("The product is  removed from the cart");
	  
	  Thread.sleep(3000);
	  driver.navigate().back();
	  pd.backbutton();
	  Assert.assertTrue(driver.getCurrentUrl().equals("https://www.saucedemo.com/v1/inventory.html"),"Do not navigate to the productpage");
	  System.out.println("Navigate to product page");
	  
	  Thread.sleep(3000);
	  qa.adddTocartIcon();
	  Thread.sleep(3000);
	  cp.continuesshopping();
	  Thread.sleep(3000);
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
		  wait=new WebDriverWait(driver, Duration.ofSeconds(3));
		  
	  
  }

  @AfterMethod
  public void afterMethod() {
	  driver.close();
  }

}

