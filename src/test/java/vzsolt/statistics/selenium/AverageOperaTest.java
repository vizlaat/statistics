package vzsolt.statistics.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

public class AverageOperaTest {

	private WebDriver driver;
	private String baseUrl;

	public AverageOperaTest() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void initBrowser() {
    	System.setProperty("webdriver.opera.driver", TestData.OPERA_DRIVER);
    	driver = new OperaDriver();
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
