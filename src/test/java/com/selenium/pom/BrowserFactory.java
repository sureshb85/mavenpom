package com.selenium.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

	private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static final String PROJECT_DIR = System.getProperty("user.dir");

	public WebDriver createDriver(String browser) {
		System.out.println("initializing driver: " + browser);
		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver.set(new EdgeDriver());
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver.set(new FirefoxDriver());
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		} else {
			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());
		}
		return driver.get();
	}

}
