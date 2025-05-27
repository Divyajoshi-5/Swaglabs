package techflare.swag;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cartpage {
	WebDriver driver;
    WebDriverWait wait;
	
	public Cartpage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	
	public List<String> productlist () {
		List<WebElement>productlist=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='cart_item']")));
		List<String>productsname=new ArrayList<>();
		for(WebElement product:productlist ) {
			
			WebElement productname=product.findElement(By.xpath(".//div[@class='inventory_item_name']"));
			String productText=productname.getText();
			wait.until(ExpectedConditions.visibilityOf(productname));
			productsname.add(productText);
			
		}
		    return productsname;	
			
	}
	public void  remove() {
		List<WebElement>button=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='cart_item']")));	
		for(WebElement product:button ) {
			WebElement removebutton=product.findElement(By.xpath(".//button[text()='REMOVE']"));
			wait.until(ExpectedConditions.elementToBeClickable(removebutton));
			removebutton.click();
}
	}
	
	public void continuesshopping() {
		WebElement shopping=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Continue Shopping']")));
		shopping.click();
	}
}