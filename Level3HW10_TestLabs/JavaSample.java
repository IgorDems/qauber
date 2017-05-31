package browserstack;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class JavaSample {

	public static final String USERNAME = "bsuser33622";
	public static final String AUTOMATE_KEY = "WqzFexvNe1C28eNLCquo";
	public static final String remAddr = "https://" + USERNAME + ":" + AUTOMATE_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	public static void main(String[] args) throws Exception {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "IE");
		caps.setCapability("browser_version", "7.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "XP");
		caps.setCapability("browserstack.debug", "true");

		URL url = new URL(remAddr);
		WebDriver driver = new RemoteWebDriver(url, caps);
		driver.get("http://www.google.com");
		WebElement element = driver.findElement(By.name("q"));

		element.sendKeys("BrowserStack");
		element.submit();

		System.out.println(driver.getTitle());
		driver.quit();
	}

	public static void takeScreenshot(WebDriver driver) {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// FileUtils.copyFile(src, new File("C:/selenium/error.png"));
			FileUtils.copyFile(src, new File("/tmp/error.png"));
		} catch (IOException e) {
			System.out.println("Not able to create Screenshot .. Check it ..");
		}
	}

}