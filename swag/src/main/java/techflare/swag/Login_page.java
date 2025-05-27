package techflare.swag;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_page {
	WebDriver driver;
	WebDriverWait wait;
	
	
	public Login_page(WebDriver driver)
	{
		this.driver=driver;
		this.wait=new WebDriverWait(driver,Duration.ofSeconds(20));
	}
	
	public void getUrl(String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	
	public void login(String username_input, String password_input) {
		driver.findElement(By.id("user-name")).sendKeys(username_input);
		driver.findElement(By.id("password")).sendKeys(password_input);
		driver.findElement(By.id("login-button")).click();
		
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			Alert a=driver.switchTo().alert();
			a.accept();
			
		}
		catch(Exception e)
		{
			System.out.println("No alert appered this time");
		}
		
		
	}
	
	
	public String error() {
		
			
			WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(3));
			WebElement ermsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
			String errmsg=ermsg.getText();
			return errmsg;
		}
	
	
	}


