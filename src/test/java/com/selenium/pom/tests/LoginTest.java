package com.selenium.pom.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.selenium.pom.pages.LoginPage;

public class LoginTest extends BaseTest {

	@Test
	public void validLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("user", "pass");
		
		//home page
		// find a element on home page
		// verify the element is displayed -- Login success test case
		
		Assert.assertTrue(true);
	}

	
	@Test
	public void invalidLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.doLogin("user", "pass");
		
		// login page
		// find a element on which shows the error text
		// verify the element error text is displayed -- Login failed testcase
		Assert.fail("Invalid Login");
	}
}
