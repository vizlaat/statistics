package vzsolt.statistics.selenium;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AverageTestMethods {

	public AverageTestMethods() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static void testAverageWhitoutFilter(WebDriver driver) {
		driver.findElement(By.id("calculateAverage")).click();
		checkAllAverage(driver, TestData.UNFILTERED_AVERAGES);
	}
	
	public static void testAverageForWomen(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("$('.filterSelector').val('1')");
		((JavascriptExecutor) driver).executeScript("$('.filterValue').val('woman')");
		driver.findElement(By.id("submitFilters")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		driver.findElement(By.id("calculateAverage")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		checkAllAverage(driver, TestData.WOMAN_AVERAGES);
	}
	
	public static void testAverageForFrance(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("$('.filterSelector').val('5')");
		((JavascriptExecutor) driver).executeScript("$('.filterValue').val('francia')");
		driver.findElement(By.id("submitFilters")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		driver.findElement(By.id("calculateAverage")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		checkAllAverage(driver, TestData.FRANCE_AVERAGES);
	}
	
	public static void testFilterRemoving(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("$('.filterSelector').val('5')");
		((JavascriptExecutor) driver).executeScript("$('.filterValue').val('francia')");
		driver.findElement(By.id("submitFilters")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		driver.findElement(By.id("removeFilters")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		driver.findElement(By.id("calculateAverage")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		checkAllAverage(driver,TestData.UNFILTERED_AVERAGES);
	}
	
	private static void checkAllAverage(WebDriver driver, Map<String,String> averages) {
		for(Entry<String,String> entry:averages.entrySet()) {
			WebElement actual = driver.findElements(By.className(entry.getKey())).get(0);
			assertEquals(Double.valueOf(entry.getValue()), Double.valueOf(actual.getText()),TestData.DELTA);
		}
	}
	
	
}
