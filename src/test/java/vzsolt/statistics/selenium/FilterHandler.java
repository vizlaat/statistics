package vzsolt.statistics.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FilterHandler {

	public FilterHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public static void setFilter(WebDriver driver,String selector, String value) {
		((JavascriptExecutor) driver).executeScript("$('.filterSelector').val(arguments[0])",selector);
		((JavascriptExecutor) driver).executeScript("$('.filterValue').val(arguments[0])",value);
		driver.findElement(By.id("submitFilters")).click();
	}
	
	public static void setFilterKey(WebDriver driver,WebElement element, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];",element,value);
	}

}
