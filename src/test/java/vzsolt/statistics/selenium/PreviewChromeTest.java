package vzsolt.statistics.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class PreviewChromeTest{
	private WebDriver driver;
	private String baseUrl;
	

	public PreviewChromeTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Before
	public void initBrowser() {
    	System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chrome-driver\\chromedriver.exe");
    	driver = new ChromeDriver();
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
