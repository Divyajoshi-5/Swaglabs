package techflare.swag;

import java.awt.event.MouseAdapter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cartpage {
	WebDriver driver;
	WebDriverWait wait;
	Actions mouse;

	public Cartpage(WebDriver driver) {
		this.driver=driver;
		this.wait= new WebDriverWait(driver, Duration.ofSeconds(20));
		this.mouse=new Actions(driver);
	}


	public List<String> productList() {
	    List<String> productNames = new ArrayList<>();
	    try {
	        // Use presence instead of visibility
	        List<WebElement> productElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'inventory_item_name')]")));
	        for (WebElement product : productElements) {
	            if (product.isDisplayed()) {
	                productNames.add(product.getText());
	            }
	        }

	    } catch (TimeoutException e) {
	        System.out.println("Timed out waiting for product elements. Possibly the page hasn't loaded correctly.");
	    }

	    if (productNames.isEmpty()) {
	        System.out.println("Cart is empty or product names are not visible.");
	    }

	    return productNames;
	}

	public void remove() {
	    try {
	        List<WebElement> rbuttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[normalize-space()='REMOVE']")));

	        for (WebElement rbutton : rbuttons) {
	            mouse.moveToElement(rbutton).click().perform();
	        }

	    } catch (TimeoutException e) {
	        System.out.println("No 'REMOVE' buttons found — maybe no items are added to the cart.");
	    } catch (InvalidSelectorException e) {
	        System.out.println("Invalid XPath selector: " + e.getMessage());
	    }
	}


	public void continuesshopping() {
		WebElement shopping=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Continue Shopping']")));
		shopping.click();
	}
}