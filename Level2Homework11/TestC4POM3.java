package POMJiraProject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestC4POM3 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

//		String newdep = "NEW39";

		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "/Users/igor/Downloads/chromedriver");
		ChromeDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
//		Navigation navigate = driver.navigate();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		/*
		 * Go to Login Page of TestWave
		 */
		driver.get("http://testwave.qabidder.net/#/page/login");

		/*
		 * Login into TestWave
		 */

		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).sendKeys("dems_i@mail.ru");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).sendKeys("11111111");

		// wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button")))
		// .click();

		WebElement loginButton = driver.findElement(By.tagName("button"));
		Thread.sleep(6000);
		loginButton.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Vasyl']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Edit Profile']"))).click();

		List<WebElement> passwds = driver.findElements(By.xpath(""

				+ "html/body/div[2]/section/div/div/div[2]/form[2]/div/div[2]//input"));

		System.out.println(passwds.size());
		for (int i = 0; i < passwds.size(); i++)

		{

			WebElement textfield = passwds.get(i);
			String text = textfield.getText();
			System.out.println(text);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			// Thread.sleep(3000);

			if (i == 0) {
				textfield.sendKeys("11111111");
			} else {
				textfield.sendKeys("AA");
			}

			System.out.println(textfield.isDisplayed());
			System.out.println(textfield.isEnabled());
			System.out.println(textfield.getTagName());
			System.out.println(textfield.getSize());

		}

		WebElement chpwd = driver
				.findElement(By.xpath("//html/body/div[2]/section/div/div/div[2]/form[2]/div/div[3]/button"));
		Thread.sleep(3000);
		chpwd.click();

		//////////////////////////////////////////////// ------result-----------------
		WebElement err = driver
				.findElement(By.xpath("//fieldset[2]/div/div/span[2][text()='Input should be 6-20 length']"));
		Thread.sleep(3000);

		String text = err.getText();

		if (text.contains("Input should be 6-20 length") == true) {
			System.out.println("Test Case Passed");
		} else {
			System.out.println("Test Case NOTPASSED");
		}

		System.out.println(text);

		Thread.sleep(6000);
		driver.close();
	}

}
