/**
 * 
 */
package DataDrivenMySql;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author igor
 *
 */
public class DataDrivenTestingfromMySql {

	/**
	 * @param args
	 */
	public class BrowserStackTestNGMySql {
		@Test(dataProvider = "givePlatformAndBrowsers")
		public void testSearch(String browser, String version, String platform) throws
		Exception {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform", platform);
		capability.setCapability("browserName", browser);
		capability.setCapability("browserVersion", version);
		WebDriver driver = new RemoteWebDriver(new URL("https://yagnanarayanadan1:"+""
				+ "MFeUND4Ps81xcyb33hKx@hub-cloud.browserstack.com/wd/hub"),capability);
		driver.get("http://www.google.com");
		System.out.println("Page title is: " + driver.getTitle());
		WebElement element = driver.findElement(By.name("q"));
		element.sendKeys("Browser Stack");
		element.submit();
		driver.quit();
		}

		@DataProvider
		public Object[][] givePlatformAndBrowsers() throws Exception {
			Object[][] testObjArray = new Object[2][3];
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root",
						"root");
				// here sonoo is database name, root is username and password
				Statement stmt = (Statement) con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from platforms");
				// Object array should be created here by getting number of rows
				// fromRS
				int count = 0;
				while (rs.next()) {
					testObjArray[count][0] = rs.getString(1);
					testObjArray[count][1] = rs.getString(2);
					testObjArray[count][2] = rs.getString(3);
					count++;
					System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			return testObjArray;
		}
	}

}
