package ProfileFF;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class TestBase {
	WebDriver driver;
	WebDriverWait wait;
	
	public TestBase(){
		
	}

	public void login() throws InterruptedException {
		System.out.println("Login");
		System.setProperty("webdriver.chrome.driver","/Users/igor/Downloads/chromedriver");
	    driver=new ChromeDriver();
	    driver.manage().window().maximize();
	    wait = new WebDriverWait(driver, 30);
	    driver.get("http://qabidder.net/testwave/#/page/login");
	    System.out.println(driver.getCurrentUrl()+" ==== "+ driver.getTitle() +"page opened");
	    wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).sendKeys("dems_i@mail.ru");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).sendKeys("11111111");

		// wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")))
		// .click();

		WebElement loginButton = driver.findElement(By.tagName("button"));
		Thread.sleep(6000);
		loginButton.click();
		System.out.println("Login OK!");
		Assert.assertFalse(false,"Assert OK!");
	}

	public void logout() {
		System.out.println("Logout");
		driver.close();
		System.out.println("=========================BOWSER WAS CLOSED - OK!=======================");
	}

}
