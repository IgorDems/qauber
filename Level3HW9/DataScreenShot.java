package datadriver;
//Level3HW9
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

public class DataScreenShot {
	WebDriver driver;
	WebDriverWait wait;

	// @BeforeTest
	// public void beforeTest() {
	//
	// System.setProperty("webdriver.chrome.driver",
	// "/Users/igor/Downloads/chromedriver");
	// driver = new ChromeDriver();
	// driver.manage().window().maximize();
	// wait = new WebDriverWait(driver, 30);
	//
	// }

	public static final String USERNAME = "yagnanarayanadan1";
	public static final String AUTOMATE_KEY = "MFeUND4Ps81xcyb33hKx";
	public static final String remAddr = "https://" + USERNAME + ":" + AUTOMATE_KEY
			+ "@hub-cloud.browserstack.com/wd/hub";

	@Test(dataProvider = "dataProviderMethod")
	public void login(String id, String passwd) throws Exception {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "Safari");
		caps.setCapability("browser_version", "10.0");
		caps.setCapability("os", "OS X");
		caps.setCapability("os_version", "Sierra");
		caps.setCapability("resolution", "1024x768");

		URL url = new URL(remAddr);
		WebDriver driver = new RemoteWebDriver(url, caps);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 3000);
		driver.get("http://qabidder.net/testwave/#/page/login");
		// WebElement element = driver.findElement(By.name("q"));

		System.out.println(driver.getCurrentUrl() + " ==== " + driver.getTitle() + "page opened");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).sendKeys(id);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).sendKeys(passwd);
		Thread.sleep(6000);
		takeScreenshot(driver); // ScreenShot
		wait.until(ExpectedConditions.elementToBeClickable(By.tagName("button"))).click();
		// go to
		// profile//===========================================================================================
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text() = 'Vasyl']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Edit Profile']"))).click();

		// element.sendKeys("BrowserStack");
		// element.submit();
		takeScreenshot(driver); // ScreenShot

		System.out.println(driver.getTitle());
		driver.quit();
	}

	public static void takeScreenshot(WebDriver driver) {
		LocalDateTime ldt = LocalDateTime.now();
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// FileUtils.copyFile(src, new File("C:/selenium/error.png"));
			FileUtils.copyFile(src, new File("/Users/igor/tmp/error"+ldt+".png"));
		} catch (IOException e) {
			System.out.println("Not able to create Screenshot .. Check it ..");
		}
	}

	@DataProvider(name = "dataProviderMethod")
	public Object[][] dataProviderMethod() throws Exception {
		Object[][] testObjArray = readExcel("src/main/resources/Word_book1.xls", "DATA");
		return testObjArray;
	}

	public Object[][] readExcel(String xlFilePath, String sheetName) throws Exception {
		String[][] tabArray = null;
		try {
			File f = new File(xlFilePath);
			Workbook workbook = Workbook.getWorkbook(f);
			Sheet sheet = workbook.getSheet(sheetName);
			int startRow = 1;
			int startCol = 0;
			int ci, cj;
			int totalRows = 0;
			int totalCols = 0;
			totalRows = sheet.getRows();
			totalCols = sheet.getColumns();
			System.out.println("total cols = " + totalCols);
			System.out.println("total rows = " + totalRows);
			tabArray = new String[totalRows - 1][totalCols];
			ci = 0;
			for (int i = startRow; i < totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j < totalCols; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

	@AfterTest
	public void closeApp() {
		driver.close();
		System.out.println("=========================BOWSER WAS CLOSED OK!=======================");
	}
}
