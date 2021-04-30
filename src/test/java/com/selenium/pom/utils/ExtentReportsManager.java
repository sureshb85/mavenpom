package com.selenium.pom.utils;

import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsManager {

	static ExtentReports extent = null;

	public static ExtentReports initReport() {
		Date d = new Date();
		String reportName = "report_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "\\reports\\" + reportName);
		reporter.config().setDocumentTitle("eBay Application");
		reporter.config().setReportName("Test Report");

		extent = new ExtentReports();
		extent.attachReporter(reporter);

		extent.setSystemInfo("Environment", "Dev");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Version", "1.01");
		extent.setSystemInfo("Release", "June Release");
		return extent;

	}
}
