package com.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.pom.BasePage;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	private By signInLink = By.xpath("//li/span//a[text()='Sign in']");
	private By usernameText = By.cssSelector("#userid");
	private By continueButton = By.cssSelector("#signin-continue-btn");
	private By passwordText = By.cssSelector("#pass");

	public void doLogin(String username, String password) {

		// click signin link
		waitAndClickElement(signInLink);

		// username - using sendKeysToWebElement - element method
		WebElement user = getElement(usernameText);
		sendKeysToWebElement(user, username);

		// click sign continue 
		waitAndClickElement(continueButton);

		// password - using sendKeysToWebElement - By method
		sendKeysToWebElement(passwordText, password);
	}

}
