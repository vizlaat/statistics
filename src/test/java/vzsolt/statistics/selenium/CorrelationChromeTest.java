package vzsolt.statistics.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CorrelationChromeTest {

	private WebDriver driver;
	private String baseUrl;

	public CorrelationChromeTest() {
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
	public void testUnfilteredCorrelation1() {
		CorrelationTestMethods.testWhitoutFilterCorr1(driver);
	}
	
	@Test
	public void testWomanCorrelation1() {
		CorrelationTestMethods.testWomenCorrelation(driver);
	}
	
	@Test
	public void testFranceCorrelation1() {
		CorrelationTestMethods.testFranceCorrelation(driver);
	}
	
	@Test
	public void testHungarianManCorrelation1() {
		CorrelationTestMethods.testHungarianManCorrelation(driver);
	}


	
	@After
	public void tearDown() throws Exception{
		TearDown.tearDown(driver);
		
	}
	
	
}
