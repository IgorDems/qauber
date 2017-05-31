/**
 * 
 */
package browserstack;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * @author igor
 *
 */
public class SauceLabs {
	public static final String USERNAME = "dandeyagna";
	public static final String ACCESS_KEY = "42000208-b0bc-4edb-b489-e36d31a52445";
	public static final String remoteAddress = "https://" + USERNAME + ":" + ACCESS_KEY
			+ "@ondemand.saucelabs.com:443/wd/hub";

	public static void main(String[] args) throws Exception {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows XP");
		caps.setCapability("version", "43.0");
		URL url = new URL(remoteAddress);
		WebDriver driver = new RemoteWebDriver(url, caps);
		driver.get("http://www.google.com");
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("BrowserStack");
		element.submit();
		System.out.println(driver.getTitle());
		driver.quit();
	}
}