package datadriver;
//level3HW9
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;

public class JxlDriver {
	// FirefoxDriver fd;
	// WebDriverWait wait;

	// public void DataDrivenExcel(String abc) {
	// System.out.println("Hello" + abc);
	// }

	WebDriver driver;
	WebDriverWait wait;

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", "/Users/igor/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
		driver.get("http://qabidder.net/testwave/#/page/login");
	}

	@Test(dataProvider = "dataProviderMethod")
	public void login(String id, String passwd) throws InterruptedException {

		System.out.println(driver.getCurrentUrl() + " ==== " + driver.getTitle() + "page opened");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputEmail1"))).sendKeys(id);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("exampleInputPassword1"))).sendKeys(passwd);
		Thread.sleep(6000);

		// driver.get("http://qabidder.net/testwave/#/page/login");

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
