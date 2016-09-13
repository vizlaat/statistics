package vzsolt.statistics.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AnalyzeInitiator {

	public AnalyzeInitiator() {
		// TODO Auto-generated constructor stub
	}

	
	public static void initBrowserState(WebDriver driver, String data) {
		//driver.findElement(By.id("file")).clear();
		driver.findElement(By.id("file")).sendKeys(data);
		driver.findElement(By.id("upload")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
		driver.findElement(By.linkText("Analyze")).click();
		while(!JSChecker.waitForJStoLoad(driver)) {}
	}
}
