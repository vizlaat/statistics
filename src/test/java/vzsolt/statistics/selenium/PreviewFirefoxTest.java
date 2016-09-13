package vzsolt.statistics.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class PreviewFirefoxTest {
	private WebDriver driver;
	private String baseUrl;
	

	public PreviewFirefoxTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void initBrowser() {
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    	capabilities.setCapability("marionette", true);
    	System.setProperty("webdriver.gecko.driver","C:\\selenium\\firefox-driver\\geckodriver.exe");
    	driver = new FirefoxDriver(capabilities);
    	baseUrl = "http://localhost:8090/";
    	driver.get(baseUrl + "/main");
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testPreviewLength() {		
		PreviewTestMethods.testPreviewLength(driver);
	}
	
	@Test
	public void testPreviewWidth() {
		PreviewTestMethods.testPreviewWidth(driver);
	}
	
	@After
	public void tearDown() throws Exception{
		TearDown.tearDown(driver);
		
	}
}
