package techflare.swag;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product_detailpage {
	WebDriver driver;
	WebDriverWait wait;
	
	public Product_detailpage(WebDriver driver) {
		this.driver=driver;
		this.wait=new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public void addtocart() {
		WebElement addtocart=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='ADD TO CART']")));
		addtocart.click();
	}
	
	public void remove() {
		WebElement removebutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='REMOVE']")));
		removebutton.click();
	}
	
	public void backbutton() {
		WebElement backbutton=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='inventory_details_back_button']")));
		backbutton.click();
	}
	
	public String verifyproductname() throws InterruptedException {
	
		WebElement dproductname=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='inventory_details_name']")));
		String productdetailtext=dproductname.getText();
		return productdetailtext;
		
	}
	
    public String verifyproductprice() {
    	WebElement dproductprice=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='inventory_details_price']")));
    	String productdetailprice=dproductprice.getText();
    	return productdetailprice;
    } 

}
