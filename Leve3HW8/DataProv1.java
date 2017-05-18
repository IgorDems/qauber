package TestNG;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProv1 {

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "/Users/igor/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://qabidder.net/testwave/#/page/login");
		System.out.println(driver.getCurrentUrl() + " ==== " + driver.getTitle() + "page opened");
	}

	@Test(dataProvider = "giveUnameAndPass")
	public void login(String emailID, String passwd) throws InterruptedException {

		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).sendKeys(emailID);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).sendKeys(passwd);
		Thread.sleep(6000);
		wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
//go to profile//===========================================================================================
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
			textfield.sendKeys("11111111");
			

		}

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("" + "//html/body/div[2]/section/div/div/div[2]/form[2]/div/div[3]/button"))).click();
		System.out.println("Password was changed - OK!");
		driver.get("http://qabidder.net/testwave/#/page/login");
	}

	@DataProvider
	public Object[][] giveUnameAndPass() throws Exception {

		Object[][] objArray = { { "dems_i@mail.ru", "11111111" }, { "dems_i@mail.ru", "11111111" },
				{ "dems_i@mail.ru", "11111111" }, { "dems_i@mail.ru", "11111111" }, { "dems_i@mail.ru", "11111111" } };
		return objArray;
	}

	@AfterTest
	public void closeApp() {
		driver.close();
		System.out.println("=========================BOWSER WAS CLOSED OK!=======================");
	}
}
