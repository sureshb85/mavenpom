package com.selenium.pom.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.selenium.pom.tests.BaseTest;

public class TestListeners extends BaseTest implements ITestListener {

	ExtentReports extent;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		extent = ExtentReportsManager.initReport();
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Successfully passed");
	}

	public void onTestFinish(ITestContext context) {
		extent.flush();
	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
		WebDriver driver = null;
		String testMethodName = result.getMethod().getMethodName();
		Object testClass = result.getInstance();
		try {
			driver = ((BaseTest) testClass).getDriver();
			test.addScreenCaptureFromPath(getScreenShotPath(testMethodName, driver), testMethodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getScreenShotPath(String testMethodName, WebDriver driver2) {

		File source = ((TakesScreenshot) driver2).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\reports\\" + testMethodName + ".png";
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}
}
