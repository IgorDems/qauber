package Level2HW3;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Level2_Hw3 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "/Users/igor/Downloads/geckodriver");// "/Volumes/HDD/SeleniumWebDrv/chromedriver");
		FirefoxDriver driver = new FirefoxDriver();
		Navigation navigate = driver.navigate();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		// driver.get("file:///Users/igor/Desktop/project/src/main/java/Level2HW3/Test.html");
		driver.get("https://dems.joomla.com/2-uncategorised/2-qauber-2-3");

		WebElement firstlink = driver.findElement(By.linkText("First Link"));
		firstlink.click();

		navigate.back();
		Thread.sleep(3000);

		WebElement secondlink = driver.findElement(By.linkText("Second Link"));
		secondlink.click();

		String result;
		String link = "google";
		result = driver.getCurrentUrl();

		// string contains the specified sequence of char values
		boolean retval = result.contains(link);

		if (retval == true) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED");
		}
		driver.close();
	}

}
