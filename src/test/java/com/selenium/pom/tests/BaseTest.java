package com.selenium.pom.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.selenium.pom.BrowserFactory;

public class BaseTest {
	protected WebDriver driver = null;
	public static String PROJECT_DIR = System.getProperty("user.dir");
	ExtentReports extent = null;

	@Parameters("browser")
	@BeforeMethod
	public void intiDriver(@Optional("chrome") String browser) {

		BrowserFactory browserFactory = new BrowserFactory();
		driver = browserFactory.createDriver(browser);
		driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.ebay.com");

	}

	@AfterMethod
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public WebDriver getDriver() {
		return driver;
	}
}
