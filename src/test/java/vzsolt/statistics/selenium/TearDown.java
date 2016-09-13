package vzsolt.statistics.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class TearDown {

	public TearDown() {
		// TODO Auto-generated constructor stub
	}
	
	public static void tearDown(WebDriver driver) {
		 try {
				driver.quit();
			} catch (UnreachableBrowserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
