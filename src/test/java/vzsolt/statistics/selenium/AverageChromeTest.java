package vzsolt.statistics.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AverageChromeTest {

	private WebDriver driver;
	private String baseUrl;


	public AverageChromeTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void initBrowser() {
    	System.setProperty("webdriver.chrome.driver", TestData.CHROME_DRIVER);
    	driver = new ChromeDriver();
    	baseUrl = TestData.BASE_URL;
    	driver.get(baseUrl + "/main");
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	AnalyzeInitiator.initBrowserState(driver, TestData.SAMPLE_DATA);
	}
	
	@Test
	public void testAverage() {
		AverageTestMethods.testAverageWhitoutFilter(driver);
	}
	
	@Test
	public void testAverageForWomen() {
		AverageTestMethods.testAverageForWomen(driver);
	}
	
	@Test
	public void testAverageForFrance() {
		AverageTestMethods.testAverageForFrance(driver);
		
	}
	
	@Test
	public void testAverageForRemoveFilter() {
		AverageTestMethods.testFilterRemoving(driver);
		
	}
	
	
	@After
	public void tearDown() throws Exception{
		TearDown.tearDown(driver);
		
	}

}
