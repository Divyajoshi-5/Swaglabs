package techflare.swag;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Productpage { 
	WebDriver driver;
	WebDriverWait wait;
	Actions mouse;

	public Productpage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		this.mouse=new Actions(driver);

	}

	public List <String> ProductsNameList()
	{
		List<WebElement>prodNames=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_name']")));
		List<String> productsNames=new ArrayList<>();
		for(WebElement prodName:prodNames)
		{
			String prodText=prodName.getText();
			wait.until(ExpectedConditions.visibilityOf(prodName));
			productsNames.add(prodText);
		}
		return productsNames;	
	}

	//product name to navigate product detailpage\

	public String clickproductname(String productname) {
		List<WebElement>pl=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item']")));
		for(WebElement product:pl) {
			WebElement prodName=product.findElement(By.xpath(".//div[@class='inventory_item_name']"));
			String prodText=prodName.getText();
			if(prodText.equalsIgnoreCase(productname)) {

				WebElement productprice=product.findElement(By.xpath(".//div[@class='inventory_item_price']"));
				String productprices=productprice.getText();
				wait.until(ExpectedConditions.elementToBeClickable(prodName));
				prodName.click();
				return productprices;
			}
		}

		return null;
		


	}
	public List<Double> productPriceList() {
		List<WebElement>p=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='inventory_item_price']")));
		List<Double>prices=new ArrayList<>();
		for(WebElement product:p) {
			String priceText=product.getText().replace("$","").trim();
			prices.add(Double.parseDouble(priceText));
			//System.out.println(prices);
		}
		return prices;

	} 

	public void addToCart() {
		List<WebElement>add=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='ADD TO CART']")));
		for(WebElement addto:add) 
		{
			addto.click();
					
		}
	}
	public void adddTocartIcon() {
		WebElement addtocarticon=wait.until(ExpectedConditions.elementToBeClickable(By.id("shopping_cart_container")));
		addtocarticon.click();
	}




	public void Menu_button() {
		WebElement menubar=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='bm-burger-button']")));
		mouse.moveToElement(menubar).click().perform();

	}

	public void Allitems() {

		WebElement allitems=wait.until(ExpectedConditions.elementToBeClickable(By.id("inventory_sidebar_link")));
		allitems.click();
	}

	public void About() {

		WebElement about=wait.until(ExpectedConditions.elementToBeClickable(By.id("about_sidebar_link")));
		about.click();
	}

	public void Logout() {

		WebElement logout=wait.until(ExpectedConditions.elementToBeClickable(By.id("logout_sidebar_link")));
		Actions mouse=new Actions(driver);
		mouse.moveToElement(logout).click().perform();
	}

	public void Resetappstate() {

		WebElement resetappstate=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='reset_sidebar_link']")));
		resetappstate.click();

	}

	public void asendingsort() {
		WebElement ascendingsort=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='product_sort_container']")));
		Select ad=new Select(ascendingsort);
		ad.selectByValue("az");

	}

	public void decendingoredr() {
		WebElement decendingsort=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='product_sort_container']")));
		Select ad=new Select(decendingsort);
		ad.selectByValue("za");

	}

	public void lowtohighsorth() {
		WebElement lowtohighsort=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='product_sort_container']")));
		Select ad=new Select(lowtohighsort);
		ad.selectByValue("lohi");

	}


	public void hightolowsorth() {
		WebElement hightolowsort=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class='product_sort_container']")));
		Select ad=new Select(hightolowsort);
		ad.selectByValue("hilo");

	}

	public void removebuttonproductpage() {
		List<WebElement>e=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='REMOVE']")));
		for(WebElement el:e) {
			
			el.click();
		
		}
	}
	public boolean navigationpannel() {
		WebElement panel=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bm-menu']")));
		if(panel.isDisplayed()) {

			return true;
		}
		return false;
	}

	public boolean checkbutton() {
		List<WebElement>add=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[@class='btn_primary btn_inventory']")));
		for(WebElement addto:add) {
			//inspect cart button
			if(addto.getText().equals("ADD TO CART")) 
			{	  
				return true;
			}
		} 
		return false;
	}
}