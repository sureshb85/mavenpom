package com.selenium.pom.tests;

import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.pom.pages.RegistrationPage;
import com.selenium.pom.utils.XLSReader;

public class RegistrationTest extends BaseTest {

	@Test(dataProvider = "xlsRegisterData", enabled = false)
	public void registerPersonalUser(String firstname, String lastname, String username, String password) {
		RegistrationPage rp = new RegistrationPage(driver);
		rp.clickRegister();
		rp.selectPersonalAccount();
		rp.registerPersonalUser(firstname, lastname, username, password);
	}

	@DataProvider(name = "xlsRegisterData")
	public String[][] getXlsData() throws IOException {
		String path = PROJECT_DIR + "\\testdata\\ebay_login.xls";
		XLSReader xlsReader = new XLSReader(path);
		int totalRows = xlsReader.getRowCount("personalUser");
		int totalCols = xlsReader.getCellCount("personalUser", 0);
		String testData[][] = new String[totalRows][totalCols];
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				testData[i - 1][j] = xlsReader.getCellData("personalUser", i, j);
			}
		}
		return testData;
	}

	@Test(dataProvider = "xlsBusinessData", enabled = false)
	public void registerBusinessUserXls(String name, String email, String password, String location)
			throws InterruptedException {
		RegistrationPage rp = new RegistrationPage(driver);
		rp.clickRegister();
		rp.selectBusinessAccount();
		rp.registerBusinessUser(name, email, password, location);
	}

	@DataProvider(name = "xlsBusinessData")
	public String[][] getXlsBusinessData() throws IOException {
		String path = PROJECT_DIR + "\\testdata\\ebay_login.xls";
		XLSReader xlsReader = new XLSReader(path);
		String sheetName = "businessUser";
		int totalRows = xlsReader.getRowCount(sheetName);
		int totalCols = xlsReader.getCellCount(sheetName, 0);
		String testData[][] = new String[totalRows][totalCols];
		for (int i = 1; i <= totalRows; i++) {
			for (int j = 0; j < totalCols; j++) {
				testData[i - 1][j] = xlsReader.getCellData(sheetName, i, j);
			}
		}
		return testData;
	}

	@Test(dataProvider = "rRegisterData", enabled = true)
	public void personalUserRegistration(Map<Object, Object> excelDataMap) {
		RegistrationPage rp = new RegistrationPage(driver);
		rp.clickRegister();
		rp.selectPersonalAccount();
		String firstname = (String) excelDataMap.get("firstname");
		String lastname = (String) excelDataMap.get("lastname");
		String username = (String) excelDataMap.get("email");
		String password = (String) excelDataMap.get("password");
		rp.registerPersonalUser(firstname, lastname, username, password);
	}

	@DataProvider(name = "rRegisterData")
	public Object[][] registerPersonalData() throws IOException {
		String path = PROJECT_DIR + "\\testdata\\ebay_login.xls";
		XLSReader xlsReader = new XLSReader(path);
		return xlsReader.getSheetData("personalUser");
	}
}
