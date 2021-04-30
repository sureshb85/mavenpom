package com.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.selenium.pom.BasePage;

public class RegistrationPage extends BasePage {
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	By registerLink = By.xpath("//a[text()='register']");
	By signInLink = By.xpath("//li/span//a[text()='Sign in']");
	By personalAccountRadio = By.cssSelector("#personalaccount-radio");
	By businessAccountRadio = By.cssSelector("#businessaccount-radio");

	// personal user
	By firstNameCss = By.cssSelector("#firstname");
	By lastNameCss = By.cssSelector("#lastname");
	By usernameCss = By.cssSelector("#Email");
	By passwordCss = By.cssSelector("#password");

	// business user
	By businessName = By.cssSelector("#businessName");
	By businessEmail = By.cssSelector("#businessEmail");
	By businessPassword = By.cssSelector("#bizPassword");
	By businessCountry = By.cssSelector("#businessCountry");

	public void clickRegister() {
		waitAndClickElement(registerLink);
	}

	public void selectPersonalAccount() {
		boolean isPersonalAccount = getElement(personalAccountRadio).isSelected();
		if (isPersonalAccount) {
			System.out.println("Personal Account Registration is selected");
		} else {
			System.out.println("Select Personal Account radio button");
			waitAndClickElement(personalAccountRadio);

		}
	}

	public void selectBusinessAccount() {
		WebElement element = getElement(businessAccountRadio);
		isElementClickable(element);
		boolean isBusinessAccount = element.isSelected();
		if (isBusinessAccount) {
			System.out.println("Business Account Registration is selected");
		} else {
			System.out.println("Select Business Account radio button");
			actionMoveAndClick(element);
		}
	}

	public void registerPersonalUser(String firstName, String lastName, String userName, String uPassword) {
		// enter first name
		sendKeysToWebElement(firstNameCss, firstName);
		// enter last name
		sendKeysToWebElement(lastNameCss, lastName);
		// enter email
		sendKeysToWebElement(usernameCss, userName);
		// enter password
		sendKeysToWebElement(passwordCss, uPassword);
	}

	public void registerBusinessUser(String name, String email, String password, String country) {
		// enter business name
		sendKeysToWebElement(businessName, name);
		// enter business email
		sendKeysToWebElement(businessEmail, email);
		// enter business password
		sendKeysToWebElement(businessPassword, password);
		// select business country
		selectTextFromDropdownList(businessCountry, country);
	}

}
