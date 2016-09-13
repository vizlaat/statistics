package vzsolt.statistics.selenium;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CorrelationTestMethods {

	public CorrelationTestMethods() {
		// TODO Auto-generated constructor stub
	}
	
	public static void testWhitoutFilterCorr1(WebDriver driver) {
		evaluateCorrelation(driver, "2", "3");
		while(!JSChecker.waitForJStoLoad(driver)) {};
		checkCorrelation(driver, TestData.UNFILTERED_CORRELATION1);
	}
	
	public static void testWomenCorrelation(WebDriver driver) {
		FilterHandler.setFilter(driver, "1", "woman");
		while(!JSChecker.waitForJStoLoad(driver)) {}
		evaluateCorrelation(driver, "2", "3");
		while(!JSChecker.waitForJStoLoad(driver)) {};
		checkCorrelation(driver, TestData.WOMAN_CORRELATION1);
	}
	
	public static void testFranceCorrelation(WebDriver driver) {
		FilterHandler.setFilter(driver, "5", "francia");
		while(!JSChecker.waitForJStoLoad(driver)) {}
		evaluateCorrelation(driver, "2", "3");
		while(!JSChecker.waitForJStoLoad(driver)) {};
		checkCorrelation(driver, TestData.FRANCE_CORRELATION1);
	}
	
	public static void testHungarianManCorrelation(WebDriver driver) {
		WebElement filter1 = driver.findElement(By.name("filter1"));
		FilterHandler.setFilterKey(driver, filter1, "1");		
	    driver.findElement(By.cssSelector("textarea.filterValue")).clear();
	    driver.findElement(By.cssSelector("textarea.filterValue")).sendKeys("man");
	    driver.findElement(By.id("addFilter")).click();
	    WebElement filter2 = driver.findElement(By.xpath("(//select[@name='filter1'])[2]"));
	    FilterHandler.setFilterKey(driver, filter2, "5");	
	    new Select(filter2).selectByVisibleText("nemzetiseg");
	    driver.findElement(By.xpath("//tbody[@id='filterBody']/tr[2]/td[2]/textarea")).clear();
	    driver.findElement(By.xpath("//tbody[@id='filterBody']/tr[2]/td[2]/textarea")).sendKeys("magyar");
	    driver.findElement(By.id("submitFilters")).click();
	    while(!JSChecker.waitForJStoLoad(driver));
	    evaluateCorrelation(driver, "2", "3");
	    while(!JSChecker.waitForJStoLoad(driver));
	    checkCorrelation(driver, TestData.HUNGARIAN_MAN_CORRELATION1);
		
	}
	
	
	
	private static void evaluateCorrelation(WebDriver driver, String corrFieldOne, String corrFieldTwo) {
		((JavascriptExecutor) driver).executeScript("$('#correlationFieldOne').val(arguments[0])",corrFieldOne);
		((JavascriptExecutor) driver).executeScript("$('#correlationFieldTwo').val(arguments[0])",corrFieldTwo);
		driver.findElement(By.id("correlation")).click();
	}
	
	
	
	private static void checkCorrelation(WebDriver driver, Double correlation) {
		WebElement actual = driver.findElements(By.className("correlation")).get(0);
		assertEquals(Double.valueOf(correlation), Double.valueOf(actual.getText()),TestData.DELTA);
		
	}

}
