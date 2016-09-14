package vzsolt.statistics.selenium;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class PreviewTestMethods {

	public PreviewTestMethods() {
		// TODO Auto-generated constructor stub
	}
	
	public static void testPreviewLength(WebDriver driver) {
		openSampleData(driver);
		JSChecker.waitForJStoLoad(driver);
		long previewLengtsh = (long) ((JavascriptExecutor) driver).executeScript("return document.getElementsByTagName('tr').length");
		
		assertEquals(6, previewLengtsh);
	}
	
	
	public static void testPreviewWidth(WebDriver driver) {
		openSampleData(driver);
		JSChecker.waitForJStoLoad(driver);
		long previewLengtsh = (long) ((JavascriptExecutor) driver).executeScript("return document.getElementsByTagName('td').length");
		assertEquals(36, previewLengtsh);
	}
	
	private static void openSampleData(WebDriver driver) {
		driver.findElement(By.id("file")).clear();
		String fullPath = new File(TestData.SAMPLE_DATA).getAbsolutePath();
		driver.findElement(By.id("file")).sendKeys(fullPath);
		driver.findElement(By.id("upload")).click();
	}
	
}
